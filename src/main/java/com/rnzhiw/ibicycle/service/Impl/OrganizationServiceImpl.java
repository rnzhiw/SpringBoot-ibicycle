package com.rnzhiw.ibicycle.service.Impl;


import com.rnzhiw.ibicycle.dao.*;
import com.rnzhiw.ibicycle.form.OrganizationForm;
import com.rnzhiw.ibicycle.model.domain.*;
import com.rnzhiw.ibicycle.model.dto.OrganizationDTO;
import com.rnzhiw.ibicycle.service.OrganizationService;
import com.rnzhiw.ibicycle.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 组织业务实现类
 *
 * @author fuenhui
 * @date 2018/12/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationServiceImpl implements OrganizationService {
    

    @Value("${system.super-user-id}")
    private Long superUserId;

    @Value("${system.plat-org-id}")
    private Long platOrgId;

    private OrganizationDAO organizationDAO;

    private MemberDAO memberDAO;

    private AddressDAO addressDAO;

    private AreaDAO areaDAO;

    private AreaTownDAO areaTownDAO;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO, MemberDAO memberDAO, AddressDAO addressDAO,
                                   AreaDAO areaDAO, AreaTownDAO areaTownDAO) {
        this.organizationDAO = organizationDAO;
        this.memberDAO = memberDAO;
        this.addressDAO = addressDAO;
        this.areaDAO = areaDAO;
        this.areaTownDAO = areaTownDAO;
    }

    @Override
    public List<OrganizationDTO> findAllByUserId(Long userId) {

        if (userId == null) {
            return new ArrayList<>();
        }

        TMember member = memberDAO.getOne(userId);
        if (member == null) {
            return new ArrayList<>();
        }

        if (superUserId.equals(userId)) {
            return e2d(organizationDAO.findAll());
        } else {
            TOrganization organization = member.getOrganization();
            if (organization == null) {
                return new ArrayList<>();
            }
            return e2d(organizationDAO.findOrgAndChildOrgsById(organization.getId()));
        }
    }

    @Override
    public List<OrganizationDTO> findAllByUserIdAndNameLike(Long userId, String name) {

        if (userId == null || StringUtil.isEmpty(name)) {
            return new ArrayList<>();
        }

        TMember member = memberDAO.getOne(userId);
        if (member == null) {
            return new ArrayList<>();
        }

        if (superUserId.equals(userId)) {
            return e2d(organizationDAO.findByNameLike(name));
        } else {
            TOrganization organization = member.getOrganization();
            if (organization == null) {
                return new ArrayList<>();
            }
            return e2d(organizationDAO.findOrgAndChildOrgsByIdAndNameLike(organization.getId(), name));
        }
    }

    @Override
    public OrganizationDTO save(OrganizationForm form, Long userId) {

        if (form == null || userId == null) {
            return null;
        }

        //获取当前登录用户
        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return null;
        }

        //获取当前登录用户所在组织
        TOrganization userOrganization = user.getOrganization();
        if (userOrganization == null) {
            return null;
        }

        //获取当前用户的可管理组织
        List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(userOrganization.getId());

        //添加组织
        TOrganization organization = new TOrganization();
        organization.setCreateTime(new Date());
        organization.setCreator(user);

        //设置详细地址
        TAddress address = new TAddress();
        address.setGpsLng(form.getGpsLng());
        address.setGpsLat(form.getGpsLat());
        address.setDetail(form.getAddressDetail());
        organization.setAddress(addressDAO.save(address));

        //设置父组织
        //只有超级管理员可以添加顶级组织(将父组织设为空)
        TOrganization parent = null;
        if (form.getParentId() == null) {
            if (superUserId.equals(userId)) {
                organization.setParent(null);
            } else {
                return null;
            }
        } else {
            //判断当前登录用户是否有设置此父组织的权限
            parent = organizationDAO.getOne(form.getParentId());
            if (!organizations.contains(parent)) {
                return null;
            }
            organization.setParent(parent);
        }

        TArea area = areaDAO.findByCode(form.getDistrictCode());
        organization.setArea(area);

        if (StringUtil.isNotEmpty(form.getTownCode())) {
            TAreaTown areaTown = areaTownDAO.findByCode(form.getTownCode());
            organization.setAreaTown(areaTown);
        }

        organization.setName(form.getName());
        organization.setShortName(form.getShortName());
        organization.setType(form.getType());
        organization.setStatus(form.getStatus());

        TOrganization result = organizationDAO.save(organization);
        if (parent != null) {
            result.setCatalogIds(parent.getCatalogIds() + "," + result.getId() + ",");
        } else {
            result.setCatalogIds("," + result.getId() + ",");
        }

        return e2d(result);
    }

    @Override
    public OrganizationDTO update(OrganizationForm form, Long userId) {

        if (form == null || userId == null || form.getId() == null) {
            return null;
        }

        //获取当前登录用户
        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return null;
        }

        //获取当前登录用户所在组织
        TOrganization userOrganization = user.getOrganization();
        if (userOrganization == null) {
            return null;
        }

        //获取当前用户的可管理组织
        List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(userOrganization.getId());

        //修改组织
        TOrganization organization = organizationDAO.getOne(form.getId());
        if (organization == null) {
            return null;
        }

        //判断该用户是否有修改该组织的权限
        if (!organizations.contains(organization)) {
            return null;
        }

        //设置详细地址
        TAddress address = organization.getAddress();
        address.setGpsLng(form.getGpsLng());
        address.setGpsLat(form.getGpsLat());
        address.setDetail(form.getAddressDetail());

        //设置父组织
        //只有超级管理员可以添加顶级组织(将父组织设为空)
        TOrganization parent = null;
        if (form.getParentId() == null) {
            if (superUserId.equals(userId)) {
                organization.setParent(null);
            } else {
                return null;
            }
        } else {
            //判断当前登录用户是否有设置此父组织的权限
            parent = organizationDAO.getOne(form.getParentId());
            if (!organizations.contains(parent)) {
                return null;
            }
            organization.setParent(parent);
        }

        TArea area = areaDAO.findByCode(form.getDistrictCode());
        organization.setArea(area);

        if (StringUtil.isNotEmpty(form.getTownCode())) {
            TAreaTown areaTown = areaTownDAO.findByCode(form.getTownCode());
            organization.setAreaTown(areaTown);
        }

        organization.setName(form.getName());
        organization.setShortName(form.getShortName());
        organization.setType(form.getType());
        organization.setStatus(form.getStatus());

        TOrganization result = organizationDAO.save(organization);
        if (parent != null) {
            result.setCatalogIds(parent.getCatalogIds() + "," + result.getId() + ",");
        } else {
            result.setCatalogIds("," + result.getId() + ",");
        }

        return e2d(result);
    }

    @Override
    public OrganizationDTO findOne(Long id) {

        if (id == null) {
            return null;
        }

        return e2d(organizationDAO.getOne(id));
    }

    @Override
    public OrganizationDTO get(Long id, Long userId) {

        if (id == null || userId == null) {
            return null;
        }

        //获取所要返回的组织
        TOrganization organization = organizationDAO.getOne(id);
        if (organization == null) {
            return null;
        }

        //获取当前登录用户
        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return null;
        }

        //获取当前用户所在组织
        TOrganization userOrg = user.getOrganization();
        if (userOrg == null) {
            return null;
        }

        //获取用户所有可视组织
        List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(userOrg.getId());
        if (organizations == null || organizations.size() == 0) {
            return null;
        }

        //判断用户是否可以查看此组织
        if (!organizations.contains(organization)) {
            return null;
        }

        return e2d(organization);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        if (id == null || userId == null) {
            return false;
        }

        //获取当前登录用户
        TMember member = memberDAO.getOne(userId);
        if (member == null) {
            return false;
        }

        //获取当前登录用户所在组织
        TOrganization organization = member.getOrganization();
        if (organization == null) {
            return false;
        }

        //获取当前登录用户所有可管理组织
        List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(organization.getId());
        if (organizations == null || organizations.size() == 0) {
            return false;
        }

        //获取需要删除的组织
        TOrganization deleteOrg = organizationDAO.getOne(id);
        if (deleteOrg == null) {
            return false;
        }

        //判断用户权限
        if (!organizations.contains(deleteOrg)) {
            return false;
        }

        //获取组织的子节点
        List<TOrganization> childOrgs = organizationDAO.findByParent(deleteOrg);

        //若组织存在子节点，则不允许删除
        if (childOrgs != null && childOrgs.size() != 0) {
            return false;
        }

        organizationDAO.delete(organizationDAO.getOne(id));
        return true;
    }

    private OrganizationDTO e2d(TOrganization organization) {

        if (organization == null) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization, organizationDTO);

        if (organization.getAddress() != null) {
            organizationDTO.setAddressId(organization.getAddress().getId());
            organizationDTO.setAddressDetail(organization.getAddress().getDetail());
            organizationDTO.setGpsLat(organization.getAddress().getGpsLat());
            organizationDTO.setGpsLng(organization.getAddress().getGpsLng());
        }
        if (organization.getArea() != null) {
            organizationDTO.setAreaId(organization.getArea().getId());
            organizationDTO.setAreaCode(organization.getArea().getCode());
        }
        if (organization.getAreaTown() != null) {
            organizationDTO.setAreaTownId(organization.getAreaTown().getId());
            organizationDTO.setAreaTownCode(organization.getAreaTown().getCode());
        }
        if (organization.getContact() != null) {
            organizationDTO.setContactId(organization.getContact().getId());
        }
        if (organization.getCreator() != null) {
            organizationDTO.setCreatorId(organization.getCreator().getId());
        }
        if (organization.getParent() != null) {
            organizationDTO.setParentId(organization.getParent().getId());
            organizationDTO.setParentName(organization.getParent().getName());
        }
        switch (organization.getType()) {
            case 0: {
                organizationDTO.setTypeDesc("平台");
                break;
            }
            case 1: {
                organizationDTO.setTypeDesc("公安");
                break;
            }
            case 2: {
                organizationDTO.setTypeDesc("运维");
                break;
            }
            default: {
            }
        }

        return organizationDTO;
    }

    private List<OrganizationDTO> e2d(List<TOrganization> organizations) {

        List<OrganizationDTO> organizationDTOs = new ArrayList<>();

        if (organizations == null || organizations.size() == 0) {
            return new ArrayList<>();
        }

        for (TOrganization organization : organizations) {
            if (organization != null) {
                organizationDTOs.add(e2d(organization));
            }
        }

        return organizationDTOs;
    }
}
