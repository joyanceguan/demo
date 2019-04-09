package com.joyance.demo.base.sort;

public class Quote {
	
	private String name;
	
	public Quote(String name){
		this.name = name;
	}
	
    public void x(Quote t){
    	t = new Quote("b");
    	t.name = " abv ";
    }
	
    public String getName(){
    	return name;
    }

	public static void main(String[] args) {
		Quote t = new Quote("a");
		t.x(t);
		System.out.println(t.name);
	}
	
}
