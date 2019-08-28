package com.rnzhiw.ibicycle.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 电动车定位DTO
 *
 * @author fuenhui
 * @date 2019/1/6
 */
public class LocationDTO {

    /**
     * 电动车的id
     */
    private Long bicycleId;

    /**
     * 安装的设备序列号
     */
    private String deviceSn;

    /**
     * 所在经度
     */
    private Double gpsLng;

    /**
     * 所在纬度
     */
    private Double gpsLat;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public Long getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Double getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(Double gpsLng) {
        this.gpsLng = gpsLng;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "bicycleId=" + bicycleId +
                ", deviceSn='" + deviceSn + '\'' +
                ", gpsLng=" + gpsLng +
                ", gpsLat=" + gpsLat +
                ", time=" + time +
                '}';
    }
}
