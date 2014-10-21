package com.namnguyen.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.namnguyen.object.Product;

public class Main {
	
	// create list of product
	public static ArrayList<Product> productList = new ArrayList<Product>();
		
	public void initData() {
		
		// initialize data for product
	    Product product1 = new Product("Plasma TV 32 inches", 1, 1000	, 0, 0);
	    Product product2 = new Product("Macbook Pro 18"		, 2, 1500	, 0, 0);
	    Product product3 = new Product("Mitsumi Mouse"		, 3, 5		, 0, 0);
	    Product product4 = new Product("Elead PC"			, 4, 500	, 0, 0);
	    Product product5 = new Product("BKAV antivirus"		, 5, 30		, 0, 0);	    
	    
	    // Adding product to list
	    productList.add(product1);
	    productList.add(product2);
	    productList.add(product3);
	    productList.add(product4);
	    productList.add(product5);
	}
	
	public void printMainMenu() {
		 System.out.println("Welcome to media mart eshop");
         System.out.println("---------------------------");
         System.out.println("1- Buy products");
         System.out.println("2- Check out");
         System.out.println("3- Exit");
         System.out.print("Select your choice <1,2,3>: ");
         
         int choice = new Scanner(System.in).nextInt();
         checkChoice(choice);
	}
	
	public void printProductList() {
	
		System.out.println("Our product:\n"
                + "1- Plasma TV 32 inches\t1000$\n"
                + "2- Macbook Pro 18\t1500$\n"
                + "3- Mitsumi Mouse\t5$\n"
                + "4- Elead PC\t500$\n"
                + "5- BKAV antivirus\t30$");
		
		System.out.println("-> Do you want to buy?<1,2,3,4,5>");
		
		int productIDSelected = new Scanner(System.in).nextInt();
		
		checkProductIDSelected(productIDSelected);
	}
	
	public void getInvoice() {
		Iterator<Product> itr = productList.iterator();
		while (itr.hasNext()) {
			Product pro = itr.next();
			if (pro.getQuantity() > 0) {
				System.out.println(pro.getName() + "\t" + pro.getQuantity() + "\t" + pro.getTotal()); 
			}
		}
	}
	
	public void checkChoice(int choice) {
		switch(choice) {
            case 1:
            	printProductList();
                break;
            case 2:
            	getInvoice();
            	break;
            case 3:
            	System.exit(0);
            	break;
		}
	}

	public void checkProductIDSelected(int productIDSelected) {
		int number = 0;
		Iterator<Product> itr = productList.iterator();
		int yesno = 0;
		switch(productIDSelected) {
            case 1:
                System.out.println("You buy 'Plasma TV 32 inches', how many item: ");
                number = new Scanner(System.in).nextInt();
                itr = productList.iterator();
                
                while (itr.hasNext()) {
                	Product pro = itr.next();
                	if (pro.getID() == 1) {
                		pro.setQuantity(number);
                		pro.setTotal(pro.getQuantity() * pro.getUnitPrice());
                	}
                }
                
                System.out.print("Do you want to buy more <1-yes, 0-no>: ");
                yesno = new Scanner(System.in).nextInt();
                if (yesno == 1) {
                	printProductList();
                } else {
                	printMainMenu();
                }
                break;
            case 2:
                System.out.println("You buy 'Macbook Pro 18', how many item: ");
                number = new Scanner(System.in).nextInt();
                itr = productList.iterator();
                
                while (itr.hasNext()) {
                	Product pro = itr.next();
                	if (pro.getID() == 2) {
                		pro.setQuantity(number);
                		pro.setTotal(pro.getQuantity() * pro.getUnitPrice());
                	}
                }
                System.out.print("Do you want to buy more <1-yes, 0-no>: ");
                yesno = new Scanner(System.in).nextInt();
                if (yesno == 1) {
                	printProductList();
                } else {
                	printMainMenu();
                }
                
                break;
            case 3:
                System.out.println("You buy 'Mitsumi Mouse', how many item: ");
                number = new Scanner(System.in).nextInt();
                itr = productList.iterator();
                
                while (itr.hasNext()) {
                	Product pro = itr.next();
                	if (pro.getID() == 3) {
                		pro.setQuantity(number);
                		pro.setTotal(pro.getQuantity() * pro.getUnitPrice());
                	}
                }
                System.out.print("Do you want to buy more <1-yes, 0-no>: ");
                yesno = new Scanner(System.in).nextInt();
                if (yesno == 1) {
                	printProductList();
                } else {
                	printMainMenu();
                }
                break;
            case 4:
                System.out.println("You buy 'Elead PC', how many item: ");
                number = new Scanner(System.in).nextInt();
                itr = productList.iterator();
                
                while (itr.hasNext()) {
                	Product pro = itr.next();
                	if (pro.getID() == 4) {
                		pro.setQuantity(number);
                		pro.setTotal(pro.getQuantity() * pro.getUnitPrice());
                	}
                }
                
                System.out.print("Do you want to buy more <1-yes, 0-no>: ");
                yesno = new Scanner(System.in).nextInt();
                if (yesno == 1) {
                	printProductList();
                } else {
                	printMainMenu();
                }
                break;
            case 5:
                System.out.println("You buy 'BKAV antivirus', how many item: ");
                number = new Scanner(System.in).nextInt();
                itr = productList.iterator();
                
                while (itr.hasNext()) {
                	Product pro = itr.next();
                	if (pro.getID() == 5) {
                		pro.setQuantity(number);
                		pro.setTotal(pro.getQuantity() * pro.getUnitPrice());
                	}
                }
                System.out.print("Do you want to buy more <1-yes, 0-no>: ");
                yesno = new Scanner(System.in).nextInt();
                if (yesno == 1) {
                	printProductList();
                } else {
                	printMainMenu();
                }
                break;
        }
	}
	
    public static void main(String[] args) {
    	
    	// initialize data
    	new Main().initData();
    	
    	new Main().printMainMenu();
    	
    }
}
