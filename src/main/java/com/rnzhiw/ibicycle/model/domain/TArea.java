package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 行政区域实体类
 *
 * @author fuenhui
 * @date 2018/11/16
 */
@Entity
@Table(name = "t_area")
public class TArea {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 区域名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 区域编码
     */
    @Column(nullable = false, length = 50, unique = true)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
