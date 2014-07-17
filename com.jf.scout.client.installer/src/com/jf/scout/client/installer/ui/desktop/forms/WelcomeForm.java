/**
 *
 */
package com.jf.scout.client.installer.ui.desktop.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.shared.TEXTS;

import com.jf.scout.client.installer.ui.desktop.forms.WelcomeForm.MainBox.GroupBox;
import com.jf.scout.client.installer.ui.desktop.forms.WelcomeForm.MainBox.GroupBox.WelcomeMessageField;
import com.jf.scout.shared.installer.ui.desktop.forms.WelcomeFormData;

/**
 * @author Hoàng
 */
@FormData(value = WelcomeFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class WelcomeForm extends AbstractForm {

  private Long m_welcomeNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public WelcomeForm() throws ProcessingException {
    super();
  }

  @Override
  protected void execFormActivated() throws ProcessingException {
    //TODO [Hoàng] Auto-generated method stub.
    super.execFormActivated();
  }

  /**
   * @return the WelcomeNr
   */
  @FormData
  public Long getWelcomeNr() {
    return m_welcomeNr;
  }

  /**
   * @param welcomeNr
   *          the WelcomeNr to set
   */
  @FormData
  public void setWelcomeNr(Long welcomeNr) {
    m_welcomeNr = welcomeNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Welcome");
  }

  /**
   * @return the GroupBox
   */
  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the WelcomeMessageField
   */
  public WelcomeMessageField getWelcomeMessageField() {
    return getFieldByClass(WelcomeMessageField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class WelcomeMessageField extends AbstractHtmlField {
        @Override
        protected double getConfiguredGridWeightX() {
          return 1.0;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 1.0;
        }

        /* (non-Javadoc)
         * @see org.eclipse.scout.rt.client.ui.form.fields.AbstractValueField#getInitValue()
         */
        @Override
        public String getDisplayText() {
          // TODO Auto-generated method stub
          return TEXTS.get("installer.welcome.text");
        }
      }
    }
  }
}
