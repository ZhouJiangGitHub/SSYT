
package com.second.ssyt.PublicEntity;

import java.util.Date;



/**
 * ExamPlan entity. @author MyEclipse Persistence Tools
 */

public class ExamPlanEntity {

	// Fields

	private int id;
	private Date examTimeStart;
	private Date examTimeStop;
	private String examClassroom;
	private int examPaperId;
	private String toUserIds;
	private int toClassId;
	private int operateUserId;
	private Date operateTime;
	private Short state;
	private String memo;

	// Constructors

	/** default constructor */
	public ExamPlanEntity() {
	}

	/** minimal constructor */
	public ExamPlanEntity(Date examTimeStart, Date examTimeStop,
			String examClassroom, int examPaperId, int operateUserId,
			Date operateTime, Short state) {
		this.examTimeStart = examTimeStart;
		this.examTimeStop = examTimeStop;
		this.examClassroom = examClassroom;
		this.examPaperId = examPaperId;
		this.operateUserId = operateUserId;
		this.operateTime = operateTime;
		this.state = state;
	}

	/** full constructor */
	public ExamPlanEntity(Date examTimeStart, Date examTimeStop,
			String examClassroom, int examPaperId, String toUserIds,
			int toClassId, int operateUserId, Date operateTime, Short state,
			String memo) {
		this.examTimeStart = examTimeStart;
		this.examTimeStop = examTimeStop;
		this.examClassroom = examClassroom;
		this.examPaperId = examPaperId;
		this.toUserIds = toUserIds;
		this.toClassId = toClassId;
		this.operateUserId = operateUserId;
		this.operateTime = operateTime;
		this.state = state;
		this.memo = memo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExamTimeStart() {
		return this.examTimeStart;
	}

	public void setExamTimeStart(Date examTimeStart) {
		this.examTimeStart = examTimeStart;
	}

	public Date getExamTimeStop() {
		return this.examTimeStop;
	}

	public void setExamTimeStop(Date examTimeStop) {
		this.examTimeStop = examTimeStop;
	}

	public String getExamClassroom() {
		return this.examClassroom;
	}

	public void setExamClassroom(String examClassroom) {
		this.examClassroom = examClassroom;
	}

	public int getExamPaperId() {
		return this.examPaperId;
	}

	public void setExamPaperId(int examPaperId) {
		this.examPaperId = examPaperId;
	}

	public String getToUserIds() {
		return this.toUserIds;
	}

	public void setToUserIds(String toUserIds) {
		this.toUserIds = toUserIds;
	}

	public int getToClassId() {
		return this.toClassId;
	}

	public void setToClassId(int toClassId) {
		this.toClassId = toClassId;
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