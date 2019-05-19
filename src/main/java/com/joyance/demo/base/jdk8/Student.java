package com.joyance.demo.base.jdk8;

public class Student {
	
	public Student(String clasz,String name,int score){
		this.clasz = clasz;
		this.name = name;
		this.score = score;
	}

	private String clasz;
	private String name;
	private int score;
	
	public String getClasz() {
		return clasz;
	}
	public void setClasz(String clasz) {
		this.clasz = clasz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
