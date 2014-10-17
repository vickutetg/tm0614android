
import java.util.Scanner;
public class Carpet {
	public static void building(){
		System.out.print("Enter width : ");
		Scanner input = new Scanner(System.in);
		int width = input.nextInt();
		
		System.out.print("Enter height : ");
		int height = input.nextInt();
		
		System.out.println("Start building");
		for(int i = 1; i <= height; i++){
			for(int j = 1; j <= width; j++){
				if(j == width){
					System.out.println("*");
				}else{
					System.out.print("*");
				}
			}
		}
	}
}
