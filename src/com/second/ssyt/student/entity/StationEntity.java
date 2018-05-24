package com.second.ssyt.student.entity;

import java.sql.Date;

public class StationEntity {

    // Fields

    private int id;
    private String title;
    private String content;
    private Date sendTime;
    private int senderId;
    private String receiverUserIds;
    private int receiverClassIds;
    private Date expireTime;
    private Short state;

    // Constructors
    private String rName;
    private String uName;

    /**
     * default constructor
     */
    public StationEntity() {
    }

    /**
     * minimal constructor
     */
    public StationEntity(String title, String content, Date sendTime,
                         int senderId, Date expireTime, Short state) {
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.senderId = senderId;
        this.expireTime = expireTime;
        this.state = state;
    }

    /**
     * full constructor
     */
    public StationEntity(String title, String content, Date sendTime,
                         int senderId, String receiverUserIds, int receiverClassIds,
                         Date expireTime, Short state) {
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.senderId = senderId;
        this.receiverUserIds = receiverUserIds;
        this.receiverClassIds = receiverClassIds;
        this.expireTime = expireTime;
        this.state = state;
    }

    // Property accessors


    public int getId() {
        return this.id;
    }


    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getSenderId() {
        return this.senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getReceiverUserIds() {
        return this.receiverUserIds;
    }

    public void setReceiverUserIds(String receiverUserIds) {
        this.receiverUserIds = receiverUserIds;
    }

    public int getReceiverClassIds() {
        return this.receiverClassIds;
    }

    public void setReceiverClassIds(int receiverClassIds) {
        this.receiverClassIds = receiverClassIds;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Short getState() {
        return this.state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}