/**
 *
 */
package com.jf.scout.server.core.services;

import java.sql.SQLException;
import java.util.Calendar;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.osgi.framework.ServiceRegistration;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.commons.datamodels.Extension;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.scout.commons.IInstallable;
import com.jf.scout.shared.core.services.IDatabaseHelperService;
import com.jf.scout.shared.core.services.IExtensionReposService;

/**
 * @author Hoàng
 */
public class ExtensionReposService extends AbstractService implements IExtensionReposService, IInstallable {
  private Dao<Extension, Long> dao;
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#initializeService(org.osgi.framework.ServiceRegistration)
   */
  @Override
  public void initializeService(ServiceRegistration registration) {
    // TODO Auto-generated method stub
    super.initializeService(registration);

    // install service it-self
    try {
      // init dao for sql helper
      dao = SERVICES.getService(IDatabaseHelperService.class).createDao(Extension.class);

      if (!isInstalled(getClass().getName())) install(this);
    }
    catch (Exception e) {
      logger.info(e.getMessage(), e);
    }
  }

  @Override
  public boolean isInstalled(String extName) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      return !dao.queryBuilder().where()
          .eq(Extension.FIELD_EXT_CLASS_NAME, extName)
          .and().ne(Extension.FIELD_RECORD_STATUS, RecordStatus.DELETE)
          .and().eq(Extension.FIELD_DEBUG, false).query().isEmpty();
    }
    catch (SQLException ex) {
      logger.info(ex.getMessage(), ex);
    }

    return false;
  }

  @Override
  public void install(IInstallable ext) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    Extension model = createExtensionModel(ext);

    try {
      if (model.debug) {
        executeInternalUninstall(ext);
        removeExtension(ext.getClass().getName());
      }
      else if (isInstalled(ext.getClass().getName())) return;

      executeInternalInstall(ext);

      dao.create(model);

      ext.doInstall();
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      throw new VetoException(e.getMessage(), e);
    }
  }

  /**
   * @param ext
   */
  private void executeInternalInstall(IInstallable ext) {
    // TODO Auto-generated method stub
    try {
      ext.doInstall();
    }
    catch (Exception ex) {
      logger.info(ex.getMessage(), ex);
    }
  }

  /**
   * @param name
   */
  private void removeExtension(String name) throws Exception {
    // TODO Auto-generated method stub
    dao.queryForEq(Extension.FIELD_EXT_CLASS_NAME, name).forEach((m) -> {

      try {
        dao.delete(m);
      }
      catch (Exception ex) {
        logger.info(ex.getMessage(), ex);
      }
    });
  }

  /**
   * @param ext
   */
  private void executeInternalUninstall(IInstallable ext) {
    // TODO Auto-generated method stub
    try {
      ext.doUnInstall();
    }
    catch (Exception ex) {
      logger.info(ex.getMessage(), ex);
    }
  }

  /**
   * @param ext
   * @return
   */
  private Extension createExtensionModel(IInstallable ext) {
    // TODO Auto-generated method stub
    Extension model = new Extension();
    model.extClassName = ext.getClass().getName();
    model.author = ext.getClass().getAnnotation(Author.class).name();
    model.version = ext.getClass().getAnnotation(Version.class).version();
    model.setCreator(this.getClass().getName());
    model.setCreatedTime(Calendar.getInstance().getTime());

    return model;
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    // TODO Auto-generated method stub
    TableUtils.createTable(dao.getConnectionSource(), Extension.class);
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // TODO Auto-generated method stub
    TableUtils.dropTable(dao.getConnectionSource(), Extension.class, true);
  }

  @Override
  public void uninstall(String extName) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      dao.updateBuilder().updateColumnValue(Extension.FIELD_RECORD_STATUS, RecordStatus.DELETE);
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      logger.info(e.getMessage(), e);

      throw new VetoException(e.getMessage(), e);
    }
  }
}
