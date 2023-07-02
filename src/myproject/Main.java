package myproject;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sp = new Scanner(System.in);
		int choice;
		while(true){
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.Exit");
			choice = sp.nextInt();
			
			sp.nextLine();
			
			String user;
			String pass;
			
			switch(choice) {
				case 1:
					 user = sp.nextLine();
		             pass = sp.nextLine();

		             Admin admin = new Admin();

		             boolean adminLoggedIn = admin.Login(user, pass);
		             
		             if (adminLoggedIn) {
		            	 System.out.println("Logged In");
		            	 
		            	 System.out.println("Enter Product ID: ");		            	 
		            	 int pId = sp.nextInt();
		            	 
		            	 sp.nextLine();	
		            	 
		            	 System.out.println("Enter Product Name: ");		            	 
		            	 String pName = sp.nextLine();
		            	 
		            	 System.out.println("Enter Minimum Sell Quantity: ");		            	 
		            	 int mSqty = sp.nextInt();
		            	 
		            	 System.out.println("Enter Price: ");	            	 
		            	 int price = sp.nextInt();
		            	 
		            	 System.out.println("Enter Quantity: ");		            	 
		            	 int qty = sp.nextInt();
		            	 
		            	 admin.addProduct(pId, pName, mSqty, price, qty);
		            	 admin.viewAllProducts();
		                } 
		                else {
		                    System.out.println("Couldn't Login");
		                }

					break;
				case 2:
					user = sp.nextLine();
					pass = sp.nextLine();
					
					Agent agent = new Agent();
					boolean agentLoggedIn = agent.Agentlogin(user, pass);
					
					if (agentLoggedIn) {
						System.out.println("Logged In");
						agent.choice();
					}
					else {
						System.out.println("Couldn't Login");
		            }
					break;
				case 3:
					System.out.println("Happy Customer...!!!");
	                System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					main(null);
					break;
			}
		}
	}
}
