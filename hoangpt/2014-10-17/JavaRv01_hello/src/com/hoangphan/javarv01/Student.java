package com.hoangphan.javarv01;

/**
 * entity
 * 
 * @author hoangpt
 *
 */
public class Student {
	//encapsulate
	private String name;
	private int age;
	
	public Student() {
		super();
	}

	public Student(String name, int age) {
		super(); //parent
		this.name = name; //myself
		this.age = age;
	}

	public void gotoGameCenter(){
	}
	
	//extends
	protected int makeLove(String name){
		return 1000000;
	}
	
	private void cleanTeeth(){
	}
	
	void learn(){
	}

	public String getName() {
		return name;
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
	
	

}
