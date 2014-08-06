/**
 * 
 */
package jfscout.client.hrm.ui.desktop.outlines;

import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author Ho�ng
 */
public class DepartmentOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Department");
  }
}
