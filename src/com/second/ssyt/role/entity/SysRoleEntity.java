package com.second.ssyt.role.entity;

import java.sql.Date;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */


public class SysRoleEntity  {

	// Fields

	private int id;
	private String name;
	private String menuIds;
	private int operateUserId;
	private Date operateTime;
	private Short state;
	private String memo;
   
	private String sysUser$Name;
	// Property accessors

	
	
	public int getId() {
		return this.id;
	}




	public String getSysUser$Name() {
		return sysUser$Name;
	}




	public void setSysUser$Name(String sysUser$Name) {
		this.sysUser$Name = sysUser$Name;
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
    public String getOperateUserIdStr(){
    	if(operateUserId == 1){
    		return "admin";
    	}else if(operateUserId == 2){
    		return "张三";
    	}else{
    		return "Runs";
    	}
    }

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getStateStr() {
		if (state == 2) {
			return "禁用";
		} else if(state == 1) {
			return "启用";
		}else{
			return "Unknown";
		}
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

	@Override
	public String toString() {
		return "SysRoleEntity [id=" + id + ", name=" + name + ", menuIds="
				+ menuIds + ", operateUserId=" + operateUserId
				+ ", operateTime=" + operateTime + ", state=" + state
				+ ", memo=" + memo + "]";
	}

}