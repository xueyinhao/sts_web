package com.hao.entity;

public class User2 extends User{
	public String name2;
	public String age;
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User2 [name2=" + name2 + ", age=" + age + "]";
	}
	
}
