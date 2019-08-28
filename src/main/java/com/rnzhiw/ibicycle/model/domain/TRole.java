package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;
import java.util.List;

/**
 * 角色实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_role")
public class TRole {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 可以访问的资源
     */
    @ManyToMany
    @JoinTable(name = "t_role_resource", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns =
            {@JoinColumn(name = "resource_id")})
    private List<TResource> resource;

    /**
     * 角色名
     */
    @Column(length = 30, unique = true, nullable = false)
    private String roleName;

    /**
     * 角色描述
     */
    @Column(length = 512)
    private String description;

    /**
     * 角色是否可用
     */
    @Column(nullable = false)
    private Boolean status;

    /**
     * 角色对应的组织类型，详见OrganizationService
     */
    @Column(nullable = false)
    private Short orgType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TResource> getResource() {
        return resource;
    }

    public void setResource(List<TResource> resource) {
        this.resource = resource;
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
