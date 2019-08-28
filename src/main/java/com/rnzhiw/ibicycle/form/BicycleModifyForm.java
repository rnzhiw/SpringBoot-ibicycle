package com.rnzhiw.ibicycle.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 电动车属性表单
 *
 * @author fuenhui
 * @date 2018/11/29
 */
public class BicycleModifyForm {

    /**
     * 主键
     */
    private Long id;

    /**
     * 品牌
     */
    @NotBlank
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 车架号
     */
    @NotBlank
    private String vin;

    /**
     * 电机号
     */
    @NotBlank
    private String motorNumber;

    /**
     * 牌照号
     */
    private String licenseNumber;

    /**
     * 颜色
     */
    @NotBlank
    private String color;

    /**
     * 电子标签号
     */
    private String rfid;

    /**
     * 购买时间
     */
    @NotNull
    private Date buyTime;

    /**
     * 购买价格
     */
    private Float buyPrice;

    /**
     * 购买地址
     */
    private String buyAddress;

    /**
     * 安装时间
     */
    private Date installTime;

    /**
     * 所有者id
     */
    @NotNull
    private Long ownerId;

    /**
     * 安全防盗器终端sn编号
     */
    private String deviceSn;

    /**
     * 安装点Id
     */
    private Long installOrgId;

    /**
     * 状态
     * <p>
     * -1.已预约，但未安装
     * 0.正常状态
     * 1.挂失状态
     * 2.离线状态
     */
    @NotNull
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber = motorNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }


    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getInstallOrgId() {
        return installOrgId;
    }

    public void setInstallOrgId(Long installOrgId) {
        this.installOrgId = installOrgId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
