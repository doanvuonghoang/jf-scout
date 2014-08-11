/**
 * 
 */
package jfscout.client.hrm.ui.forms;

import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.ResetButton;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.SearchButton;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.TabBox;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.TabBox.FieldBox;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.TabBox.FieldBox.CodeField;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.TabBox.FieldBox.FullNameField;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm.MainBox.TabBox.FieldBox.HeadOfUnitField;
import jfscout.shared.hrm.ui.forms.EmployeeListSearchFormData;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * @author Ho�ng
 */
@FormData(value = EmployeeListSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class EmployeeListSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public EmployeeListSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("EmployeeList");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    EmployeeListSearchFormData formData = new EmployeeListSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the CodeField
   */
  public CodeField getCodeField() {
    return getFieldByClass(CodeField.class);
  }

  /**
   * @return the FieldBox
   */
  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  /**
   * @return the FullNameField
   */
  public FullNameField getFullNameField() {
    return getFieldByClass(FullNameField.class);
  }

  /**
   * @return the HeadOfUnitField
   */
  public HeadOfUnitField getHeadOfUnitField() {
    return getFieldByClass(HeadOfUnitField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the ResetButton
   */
  public ResetButton getResetButton() {
    return getFieldByClass(ResetButton.class);
  }

  /**
   * @return the SearchButton
   */
  public SearchButton getSearchButton() {
    return getFieldByClass(SearchButton.class);
  }

  /**
   * @return the TabBox
   */
  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class FieldBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("searchCriteria");
        }

        @Order(10.0)
        public class CodeField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Code");
          }
        }

        @Order(20.0)
        public class HeadOfUnitField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("HeadOfUnit");
          }
        }

        @Order(40.0)
        public class FullNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("FullName");
          }
        }
      }
    }

    @Order(70.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(80.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}
