package com.rnzhiw.ibicycle.model.domain;

import com.rnzhiw.ibicycle.service.MemberService;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * 组织实体类
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Entity
@Table(name = "t_organization")
public class TOrganization {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 父组织
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TOrganization parent;

    /**
     * 组织目录
     */
    private String catalogIds;

    /**
     * 组织全称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 组织简称
     */
    @Column(length = 20)
    private String shortName;

    /**
     * 组织创建者
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private TMember creator;

    /**
     * 组织创建时间
     */
    private Date createTime;

    /**
     * 组织联系方式
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contact_id")
    private TContact contact;

    /**
     * 所在区域
     */
    @ManyToOne
    @JoinColumn(name = "area_id")
    private TArea area;

    /**
     * 所属街道
     */
    @ManyToOne
    @JoinColumn(name = "area_town_id")
    private TAreaTown areaTown;

    /**
     * 详细地址
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private TAddress address;

    /**
     * 组织类型
     */
    private Short type;

    /**
     * 组织状态
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

    public TOrganization getParent() {
        return parent;
    }

    public void setParent(TOrganization parent) {
        this.parent = parent;
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

    public TMember getCreator() {
        return creator;
    }

    public void setCreator(TMember creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TContact getContact() {
        return contact;
    }

    public void setContact(TContact contact) {
        this.contact = contact;
    }

    public TArea getArea() {
        return area;
    }

    public void setArea(TArea area) {
        this.area = area;
    }

    public TAreaTown getAreaTown() {
        return areaTown;
    }

    public void setAreaTown(TAreaTown areaTown) {
        this.areaTown = areaTown;
    }

    public TAddress getAddress() {
        return address;
    }

    public void setAddress(TAddress address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "TOrganization{" +
                "id=" + id +
                ", parent=" + parent +
                ", catalogIds='" + catalogIds + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", contact=" + contact +
                ", area=" + area +
                ", areaTown=" + areaTown +
                ", address=" + address +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
