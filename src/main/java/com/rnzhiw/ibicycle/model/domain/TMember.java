package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_member")
public class TMember {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 所属组织
     */
    @ManyToOne
    @JoinColumn(name = "org_id")
    private TOrganization organization;

    /**
     * 手机号
     */
    @Column(length = 11, nullable = false)
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    @Column(length = 50)
    private String realName;

    /**
     * 身份证号
     */
    @Column(length = 50)
    private String idCard;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 性别
     * <p>
     * 1.男性
     * 2.女性
     */
    private Short gender;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * token
     */
    private String token;

    /**
     * 用户对应的角色
     */
    @ManyToMany
    @JoinTable(name = "t_member_role", joinColumns = {@JoinColumn(name = "member_id")}, inverseJoinColumns =
    {@JoinColumn(name = "role_id")})
    private List<TRole> roles;

    /**
     * 帐号状态
     * <p>
     * -1.删除
     * 0.待审核
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

    public TOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(TOrganization organization) {
        this.organization = organization;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<TRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TRole> roles) {
        this.roles = roles;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
