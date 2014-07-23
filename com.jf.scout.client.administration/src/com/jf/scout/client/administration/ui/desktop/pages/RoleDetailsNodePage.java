/**
 *
 */
package com.jf.scout.client.administration.ui.desktop.pages;

import java.util.Collection;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

import com.jf.scout.shared.core.Icons;

/**
 * @author Hoàng
 */
public class RoleDetailsNodePage extends AbstractPageWithNodes {

  private Long m_roleNr;

  @Override
  protected String getConfiguredIconId() {
    return Icons.Role;
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    UserTablePage userTablePage = new UserTablePage();
    userTablePage.setRoleNr(getRoleNr());
    pageList.add(userTablePage);
    PermissionManagementTablePage permissionManagementTablePage = new PermissionManagementTablePage();
    permissionManagementTablePage.setRoleId(getRoleNr());
    pageList.add(permissionManagementTablePage);

  }

  /**
   * @return the RoleNr
   */
  @FormData
  public Long getRoleNr() {
    return m_roleNr;
  }

  /**
   * @param roleNr
   *          the RoleNr to set
   */
  @FormData
  public void setRoleNr(Long roleNr) {
    m_roleNr = roleNr;
  }
}
