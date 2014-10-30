package mediamarteshop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class MediaMartEshop {

    public static List<Products> list = new ArrayList<>();

    public static void main(String[] args) {
        new MediaMartEshop().doMain();
    }

    public void doMain() {
        int menuSelected = 0;
        do {
            System.out.println("\nWelcome to media mark eshop");
            System.out.println("-----------------------------------");
            System.out.println("1- Buy products.");
            System.out.println("2- Checkout.");
            System.out.println("3- Exit.");
            System.out.print("Select your choice <1,2,3>: ");

            Scanner scanner = new Scanner(System.in);
            try {
                menuSelected = scanner.nextInt();
                if (menuSelected == 1) {
                    new BuyingProduct().doBuyProducts(list);
                } else if (menuSelected == 2) {
                    printStoredList();
                }
            } catch (Exception ex) {
            }
        } while (menuSelected != 3);
        System.out.println("Bye bye, see you later! ");
    }

    // Hàm in ra màn hình danh sách hàng đã chọn
    public void printStoredList() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String name;

        System.out.println("Here are your invoice:");
        System.out.printf("%-25s %-4s %-15s %-14s %-15s\n", "Name", "id", "unit price", "quantity", "total");
        System.out.println("-----------------------------------------------------------------------");
        int i = 0, sum = 0;
        for (Products temp : list) {
            System.out.printf("%-25s %-4s %-15d %-14d %5d\n", temp.getNameProduct(),
                    temp.getId(), temp.getPrice(), temp.getQuantity(), (temp.getPrice() * temp.getQuantity()));
            sum += temp.getQuantity() * temp.getPrice();
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-61s %5d\n", "Net", sum);
        System.out.printf("%-61s %5d\n", "VAT (10%)", sum / 10);
        System.out.printf("%-61s %5d\n", "Total", sum + sum / 10);

        System.out.print("Your name: ");
        name = scanner.nextLine();
        printToText(name);
        System.out.println("Thank " + name + ". Waiting for your invoice. ... Done");

    }

    void printToText(String name) throws IOException {
        String fileName = name + "_"
                + (new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())) + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName, false);
        PrintWriter writer = new PrintWriter(fos);
        writer.println("Here are your invoice:");
        writer.printf("%-25s %-4s %-15s %-14s %-15s", "Name", "id", "unit price", "quantity", "total");
        writer.println();
        writer.println("-----------------------------------------------------------------------");

        int i = 0, sum = 0;
        for (Products temp : list) {
            writer.printf("%-25s %-4s %-15d %-14d %5d", temp.getNameProduct(),
                    temp.getId(), temp.getPrice(), temp.getQuantity(), (temp.getPrice() * temp.getQuantity()));
            sum += temp.getQuantity() * temp.getPrice();
            writer.println();
        }
        writer.println("-----------------------------------------------------------------------");
        writer.printf("%-61s %5d\n", "Net", sum);
        writer.println();
        writer.printf("%-61s %5d\n", "VAT (10%)", sum / 10);
        writer.println();
        writer.printf("%-61s %5d\n", "Total", sum + sum / 10);
        writer.println();
        writer.println("Your name: " + name);
        writer.close();
        fos.flush();
        fos.close();
    }
}
