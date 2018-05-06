package com.second.ssyt.PublicEntity;

import java.sql.Date;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */


public class SysUserEntity  {

	// Fields

	private int id;
	private String code;
	private String password;
	private String name;
	private Short sex;
	private String phone;
	private String email;
	private Date createTime;
	private int operateUserId;
	private Date operateTime;
	private int roleId;
	private int classId;
	private Short state;
	private String memo;
   private String rolename;
	// Constructors

	public String getRolename() {
	return rolename;
}

public void setRolename(String rolename) {
	this.rolename = rolename;
}

	/** default constructor */
	public SysUserEntity() {
	}

	/** minimal constructor */
	public SysUserEntity(String code, String password, String name, Short sex,
			String phone, String email, Date createTime,
			int operateUserId, Date operateTime, int roleId,
			Short state) {
		this.code = code;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.createTime = createTime;
		this.operateUserId = operateUserId;
		this.operateTime = operateTime;
		this.roleId = roleId;
		this.state = state;
	}

	/** full constructor */
	public SysUserEntity(String code, String password, String name, Short sex,
			String phone, String email, Date createTime,
			int operateUserId, Date operateTime, int roleId,
			int classId, Short state, String memo) {
		this.code = code;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.createTime = createTime;
		this.operateUserId = operateUserId;
		this.operateTime = operateTime;
		this.roleId = roleId;
		this.classId = classId;
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

	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Short getSex() {
		return this.sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
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