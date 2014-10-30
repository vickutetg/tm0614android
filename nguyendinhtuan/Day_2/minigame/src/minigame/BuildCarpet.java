package minigame;

import java.util.Scanner;
/**
 * Class vẽ thảm thần
 */
public class BuildCarpet {

    public void buildMagicCarpet() {
        int w = 0, h = 0;
        System.out.println("======================================");
        System.out.println("Add Magic Carpet size(integer only): ");

        w = getIntValue("width");
        h = getIntValue("heigh");
        System.out.println("Start building:");

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
    
// Hàm trả về giá trị là số nguyên dương, và yêu cầu nhập đến khi nhập đúng giá trị nguyên dương
    public int getIntValue(String name) {
        boolean check = false;
        int value = 0;
        do {
            Scanner s = new Scanner(System.in);
            check = false;
            System.out.print(" Enter " + name + ": ");
            try {
                value = s.nextInt();
                if (value < 0) {
                    check = true;
                }
            } catch (Exception ex) {
                check = true;
            }
        } while (check);
        return value;
    }
}
