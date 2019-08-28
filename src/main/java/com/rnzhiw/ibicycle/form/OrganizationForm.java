package com.rnzhiw.ibicycle.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 组织添加、修改表单
 *
 * @author fuenhui
 * @date 2018/12/12
 */
public class OrganizationForm {

    /**
     * 主键
     */
    private Long id;

    /**
     * 详细地址id
     */
    private Long addressId;

    /**
     * 父组织id
     */
    private Long parentId;

    /**
     * 组织全称
     */
    @NotBlank(message = "组织全称不能为空")
    private String name;

    /**
     * 组织简称
     */
    private String shortName;

    /**
     * 省编号
     */
    @NotBlank(message = "省编号不能为空")
    private String provinceCode;

    /**
     * 市编号
     */
    @NotBlank(message = "市编号不能为空")
    private String cityCode;

    /**
     * 区编号
     */
    @NotBlank(message = "区编号不能为空")
    private String districtCode;

    /**
     * 街道、乡镇编号
     */
    private String townCode;

    /**
     * 详细地址描述
     */
    private String addressDetail;

    /**
     * GPS纬度
     */
    private Double gpsLat;

    /**
     * GPS经度
     */
    private Double gpsLng;

    /**
     * 组织类型
     */
    @NotNull(message = "组织类型不能为空")
    private Short type;

    /**
     * 组织状态
     */
    @NotNull(message = "组织状态不能为空")
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
