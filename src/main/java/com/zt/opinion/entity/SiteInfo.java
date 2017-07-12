package com.zt.opinion.entity;

import javax.persistence.*;

/**
 * Created by zhangtong on 2017/7/11.
 */
@Entity
@Table(name = "site_20170629175435")
public class SiteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "sitename", unique = false, nullable = true)
    private String sitename;

    @Column(name = "sitenames", unique = false, nullable = true)
    private String sitenames;

    @Column(name = "siteurl", unique = false, nullable = true)
    private String siteurl;

    @Column(name = "sitetype", unique = false, nullable = true)
    private String sitetype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSitenames() {
        return sitenames;
    }

    public void setSitenames(String sitenames) {
        this.sitenames = sitenames;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getSitetype() {
        return sitetype;
    }

    public void setSitetype(String sitetype) {
        this.sitetype = sitetype;
    }
}
