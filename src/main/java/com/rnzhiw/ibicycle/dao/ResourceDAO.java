package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源表访问接口
 *
 * @author fuenhui
 * @date 2018/11/27
 */
@Repository
public interface ResourceDAO extends JpaRepository<TResource, Long> {

    /**
     * 通过状态查询资源
     *
     * @param status
     * @param sort
     * @return
     */
    List<TResource> findByStatus(Boolean status, Sort sort);
}
