package com.rnzhiw.ibicycle.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class RoleForm {


    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
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
