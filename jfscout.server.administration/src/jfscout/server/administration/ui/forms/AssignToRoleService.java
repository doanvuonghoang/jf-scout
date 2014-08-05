/**
 *
 */
package jfscout.server.administration.ui.forms;

import java.util.concurrent.Callable;

import jfscout.shared.administration.ui.forms.AssignToRoleFormData;
import jfscout.shared.administration.ui.forms.CreateAssignToRolePermission;
import jfscout.shared.administration.ui.forms.IAssignToRoleService;
import jfscout.shared.administration.ui.forms.ReadAssignToRolePermission;
import jfscout.shared.administration.ui.forms.UpdateAssignToRolePermission;
import jfscout.shared.core.services.IDatabaseService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.jf.commons.datamodels.Role;
import com.jf.commons.datamodels.RolePermission;

/**
 * @author Hoàng
 */
public class AssignToRoleService extends AbstractService implements IAssignToRoleService {

  @Override
  public AssignToRoleFormData create(AssignToRoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateAssignToRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    Dao<RolePermission, Long> rpdao = SERVICES.getService(IDatabaseService.class).getDao(RolePermission.class);

    try {
      TransactionManager.callInTransaction(rpdao.getConnectionSource(), new Callable<Void>() {
        /* (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Void call() throws Exception {
          // for each selected role
          for (Long t : formData.getRole().getValue()) {
            // add or update permission
            for (String perm : formData.getPermission()) {
              RolePermission rp = new RolePermission();
              rp.setNew(true);

              rp.setPermission(perm);

              Role r = new Role();
              r.setId(t);
              rp.setRole(r);

              rpdao.createOrUpdate(rp);
            }

          }
          return null;
        }
      });
    }
    catch (Exception e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public AssignToRoleFormData load(AssignToRoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadAssignToRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    return formData;
  }

  @Override
  public AssignToRoleFormData prepareCreate(AssignToRoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateAssignToRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    return formData;
  }

  @Override
  public AssignToRoleFormData store(AssignToRoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateAssignToRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    return formData;
  }
}
