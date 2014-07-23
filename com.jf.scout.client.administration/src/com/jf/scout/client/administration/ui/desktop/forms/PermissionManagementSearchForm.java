/**
 * 
 */
package com.jf.scout.client.administration.ui.desktop.forms;

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

import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm.MainBox.ResetButton;
import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm.MainBox.SearchButton;
import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm.MainBox.TabBox;
import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm.MainBox.TabBox.FieldBox;
import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm.MainBox.TabBox.FieldBox.PermissionNameField;
import com.jf.scout.shared.administration.ui.desktop.forms.PermissionManagementSearchFormData;

/**
 * @author Hoàng
 */
@FormData(value = PermissionManagementSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PermissionManagementSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public PermissionManagementSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PermissionManagement");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    PermissionManagementSearchFormData formData = new PermissionManagementSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the FieldBox
   */
  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the PermissionNameField
   */
  public PermissionNameField getPermissionNameField() {
    return getFieldByClass(PermissionNameField.class);
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
        public class PermissionNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("PermissionName");
          }
        }
      }
    }

    @Order(20.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(30.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}
