package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织表访问接口
 *
 * @author fuenhui
 * @date 2018/11/24
 */
@Repository
public interface OrganizationDAO extends JpaRepository<TOrganization, Long> {

    /**
     * 通过父组织查询子组织
     *
     * @param organization
     * @return
     */
    List<TOrganization> findByParent(TOrganization organization);

    /**
     * 根据组织名称查询组织
     *
     * @param orgName
     * @return
     */
    TOrganization findByName(String orgName);

    /**
     * 通过组织的id查询该组织以及该组织下的所有子组织(包括多级子组织，直到叶子节点)
     *
     * @param id
     * @return
     */
    @Query("from TOrganization where catalogIds like concat('%,',:id,',%')")
    List<TOrganization> findOrgAndChildOrgsById(@Param("id") Long id);

    /**
     * 通过组织的id查询该组织下的所有安装点组织(包括多级子组织，直到叶子节点)
     */
    @Query("from TOrganization where catalogIds like concat('%,',:id,',%') and type=2")
    List<TOrganization> findChildInstallOrgsById(@Param("id") Long id);

    /**
     * 根据名称模糊查询所有组织
     *
     * @param name
     * @return
     */
    @Query("from TOrganization where name like concat('%',:name,'%')")
    List<TOrganization> findByNameLike(@Param("name") String name);

    /**
     * 通过组织的id查询该组织以及该组织下的所有子组织(包括多级子组织，直到叶子节点)
     * 并加以组织全称模糊查询限制
     *
     * @param id
     * @param name
     * @return
     */
    @Query("from TOrganization where catalogIds like concat('%,',:id,',%') and name like concat('%',:name,'%')")
    List<TOrganization> findOrgAndChildOrgsByIdAndNameLike(@Param("id") Long id, @Param("name") String name);


}
