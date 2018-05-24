package com.second.ssyt.Stand.Entity;

public class StandEntity {

    private int id;
    private String title;
    private String content;
    private String send_time;
    private int sender_id;
    private String receiver_user_name;

    public String getReceiver_user_name() {
        return receiver_user_name;
    }

    public void setReceiver_user_name(String receiver_user_name) {
        this.receiver_user_name = receiver_user_name;
    }

    @Override
    public String toString() {
        return "StandEntity [id=" + id + ", title=" + title + ", content="
                + content + ", send_time=" + send_time + ", sender_id="
                + sender_id + ", receiver_user_ids=" + receiver_user_ids
                + ", receiver_class_ids=" + receiver_class_ids
                + ", expire_time=" + expire_time + ", state=" + state
                + ", name=" + name + ", sys_role_name=" + sys_role_name + "]";
    }

    private String receiver_user_ids;
    private int receiver_class_ids;
    private String expire_time;
    private int state;

    //扩展属性
    private String name;
    private String sys_role_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_user_ids() {
        return receiver_user_ids;
    }

    public void setReceiver_user_ids(String receiver_user_ids) {
        this.receiver_user_ids = receiver_user_ids;
    }

    public int getReceiver_class_ids() {
        return receiver_class_ids;
    }

    public void setReceiver_class_ids(int receiver_class_ids) {
        this.receiver_class_ids = receiver_class_ids;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSys_role_name() {
        return sys_role_name;
    }

    public void setSys_role_name(String sys_role_name) {
        this.sys_role_name = sys_role_name;
    }
}