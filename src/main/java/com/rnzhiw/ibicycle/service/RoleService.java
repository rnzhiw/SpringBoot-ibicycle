package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.form.RoleForm;
import com.rnzhiw.ibicycle.model.dto.RoleDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 角色业务接口
 *
 * @author fuenhui
 * @date 2018/12/13
 */
public interface RoleService {

    /**
     * id升序排序
     */
    Sort ID_SORT = new Sort(Sort.Direction.ASC, "id");

    /**
     * 列出所有角色,并按条件排序
     *
     * @param sort
     * @return
     */
    List<RoleDTO> findAll(Sort sort);

    /**
     * 保存角色
     *
     * @param roleForm
     * @return
     */
    AjaxResult save(RoleForm roleForm);

    /**
     * 删除id对应角色
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 修改角色信息
     *
     * @param roleDTO
     * @return
     */
    boolean update(RoleDTO roleDTO);

    /**
     * 保存指定角色可访问资源
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    boolean saveResources(Long roleId, Long[] resourceIds);
}
