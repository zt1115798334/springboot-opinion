package com.zt.opinion.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zt.opinion.base.entity.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>Title: MonitorEnterprise</p>
 * <p>Description: 监控企业信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月20日
 */
@Entity
@Table(name = "t_monitor_enterprise")
public class MonitorEnterprise extends BasePageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_name", unique = false, nullable = false)
    private String enterpriseName;    // 企业名称

    @Column(name = "expire_date", unique = false, nullable = false)
    private Date expireDate;    //到期时间，默认值：创建时间+3个月

    @Column(name = "status", unique = false, nullable = false)
    private String status;    //状态：监控中，已到期

    @Column(name = "create_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;    //创建时间

    @Column(name = "create_user", unique = false, nullable = false)
    private String createUser;    //创建用户

    @Column(name = "update_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;    //更新时间

    @Column(name = "update_user", unique = false, nullable = false)
    private String updateUser;    //更新用户

    @Column(name = "user_account", unique = false, nullable = false)
    private String userAccount;    //关联t_userbase表的user_account

    @Transient
    private Integer groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
