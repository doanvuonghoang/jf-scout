/**
 *
 */
package com.jf.scout.shared.administration.ui.desktop.forms;

import java.util.List;
import java.util.Set;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

import com.jf.commons.datamodels.RolePermission;
import com.jf.commons.datamodels.User;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IRoleService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  RoleFormData create(RoleFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  RoleFormData load(RoleFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  RoleFormData prepareCreate(RoleFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  RoleFormData store(RoleFormData formData) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getAllRoles() throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void deleteRoles(Long[] ids) throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void deleteRolesPermantly(Long[] ids) throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void restoreRoles(Long[] ids) throws ProcessingException;

  /**
   * @param uids
   * @param rid
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void addUsersToRole(long[] uids, long rid) throws ProcessingException;

  /**
   * @param rid
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Set<User> getUsersInRole(Long rid) throws ProcessingException;

  /**
   * @param roleId
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Set<RolePermission> getPermissionsOfRole(Long roleId) throws ProcessingException;

  /**
   * @param roleId
   * @param permissions
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void revokePermissionsOfRole(Long roleId, List<String> permissions) throws ProcessingException;
}
