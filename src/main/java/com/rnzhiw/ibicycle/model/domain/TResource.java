package com.rnzhiw.ibicycle.model.domain;

import com.rnzhiw.ibicycle.enums.ResourceType;

import javax.persistence.*;

/**
 * 资源实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_resource")
public class TResource {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 父资源
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TResource parent;

    /**
     * 资源名称
     */
    @Column(length = 128, nullable = false, unique = true)
    private String resName;

    /**
     * 资源键
     */
    @Column(length = 128, nullable = false, unique = true)
    private String resKey;

    /**
     * 资源类型
     */
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceType resType;

    /**
     * 目录路径
     */
    @Column(length = 128)
    private String menuUrl;

    /**
     * 功能路径
     */
    @Column(length = 1024)
    private String funUrls;

    private Integer weight = 0;

    /**
     * 资源是否可用
     */
    @Column(nullable = false)
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TResource getParent() {
        return parent;
    }

    public void setParent(TResource parent) {
        this.parent = parent;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public ResourceType getResType() {
        return resType;
    }

    public void setResType(ResourceType resType) {
        this.resType = resType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFunUrls() {
        return funUrls;
    }

    public void setFunUrls(String funUrls) {
        this.funUrls = funUrls;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
