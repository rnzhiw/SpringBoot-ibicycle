package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行政区域表访问接口
 *
 * @author fuenhui
 * @date 2018/12/12
 */
@Repository
public interface AreaDAO extends JpaRepository<TArea, Long> {

    @Query("select a from TArea a where a.code like ?1")
    List<TArea> findByCodeFix(String code);

    /**
     * 通过编号查询区域
     *
     * @param code
     * @return
     */
    TArea findByCode(String code);
}
