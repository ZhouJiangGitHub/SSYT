package com.second.ssyt.PublicEntity;

import java.sql.Date;

/**
 * QesExamPaper entity. @author MyEclipse Persistence Tools
 */

public class QesExamPaperEntity  {

	// Fields

	private int id;
	private String name;
	private String paperUrl;
	private Short paperType;
	private int courseId;
	private int totalPoint;
	private int passPoint;
	private int totalMinutes;
	private Short singleOptionNumber;
	private Short singleOptionEachPoint;
	private Short singleOptionDifficulty;
	private Short multiOptionNumber;
	private Short multiOptionEachPoint;
	private Short multiOptionDifficulty;
	private Short judgeNumber;
	private Short judgeEachPoint;
	private Short judgeDifficulty;
	private int opreateUserId;
	private Date opreateTime;
	private Short state;
	private String memo;

	// Constructors

	/** default constructor */
	public QesExamPaperEntity() {
	}

	/** minimal constructor */
	public QesExamPaperEntity(String name, String paperUrl, Short paperType,
			int courseId, int totalPoint, int passPoint,
			int totalMinutes, int opreateUserId, Date opreateTime,
			Short state, String memo) {
		this.name = name;
		this.paperUrl = paperUrl;
		this.paperType = paperType;
		this.courseId = courseId;
		this.totalPoint = totalPoint;
		this.passPoint = passPoint;
		this.totalMinutes = totalMinutes;
		this.opreateUserId = opreateUserId;
		this.opreateTime = opreateTime;
		this.state = state;
		this.memo = memo;
	}

	/** full constructor */
	public QesExamPaperEntity(String name, String paperUrl, Short paperType,
			int courseId, int totalPoint, int passPoint,
			int totalMinutes, Short singleOptionNumber,
			Short singleOptionEachPoint, Short singleOptionDifficulty,
			Short multiOptionNumber, Short multiOptionEachPoint,
			Short multiOptionDifficulty, Short judgeNumber,
			Short judgeEachPoint, Short judgeDifficulty, int opreateUserId,
			Date opreateTime, Short state, String memo) {
		this.name = name;
		this.paperUrl = paperUrl;
		this.paperType = paperType;
		this.courseId = courseId;
		this.totalPoint = totalPoint;
		this.passPoint = passPoint;
		this.totalMinutes = totalMinutes;
		this.singleOptionNumber = singleOptionNumber;
		this.singleOptionEachPoint = singleOptionEachPoint;
		this.singleOptionDifficulty = singleOptionDifficulty;
		this.multiOptionNumber = multiOptionNumber;
		this.multiOptionEachPoint = multiOptionEachPoint;
		this.multiOptionDifficulty = multiOptionDifficulty;
		this.judgeNumber = judgeNumber;
		this.judgeEachPoint = judgeEachPoint;
		this.judgeDifficulty = judgeDifficulty;
		this.opreateUserId = opreateUserId;
		this.opreateTime = opreateTime;
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

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPaperUrl() {
		return this.paperUrl;
	}

	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}

	
	public Short getPaperType() {
		return this.paperType;
	}

	public void setPaperType(Short paperType) {
		this.paperType = paperType;
	}

	
	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	
	public int getTotalPoint() {
		return this.totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	
	public int getPassPoint() {
		return this.passPoint;
	}

	public void setPassPoint(int passPoint) {
		this.passPoint = passPoint;
	}

	
	public int getTotalMinutes() {
		return this.totalMinutes;
	}

	public void setTotalMinutes(int totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	
	public Short getSingleOptionNumber() {
		return this.singleOptionNumber;
	}

	public void setSingleOptionNumber(Short singleOptionNumber) {
		this.singleOptionNumber = singleOptionNumber;
	}

	
	public Short getSingleOptionEachPoint() {
		return this.singleOptionEachPoint;
	}

	public void setSingleOptionEachPoint(Short singleOptionEachPoint) {
		this.singleOptionEachPoint = singleOptionEachPoint;
	}

	
	public Short getSingleOptionDifficulty() {
		return this.singleOptionDifficulty;
	}

	public void setSingleOptionDifficulty(Short singleOptionDifficulty) {
		this.singleOptionDifficulty = singleOptionDifficulty;
	}

	
	public Short getMultiOptionNumber() {
		return this.multiOptionNumber;
	}

	public void setMultiOptionNumber(Short multiOptionNumber) {
		this.multiOptionNumber = multiOptionNumber;
	}

	
	public Short getMultiOptionEachPoint() {
		return this.multiOptionEachPoint;
	}

	public void setMultiOptionEachPoint(Short multiOptionEachPoint) {
		this.multiOptionEachPoint = multiOptionEachPoint;
	}

	
	public Short getMultiOptionDifficulty() {
		return this.multiOptionDifficulty;
	}

	public void setMultiOptionDifficulty(Short multiOptionDifficulty) {
		this.multiOptionDifficulty = multiOptionDifficulty;
	}

	
	public Short getJudgeNumber() {
		return this.judgeNumber;
	}

	public void setJudgeNumber(Short judgeNumber) {
		this.judgeNumber = judgeNumber;
	}

	
	public Short getJudgeEachPoint() {
		return this.judgeEachPoint;
	}

	public void setJudgeEachPoint(Short judgeEachPoint) {
		this.judgeEachPoint = judgeEachPoint;
	}

	
	public Short getJudgeDifficulty() {
		return this.judgeDifficulty;
	}

	public void setJudgeDifficulty(Short judgeDifficulty) {
		this.judgeDifficulty = judgeDifficulty;
	}

	
	public int getOpreateUserId() {
		return this.opreateUserId;
	}

	public void setOpreateUserId(int opreateUserId) {
		this.opreateUserId = opreateUserId;
	}

	
	public Date getOpreateTime() {
		return this.opreateTime;
	}

	public void setOpreateTime(Date opreateTime) {
		this.opreateTime = opreateTime;
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