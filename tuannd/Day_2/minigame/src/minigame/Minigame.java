package minigame;

import java.util.Scanner;

/**
 * @author Tuan
 */
public class Minigame {

    public static void main(String[] args) {
        int menuSelected = 0;
        do {
            System.out.println("-----------------------------------");
            System.out.println("Welcome to Mini game:");
            System.out.println("1. Do math ax2 + bx + c = 0");
            System.out.println("2. Build a magic carpet.");
            System.out.println("3. Exit.");
            System.out.print("What do you want(1,2,3): ");

            Scanner scanner = new Scanner(System.in);
            // Sử dụng try catch để ném lỗi khi người dùng nhập khác 1,2,3
            try {
                menuSelected = scanner.nextInt();
                if (menuSelected == 1) {
                    new MathSolver().mathSolve();
                } else if (menuSelected == 2) {
                    new BuildCarpet().buildMagicCarpet();
                }
            } catch (Exception ex) {
            }
        } while (menuSelected != 3);
        System.out.println("Bye bye, see you later! ");
    }
}
