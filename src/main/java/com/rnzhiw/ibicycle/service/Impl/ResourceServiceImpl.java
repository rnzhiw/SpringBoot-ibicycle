package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.dao.ResourceDAO;
import com.rnzhiw.ibicycle.dao.RoleDAO;
import com.rnzhiw.ibicycle.enums.ResourceType;
import com.rnzhiw.ibicycle.form.ResourceForm;
import com.rnzhiw.ibicycle.model.domain.TResource;
import com.rnzhiw.ibicycle.model.domain.TRole;
import com.rnzhiw.ibicycle.model.dto.ResourceDTO;
import com.rnzhiw.ibicycle.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源业务实现类
 *
 * @author fuenhui
 * @date 2018/12/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    @Value("${system.super-user-id}")
    private Long superUserId;

    private ResourceDAO resourceDAO;
    private RoleDAO roleDAO;

    @Autowired
    public ResourceServiceImpl(ResourceDAO resourceDAO, RoleDAO roleDAO) {
        this.resourceDAO = resourceDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public List<ResourceDTO> findAll(Sort sort) {
        if (sort == null) {
            return e2d(resourceDAO.findAll());
        } else {
            return e2d(resourceDAO.findAll(sort));
        }
    }

    @Override
    public ResourceDTO save(ResourceForm resourceForm) {

        if (resourceForm == null) {
            return null;
        }

        //添加资源
        TResource resource = new TResource();
        BeanUtils.copyProperties(resourceForm, resource);
        if (resourceForm.getParentId() != null) {
            resource.setParent(resourceDAO.getOne(resourceForm.getParentId()));
        } else {
            resource.setParent(null);
        }
        resource.setResType(ResourceType.valueOf(resourceForm.getResType()));
        return e2d(resourceDAO.save(resource));
    }

    @Override
    public ResourceDTO update(ResourceForm resourceForm) {

        if (resourceForm == null || resourceForm.getId() == null) {
            return null;
        }

        //找到id对应的资源
        TResource resource = resourceDAO.getOne(resourceForm.getId());
        if (resource == null) {
            return null;
        }
        resource.setFunUrls(resourceForm.getFunUrls());
        resource.setMenuUrl(resourceForm.getMenuUrl());
        resource.setResKey(resourceForm.getResKey());
        resource.setResName(resourceForm.getResName());
        resource.setWeight(resourceForm.getWeight());
        resource.setStatus(resourceForm.getStatus());

        if (resourceForm.getParentId() != null) {
            resource.setParent(resourceDAO.getOne(resourceForm.getParentId()));
        } else {
            resource.setParent(null);
        }
        resource.setResType(ResourceType.valueOf(resourceForm.getResType()));
        return e2d(resource);
    }


    @Override
    public List<ResourceDTO> getResources(Long id) {
        //首次进入页面时
        if (id == null) {
            List<ResourceDTO> resourceDTOS = new ArrayList<>();
            return e2d(resourceDAO.findAll());
        } else {
            if (id.equals(superUserId)) {
                List<ResourceDTO> resourceDTOS = e2d(resourceDAO.findAll());
                for (ResourceDTO t : resourceDTOS) {
                    t.setHasResource(true);
                }
                return resourceDTOS;
            }
            TRole role = roleDAO.getOne(id);
            //前端生成新角色时返回空白资源列表
            if (role == null) {
                List<ResourceDTO> resourceDTOS = e2d(resourceDAO.findAll());
                for (ResourceDTO t : resourceDTOS) {
                    t.setHasResource(false);
                }
                return resourceDTOS;
            }
            List<ResourceDTO> resourceDTOS = e2d(resourceDAO.findAll());
            List<TResource> roleResources = role.getResource();
            for (ResourceDTO t : resourceDTOS) {
                for (TResource resource : roleResources) {
                    if (t.getId().equals(resource.getId())) {
                        t.setHasResource(true);
                    }
                }
            }
            return resourceDTOS;
        }

    }

    @Override
    public ResourceDTO findOne(Long id) {

        if (id == null) {
            return null;
        }

        return e2d(resourceDAO.getOne(id));
    }

    @Override
    public boolean delete(Long id) {

        if (id == null) {
            return false;
        }

        TResource resource = resourceDAO.getOne(id);
        if (resource == null) {
            return false;
        }

        resourceDAO.delete(resourceDAO.getOne(id));
        return true;
    }

    private ResourceDTO e2d(TResource resource) {

        if (resource == null) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resource, resourceDTO);

        if (resource.getParent() != null) {
            resourceDTO.setParentId(resource.getParent().getId());
            resourceDTO.setParentName(resource.getParent().getResName());
        }

        if (resource.getResType() != null) {
            resourceDTO.setResType(resource.getResType().toString());
        }

        return resourceDTO;
    }

    private List<ResourceDTO> e2d(List<TResource> resources) {

        if (resources == null || resources.size() == 0) {
            return new ArrayList<>();
        }

        List<ResourceDTO> resourceDTOs = new ArrayList<>();
        for (TResource resource : resources) {
            if (resource != null) {
                resourceDTOs.add(e2d(resource));
            }
        }

        return resourceDTOs;
    }
}
