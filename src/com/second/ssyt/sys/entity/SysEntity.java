package com.second.ssyt.sys.entity;

import java.util.Date;

/**
 * 学生实体
 * @author lenovo
 *
 */
public class SysEntity {
	private int id;
	private String code;
	private String password;
	private String name;
	private int sex;
	private String phone;
	private String email;
	private Date create_time;
	private int operate_user_id;
	private Date operate_time;
	private int state;
	private String memo; 
	private int role_id;
	private int class_id;
	
	//扩展属性
	private String rolename;
	private String classname;
	
	
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSexs() {
		if (sex == 1) {
			return "男";
		} else if (sex == 2) {
			return "女";
		} else  {
			return "Unknow";
		}
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getOperate_user_id() {
		return operate_user_id;
	}
	public void setOperate_user_id(int operate_user_id) {
		this.operate_user_id = operate_user_id;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_ids() {
		if (role_id == 1) {
			return "管理员";
		} else if (role_id == 2) {
			return "老师";
		} else  {
			return "学生";
		}
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStates() {
		if (state == 1) {
			return "已启用";
		} else if (state == 2) {
			return "已禁用";
		} else  {
			return "Unknow";
		}
	}
	public String getStatess() {
		if (state == 1) {
			return "禁用";
		} else if (state == 2) {
			return "启用";
		} else  {
			return "Unknow";
		}
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
    
	
}
