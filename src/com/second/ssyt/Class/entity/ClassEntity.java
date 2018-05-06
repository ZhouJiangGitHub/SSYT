package com.second.ssyt.Class.entity;

public class ClassEntity {
   private int id;
   private String name;
   private int course_id;
   private int operate_user_id ;
   private String courseName;
	private String userName;
   public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
private  String  operate_time ;
   private  int State ;
   private  String  memo;
   public ClassEntity() {
		super();		
	}
   
public ClassEntity(int id, String name, int course_id, int operate_user_id,
		String operate_time, int state, String memo) {
	super();
	this.id = id;
	this.name = name;
	this.course_id = course_id;
	this.operate_user_id = operate_user_id;
	this.operate_time = operate_time;
	State = state;
	this.memo = memo;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public  String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getCourse_id() {
	return course_id;
}
public void setCourse_id(int course_id) {
	this.course_id = course_id;
}
public int getOperate_user_id() {
	return operate_user_id;
}
public void setOperate_user_id(int operate_user_id) {
	this.operate_user_id = operate_user_id;
}
public String getOperate_time() {
	return operate_time;
}
public void setOperate_time(String operate_time) {
	this.operate_time = operate_time;
}
public int getState() {
	return State;
}
public void setState(int state) {
	State = state;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
}