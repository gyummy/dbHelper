package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.Occ;
import dk.eplusi.dbHelper.model.code.OccType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccDao extends JpaRepository<Occ, Integer> {
    List<Occ> findByOccName(String occName);
}
