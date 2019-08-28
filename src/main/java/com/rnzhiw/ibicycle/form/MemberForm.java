package com.rnzhiw.ibicycle.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Member的个人信息修改表单
 * @author hjb
 * @date 2018/12/7
 */
public class MemberForm {

    /**
     * 主键
     */
    private Long id;

    /**
     * 真实姓名
     */
    @NotBlank(message = "姓名不得为空")
    private String realName;

    /**
     * 性别
     */
    @NotNull(message = "性别不得为空")
    private Short gender;

    /**
     * 联系方式
     */
    @NotBlank(message = "手机号不得为空")
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 组织id
     */
    @NotNull(message = "用户组织不能为空")
    private Long orgId;

    /**
     * 角色id集合
     */
    private List<Long> roleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
