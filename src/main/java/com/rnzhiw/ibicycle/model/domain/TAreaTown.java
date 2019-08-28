package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;

/**
 * 街道实体类
 *
 * @author fuenhui
 * @date 2018/11/16
 */
@Entity
@Table(name = "t_area_town")
public class TAreaTown {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 父级区域编码
     */
    @Column(length = 10)
    private String parentCode;

    /**
     * 街道编码
     */
    @Column(length = 20)
    private String code;

    /**
     * 街道名称
     */
    @Column(length = 50)
    private String name;

    /**
     * 是否可用
     */
    @Column(nullable = false)
    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
