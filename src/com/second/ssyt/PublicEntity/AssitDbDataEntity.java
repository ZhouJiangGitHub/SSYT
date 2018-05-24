package com.second.ssyt.PublicEntity;

import java.sql.Date;

public class AssitDbDataEntity {

    // Fields

    private int id;
    private String bakUrl;
    private Date bakTime;
    private String bakMemo;
    private Date restoreTime;
    private String restoreMemo;
    private int operateUserId;
    private Short isNewData;

    // Constructors

    /**
     * default constructor
     */
    public AssitDbDataEntity() {
    }

    /**
     * minimal constructor
     */
    public AssitDbDataEntity(String bakUrl, Date bakTime, String bakMemo,
                             int operateUserId, Short isNewData) {
        this.bakUrl = bakUrl;
        this.bakTime = bakTime;
        this.bakMemo = bakMemo;
        this.operateUserId = operateUserId;
        this.isNewData = isNewData;
    }

    /**
     * full constructor
     */
    public AssitDbDataEntity(String bakUrl, Date bakTime, String bakMemo,
                             Date restoreTime, String restoreMemo, int operateUserId,
                             Short isNewData) {
        this.bakUrl = bakUrl;
        this.bakTime = bakTime;
        this.bakMemo = bakMemo;
        this.restoreTime = restoreTime;
        this.restoreMemo = restoreMemo;
        this.operateUserId = operateUserId;
        this.isNewData = isNewData;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBakUrl() {
        return this.bakUrl;
    }

    public void setBakUrl(String bakUrl) {
        this.bakUrl = bakUrl;
    }

    public Date getBakTime() {
        return this.bakTime;
    }

    public void setBakTime(Date bakTime) {
        this.bakTime = bakTime;
    }

    public String getBakMemo() {
        return this.bakMemo;
    }

    public void setBakMemo(String bakMemo) {
        this.bakMemo = bakMemo;
    }

    public Date getRestoreTime() {
        return this.restoreTime;
    }

    public void setRestoreTime(Date restoreTime) {
        this.restoreTime = restoreTime;
    }

    public String getRestoreMemo() {
        return this.restoreMemo;
    }

    public void setRestoreMemo(String restoreMemo) {
        this.restoreMemo = restoreMemo;
    }

    public int getOperateUserId() {
        return this.operateUserId;
    }

    public void setOperateUserId(int operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Short getIsNewData() {
        return this.isNewData;
    }

    public void setIsNewData(Short isNewData) {
        this.isNewData = isNewData;
    }

}