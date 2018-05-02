package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.BizType;
import dk.eplusi.dbHelper.model.code.Occ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BizTypeDao extends JpaRepository<BizType, Integer> {

}
