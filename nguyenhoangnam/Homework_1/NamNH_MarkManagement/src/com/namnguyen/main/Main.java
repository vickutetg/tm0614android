package com.namnguyen.main;

import java.util.Scanner;
import com.namnguyen.list.ListStudent;

public class Main {
	
	static int choice = 0;
	
	public static void main(String[] args) {
		
		do {
			new Main().printWelcomeText();
			
		} while(choice != 3);
		
	}
	
	///////////////////////////////////////////////////
	// Print the welcome message
	// Handle input choice
	// Check whether student was inputed or not and return message to user
	///////////////////////////////////////////////////		
	private void printWelcomeText() {

		System.out.println("Welcome to bkacad mark manager");
		System.out.println("------------------------------");
		System.out.println("1- Record students information");
		System.out.println("2- Display sorted list");
		System.out.println("3- Exit");
		System.out.print("Select your choice <1,2,3>: ");
		
		choice = new Scanner(System.in).nextInt();
		
		switch (choice) {
			case 1: {
				if (inputStudentInfo()) {
					break;
				} else {
					System.out.println("Error Occurred, please check the data input");
					break;
				}
					
			}
			case 2: {
				ListStudent tmpListStudent = ListStudent.getListStudentInstance();
				
				tmpListStudent.printStudentList();
				
				break;
			}
			case 3: {
				System.out.println("Exit");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Wrong choice! Choose again!");
				break;
			}
		}
	}
	
	///////////////////////////////////////////////////
	// Input information of student 
	///////////////////////////////////////////////////	
	private boolean inputStudentInfo() {
		
		String 	name;
		int 	id;
		String 	className;
		
		float	markBasicJava;
		float	markAdvJava;
		float	markCSharp;
		float	markAdvCSharp;
		float	markRDBMS;
		float	markSQL2008;
		
		Scanner input = new Scanner(System.in);
		
		ListStudent tmpListStudent = ListStudent.getListStudentInstance();
		
		System.out.println("Student " + (tmpListStudent.getSize() + 1) + ": ");
		System.out.print("--- name: ");
		name = input.nextLine();
		System.out.print("--- id: ");
		id = input.nextInt();
		System.out.print("--- class: ");
		className = input.next();
		
		System.out.print("--- basic java: ");
		markBasicJava = input.nextFloat();
		System.out.print("--- advance java: ");
		markAdvJava = input.nextFloat();
		System.out.print("--- c#: ");
		markCSharp = input.nextFloat();
		System.out.print("--- advance c#: ");
		markAdvCSharp = input.nextFloat();
		System.out.print("--- rdbms: ");
		markRDBMS = input.nextFloat();
		System.out.print("--- sql 2008: ");
		markSQL2008 = input.nextFloat();
		
		return tmpListStudent.addStudent(name, id, className, markBasicJava, markAdvJava, markCSharp, markAdvCSharp, markRDBMS, markSQL2008);
	}
}





















