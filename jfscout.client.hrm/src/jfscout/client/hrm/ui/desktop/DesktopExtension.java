package jfscout.client.hrm.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import jfscout.client.hrm.ui.desktop.outlines.CVManagementOutline;
import jfscout.client.hrm.ui.desktop.outlines.ContractManagementOutline;
import jfscout.client.hrm.ui.desktop.outlines.EmployeeManagementOutline;
import jfscout.client.hrm.ui.desktop.outlines.InsuranceManagementOutline;
import jfscout.client.hrm.ui.desktop.outlines.LeaveManagementOutline;
import jfscout.client.hrm.ui.desktop.outlines.TrainingManagementOutline;
import jfscout.shared.hrm.services.IExtensionService;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.action.tool.AbstractToolButton;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.scout.commons.IInstallable;

@Author(name = "Hoang Doanz")
@Version(version = "1.0.0")
public class DesktopExtension extends AbstractDesktopExtension implements IInstallable {
  public DesktopExtension() {
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    // call extension service to install
    SERVICES.getService(IExtensionService.class).installDB();
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // do nothing now
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(EmployeeManagementOutline.class);
    outlines.add(ContractManagementOutline.class);
    outlines.add(CVManagementOutline.class);
    outlines.add(InsuranceManagementOutline.class);
    outlines.add(TrainingManagementOutline.class);
    outlines.add(LeaveManagementOutline.class);
    return outlines;
  }

  @Order(10.0)
  public class EmployeeManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public EmployeeManagementOutlineViewButton() {
      super(getCoreDesktop(), EmployeeManagementOutline.class);
    }
  }

  @Order(10.0)
  public class CreateEmployeeTool extends AbstractToolButton {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("CreateEmployee");
    }
  }

  @Order(20.0)
  public class ContractManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public ContractManagementOutlineViewButton() {
      super(getCoreDesktop(), ContractManagementOutline.class);
    }
  }

  @Order(30.0)
  public class CVManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public CVManagementOutlineViewButton() {
      super(getCoreDesktop(), CVManagementOutline.class);
    }
  }

  @Order(40.0)
  public class InsuranceManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public InsuranceManagementOutlineViewButton() {
      super(getCoreDesktop(), InsuranceManagementOutline.class);
    }
  }

  @Order(50.0)
  public class TrainingManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public TrainingManagementOutlineViewButton() {
      super(getCoreDesktop(), TrainingManagementOutline.class);
    }
  }

  @Order(60.0)
  public class LeaveManagementOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public LeaveManagementOutlineViewButton() {
      super(getCoreDesktop(), LeaveManagementOutline.class);
    }
  }
}
