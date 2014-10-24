/**
 * declare package, domain reverse
 */
package com.hoangphan.javarv01;

public class Bootstrap {
	/**
	 * console
	 * 
	 * @param args console argument
	 */
	public static void main(String[] args) {
		//[] = array
		String hehe = args[0];
		
		//system.out = monitor
		System.out.println("Hello Java");
		System.out.println("My name is "+hehe+". ^^");
		System.out.println("How about you?");
		
		//print your self: 18 years, Ha noi
		
		
		//primitive
		int age = 25; //byte, long
		long money = -10000;
		int sumMoney = (int) (age * money); //cast from big->small
		long age2 = age; //implicit cast
		
		String name = "Hoang";
		char nick = 'C';
		
		double price = 12.5;
		float interest = 4.5f;
		
		//class, new constructor
		Hello hello = new Hello();
		hello.sayGoodMorning(); //say to all
		hello.sayGoodMorning("MR Trung");
		
		//camelCase
		long moneyAbs = Math.abs(sumMoney); //static function
		System.out.println(moneyAbs);
		
		hello.name = "MR Bean";
		
		//init student
		Student nam = new Student();
		//nam.name = "Nam";
		//nam.age = 18;
		nam.makeLove("Trung");
		//nam.cleanTeeth();
		
		
		
	}
}
