package com.second.ssyt.PublicEntity;

import java.sql.Date;


/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */


public class SysRoleEntity {

    // Fields

    private int id;
    private String name;
    private String menuIds;
    private int operateUserId;
    private Date operateTime;
    private Short state;
    private String memo;

    // Constructors

    /**
     * default constructor
     */
    public SysRoleEntity() {
    }

    /**
     * minimal constructor
     */
    public SysRoleEntity(String name, int operateUserId, Date operateTime,
                         Short state) {
        this.name = name;
        this.operateUserId = operateUserId;
        this.operateTime = operateTime;
        this.state = state;
    }

    /**
     * full constructor
     */
    public SysRoleEntity(String name, String menuIds, int operateUserId,
                         Date operateTime, Short state, String memo) {
        this.name = name;
        this.menuIds = menuIds;
        this.operateUserId = operateUserId;
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


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMenuIds() {
        return this.menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }


    public int getOperateUserId() {
        return this.operateUserId;
    }

    public void setOperateUserId(int operateUserId) {
        this.operateUserId = operateUserId;
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