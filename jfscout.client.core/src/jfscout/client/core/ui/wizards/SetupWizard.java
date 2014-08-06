/**
 *
 */
package jfscout.client.core.ui.wizards;

import jfscout.client.core.ui.forms.DatabaseSetupForm;
import jfscout.client.core.ui.forms.WelcomeForm;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author Hoàng
 */
public class SetupWizard extends AbstractWizard {

  /**
   *
   */
  public SetupWizard() {
    super();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected boolean getConfiguredModal() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Setup");
  }

  @Override
  protected void execFinish() throws ProcessingException {
    getDatabaseSetupStep().getForm().validateForm();

    super.execFinish();
  }

  /**
   * @return the DatabaseSetupStep
   */
  public DatabaseSetupStep getDatabaseSetupStep() {
    return getStep(SetupWizard.DatabaseSetupStep.class);
  }

  /**
   * @return the WelcomeStep
   */
  public WelcomeStep getWelcomeStep() {
    return getStep(SetupWizard.WelcomeStep.class);
  }

  @Order(10.0)
  public class WelcomeStep extends AbstractWizardStep<WelcomeForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Welcome");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      WelcomeForm form = getForm();
      if (form == null) {
        form = new WelcomeForm();
        // start the form by executing the form handler
        //TODO [Hoàng] start the form (e.g. form.startWizardStep(this, MyForm.ModifyHandler.class);
        // Register the form on the step
        setForm(form);
      }
      // Set the form on the wizard
      // This will automatically display it as inner form of the wizard container form
      getWizard().setWizardForm(form);
    }

    @Override
    protected void execDeactivate(int stepKind) throws ProcessingException {
      // Save the form if the user clicks next
      if (stepKind == STEP_NEXT) {
        WelcomeForm form = getForm();
        if (form != null) {
          form.doSave();
        }
      }
    }
  }

  @Order(20.0)
  public class DatabaseSetupStep extends AbstractWizardStep<DatabaseSetupForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("DatabaseSetup");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      DatabaseSetupForm form = getForm();
      if (form == null) {
        form = new DatabaseSetupForm();
        // start the form by executing the form handler
        form.startWizardStep(this, DatabaseSetupForm.FormHandler.class);
        // Register the form on the step
        setForm(form);
      }
      // Set the form on the wizard
      // This will automatically display it as inner form of the wizard container form
      getWizard().setWizardForm(form);
    }

    @Override
    protected void execDeactivate(int stepKind) throws ProcessingException {
      // Save the form if the user clicks next
      if (stepKind == STEP_NEXT) {
        DatabaseSetupForm form = getForm();
        if (form != null) {
          form.doSave();
        }
      }
    }
  }
}
