package com.second.ssyt.question.entity;

import java.sql.Date;


/**
 * SysCourse entity. @author MyEclipse Persistence Tools
 */

public class SysCourseEntity  {

	// Fields

	private int id;
	private int pid;
	private Short isLeaf;
	private String name;
	private int operateUserId;
	private Date operateTime;
	private Short state;
	private String memo;

	// Constructors

	/** default constructor */
	public SysCourseEntity() {
	}

	/** minimal constructor */
	public SysCourseEntity(int pid, Short isLeaf, String name,
			int operateUserId, Date operateTime, Short state) {
		this.pid = pid;
		this.isLeaf = isLeaf;
		this.name = name;
		this.operateUserId = operateUserId;
		this.operateTime = operateTime;
		this.state = state;
	}

	/** full constructor */
	public SysCourseEntity(int pid, Short isLeaf, String name,
			int operateUserId, Date operateTime, Short state,
			String memo) {
		this.pid = pid;
		this.isLeaf = isLeaf;
		this.name = name;
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