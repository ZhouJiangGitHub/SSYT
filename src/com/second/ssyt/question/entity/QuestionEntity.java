package com.second.ssyt.question.entity;

import java.util.Date;


/**
 * QesQestions entity. @author MyEclipse Persistence Tools
 */

public class QuestionEntity {

    // Fields

    private int id;
    private String question;
    private String attachment;
    private Short questionType;
    private int courseId;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answer;
    private Short difficulty;
    private String analysis;
    private String keywords;
    private int operateUserId;
    private Date operateTime;
    private Short state;
    private String memo;
    private String sysCourse$Name;
    private String user$Name;

    private String error_answer;
    // Constructors

    public String getError_answer() {
        return error_answer;
    }

    public void setErrorAnswer(String error_answer) {
        this.error_answer = error_answer;
    }

    /**
     * default constructor
     */
    public QuestionEntity() {
    }

    /**
     * minimal constructor
     */
    public QuestionEntity(String question, Short questionType, int courseId,
                          String answer, Short difficulty, int operateUserId,
                          Date operateTime, Short state) {
        this.question = question;
        this.questionType = questionType;
        this.courseId = courseId;
        this.answer = answer;
        this.difficulty = difficulty;
        this.operateUserId = operateUserId;
        this.operateTime = operateTime;
        this.state = state;
    }

    /**
     * full constructor
     */
    public QuestionEntity(String question, String attachment, Short questionType,
                          int courseId, String answerA, String answerB, String answerC,
                          String answerD, String answer, Short difficulty, String analysis,
                          String keywords, int operateUserId, Date operateTime,
                          Short state, String memo) {
        this.question = question;
        this.attachment = attachment;
        this.questionType = questionType;
        this.courseId = courseId;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer = answer;
        this.difficulty = difficulty;
        this.analysis = analysis;
        this.keywords = keywords;
        this.operateUserId = operateUserId;
        this.operateTime = operateTime;
        this.state = state;
        this.memo = memo;
    }

    // Property accessors


    public int getId() {
        return this.id;
    }

    public String getSysCourse$Name() {
        return sysCourse$Name;
    }

    public void setSysCourse$Name(String sysCourse$Name) {
        this.sysCourse$Name = sysCourse$Name;
    }

    public String getUser$Name() {
        return user$Name;
    }

    public void setUser$Name(String user$Name) {
        this.user$Name = user$Name;
    }


    public void setError_answer(String error_answer) {
        this.error_answer = error_answer;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAttachment() {
        return this.attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getQuestionTypeStr() {
        if (questionType == 1) {
            return "单选题";
        } else if (questionType == 2) {
            return "多选题";
        } else {
            return "判断题";
        }
    }

    public Short getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(Short questionType) {
        this.questionType = questionType;
    }


    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseIdStr() {
        if (courseId == 2) {
            return "java";
        } else if (courseId == 3) {
            return "数字艺术";
        } else if (courseId == 4) {
            return ".net";
        } else if (courseId == 5) {
            return "android";
        } else {
            return "ios";
        }
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public String getAnswerA() {
        return this.answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }


    public String getAnswerB() {
        return this.answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }


    public String getAnswerC() {
        return this.answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }


    public String getAnswerD() {
        return this.answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }


    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDifficultyStr() {
        if (difficulty == 1) {
            return "低";
        } else if (difficulty == 2) {
            return "中";
        } else if (difficulty == 3) {
            return "较高";
        } else {
            return "高";
        }
    }

    public Short getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Short difficulty) {
        this.difficulty = difficulty;
    }


    public String getAnalysis() {
        return this.analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }


    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getStateStr() {
        if (state == 1) {
            return "有效";
        } else {
            return "无效";
        }
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