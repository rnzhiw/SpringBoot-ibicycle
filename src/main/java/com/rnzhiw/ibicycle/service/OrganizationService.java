package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.form.OrganizationForm;
import com.rnzhiw.ibicycle.model.dto.OrganizationDTO;

import java.util.List;

/**
 * 组织业务接口
 *
 * @author fuenhui
 * @date 2018/11/24
 */
public interface OrganizationService {

    /**
     * 平台
     */
    short TYPE_CODE_PLAT = 0;

    /**
     * 公安
     */
    short TYPE_CODE_POLICE = 1;

    /**
     * 运维
     */
    short TYPE_CODE_MAINTAIN = 2;

    /**
     * 根据当前登录用户id查询用户可视的所有组织
     *
     * @param userId
     * @return
     */
    List<OrganizationDTO> findAllByUserId(Long userId);

    /**
     * 根据当前登录用户id和组织名称模糊查询用户可视的所有组织
     *
     * @param userId
     * @param name
     * @return
     */
    List<OrganizationDTO> findAllByUserIdAndNameLike(Long userId, String name);

    /**
     * 添加组织
     * <p>
     * 注意：
     * 调用此方法时应保证form的存在性且form的属性满足Validated判断条件
     * 否则不可调用此方法
     *
     * @param form
     * @param userId
     * @return
     */
    OrganizationDTO save(OrganizationForm form, Long userId);

    /**
     * 编辑组织
     * <p>
     * 注意：
     * 调用此方法时应保证form的存在性且form的属性满足Validated判断条件
     * 否则不可调用此方法
     *
     * @param form
     * @param userId
     * @return
     */
    OrganizationDTO update(OrganizationForm form, Long userId);

    /**
     * 根据组织id查询组织
     *
     * @param id
     * @return
     */
    OrganizationDTO findOne(Long id);

    /**
     * 根据组织id查询组织，并判断用户权限是否可以查看此组织，若不能则返回null
     *
     * @param id 组织id
     * @param userId 当前登录用户id
     * @return 组织DTO
     */
    OrganizationDTO get(Long id, Long userId);

    /**
     * 根据组织id删除组织，并判断用户权限是否可以查看此组织，且判断组织是否有下级组织
     * 若组织有下级组织或用户权限不足，则删除失败
     *
     * @param id 组织id
     * @param userId 当前登录用户id
     * @return 是否成功删除
     */
    boolean delete(Long id, Long userId);
}
