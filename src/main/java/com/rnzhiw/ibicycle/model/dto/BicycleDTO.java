package com.rnzhiw.ibicycle.model.dto;

import java.util.Date;

/**
 * 电动车DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class BicycleDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 电机号
     */
    private String motorNumber;

    /**
     * 牌照号
     */
    private String licenseNumber;

    /**
     * 颜色
     */
    private String color;

    /**
     * 电子标签号
     */
    private String rfid;

    /**
     * 购买时间
     */
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
     * 安装点名称
     */
    private String installOrgName;

    /**
     * 电动车图片路径
     */
    private String imgUrl;

    /**
     * 所有真实姓名
     */
    private String ownerName;

    /**
     * 车主手机号
     */
    private String ownerPhone;

    /**
     * 状态
     * <p>
     * -2.已预约，但未安装
     * -1.离线
     * 0.挂失
     * 1.正常
     */
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getInstallOrgId() {
        return installOrgId;
    }

    public void setInstallOrgId(Long installOrgId) {
        this.installOrgId = installOrgId;
    }

    public String getInstallOrgName() {
        return installOrgName;
    }

    public void setInstallOrgName(String installOrgName) {
        this.installOrgName = installOrgName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
