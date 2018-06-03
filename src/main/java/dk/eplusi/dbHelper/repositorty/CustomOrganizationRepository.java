package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.eplusi.Organization;

import java.util.Date;
import java.util.List;

public interface CustomOrganizationRepository {
    List<Organization> findByAppliedYear(Date appliedYearStart);
}