/**
 *
 */
package com.jf.scout.client.administration.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;

import com.jf.scout.client.administration.ui.desktop.pages.UserTablePage;
import com.jf.scout.client.administration.ui.desktop.pages.RoleTablePage;

/**
 * @author Hoàng
 */
public class AdministrationOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Administration");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    UserTablePage userTablePage = new UserTablePage();
    pageList.add(userTablePage);
    RoleTablePage roleTablePage = new RoleTablePage();
    pageList.add(roleTablePage);
  }
}
