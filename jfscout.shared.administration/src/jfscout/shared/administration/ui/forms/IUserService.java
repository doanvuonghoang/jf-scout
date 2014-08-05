/**
 *
 */
package jfscout.shared.administration.ui.forms;

import java.util.List;
import java.util.Set;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

import com.jf.commons.datamodels.RolePermission;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IUserService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  UserFormData create(UserFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  UserFormData load(UserFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  UserFormData prepareCreate(UserFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  UserFormData store(UserFormData formData) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getAllUsers() throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void deleteUsers(Long[] ids) throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void deleteUsersPermantly(Long[] ids) throws ProcessingException;

  /**
   * @param ids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void restoreUsers(Long[] ids) throws ProcessingException;

  /**
   * @param rid
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Set<Long> getRoleIdsOfUser(Long rid) throws ProcessingException;

  /**
   * @param uid
   * @param rids
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void addRoleIdsOfUser(Long uid, Set<Long> rids) throws ProcessingException;

  /**
   * @param rid
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getAllUsers(Long rid) throws ProcessingException;

  /**
   * @param userName
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  List<RolePermission> getPermissionsOfUser(String userName) throws ProcessingException;
}
