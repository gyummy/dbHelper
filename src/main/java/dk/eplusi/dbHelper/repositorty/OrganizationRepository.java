package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.eplusi.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

}