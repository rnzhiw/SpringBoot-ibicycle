package com.rnzhiw.ibicycle.model.dto;

import java.util.List;

/**
 * 角色DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class RoleDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 可以访问的资源id
     */
    private List<Long> resourceId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色是否可用
     */
    private Boolean status;

    /**
     * 角色对应的组织类型
     */
    private Short orgType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getResourceId() {
        return resourceId;
    }

    public void setResourceId(List<Long> resourceId) {
        this.resourceId = resourceId;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Short getOrgType() {
        return orgType;
    }

    public void setOrgType(Short orgType) {
        this.orgType = orgType;
    }
}
