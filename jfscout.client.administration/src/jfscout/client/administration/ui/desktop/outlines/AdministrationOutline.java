/**
 *
 */
package jfscout.client.administration.ui.desktop.outlines;

import java.util.Collection;

import jfscout.client.administration.ui.desktop.pages.PermissionManagementTablePage;
import jfscout.client.administration.ui.desktop.pages.RoleTablePage;
import jfscout.client.administration.ui.desktop.pages.UserTablePage;
import jfscout.shared.administration.security.ViewOutlinePermission;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author Hoàng
 */
public class AdministrationOutline extends AbstractExtensibleOutline {

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.ui.basic.tree.AbstractTree#execInitTree()
   */
  @Override
  protected void execInitTree() throws ProcessingException {
    setVisiblePermission(new ViewOutlinePermission());

    super.execInitTree();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Administration");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    RoleTablePage roleTablePage = new RoleTablePage();
    pageList.add(roleTablePage);
    UserTablePage userTablePage = new UserTablePage();
    pageList.add(userTablePage);
    PermissionManagementTablePage permissionManagementTablePage = new PermissionManagementTablePage();
    pageList.add(permissionManagementTablePage);
  }
}
