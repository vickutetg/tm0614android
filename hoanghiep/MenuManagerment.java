
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
public class MenuManagerment {
	private static ArrayList<Student> student = new ArrayList<Student>();
	private static Scanner input = new Scanner(System.in);
	private static String answer = "y";
	public static void main(String args[]){
		boolean loop = true;
		int i = 0;
		while(loop){
			i++;
			System.out.println("Welcome to Techmaster mark manager.");
			System.out.println("----------------------------------------------------------------------");
			System.out.println("1. Record Students information");
			System.out.println("2. Display sorted list.");
			System.out.println("3. Exit");
			System.out.print("Select your choise<1,2,3>:");
			int choose = input.nextInt();
			switch(choose){
			case 1:
				while(answer.compareTo("y") == 0){
					answer = displayStudent();
				}
				break;
			case 2:
				StudentManager student1 = new StudentManager();
				student1.display(student);
				break;
			case 3:
				System.out.println("Bye bye, see you late");
				loop = false;
				break;
			}
		}
	}
	
	public static String displayStudent(){
		StudentManager person = new StudentManager();
		person.addRecord(student);
		System.out.print("Do you want to add new record?(y/n)" + input.nextLine());
		answer = input.nextLine();
		return answer;
	}
}
