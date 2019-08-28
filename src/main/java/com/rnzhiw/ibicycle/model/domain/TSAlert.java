package com.rnzhiw.ibicycle.model.domain;

import java.util.Date;

/**
 * 报警信息历史纪录
 *
 * @author fuenhui
 * @date 2018/11/22
 */
public class TSAlert {

    /**
     * 终端设备sn
     */
    private String deviceSn;

    /**
     * 报警类型
     *
     * 1.离线报警
     * 2.数据项报警
     */
    private Short type;

    /**
     * 数据项
     */
    private String code;

    /**
     * 数据项的值
     */
    private Double value;

    /**
     * 报警描述
     */
    private String discription;

    /**
     * 报警时间
     */
    private Date alertTime;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }
}
