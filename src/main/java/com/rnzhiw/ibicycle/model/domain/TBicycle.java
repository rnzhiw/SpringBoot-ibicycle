package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 电动车实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_bicycle")
public class TBicycle {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 品牌
     */
    @Column(nullable = false)
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 车架号
     */
    @Column(nullable = false)
    private String vin;

    /**
     * 电机号
     */
    @Column(nullable = false)
    private String motorNumber;

    /**
     * 牌照号
     */
    private String licenseNumber;

    /**
     * 颜色
     */
    @Column(nullable = false)
    private String color;

    /**
     * 电子标签号
     */
    private String rfid;

    /**
     * 购买时间
     */
    @Column(nullable = false)
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
     * 电动车图片路径
     */
    private String imgUrl;

    /**
     * 所有者
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private TMember owner;

    /**
     * 安全防盗器终端sn编号
     */
    private String deviceSn;

    /**
     * 安装点（备案登记点）
     */
    @ManyToOne
    @JoinColumn(name = "install_org_id")
    private TOrganization installOrg;

    /**
     * 状态
     * <p>
     * -2.已预约，但未安装
     * -1.离线
     * 0.挂失
     * 1.正常
     */
    @Column(nullable = false)
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public TMember getOwner() {
        return owner;
    }

    public void setOwner(TMember owner) {
        this.owner = owner;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public TOrganization getInstallOrg() {
        return installOrg;
    }

    public void setInstallOrg(TOrganization installOrg) {
        this.installOrg = installOrg;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
