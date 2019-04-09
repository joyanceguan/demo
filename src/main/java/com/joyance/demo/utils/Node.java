package com.joyance.demo.utils;

public class Node {

	private String key;
	
	private Integer value;
	
	private Node next;
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public boolean hasNext(){
		return next != null ? true: false;
	}
}
