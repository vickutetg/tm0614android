import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {
	public static void main(String args[]) {
		Scanner choose = new Scanner(System.in);
		boolean loop = true;
		ArrayList<Student> person = new ArrayList<Student>();
		while (loop) {
			System.out.println("He thong nhap du lieu sinh vien");
			System.out.println("1. Them sinh vien");
			System.out.println("2. Hien thi danh sach sinh vien");
			System.out.println("3. Sua thong tin sinh vien");
			System.out.println("4. Xoa sinh vien");
			System.out.println("5. Thoat");
			int choise = choose.nextInt();
			switch (choise) {
			case 1:
				addStudent(person);
				break;
			case 2:
				loop = false;
				displayStudent(person);
				break;
			case 3:
				loop = false;
				break;
			case 4:
				loop = false;
				break;
			case 5:
				loop = false;
				break;
			}
		}
	}

	public static void addStudent(ArrayList<Student> person) {
		Scanner amount = new Scanner(System.in);
		Scanner nhap = new Scanner(System.in);
		System.out.print("So sinh vien can nhap: ");
		int num = amount.nextInt();
		for (int i = 1; i <= num; i++) {
			System.out.println("Sinh vien thu " + i);
			Student student = new Student();

//			System.out.print("Ten day du: " + nhap.nextLine());
//			student.setFullname(nhap.nextLine());

//			System.out.print("Gioi tinh: " + nhap.nextLine());
//			student.setGender(nhap.nextLine());
//
//			System.out.print("Tuoi: " + nhap.nextLine());
//			student.setAge(nhap.nextInt());
//
//			System.out.print("So dien thoai: " + nhap.nextLine());
//			student.setPhonenumber(nhap.nextInt());
//			person.add(student);
		}
	}

	public static void displayStudent(ArrayList<Student> person) {
		Iterator iterator = person.iterator();
		System.out.println("============================");
		System.out.println("Ten day du\t\tGioi tinh\tTuoi\tDien thoai");
//		while (iterator.hasNext()) {
//			Student student = (Student) iterator.next();
//			System.out.print(student.getFullname() + "\t\t" + student.getGender()
//							+ "\t\t" + student.getAge() + "\t"
//							+ student.getPhonenumber());
//			System.out.print("\n");
//		}
	}
}
