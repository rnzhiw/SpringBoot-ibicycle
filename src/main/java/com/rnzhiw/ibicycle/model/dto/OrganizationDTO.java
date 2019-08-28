package com.rnzhiw.ibicycle.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 组织DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class OrganizationDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父组织id
     */
    private Long parentId;

    /**
     * 父组织名称
     */
    private String parentName;

    /**
     * 组织目录
     */
    private String catalogIds;

    /**
     * 组织全称
     */
    private String name;

    /**
     * 组织简称
     */
    private String shortName;

    /**
     * 组织创建者id
     */
    private Long creatorId;

    /**
     * 组织创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 组织联系方式id
     */
    private Long contactId;

    /**
     * 所在区域id
     */
    private Long areaId;

    /**
     * 所在区域编码
     */
    private String areaCode;

    /**
     * 所属街道id
     */
    private Long areaTownId;

    /**
     * 所在街道编码
     */
    private String areaTownCode;

    /**
     * 详细地址id
     */
    private Long addressId;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * gps经度
     */
    private Double gpsLng;

    /**
     * gps纬度
     */
    private Double gpsLat;

    /**
     * 组织类型
     */
    private Short type;

    /**
     * 组织类型描述
     */
    private String typeDesc;

    /**
     * 组织状态
     * <p>
     * -1.删除
     * 0.待审核
     * 1.正常
     */
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCatalogIds() {
        return catalogIds;
    }

    public void setCatalogIds(String catalogIds) {
        this.catalogIds = catalogIds;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getAreaTownId() {
        return areaTownId;
    }

    public void setAreaTownId(Long areaTownId) {
        this.areaTownId = areaTownId;
    }

    public String getAreaTownCode() {
        return areaTownCode;
    }

    public void setAreaTownCode(String areaTownCode) {
        this.areaTownCode = areaTownCode;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", catalogIds='" + catalogIds + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", contactId=" + contactId +
                ", areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", areaTownId=" + areaTownId +
                ", areaTownCode='" + areaTownCode + '\'' +
                ", addressId=" + addressId +
                ", addressDetail='" + addressDetail + '\'' +
                ", gpsLng=" + gpsLng +
                ", gpsLat=" + gpsLat +
                ", type=" + type +
                ", typeDesc='" + typeDesc + '\'' +
                ", status=" + status +
                '}';
    }
}
