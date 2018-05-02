package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.OccType;
import dk.eplusi.dbHelper.model.code.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccTypeDao extends JpaRepository<OccType, Integer> {

}
