package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.code.ReligionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReligionTypeRepository extends JpaRepository<ReligionType, Integer> {
    List<ReligionType> findByReligionType(String religionType);
}
