package dk.eplusi.dbHelper.dao;

import dk.eplusi.dbHelper.model.code.BizType;
import dk.eplusi.dbHelper.model.code.Occ;
import dk.eplusi.dbHelper.model.code.OccType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BizTypeDao extends JpaRepository<BizType, Integer> {
    List<BizType> findByBizType(String bizType);
}
