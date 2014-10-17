
import java.util.Scanner;
public class MiniGame {
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		do{
			System.out.println("Welcome to Mini game:");
			System.out.println("1. Do match ax2+bx+c=0");
			System.out.println("2. Build a magic carpet");
			System.out.println("3. Exit");
			System.out.print("What do you want (1,2,3)");
			int choose = input.nextInt();
			switch(choose){
				case 1:
					MathSolver math = new MathSolver();
					math.math();
					break;
				case 2:
					Carpet carpet = new Carpet();
					carpet.building();
					break;
				case 3:
					loop = false;
					break;
			}
		}while(loop);
	}
}
