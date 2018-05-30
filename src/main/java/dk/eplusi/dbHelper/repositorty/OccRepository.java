package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.eplusi.Occ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccRepository extends JpaRepository<Occ, Integer> {
    List<Occ> findByOccName(String occName);
}
