package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.code.OccType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccTypeRepository extends JpaRepository<OccType, Integer> {
    List<OccType> findByOccType(String occType);
}
