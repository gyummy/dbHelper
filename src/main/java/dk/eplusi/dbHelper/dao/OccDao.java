package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.Occ;
import dk.eplusi.dbHelper.model.code.OccType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccDao extends JpaRepository<Occ, Integer> {

}
