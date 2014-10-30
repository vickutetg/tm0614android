
import java.util.Scanner;
public class MathSolver {
	
	public static void math(){
		Scanner input = new Scanner(System.in);		
		System.out.print("Enter a: ");
		double a = input.nextInt();
		System.out.print("Enter b: ");
		double b = input.nextInt();
		System.out.print("Enter c: ");
		double c = input.nextInt();
		
		double delta = (b*b)-(4*a*c);
		if (delta < 0){
			System.out.println("Phuong trinh vo nghiem");
		}else if(delta == 0){
			double x = -(b/(2*a));
			System.out.println("Phuong trinh co nghiem kep x1 = x2 = " + x);
		}else{
			double x1 = ((-b)+Math.sqrt(delta))/(2*a);
			double x2 = ((-b)-Math.sqrt(delta))/(2*a);
			System.out.println("This problem has x1=" + x1 + " , x2=" + x2 + "\n");
		}
	}
}
