package com.rnzhiw.ibicycle.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资源添加、修改表单
 *
 * @author fuenhui
 * @date 2018/12/12
 */
public class ResourceForm {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父资源id
     */
    private Long parentId;

    /**
     * 资源名称
     */
    @NotBlank(message = "资源名称不能为空")
    private String resName;

    /**
     * 资源标识
     */
    @NotBlank(message = "资源标识不能为空")
    private String resKey;

    /**
     * 资源类型
     */
    @NotBlank(message = "资源类型不能为空")
    private String resType;

    /**
     * 菜单路径
     */
    @NotNull(message = "菜单路径不能为空")
    private String menuUrl;

    /**
     * 功能路径
     */
    @NotNull(message = "功能路径不能为空")
    private String funUrls;

    /**
     * 权重
     */
    @NotNull(message = "权重不能为空")
    private Integer weight = 0;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Boolean status;

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
}
