package com.hoangphan.minigame;

import java.util.Scanner;

public class Carpet {

	public void build() {
		//input widt and height
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Width:");
		//data validation
		String strWid = scanner.next();
		while(!checkInt(strWid)){ //input wrong
			System.out.println("Wrong. Input again:");
			strWid = scanner.next();
		}
		int w = Integer.parseInt(strWid);
		
		System.out.println("Height:");
		String strHei = scanner.next();
		while(!checkInt(strHei)){ //input wrong
			System.out.println("Wrong. Input again:");
			strHei = scanner.next();
		}
		int h = Integer.parseInt(strHei);
		
		//build carpet
		System.out.println("Start build");
		
		//build row
		// ***** i = 0
		// ***** i = 1
		// ***** i = 2
		for (int i = 1; i <= h; i++) {
			//col in each row
			for (int j = 1; j <= w; j++) {
				System.out.print("* ");
			}
			System.out.println("");
		}
		
	}

	private boolean checkInt(String strWid) {
		//wrapper
		try {
			Integer.parseInt(strWid);
		} catch (NumberFormatException ex){
			return false;
		}
		
		return true;
	}
}
