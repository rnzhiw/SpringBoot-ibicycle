package com.rnzhiw.ibicycle.model.dto;

/**
 * 资源DTO
 *
 * @author csy
 * @date 2018/11/24
 */
public class ResourceDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父资源id
     */
    private Long parentId;

    /**
     * 父资源名
     */
    private String parentName;

    /**
     * 资源名称
     */
    private String resName;

    /**
     * 资源键
     */
    private String resKey;

    /**
     * 资源类型
     */
    private String resType;

    /**
     * 目录路径
     */
    private String menuUrl;

    /**
     * 功能路径
     */
    private String funUrls;

    private Integer weight = 0;

    /**
     * 资源是否可用
     */
    private Boolean status = false;

    /**
     * 判断角色是否有该权限
     */
    private Boolean hasResource = false;


    public Boolean getHasResource() {
        return hasResource;
    }

    public void setHasResource(Boolean hasResource) {
        this.hasResource = hasResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFunUrls() {
        return funUrls;
    }

    public void setFunUrls(String funUrls) {
        this.funUrls = funUrls;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
}
