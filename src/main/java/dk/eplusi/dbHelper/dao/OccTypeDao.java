package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.OccType;
import dk.eplusi.dbHelper.model.code.RoleType;
import dk.eplusi.dbHelper.model.eplusi.Youth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccTypeDao extends JpaRepository<OccType, Integer> {
    List<OccType> findByOccType(String occType);
}
