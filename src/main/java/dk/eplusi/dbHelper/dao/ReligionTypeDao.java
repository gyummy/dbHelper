package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.OccType;
import dk.eplusi.dbHelper.model.code.ReligionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReligionTypeDao extends JpaRepository<ReligionType, Integer> {
    List<ReligionType> findByReligionType(String religionType);
}
