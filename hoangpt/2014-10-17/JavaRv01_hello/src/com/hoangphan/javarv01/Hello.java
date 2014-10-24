package com.hoangphan.javarv01;

public class Hello {

	//attribute
	String name; //global
	
	//default blank constructor
	public Hello(){
	}
	
	/**
	 * method
	 */
	void sayGoodMorning(){
		System.out.println("Good morning. Nice day.");
	}
	
	/**
	 * method overloading
	 * @param name
	 */
	void sayGoodMorning(String name){
		//local
		System.out.println("Good morning "+name+".");
	}
	
	void sayGoodEvening(){
		System.out.println("Good evening "+this.name+".");
	}

}
