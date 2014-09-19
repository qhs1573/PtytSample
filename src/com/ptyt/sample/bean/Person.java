package com.ptyt.sample.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Comparable<Person>, Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private ArrayList<Teacher> teacher = new ArrayList<Teacher>();
	
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
		Teacher teachers = new Teacher();
		teachers.setName(name);
		teacher.add(teachers);
	}
	
	public ArrayList<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher(ArrayList<Teacher> teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int compareTo(Person another) {
		return 0;
	}
	
	
}
