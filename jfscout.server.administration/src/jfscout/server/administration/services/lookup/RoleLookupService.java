/**
 *
 */
package jfscout.server.administration.services.lookup;

import jfscout.shared.administration.services.lookup.IRoleLookupService;
import jfscout.shared.core.services.IDatabaseService;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.Role;

/**
 * @author Ho�ng
 */
public class RoleLookupService extends AbstractSqlLookupService<Long> implements IRoleLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    try {
      Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

      QueryBuilder<Role, Long> qb = dao.queryBuilder();
      qb.selectColumns("id", Role.FIELD_NAME)
          .where()
          .eq(Role.FIELD_VALID, true)
          .and().ne(Role.FIELD_RECORD_STATUS, RecordStatus.DELETE);

      String result = qb.prepareStatementString();
      result += " " +
          "<key>   AND \"id\" = :key </key> " +
          "<text>  AND UPPER(\"" + Role.FIELD_NAME + "\") LIKE UPPER(:text||'%') </text> " +
          "<all>   </all> ";

      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }
}
