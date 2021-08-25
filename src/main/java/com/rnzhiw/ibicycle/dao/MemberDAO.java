package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TMember;
import com.rnzhiw.ibicycle.model.domain.TOrganization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表访问接口
 *
 * @author ruanzhiwei
 * @date 2018/11/24
 */
@Repository
public interface MemberDAO extends JpaRepository<TMember, Long>, JpaSpecificationExecutor<TMember> {

    /**
     * 根据手机号查询用户
     *
     * @param phone
     * @return
     */
    TMember findByPhone(String phone);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    TMember findByEmail(String email);

    /**
     * 通过组织查询用户
     *
     * @param organization
     * @return
     */
    List<TMember> findByOrganization(TOrganization organization);

    /**
     * 根据真实姓名查询用户
     *
     * @param realName
     * @return
     */
    List<TMember> findByRealName(String realName);

    /**
     * 根据身份证号查询用户
     *
     * @param idCard
     * @return
     */
    TMember findByIdCard(String idCard);

    /**
     * 获取所有人员并分页
     *
     * @param pageable
     * @return
     */
    @Query("from TMember t")
    Page<TMember> findAllMembers(Pageable pageable);

    /**
     * 获取所有有组织的人员
     *
     * @param pageable
     * @return
     */
    @Query("from TMember t where t.organization<>null")
    Page<TMember> findAllOrgMembers(Pageable pageable);

    /**
     * 通过组织id集合查询人员
     *
     * @param orgIds   组织id集合
     * @param pageable
     * @return
     */
    @Query("from TMember t where t.organization.id in :orgIds")
    Page<TMember> findByOrgIds(@Param("orgIds") List<Long> orgIds, Pageable pageable);

    /**
     * 通过组织id集合和关键字查询人员
     *
     * @param orgIds   组织id集合
     * @param keyword  关键字
     * @param pageable
     * @return
     */
    @Query("from TMember t where t.organization.id in :orgIds and (t.realName like concat('%',:keyword,'%') " +
            "or t.phone like concat('%',:keyword,'%') or t.email like concat('%',:keyword,'%') " +
            "or t.idCard like concat('%',:keyword,'%'))")
    Page<TMember> findByOrgIdsAndKeyword(@Param("orgIds") List<Long> orgIds, @Param("keyword") String keyword,
                                         Pageable pageable);

}
