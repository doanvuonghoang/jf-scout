/**
 *
 */
package com.jf.scout.server.administration.ui.desktop.forms;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.jf.commons.datamodels.User;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.administration.ui.desktop.forms.CreateUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.IUserService;
import com.jf.scout.shared.administration.ui.desktop.forms.ReadUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.UpdateUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.UserFormData;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class UserService extends AbstractService implements IUserService {

  @Override
  public UserFormData create(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Hoàng] business logic here.
    User u = new User();
    u.setNew(true);
    u.setUserName(formData.getUserName().getValue());
    u.setPassword(formData.getUserPassword().getValue());
    u.setCreator(ServerSession.get().getUserId());
    try {
      SERVICES.getService(IDatabaseService.class).getDao(User.class).create(u);
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public UserFormData load(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public UserFormData prepareCreate(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public UserFormData store(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public Object[][] getAllUsers() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      List<User> us = dao.queryForAll();
      Object[][] result = new Object[us.size()][];
      int c = 0;

      for (User u : us) {
        result[c] = new Object[]{
            u.getId(),
            u.getUserName(),
            u.isValid(),
            u.getValidChangedTime(),
            u.getRecordStatus(),
            u.getCreatedTime(),
            u.getCreator(),
            u.getLastModifiedTime(),
            u.getLastModifier()
        };
        c++;
      }

      return result;
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
}
