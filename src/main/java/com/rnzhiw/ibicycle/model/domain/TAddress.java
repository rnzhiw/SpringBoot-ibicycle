package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;

/**
 * 详细地址实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_address")
public class TAddress {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * GPS纬度
     */
    @Column(precision = 22, scale = 0)
    private Double gpsLat;

    /**
     * GPS经度
     */
    @Column(precision = 22, scale = 0)
    private Double gpsLng;

    /**
     * 详细描述
     */
    @Column(length = 500)
    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Double getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(Double gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
