package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 报案实体类
 *
 * @author fuenhui
 * @date 2019/2/28
 */
@Entity
@Table(name = "t_case")
public class TCase {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 电动车车主
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private TMember owner;

    /**
     * 报案的电动车
     */
    @ManyToOne
    @JoinColumn(name = "bicycle_id")
    private TBicycle bicycle;

    /**
     * 报案描述
     */
    @Column(length = 255)
    private String description;

    /**
     * 报案时间
     */
    private Date time;

    /**
     * 报案状态
     *
     * -1.已在app上报案但公安部门未确认
     * 0.公安部门已确认报案，未找回
     * 1.已找回，结案
     */
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TMember getOwner() {
        return owner;
    }

    public void setOwner(TMember owner) {
        this.owner = owner;
    }

    public TBicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(TBicycle bicycle) {
        this.bicycle = bicycle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
