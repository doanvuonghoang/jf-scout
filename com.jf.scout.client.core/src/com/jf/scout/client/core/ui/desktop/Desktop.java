package com.jf.scout.client.core.ui.desktop;

import java.util.Calendar;
import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.IDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.menu.AbstractBookmarkMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.extension.client.Activator;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.commons.datamodels.Extension;
import com.jf.scout.client.core.ClientSession;
import com.jf.scout.client.core.ui.wizards.SetupWizard;
import com.jf.scout.commons.IInstallable;
import com.jf.scout.shared.core.services.ICoreService;
import com.jf.scout.shared.core.services.IExtensionReposService;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);
  private boolean needRestart = false;

  public Desktop() {
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop#execOpened()
   */
  @Override
  protected void execOpened() throws ProcessingException {
    if (SERVICES.getService(ICoreService.class).isFirstRun()) {
      // install for first run
      SetupWizard sw = new SetupWizard();
      sw.start();
      sw.waitFor();

      SERVICES.getService(ICoreService.class).makeNotFirstRun();

      propertySupport.firePropertyChange("needRestart", needRestart, true);
    }
  }

  @Override
  protected void injectDesktopExtensions(List<IDesktopExtension> desktopExtensions) {
    boolean isFirstRun = true;
    try {
      isFirstRun = SERVICES.getService(ICoreService.class).isFirstRun();
    }
    catch (ProcessingException ex) {
      logger.info(ex.getMessage(), ex);
    }

    if (!isFirstRun) {
      List<IDesktopExtension> extensions = Activator.getDefault().getDesktopExtensionManager().getDesktopExtensions();
      for (IDesktopExtension e : extensions) {
        if (e instanceof IInstallable) {
          IExtensionReposService svc = SERVICES.getService(IExtensionReposService.class);
          IInstallable ext = (IInstallable) e;
          Extension model = createExtensionModel(ext);

          try {
            if (model.debug) {
              executeInternalUninstall(ext);
              svc.uninstall(model.extClassName);
            }
            else if (svc.isInstalled(model.extClassName)) return;

            executeInternalInstall(ext);

            svc.install(model);
          }
          catch (ProcessingException e1) {
            // TODO Auto-generated catch block
            logger.info(e1.getMessage(), e1);
          }
        }

        e.setCoreDesktop(this);
      }
      desktopExtensions.addAll(extensions);
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
    model.setCreator(ClientSyncJob.getCurrentSession().getUserId());
    model.setCreatedTime(Calendar.getInstance().getTime());

    return model;
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

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Order(10.0)
  public class FileMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FileMenu");
    }

    @Order(100.0)
    public class ExitMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ExitMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
      }
    }
  }

  @Order(20.0)
  public class ToolsMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolsMenu");
    }
  }

  @Order(25)
  public class BookmarkMenu extends AbstractBookmarkMenu {
    public BookmarkMenu() {
      super(Desktop.this);
    }
  }

  @Order(30.0)
  public class HelpMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("HelpMenu");
    }

    @Order(10.0)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AboutMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

    @Override
    protected String getConfiguredKeyStroke() {
      return "f5";
    }

    @Override
    protected void execAction() throws ProcessingException {
      if (getOutline() != null) {
        IPage page = getOutline().getActivePage();
        if (page != null) {
          page.reloadPage();
        }
      }
    }
  }
}
