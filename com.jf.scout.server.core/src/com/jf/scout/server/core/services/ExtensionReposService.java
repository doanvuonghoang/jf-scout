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
import com.jf.scout.shared.core.services.IDatabaseService;
import com.jf.scout.shared.core.services.IExtensionReposService;

/**
 * @author Hoàng
 */
@Author(name = "Hoang Doan")
@Version(version = "1.0.0")
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
      if (!isInstalled(getClass().getName())) doInstall();
    }
    catch (Exception e) {
      logger.info(e.getMessage(), e);
    }
  }

  private Dao<Extension, Long> getDao() {
    if (dao == null) {
      try {
        dao = SERVICES.getService(IDatabaseService.class).createDao(Extension.class);
      }
      catch (Exception e) {
        logger.info(e.getMessage(), e);
      }
    }

    return dao;
  }

  @Override
  public boolean isInstalled(String extName) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      return !getDao().queryBuilder().where()
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
  public void install(Extension ext) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      getDao().create(ext);
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      throw new VetoException(e.getMessage(), e);
    }
  }

  /**
   * @param name
   */
  public void removeExtension(String name) throws Exception {
    // TODO Auto-generated method stub
    getDao().queryForEq(Extension.FIELD_EXT_CLASS_NAME, name).forEach((m) -> {

      try {
        dao.delete(m);
      }
      catch (Exception ex) {
        logger.info(ex.getMessage(), ex);
      }
    });
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    // TODO Auto-generated method stub
    TableUtils.createTable(getDao().getConnectionSource(), Extension.class);

    getDao().create(createExtensionModel(this));
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
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // TODO Auto-generated method stub
    TableUtils.dropTable(getDao().getConnectionSource(), Extension.class, true);
  }

  @Override
  public void uninstall(String extName) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      getDao().updateBuilder().updateColumnValue(Extension.FIELD_RECORD_STATUS, RecordStatus.DELETE);
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      logger.info(e.getMessage(), e);

      throw new VetoException(e.getMessage(), e);
    }
  }
}