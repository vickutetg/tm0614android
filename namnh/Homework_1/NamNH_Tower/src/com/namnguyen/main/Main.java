package com.namnguyen.main;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		int choice = 0;
		
		do {
			
			System.out.println("Welcome to Mini game:");
			System.out.println("1. Do math ax2 + bx + c = 0");
			System.out.println("2. Build a magic carpet");
			System.out.println("3. Exit");
			System.out.println("What do you want(1,2,3):");
		
			choice = new Scanner(System.in).nextInt();
			
			switch (choice) {
				case 1: {
					giaiHePTBac2();
					break;
				}
				case 2: {
					buildMagicCarpet();
					break;
				}
				case 3: {
					System.out.println("Exit");
					System.exit(0);
					break;
				}
				default: {
					System.out.println("Wrong choice!");
					break;
				}
			}
			
		} while(choice != 3);
		
	}
	
	private static void giaiHePTBac2() {
		
		int a, b, c;
		
		a = 0; 
		b = 0; 
		c = 0;
		
		System.out.println("Enter a:");
		a = new Scanner(System.in).nextInt();
		System.out.println("Enter b:");
		b = new Scanner(System.in).nextInt();
		System.out.println("Enter c:");
		c = new Scanner(System.in).nextInt();
		
		float delta;
		
		delta = b*b - 4*a*c;
		
		if (delta < 0) {
			System.out.println("PT vo nghiem");
			return;
		} else if (delta == 0) {
			System.out.println("PT co nghiem kep");
			return;
		} else {
			System.out.println("This problem has x1= " + ((-b-Math.sqrt(delta)/2*a)) + ", x2= " + ((-b+Math.sqrt(delta)/2*a)));
			return;
		}
	}
	
	private static void buildMagicCarpet() {
		int w = 0;
		int h = 0;
		
		System.out.println("Enter width:");
		w = new Scanner(System.in).nextInt();
		System.out.println("Enter height:");
		h = new Scanner(System.in).nextInt();
		
		System.out.println("Start building");
		System.out.println("");
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
	}
	
}

