package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色表访问接口
 *
 * @author hjb
 * @date 2018/12/7
 */
@Repository
public interface RoleDAO extends JpaRepository<TRole, Long> {

    /**
     * 根据角色名称查询角色
     *
     * @param roleName
     * @return
     */
    TRole findByRoleName(String roleName);

}
