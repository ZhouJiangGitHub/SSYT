package com.second.ssyt.PublicEntity;




/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class StudentEntity  {

	// Fields

	private int id;
	private String code;
	private String password;
	private String name;
	private String phone;
	private String email;
	private int age;
	private int gender;
	private String hobby;

	// Constructors

	/** default constructor */
	public StudentEntity() {
	}

	/** minimal constructor */
	public StudentEntity(String code, String password, String name, String phone,
			String email, int age, int gender) {
		this.code = code;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	/** full constructor */
	public StudentEntity(String code, String password, String name, String phone,
			String email, int age, int gender, String hobby) {
		this.code = code;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.hobby = hobby;
	}

	// Property accessors
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}