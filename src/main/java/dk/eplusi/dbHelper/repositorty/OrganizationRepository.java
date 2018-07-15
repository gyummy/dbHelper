package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.eplusi.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByAppliedYear(String appliedYear);
}