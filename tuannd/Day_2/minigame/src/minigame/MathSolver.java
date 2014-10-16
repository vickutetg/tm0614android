package minigame;

import java.util.Scanner;

/**
 * Class giải Phương trình bậc hai
 */
public class MathSolver {

    public void mathSolve() {
        double a, b, c, D, x1, x2, x;

        System.out.println("======================================");
        System.out.println("Add value:");
        a = getDoubleValue("a");
        b = getDoubleValue("b");
        c = getDoubleValue("c");

        D = b * b - 4 * a * c;
        if (D < 0) {
            System.out.println("This problem has no solution");
        } else if (D == 0) {
            x = -b / (2 * a);
            System.out.println("This problem has: x1=x2= " + x);
        } else if (D > 0) {
            x1 = (-b - Math.sqrt(D)) / (2 * a);
            x2 = (-b + Math.sqrt(D)) / (2 * a);
            System.out.format("This problem has: x1= %.3f; x2= %.3f %n", x1, x2);
        }        
    }

// Hàm trả về giá trị là số thực và yêu cầu nhập cho đến khi ký tự nhập vào là số thực
    public double getDoubleValue(String name) {
        boolean check = false;
        double value = 0;
        do {
            Scanner s = new Scanner(System.in);
            check = false;
            System.out.print(" Enter " + name + ": ");
            try {
                value = s.nextDouble();
            } catch (Exception ex) {
                check = true;
            }
        } while (check);
        return value;
    }
}
