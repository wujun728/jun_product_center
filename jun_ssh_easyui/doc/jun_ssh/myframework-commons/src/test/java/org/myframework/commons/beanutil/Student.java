package org.myframework.commons.beanutil;

import java.util.Date;

public class Student {

	static {
		System.out.println("=============");
	}

	private String name;

	private String nickname;

	private int age;

	private Date birth = new Date();

	@Override
	public String toString() {
		return "Student [name=" + name + ", nickname=" + nickname + ", age="
				+ age + ", birth=" + birth + "]";
	}

	public String getName() {
		return name;
	}

	public void setNickName(String name) {
		this.nickname = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {

		return birth;

	}

	public void setBirth(Date birth) {

		this.birth = birth;

	}

}