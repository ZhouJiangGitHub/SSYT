package com.second.ssyt.examinationpaper.entity;

public class ExaminationPaperEntity {
    private int id;
    private String name;
    private String paperUrl;
    private int paperType;
    private int courseId;
    private int totalPoint;
    private int passPoint;
    private int totalMinutes;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public int getPaperType() {
        return paperType;
    }

    public void setPaperType(int paperType) {
        this.paperType = paperType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getPassPoint() {
        return passPoint;
    }

    public void setPassPoint(int passPoint) {
        this.passPoint = passPoint;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}













