package marktmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tuan
 */
public class MarktManagement {

    List<Student> list = new ArrayList<Student>();
    RecordInformation information = new RecordInformation();

    public static void main(String[] args) {
        new MarktManagement().doMain();
    }

    void doMain() {
        int menuSelected = 0;
        int count = 0;
        do {
            System.out.println("\nWelcome to bkacad mark management.");
            System.out.println("-----------------------------------");
            System.out.println("1- Record Student information.");
            System.out.println("2- Display stored list.");
            System.out.println("3- Exit.");
            System.out.print("Select your choice <1,2,3>: ");

            Scanner scanner = new Scanner(System.in);
            try {
                menuSelected = scanner.nextInt();
                if (menuSelected == 1) {
                    ++count;
                    list.add(information.recordInformation(count));
                } else if (menuSelected == 2) {
                    printStoredList();
                }
            } catch (Exception ex) {
            }
        } while (menuSelected != 3);
        System.out.println("Bye bye, see you later! ");
    }
// Hàm in ra màn hình danh sách đã sắp xếp của các học viên vừa nhập vào
    public void printStoredList() {
        Collections.sort(list);
        System.out.printf("%-15s %-5s %-12s %-10s %-10s", "Name", "id", "class", "average", "rank");
        System.out.println("\n----------------------------------------------------");
        int i = 0;
        for (Student temp : list) {
            System.out.printf("%-15s %-5s %-12s %-10.2f %-10s \n", temp.getnameStudent(), 
                    temp.getId(), temp.getClassStudent(), temp.getAverageMark(), temp.getRank());
        }
    }
}
