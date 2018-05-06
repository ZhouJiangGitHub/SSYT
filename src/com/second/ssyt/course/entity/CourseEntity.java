package com.second.ssyt.course.entity;

import java.util.Date;

public class CourseEntity {
	
	private int id;
	private int pId;
	private int isLeaf;
	private String name;
	private int operateUserId;
	private Date operateTime;
	private int state;
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPId() {
		return pId;
	}
	public void setPId(int pId) {
		this.pId = pId;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
	
}
