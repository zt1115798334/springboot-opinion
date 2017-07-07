package com.zt.opinion.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Title: Group</p>
 * <p>Description: 企业分组信息entity</p>
 *
 * @author zhangtong
 * @date 2017年6月20日
 */
@Entity
@Table(name = "t_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "user_account", unique = false, nullable = false)
    private String userAccount;    //用户账号，关联t_userbase表的user_account

    @Column(name = "group_name", unique = false, nullable = false)
    private String groupName;    //组名称

    @Column(name = "create_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;    //创建时间

    @Column(name = "create_user", unique = false, nullable = false)
    private String createUser;    //创建用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
