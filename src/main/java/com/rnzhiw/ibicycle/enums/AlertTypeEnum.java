package com.rnzhiw.ibicycle.enums;

/**
 * 报警类型枚举
 *
 */
public enum  AlertTypeEnum {

    /**
     * 设备离线或上线报警
     */
    STATUS((short) 1,"离线报警"),

    /**
     * 位置移动、电流过载等设备数据项报警
     */
    DEVICE((short) 2,"设备报警");

    private short type;

    private String alertType;

    AlertTypeEnum(short type,String alertType){
        this.type = type;
        this.alertType = alertType;
    }


    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}
