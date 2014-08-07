/**
 *
 */
package jfscout.shared.hrm.codetypes;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

/**
 * @author Ho�ng
 */
public class EmployeeStatusCodeType extends AbstractCodeType<String, Integer> {

  private static final long serialVersionUID = 1L;
  /**
   *
   */
  public static final String ID = "EmployeeStatus";

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public EmployeeStatusCodeType() throws ProcessingException {
    super();
  }

  @Override
  public String getId() {
    return ID;
  }

  @Order(10.0)
  public static class WorkingCode extends AbstractCode<Integer> {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public static final Integer ID = 1;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Working");
    }

    @Override
    public Integer getId() {
      return ID;
    }
  }
}
