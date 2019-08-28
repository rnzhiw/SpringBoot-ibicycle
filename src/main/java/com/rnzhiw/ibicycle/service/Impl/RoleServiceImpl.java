package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.dao.ResourceDAO;
import com.rnzhiw.ibicycle.dao.RoleDAO;
import com.rnzhiw.ibicycle.form.RoleForm;
import com.rnzhiw.ibicycle.model.domain.TResource;
import com.rnzhiw.ibicycle.model.domain.TRole;
import com.rnzhiw.ibicycle.model.dto.RoleDTO;
import com.rnzhiw.ibicycle.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色业务实现类
 *
 * @author fuenhui
 * @date 2018/12/13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Value("${system.super-user-id}")
    private Long superUserId;

    private RoleDAO roleDAO;

    private ResourceDAO resourceDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO, ResourceDAO resourceDAO) {
        this.roleDAO = roleDAO;
        this.resourceDAO = resourceDAO;
    }

    @Override
    public List<RoleDTO> findAll(Sort sort) {

        if (sort == null) {
            return e2d(roleDAO.findAll());
        } else {
            return e2d(roleDAO.findAll(sort));
        }
    }


    @Override
    public AjaxResult save(RoleForm roleForm) {

        TRole role = new TRole();
        BeanUtils.copyProperties(roleForm,role,"resource");
        roleDAO.save(role);
        return AjaxResult.success(role);
    }

    @Override
    public void delete(Long id) {

        TRole tRole = roleDAO.getOne(id);
        if(tRole != null){
            roleDAO.delete(roleDAO.getOne(id));
        }
    }

    @Override
    public boolean update(RoleDTO roleDTO) {
        return false;
    }

    private RoleDTO e2d(TRole role) {

        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);

        List<TResource> resources = role.getResource();
        List<Long> resourceIds = new ArrayList<>();

        if (resources != null && resources.size() != 0) {
            for (TResource resource : resources) {
                if (resource != null) {
                    resourceIds.add(resource.getId());
                }
            }
        }

        roleDTO.setResourceId(resourceIds);
        return roleDTO;
    }


    @Override
    public boolean saveResources(Long roleId, Long[] resourceIds) {

        //超管不需要设置访问资源
        if(roleId.equals(superUserId)){
            return false;
        }
        TRole tRole = roleDAO.getOne(roleId);
        List<TResource> resources = new ArrayList<>();
        for(Long r : resourceIds){
            TResource tResource = resourceDAO.getOne(r);
            resources.add(tResource);
        }
        if(tRole != null){
            tRole.setResource(resources);
        }
        roleDAO.save(tRole);
        return true;
    }

    private List<RoleDTO> e2d(List<TRole> roles) {

        if (roles == null || roles.size() == 0) {
            return new ArrayList<>();
        }

        List<RoleDTO> roleDTOs = new ArrayList<>();

        for (TRole role : roles) {
            if (role != null) {
                roleDTOs.add(e2d(role));
            }
        }

        return roleDTOs;
    }
}
