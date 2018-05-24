package com.second.ssyt.student.entity;

/**
 * 学生实体
 *
 * @author lenovo
 */
public class StudentEntity {
    private int id;
    private String code;
    private String password;
    private String name;
    private String phone;
    private String email;
    private int age;
    private int gender;
    private String hobby;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public String setSex() {
        if (gender == 1) {
            return "男";
        }
        return "女";
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "TalentEntity [id=" + id + ", code=" + code + ", password="
                + password + ", name=" + name + ", phone=" + phone + ", email="
                + email + ", age=" + age + ", gender=" + gender + ", hobby="
                + hobby + "]";
    }
}
