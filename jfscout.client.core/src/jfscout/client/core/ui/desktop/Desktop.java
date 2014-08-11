package jfscout.client.core.ui.desktop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jfscout.client.core.ClientSession;
import jfscout.client.core.ui.desktop.outlines.DashboardOutline;
import jfscout.client.core.ui.wizards.SetupWizard;
import jfscout.shared.core.Icons;
import jfscout.shared.core.services.ICoreService;
import jfscout.shared.core.services.IExtensionReposService;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.scout.commons.CollectionUtility;
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
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTableForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTreeForm;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.commons.datamodels.Extension;
import com.jf.scout.commons.IInstallable;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);

  public Desktop() {
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(DashboardOutline.class);
    return outlines;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected void execOpened() throws ProcessingException {
    // run for first time
    if (SERVICES.getService(ICoreService.class).isFirstRun()) {
      // install for first run
      SetupWizard sw = new SetupWizard();
      sw.start();
      sw.waitFor();

      SERVICES.getService(ICoreService.class).makeNotFirstRun();

      ClientSyncJob.getCurrentSession().stopSession(IApplication.EXIT_RESTART);
    }

    //If it is a mobile or tablet device, the DesktopExtension in the mobile plugin takes care of starting the correct forms.
    if (!UserAgentUtility.isDesktopDevice()) {
      return;
    }

    // outline tree
    DefaultOutlineTreeForm treeForm = new DefaultOutlineTreeForm();
    treeForm.setIconId(Icons.EclipseScout);
    treeForm.startView();

    //outline table
    DefaultOutlineTableForm tableForm = new DefaultOutlineTableForm();
    tableForm.setIconId(Icons.EclipseScout);
    tableForm.startView();

    IOutline firstOutline = CollectionUtility.firstElement(getAvailableOutlines());
    if (firstOutline != null) {
      setOutline(firstOutline);
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
      super.injectDesktopExtensions(desktopExtensions);
      for (IDesktopExtension e : desktopExtensions) {
        if (e instanceof IInstallable) {
          IExtensionReposService svc = SERVICES.getService(IExtensionReposService.class);
          IInstallable ext = (IInstallable) e;
          Extension model = createExtensionModel(ext);

          try {
            if (model.debug) {
              executeInternalUninstall(ext);
              svc.uninstall(model.extClassName);
            }
            else if (svc.isInstalled(model.extClassName)) continue;

            executeInternalInstall(ext);

            svc.install(model);
          }
          catch (ProcessingException e1) {
            logger.info(e1.getMessage(), e1);
          }
        }
      }
    }

  }

  /**
   * @param ext
   * @return
   */
  private Extension createExtensionModel(IInstallable ext) {
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
    try {
      ext.doUnInstall();
    }
    catch (Exception ex) {
      logger.info(ex.getMessage(), ex);
    }
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

  @Order(10.0)
  public class StandardOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public StandardOutlineViewButton() {
      super(Desktop.this, DashboardOutline.class);
    }
  }
}
