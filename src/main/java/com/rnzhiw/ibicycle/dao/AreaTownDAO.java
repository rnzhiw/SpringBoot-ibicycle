package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TAreaTown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 街道表访问接口
 *
 * @author fuenhui
 * @date 2018/12/12
 */
@Repository
public interface AreaTownDAO extends JpaRepository<TAreaTown, Long> {

    /**
     * 通过父级区域(县、区)id查询街道
     *
     * @param parentCode
     * @return
     */
    List<TAreaTown> findByParentCode(String parentCode);

    /**
     * 通过编号查询街道
     *
     * @param code
     * @return
     */
    TAreaTown findByCode(String code);

}
