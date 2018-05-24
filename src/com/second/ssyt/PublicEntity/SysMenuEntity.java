package com.second.ssyt.PublicEntity;

import java.sql.Date;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenuEntity {

    // Fields

    private int id;
    private int pid;
    private Short isLeaf;
    private String name;
    private String url;
    private Short sort;
    private int opreateUserId;
    private Date operateTime;
    private Short state;
    private String memo;

    // Constructors

    /**
     * default constructor
     */
    public SysMenuEntity() {
    }

    /**
     * minimal constructor
     */
    public SysMenuEntity(int id, int pid, Short isLeaf, String name,
                         Short sort, int opreateUserId, Date operateTime,
                         Short state) {
        this.id = id;
        this.pid = pid;
        this.isLeaf = isLeaf;
        this.name = name;
        this.sort = sort;
        this.opreateUserId = opreateUserId;
        this.operateTime = operateTime;
        this.state = state;
    }

    /**
     * full constructor
     */
    public SysMenuEntity(int id, int pid, Short isLeaf, String name,
                         String url, Short sort, int opreateUserId,
                         Date operateTime, Short state, String memo) {
        this.id = id;
        this.pid = pid;
        this.isLeaf = isLeaf;
        this.name = name;
        this.url = url;
        this.sort = sort;
        this.opreateUserId = opreateUserId;
        this.operateTime = operateTime;
        this.state = state;
        this.memo = memo;
    }

    // Property accessors


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public Short getIsLeaf() {
        return this.isLeaf;
    }

    public void setIsLeaf(Short isLeaf) {
        this.isLeaf = isLeaf;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Short getSort() {
        return this.sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }


    public int getOpreateUserId() {
        return this.opreateUserId;
    }

    public void setOpreateUserId(int opreateUserId) {
        this.opreateUserId = opreateUserId;
    }


    public Date getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }


    public Short getState() {
        return this.state;
    }

    public void setState(Short state) {
        this.state = state;
    }


    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}