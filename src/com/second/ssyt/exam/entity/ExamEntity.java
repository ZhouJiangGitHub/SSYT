package com.second.ssyt.exam.entity;

public class ExamEntity {
    
  private  int  id;                   
  private  int  user_id;             
  private  int exam_plan_id;         
  private  int get_point;            
  private  String  submit_time;        
  private  String  start_time;          
  private  int    is_pass;
  
  //扩展属性
 private  String  name; 
 private  String  qes_exam_paper_name;
 private  String  sys_class_name;
 private  int   total_point;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getExam_plan_id() {
	return exam_plan_id;
}
public void setExam_plan_id(int exam_plan_id) {
	this.exam_plan_id = exam_plan_id;
}
public int getGet_point() {
	return get_point;
}
public void setGet_point(int get_point) {
	this.get_point = get_point;
}
public String getSubmit_time() {
	return submit_time;
}
public void setSubmit_time(String submit_time) {
	this.submit_time = submit_time;
}
public String getStart_time() {
	return start_time;
}
public void setStart_time(String start_time) {
	this.start_time = start_time;
}
public int getIs_pass() {
	return is_pass;
}
public void setIs_pass(int is_pass) {
	this.is_pass = is_pass;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getQes_exam_paper_name() {
	return qes_exam_paper_name;
}
public void setQes_exam_paper_name(String qes_exam_paper_name) {
	this.qes_exam_paper_name = qes_exam_paper_name;
}
public String getSys_class_name() {
	return sys_class_name;
}
public void setSys_class_name(String sys_class_name) {
	this.sys_class_name = sys_class_name;
}
public int getTotal_point() {
	return total_point;
}
public void setTotal_point(int total_point) {
	this.total_point = total_point;
}
 
}