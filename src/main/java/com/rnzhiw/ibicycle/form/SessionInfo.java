package com.rnzhiw.ibicycle.form;


import com.rnzhiw.ibicycle.model.domain.TResource;

import java.util.List;
import java.util.Set;

/**
 * session存放的信息
 *
 * @author fuenhui
 * @date 2018/11/29
 */
public class SessionInfo {

    /**
     * 所有可访问的菜单
     */
    private List<TResource> menus;

    /**
     * 所有可访问资源标识
     */
    private Set<String> resourceKey;

    /**
     * 所有可访问的资源路径
     */
    private Set<String> urls;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录用户对应的组织id
     */
    private Long orgId;

    /**
     * 所有对应的角色名称
     */
    private List<String> roleNames;

    /**
     * 是否为超级管理员
     */
    private boolean superUser;

    /**
     * 图形验证码
     */
    private String verifyCode;

    public List<TResource> getMenus() {
        return menus;
    }

    public void setMenus(List<TResource> menus) {
        this.menus = menus;
    }

    public Set<String> getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(Set<String> resourceKey) {
        this.resourceKey = resourceKey;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
