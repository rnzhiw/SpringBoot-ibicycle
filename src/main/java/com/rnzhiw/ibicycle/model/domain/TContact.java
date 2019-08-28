package com.rnzhiw.ibicycle.model.domain;

import javax.persistence.*;

/**
 * 联系方式实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_contact")
public class TContact {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 固定电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 微信公众平台
     */
    private String wechatMp;

    /**
     * 阿里旺旺
     */
    private String wangwang;

    /**
     * 微博
     */
    private String weibo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechatMp() {
        return wechatMp;
    }

    public void setWechatMp(String wechatMp) {
        this.wechatMp = wechatMp;
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
}
