/**
 *
 */
package jfscout.client.core.ui.forms;

import jfscout.client.core.ui.forms.WelcomeForm.MainBox.GroupBox;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author Hoàng
 */
public class WelcomeForm extends AbstractForm {

  private Long m_welcomeNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public WelcomeForm() throws ProcessingException {
    super();
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

        @Override
        public String getDisplayText() {
          return TEXTS.get("welcome.text");
        }
      }
    }
  }
}
