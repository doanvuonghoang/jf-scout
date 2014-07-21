/**
 * 
 */
package com.jf.scout.shared.administration.ui.desktop.pages;

import java.util.Date;

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
public class UserTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public UserTablePageData() {
  }

  @Override
  public UserTableRowData addRow() {
    return (UserTableRowData) super.addRow();
  }

  @Override
  public UserTableRowData addRow(int rowState) {
    return (UserTableRowData) super.addRow(rowState);
  }

  @Override
  public UserTableRowData createRow() {
    return new UserTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return UserTableRowData.class;
  }

  @Override
  public UserTableRowData[] getRows() {
    return (UserTableRowData[]) super.getRows();
  }

  @Override
  public UserTableRowData rowAt(int index) {
    return (UserTableRowData) super.rowAt(index);
  }

  public void setRows(UserTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class UserTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String userId = "userId";
    public static final String user = "user";
    public static final String valid = "valid";
    public static final String validChangeDate = "validChangeDate";
    public static final String status = "status";
    public static final String createdTime = "createdTime";
    public static final String creator = "creator";
    public static final String lastModifiedTime = "lastModifiedTime";
    public static final String lastModifier = "lastModifier";
    private Long m_userId;
    private String m_user;
    private Boolean m_valid;
    private Date m_validChangeDate;
    private String m_status;
    private Date m_createdTime;
    private String m_creator;
    private Date m_lastModifiedTime;
    private String m_lastModifier;

    public UserTableRowData() {
    }

    /**
     * @return the UserId
     */
    public Long getUserId() {
      return m_userId;
    }

    /**
     * @param userId
     *          the UserId to set
     */
    public void setUserId(Long userId) {
      m_userId = userId;
    }

    /**
     * @return the User
     */
    public String getUser() {
      return m_user;
    }

    /**
     * @param user
     *          the User to set
     */
    public void setUser(String user) {
      m_user = user;
    }

    /**
     * @return the Valid
     */
    public Boolean getValid() {
      return m_valid;
    }

    /**
     * @param valid
     *          the Valid to set
     */
    public void setValid(Boolean valid) {
      m_valid = valid;
    }

    /**
     * @return the ValidChangeDate
     */
    public Date getValidChangeDate() {
      return m_validChangeDate;
    }

    /**
     * @param validChangeDate
     *          the ValidChangeDate to set
     */
    public void setValidChangeDate(Date validChangeDate) {
      m_validChangeDate = validChangeDate;
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
     * @return the CreatedTime
     */
    public Date getCreatedTime() {
      return m_createdTime;
    }

    /**
     * @param createdTime
     *          the CreatedTime to set
     */
    public void setCreatedTime(Date createdTime) {
      m_createdTime = createdTime;
    }

    /**
     * @return the Creator
     */
    public String getCreator() {
      return m_creator;
    }

    /**
     * @param creator
     *          the Creator to set
     */
    public void setCreator(String creator) {
      m_creator = creator;
    }

    /**
     * @return the LastModifiedTime
     */
    public Date getLastModifiedTime() {
      return m_lastModifiedTime;
    }

    /**
     * @param lastModifiedTime
     *          the LastModifiedTime to set
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
      m_lastModifiedTime = lastModifiedTime;
    }

    /**
     * @return the LastModifier
     */
    public String getLastModifier() {
      return m_lastModifier;
    }

    /**
     * @param lastModifier
     *          the LastModifier to set
     */
    public void setLastModifier(String lastModifier) {
      m_lastModifier = lastModifier;
    }
  }
}