package com.second.ssyt.exam.entity;

import java.util.Date;

public class ExamManageEntity {

    @Override
    public String toString() {
        return "ExamManageEntity [id=" + id + ", exam_time_start="
                + exam_time_start + ", exam_time_stop=" + exam_time_stop
                + ", exam_classroom=" + exam_classroom + ", exam_paper_id="
                + exam_paper_id + ", to_user_ids=" + to_user_ids
                + ", to_class_id=" + to_class_id + ", operate_user_id="
                + operate_user_id + ", operate_time=" + operate_time
                + ", state=" + state + ", memo=" + memo + "]";
    }

    private int id;
    private Date exam_time_start;
    private Date exam_time_stop;
    private String exam_classroom;
    private int exam_paper_id;
    private String to_user_ids;
    private int to_class_id;
    private int operate_user_id;
    private Date operate_time;
    private int state;
    private String memo;

    //扩展属性
    private String paperName;
    private String className;
    private String recetName;

    public String getRecetName() {
        return recetName;
    }

    public void setRecetName(String recetName) {
        this.recetName = recetName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExam_time_start() {
        return exam_time_start;
    }

    public void setExam_time_start(Date date) {
        this.exam_time_start = date;
    }

    public Date getExam_time_stop() {
        return exam_time_stop;
    }

    public void setExam_time_stop(Date exam_time_stop) {
        this.exam_time_stop = exam_time_stop;
    }

    public String getExam_classroom() {
        return exam_classroom;
    }

    public void setExam_classroom(String exam_classroom) {
        this.exam_classroom = exam_classroom;
    }

    public int getExam_paper_id() {
        return exam_paper_id;
    }

    public void setExam_paper_id(int exam_paper_id) {
        this.exam_paper_id = exam_paper_id;
    }

    public String getTo_user_ids() {
        return to_user_ids;
    }

    public void setTo_user_ids(String to_user_ids) {
        this.to_user_ids = to_user_ids;
    }

    public int getTo_class_id() {
        return to_class_id;
    }

    public void setTo_class_id(int to_class_id) {
        this.to_class_id = to_class_id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStates() {
        if (state == 1) {
            return "生效";
        } else if (state == 2) {
            return "待生效";
        } else if (state == 3) {
            return "已删除";
        } else {
            return "Unknow";
        }
    }

    public String getStatess() {
        if (state == 2) {
            return "生效";
        } else {
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
