package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.common.PageInfo;
import com.rnzhiw.ibicycle.dao.MemberDAO;
import com.rnzhiw.ibicycle.dao.OrganizationDAO;
import com.rnzhiw.ibicycle.dao.ResourceDAO;
import com.rnzhiw.ibicycle.dao.RoleDAO;
import com.rnzhiw.ibicycle.enums.ResourceType;
import com.rnzhiw.ibicycle.form.MemberForm;
import com.rnzhiw.ibicycle.model.domain.TMember;
import com.rnzhiw.ibicycle.model.domain.TOrganization;
import com.rnzhiw.ibicycle.model.domain.TResource;
import com.rnzhiw.ibicycle.model.domain.TRole;
import com.rnzhiw.ibicycle.model.dto.MemberDTO;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.MemberService;
import com.rnzhiw.ibicycle.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ruanzhiwei
 * @date 2019/8/22
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    OrganizationDAO organizationDAO;

    /**
     * 超级管理员
     */
    @Value("${system.super-user-id}")
    private Long superUserId;

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public MemberDTO findOne(Long userId) {

        if(userId == null) {
            return null;
        }

        return e2d(memberDAO.getOne(userId));
    }

    /**
     *
     * @param pageSize 一页有多少纪录
     * @param pageNum 页数
     * @param userId 用户Id
     * @return
     */
    @Override
    public PageInfo<MemberDTO> findAllMember(Integer pageSize, Integer pageNum, Long userId) {

        if(pageSize == null || pageNum == null || userId == null) {
            return null;
        }

        Pageable pageable = new PageRequest(pageNum - 1,pageSize);
        if (superUserId.equals(userId)) {
            Page<TMember> p = memberDAO.findAllOrgMembers(pageable);
            return page2pageInfo(p);
        } else {
            List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(userId);
            List<Long> orgIds = new ArrayList<>();
            for (TOrganization organization : organizations) {
                orgIds.add(organization.getId());
            }
            Page<TMember> p = memberDAO.findByOrgIds(orgIds, pageable);
            return page2pageInfo(p);
        }
    }

    @Override
    public String login(String mobile, String password, HttpSession session) {

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return "账号或者用户名为空";
        }

        TMember member = memberDAO.findByPhone(mobile);

        String dbPassword = member.getPassword();

        if(member == null) {
            return "该用户不存在";
        } else {
            if(!dbPassword.equals(password)) {
                return  "密码错误";
            }
        }

        //所有资源，不同角色的相同资源不重复添加
        List<TResource> allResources;
        //用户对应的权限
        List<TRole> roles = member.getRoles();
        //所有资源中的目录资源
        List<TResource> menus = new ArrayList<>();
        //所有请求资源路径,若用户访问的资源路径不在此范围内，则将被拦截器拦截
        Set<String> urls = new HashSet<>();
        //所有资源的标识
        Set<String> resourceKey = new HashSet<>();

        if (superUserId.equals(member.getId())) {
            //若用户为超级管理员，直接获得所有权限资源
            allResources = resourceDAO.findByStatus(true, new Sort(Sort.Direction.DESC, "weight"));
        } else {
            //直接遍历用户对应的所有角色的可用资源，可能出现重复
            List<TResource> resourceList = new ArrayList<>();
            for (TRole role : roles) {
                //遍历用户的所有角色，并将该角色对应的资源存入集合
                resourceList.addAll(role.getResource());
            }
            //不同角色可能有持有相同的resource，这一步进行去重
            allResources = resourceList.stream().distinct().collect(Collectors.toList());
        }

        for (TResource t : allResources) {

            //获取可用菜单，用于主页面左侧菜单栏的显示
            if (t.getResType().equals(ResourceType.MENU)) {
                //不将系统管理放入菜单
                if (!"system".equals(t.getResKey())) {
                    menus.add(t);
                }
            }

            //获取所有请求资源路径，若访问的资源不在urls里，则将被拦截
            if (StringUtil.isEmpty(t.getMenuUrl())) {
                urls.add(t.getMenuUrl());
            }
            try {
                for (String url : t.getFunUrls().split(",")) {
                    if (StringUtil.isNotEmpty(url)) {
                        urls.add(url);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //添加资源标识
            resourceKey.add(t.getResKey());
        }

        //将用户可用菜单和权限存入sessionInfo
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setMenus(menus);
        sessionInfo.setUrls(urls);
        sessionInfo.setResourceKey(resourceKey);
        sessionInfo.setUserId(member.getId());
        if (member.getOrganization() != null) {
            sessionInfo.setOrgId(member.getOrganization().getId());
        }
        sessionInfo.setRoleNames(member.getRoles().stream().map(TRole::getRoleName).collect(Collectors.toList()));
        sessionInfo.setSuperUser(superUserId.equals(member.getId()));

        //将sessionInfo存入session
        session.setAttribute(Constants.SESSION_INFO, sessionInfo);

        return "success";
    }

    @Override
    public boolean deleteMember(Long id, Long userId) {

        if (id == null || userId == null) {
            return false;
        }

        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return false;
        }

        TMember member = memberDAO.getOne(id);
        if (member == null) {
            return false;
        }

        //当前登录用户所在组织
        TOrganization userOrg = user.getOrganization();
        if (userOrg == null) {
            return false;
        }

        //要删除用户所在组织
        TOrganization memberOrg = member.getOrganization();
        if (memberOrg == null) {
            return false;
        }

        //判断是否有权限删除
        if (memberOrg.getCatalogIds().contains("," + userOrg.getId() + ",")) {
            memberDAO.delete(member);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public MemberDTO save(MemberForm form, Long userId) {

        if (form == null || userId == null) {
            return null;
        }

        //获取当前登录用户
        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return null;
        }



        //获取当前登录用户所在组织
        TOrganization userOrg = user.getOrganization();
        if (userOrg == null) {
            return null;
        }

        //判断人员是否可以添加在该组织
        TOrganization memberOrg = organizationDAO.getOne(form.getOrgId());
        if (memberOrg == null) {
            return null;
        }
        if (!memberOrg.getCatalogIds().contains("," + userOrg.getId() + ",")) {
            return null;
        }

        //添加人员
        TMember member = new TMember();
        member.setOrganization(memberOrg);
        member.setRealName(form.getRealName());
        member.setGender(form.getGender());
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setPassword("0000");
        member.setStatus((short) 1);
        member.setRegTime(new Date());

        //添加成员所拥有的角色
        if (form.getRoleIds() == null || form.getRoleIds().size() == 0) {
            member.setRoles(new ArrayList<>());
        } else {
            List<TRole> roles = new ArrayList<>();
            for (Long roleId : form.getRoleIds()) {
                TRole role = roleDAO.getOne(roleId);
                if (role != null) {
                    roles.add(role);
                }
            }
        }

        return e2d(memberDAO.save(member));
    }

    @Override
    public PageInfo<MemberDTO> searchMembers(Integer pageSize, Integer pageNum, Long userId, String keyword,
                                             Long orgId) {

        if (pageSize == null || pageSize < 1 || pageNum == null || pageNum < 1 || userId == null || keyword == null) {
            return null;
        }

        Pageable pageable = new PageRequest(pageNum - 1, pageSize);
        List<Long> orgIds = new ArrayList<>();

        if (orgId == null) {
            List<TOrganization> organizations = organizationDAO.findOrgAndChildOrgsById(userId);
            for (TOrganization organization : organizations) {
                orgIds.add(organization.getId());
            }
        } else {
            orgIds.add(orgId);
        }

        return page2pageInfo(memberDAO.findByOrgIdsAndKeyword(orgIds, keyword, pageable));
    }

    @Override
    public MemberDTO update(MemberForm form, Long userId) {
        if (form == null || form.getId() == null || userId == null) {
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

        //获取需要修改的人员
        TMember member = memberDAO.getOne(form.getId());
        if (member == null) {
            return null;
        }

        //获取需要修改的人员所在组织
        TOrganization memberOrg = member.getOrganization();
        if (memberOrg == null) {
            return null;
        }

        //判断是否有权限修改用户，即判断组织从属关系
        if (!memberOrg.getCatalogIds().contains("," + userOrg.getId() + ",")) {
            return null;
        }

        //修改成员基本信息
        member.setRealName(form.getRealName());
        member.setGender(form.getGender());
        member.setPhone(form.getPhone());

        //修改成员所在组织
        if (form.getOrgId() != null) {
            TOrganization newOrg = organizationDAO.getOne(form.getOrgId());
            if (newOrg != null) {
                if (newOrg.getCatalogIds().contains("," + userOrg.getId() + ",")) {
                    member.setOrganization(newOrg);
                }
            }
        }

        //修改成员所拥有的角色
        if (form.getRoleIds() == null || form.getRoleIds().size() == 0) {
            member.setRoles(new ArrayList<>());
        } else {
            List<TRole> roles = new ArrayList<>();
            for (Long roleId : form.getRoleIds()) {
                TRole role = roleDAO.getOne(roleId);
                if (role != null) {
                    roles.add(role);
                }
            }
            member.setRoles(roles);
        }

        return e2d(memberDAO.save(member));
    }

    @Override
    public MemberDTO get(Long id, Long userId) {
        if (userId == null || id == null) {
            return null;
        }

        //获取当前登录用户
        TMember user = memberDAO.getOne(userId);
        if (user == null) {
            return null;
        }

        //获取当前登录用户所在组织
        TOrganization userOrg = user.getOrganization();
        if (userOrg == null) {
            return null;
        }

        //获取需要查看的人员
        TMember member = memberDAO.getOne(id);
        if (member == null) {
            return null;
        }

        //获取需要查看的人员所在组织
        TOrganization memberOrg = member.getOrganization();
        if (memberOrg == null) {
            return null;
        }

        //判断被修改成员的组织是否被包含在当前登录用户的下级组织中
        if (!memberOrg.getCatalogIds().contains("," + userOrg.getId() + ",")) {
            return null;
        }

        return e2d(member);
    }

    private PageInfo<MemberDTO> page2pageInfo(Page<TMember> p) {
        if (p == null) {
            return null;
        }
        PageInfo<MemberDTO> pageInfo = new PageInfo<>();
        Integer page = p.getTotalPages();
        Integer pageNum = p.getNumber();
        pageInfo.setTotal(p.getTotalElements());
        if (pageNum == 0) {
            pageInfo.setFirstPage(true);
        } else {
            pageInfo.setFirstPage(false);
        }
        if (pageNum.equals(page)) {
            pageInfo.setLastPage(true);
        } else {
            pageInfo.setLastPage(false);
        }
        pageInfo.setPageNum(p.getNumber());
        pageInfo.setTotal(p.getTotalElements());
        pageInfo.setPages(page);
        pageInfo.setList(e2d(p.getContent()));
        pageInfo.setPageSize(p.getSize());
        pageInfo.setSize(p.getContent().size());

        return pageInfo;
    }

    private MemberDTO e2d(TMember member) {

        if (member == null) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);

        //将organization对象转为orgId/orgName/orgShortName存入MemberDTO
        if (member.getOrganization() != null) {
            memberDTO.setOrgId(member.getOrganization().getId());
            memberDTO.setOrgShortName(member.getOrganization().getShortName());
            memberDTO.setOrgName(member.getOrganization().getName());
            memberDTO.setOrgType(member.getOrganization().getType());
        }

        //将role对象转为roleId/roleName存入MemberDTO
        List<Long> roleIds = new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        if (member.getRoles() != null && member.getRoles().size() != 0) {
            for (TRole role : member.getRoles()) {
                roleIds.add(role.getId());
                roleNames.add(role.getRoleName());
            }
        }
        memberDTO.setRoleIds(roleIds);
        memberDTO.setRoleNames(roleNames);

        return memberDTO;
    }

    private List<MemberDTO> e2d(List<TMember> members) {

        if (members == null || members.size() == 0) {
            return new ArrayList<>();
        }

        List<MemberDTO> memberDTOs = new ArrayList<>();

        for (TMember member : members) {
            if (member != null) {
                memberDTOs.add(e2d(member));
            }
        }

        return memberDTOs;
    }
}
