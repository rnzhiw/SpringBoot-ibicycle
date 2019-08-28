package com.rnzhiw.ibicycle.model.dto;

import java.util.Date;

/**
 * 数据DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class DataDTO {

    /**
     * 终端设备sn
     */
    private String deviceSn;

    /**
     * 数据项
     */
    private String code;

    /**
     * 数据值
     */
    private Double value;

    /**
     * 接收时间
     */
    private Date receiveTime;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
