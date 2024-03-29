/**
 * 
 */
package jfscout.shared.hrm.ui.desktop.pages;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.pagedata.PageDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class EmployeeListTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public EmployeeListTablePageData() {
  }

  @Override
  public EmployeeListTableRowData addRow() {
    return (EmployeeListTableRowData) super.addRow();
  }

  @Override
  public EmployeeListTableRowData addRow(int rowState) {
    return (EmployeeListTableRowData) super.addRow(rowState);
  }

  @Override
  public EmployeeListTableRowData createRow() {
    return new EmployeeListTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return EmployeeListTableRowData.class;
  }

  @Override
  public EmployeeListTableRowData[] getRows() {
    return (EmployeeListTableRowData[]) super.getRows();
  }

  @Override
  public EmployeeListTableRowData rowAt(int index) {
    return (EmployeeListTableRowData) super.rowAt(index);
  }

  public void setRows(EmployeeListTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class EmployeeListTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String id = "id";
    public static final String code = "code";
    public static final String fullName = "fullName";
    public static final String status = "status";
    public static final String jobTitle = "jobTitle";
    public static final String department = "department";
    public static final String headOfUnit = "headOfUnit";
    private Long m_id;
    private String m_code;
    private String m_fullName;
    private String m_status;
    private String m_jobTitle;
    private String m_department;
    private String m_headOfUnit;

    public EmployeeListTableRowData() {
    }

    /**
     * @return the Id
     */
    public Long getId() {
      return m_id;
    }

    /**
     * @param id
     *          the Id to set
     */
    public void setId(Long id) {
      m_id = id;
    }

    /**
     * @return the Code
     */
    public String getCode() {
      return m_code;
    }

    /**
     * @param code
     *          the Code to set
     */
    public void setCode(String code) {
      m_code = code;
    }

    /**
     * @return the FullName
     */
    public String getFullName() {
      return m_fullName;
    }

    /**
     * @param fullName
     *          the FullName to set
     */
    public void setFullName(String fullName) {
      m_fullName = fullName;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
      return m_status;
    }

    /**
     * @param status
     *          the Status to set
     */
    public void setStatus(String status) {
      m_status = status;
    }

    /**
     * @return the JobTitle
     */
    public String getJobTitle() {
      return m_jobTitle;
    }

    /**
     * @param jobTitle
     *          the JobTitle to set
     */
    public void setJobTitle(String jobTitle) {
      m_jobTitle = jobTitle;
    }

    /**
     * @return the Department
     */
    public String getDepartment() {
      return m_department;
    }

    /**
     * @param department
     *          the Department to set
     */
    public void setDepartment(String department) {
      m_department = department;
    }

    /**
     * @return the HeadOfUnit
     */
    public String getHeadOfUnit() {
      return m_headOfUnit;
    }

    /**
     * @param headOfUnit
     *          the HeadOfUnit to set
     */
    public void setHeadOfUnit(String headOfUnit) {
      m_headOfUnit = headOfUnit;
    }
  }
}
