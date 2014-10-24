package com.hoangphan.minigame;

import java.util.Scanner;

public class Menu {

	private static final int MARGIC_CARPET = 1;
	private static final int MATH_SOLVER = 2;

	public static void main(String[] args) {
		// loop forver, until press 3
		int answer = 0;
		do {
			// print menu
			System.out.println("Welcome to minigame:");
			System.out.println("1-Magic carpet");
			System.out.println("2-Math solver");
			System.out.println("3-Exit");
			System.out.print("Select <1,2,3>:");

			// read keyboard
			Scanner scanner = new Scanner(System.in);
			answer = scanner.nextInt();

			switch (answer) {
			// 1-->
			case MARGIC_CARPET:
				new Carpet().build();
				break;

			case MATH_SOLVER:
				new Solver().solve();
				break;

			case 3:
				System.out.println("Bye bye.");
				System.exit(0);

			default:
				System.out.println("Wrong input. Back to menu.");
				break;
			}
		} while (answer != 3);
	}
}
