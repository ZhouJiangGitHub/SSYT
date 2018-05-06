package com.second.ssyt.PublicEntity;

import java.sql.Date;


/**
 * ExamRecords entity. @author MyEclipse Persistence Tools
 */


public class ExamRecordsEntity  {

	// Fields

	private int id;
	private int userId;
	private int examPlanId;
	private int getPoint;
	private Date submitTime;
	private Date startTime;
	private Short isPass;

	// Constructors

	/** default constructor */
	public ExamRecordsEntity() {
	}

	/** minimal constructor */
	public ExamRecordsEntity(int userId, int examPlanId, Date startTime,
			Short isPass) {
		this.userId = userId;
		this.examPlanId = examPlanId;
		this.startTime = startTime;
		this.isPass = isPass;
	}

	/** full constructor */
	public ExamRecordsEntity(int userId, int examPlanId, int getPoint,
			Date submitTime, Date startTime, Short isPass) {
		this.userId = userId;
		this.examPlanId = examPlanId;
		this.getPoint = getPoint;
		this.submitTime = submitTime;
		this.startTime = startTime;
		this.isPass = isPass;
	}

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public int getExamPlanId() {
		return this.examPlanId;
	}

	public void setExamPlanId(int examPlanId) {
		this.examPlanId = examPlanId;
	}

	
	public int getGetPoint() {
		return this.getPoint;
	}

	public void setGetPoint(int getPoint) {
		this.getPoint = getPoint;
	}

	
	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

}