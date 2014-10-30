package marktmanagement;

import java.util.Scanner;

/**
 * Class nhập thông tin cho Học viên
 */
public class RecordInformation {

    Student student;

    public Student recordInformation(int stt) {
        Scanner scanner = new Scanner(System.in);
        student = new Student();
        
        System.out.println("Student " + stt + ":");
        System.out.print("--- name: ");
        student.setnameStudent(scanner.next());
        System.out.print("--- id: ");
        student.setId(scanner.next());
        System.out.print("--- class: ");
        student.setClassStudent(scanner.next());

        student.setBasicJava(getDoubleValue0_10("basic Java: "));
        student.setAdvanceJava(getDoubleValue0_10("advance Java: "));
        student.setC(getDoubleValue0_10("C#: "));
        student.setAdvanceC(getDoubleValue0_10("advance C#: "));
        student.setRdbms(getDoubleValue0_10("rdbms: "));
        student.setSql2008(getDoubleValue0_10("sql2008: "));

        double average = (student.getBasicJava() + student.getAdvanceJava() + student.getC()
                + student.getAdvanceC() + student.getRdbms() + student.getSql2008()) / 6;
        student.setAverageMark(average);

        if (average > 9) {
            student.setRank("exelent");
        } else if (average > 8) {
            student.setRank("good");
        } else if (average > 7) {
            student.setRank("fair");
        } else if (average > 5) {
            student.setRank("ok");
        } else {
            student.setRank("weak");
        }
        System.out.print("-> " + student.getnameStudent() + " (id=" + student.getId() + ") has average mark of ");
        System.out.format("%.2f", student.getAverageMark());
        System.out.println(", rank '" + student.getRank() + "'.");

        return student;
    }

// Hàm trả về giá trị là số thuc trong khoang 0-10, và yêu cầu nhập đến khi nhập đúng giá trị cho phép
    public double getDoubleValue0_10(String name) {
        boolean check = false;
        double value = 0;
        do {
            Scanner s = new Scanner(System.in);
            check = false;
            System.out.print(" Enter " + name + ": ");
            try {
                value = s.nextDouble();
                if (value < 0 || value > 10) {
                    check = true;
                }
            } catch (Exception ex) {
                check = true;
            }
        } while (check);
        return value;
    }
}
