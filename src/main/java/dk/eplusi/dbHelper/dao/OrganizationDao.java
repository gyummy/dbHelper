package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.common.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationDao extends JpaRepository<Organization, Integer> {

}