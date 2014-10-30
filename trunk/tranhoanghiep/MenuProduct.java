import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MenuProduct {
	private static ArrayList<Product> productlist = new ArrayList<Product>();
	private static ArrayList<Product> productbilling = new ArrayList<Product>();
	private static Scanner input = new Scanner(System.in);

	public static void main(String args[]) throws Exception {
		boolean loop = true;
		int i = 0;
		while (loop) {
			i++;
			System.out.println("Welcome to media mart eshop");
			System.out.println("--------------------------------------");
			System.out.println("1. Buy products.");
			System.out.println("2. Checkout");
			System.out.println("3. Exit");
			System.out.print("Select your choise<1,2,3>:");
			int choose = input.nextInt();
			String answer = "y";
			switch (choose) {
			case 1:
				while (answer.compareTo("y") == 0) {
					// get list product in database
					answer = buyProduct();
				}
				break;
			case 2:
				showProductBilling(productbilling);
				System.out.print("Your name: " + input.nextLine());
				String name = input.nextLine();
				System.out.println("Thank " + name + ". Wating for our invoice. ....Done");
				exportFile(productbilling, name);
				break;
			case 3:
				System.out.println("Bye bye, see you late");
				loop = false;
				break;
			}
		}
	}
	//buy product
	public static String buyProduct() throws Exception {
		ProductManager productmanager = new ProductManager();
		productmanager.listProduct(productlist);
		System.out.print("->Do you want to buy?<1,2,3,4,5>");
		int id = input.nextInt();

		// get product buy
		Iterator iterator = productlist.iterator();
		String name = null;
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			if (id == product.getId()) {
				name = product.getName();
			}
		}
		System.out.print("You buy \'" + name + "\', how many item: ");
		int amount = input.nextInt();
		productmanager.getProduct(id, productbilling, amount);
		System.out.print("Do you want to buy more?<y,n>" + input.nextLine());
		String answer = input.nextLine();
		return answer;
	}

	//show product in cart
	public static void showProductBilling(ArrayList<Product> productbilling) {
		System.out.println("Here are your invoice");
		System.out.println("Name\t\t\t\tid\t\tunit price\tquantity\ttotal");
		System.out.println("----------------------------------------------------------------------------------------");
		Iterator iterator = productbilling.iterator();
		int pricetotal = 0;
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			int total = product.getAmount() * product.getPrice();
			System.out.println(product.getName() + "\t\t\t" + product.getId()
					+ "\t\t" + product.getPrice() + " $\t\t" + product.getAmount() + "\t\t" + total + " $");
			pricetotal += total; 
		}
		int vat = (pricetotal*10)/100;
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Net\t\t\t\t\t\t\t\t\t\t" + pricetotal + " $");
		System.out.println("VAT(10%)\t\t\t\t\t\t\t\t\t" + vat + " $");
		System.out.println("Total\t\t\t\t\t\t\t\t\t\t" + (pricetotal + vat) + " $");
	}
	
	// export file txt
	public static void exportFile(ArrayList<Product> productbilling, String name) throws IOException{
		File file = new File("log.txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Here are your invoice\r\n");
		bw.write("Name\t\t\t\tid\t\tunit price\tquantity\ttotal\r\n");
		bw.write("----------------------------------------------------------------------------------------\r\n");
		Iterator iterator = productbilling.iterator();
		int pricetotal = 0;
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			int total = product.getAmount() * product.getPrice();
			bw.write(product.getName() + "\t\t\t" + product.getId()
					+ "\t\t" + product.getPrice() + " $\t\t" + product.getAmount() + "\t\t" + total + " $\r\n");
			pricetotal += total; 
		}
		int vat = (pricetotal*10)/100;
		bw.write("----------------------------------------------------------------------------------------\r\n");
		bw.write("Net\t\t\t\t\t\t\t\t\t\t" + pricetotal + " $\r\n");
		bw.write("VAT(10%)\t\t\t\t\t\t\t\t\t" + vat + " $\r\n");
		bw.write("Total\t\t\t\t\t\t\t\t\t\t" + (pricetotal + vat) + " $\r\n");
		bw.write("Thank: " + name);
		bw.close();
		fw.close();
	}
}
