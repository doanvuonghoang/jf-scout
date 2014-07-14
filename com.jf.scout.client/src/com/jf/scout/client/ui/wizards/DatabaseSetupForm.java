/**
 * 
 */
package com.jf.scout.client.ui.wizards;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.ui.wizards.DatabaseSetupForm.MainBox.CancelButton;
import com.jf.scout.client.ui.wizards.DatabaseSetupForm.MainBox.OkButton;
import com.jf.scout.shared.ui.wizards.DatabaseSetupFormData;
import com.jf.scout.shared.ui.wizards.IDatabaseSetupService;

/**
 * @author Hoàng
 */
@FormData(value = DatabaseSetupFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DatabaseSetupForm extends AbstractForm {

  private Long m_databaseSetupNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DatabaseSetupForm() throws ProcessingException {
    super();
  }

  /**
   * @return the DatabaseSetupNr
   */
  @FormData
  public Long getDatabaseSetupNr() {
    return m_databaseSetupNr;
  }

  /**
   * @param databaseSetupNr
   *          the DatabaseSetupNr to set
   */
  @FormData
  public void setDatabaseSetupNr(Long databaseSetupNr) {
    m_databaseSetupNr = databaseSetupNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatabaseSetup");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(20.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDatabaseSetupService service = SERVICES.getService(IDatabaseSetupService.class);
      DatabaseSetupFormData formData = new DatabaseSetupFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IDatabaseSetupService service = SERVICES.getService(IDatabaseSetupService.class);
      DatabaseSetupFormData formData = new DatabaseSetupFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDatabaseSetupService service = SERVICES.getService(IDatabaseSetupService.class);
      DatabaseSetupFormData formData = new DatabaseSetupFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IDatabaseSetupService service = SERVICES.getService(IDatabaseSetupService.class);
      DatabaseSetupFormData formData = new DatabaseSetupFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
