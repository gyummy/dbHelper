package dk.eplusi.dbHelper.repositorty;

import dk.eplusi.dbHelper.model.eplusi.BizType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BizTypeRepository extends JpaRepository<BizType, Integer> {
    List<BizType> findByBizType(String bizType);
}
