package com.second.ssyt.login.entity;

import java.util.Date;

public class UserEntity {

    private int id;
    private String code;
    private String password;
    private String name;
    private int sex;
    private String phone;
    private String email;
    private Date createTime;
    private int operateUserId;
    private Date operateTime;
    private int roleId;
    private int classId;
    private int state;

    // �������
    private String class$name;
    private int class$state;
    private String role$name;
    private int role$state;


    public String getClass$name() {
        return class$name;
    }

    public void setClass$name(String class$name) {
        this.class$name = class$name;
    }

    public String getRole$name() {
        return role$name;
    }

    public void setRole$name(String role$name) {
        this.role$name = role$name;
    }

    public int getClass$state() {
        return class$state;
    }

    public void setClass$state(int class$state) {
        this.class$state = class$state;
    }

    public int getRole$state() {
        return role$state;
    }

    public void setRole$state(int role$state) {
        this.role$state = role$state;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(int operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String memo;

}
