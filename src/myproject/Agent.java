package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Agent {
	static final String db_url = "jdbc:mysql://localhost:3306/inventorymanagement";
	static final String db_username = "root";
	static final String db_password = "root";
	
	public boolean Agentlogin(String username, String password) {
		try (Connection conn = DriverManager.getConnection(db_url, db_username, db_password)){
			String query = "SELECT * FROM login WHERE username = ? and password = ? and level=\"agent\"";
	        PreparedStatement stmt = conn.prepareStatement(query);
	           
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	           
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return true;
	        } 
	        else {
	            return false;
	        }
	    }
		catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print("Couldn't Connect");
	        return false; // Error occurred during login
	    }
}
	public void choice() {
		Scanner sp = new Scanner(System.in);
		   
		int val;
		boolean a = true;
		   
		while(a) {
			System.out.println("1. Buy");
			System.out.println("2. Sell");
			System.out.println("3. Logout");
	 
			val = sp.nextInt();
	 
			switch(val) {
				case 1:
					Admin admin = new Admin();
					System.out.println("----Add your Products----");
					
					System.out.println("Enter Product ID: ");
					int pId = sp.nextInt();
					sp.nextLine();
	             
					System.out.println("Enter Product Name: ");
					String pName = sp.nextLine();
	             
					System.out.println("Enter Minimum Quantity: ");
					int mSquantity = sp.nextInt();
	             
					System.out.println("Enter Price: ");
					int price = sp.nextInt();
	             
					System.out.println("Enter Quantity: ");
					int quantity = sp.nextInt();
	             
					admin.addProduct(pId, pName, mSquantity, price, quantity);
					break;
				case 2:
					System.out.println("----Sell Products----");
					System.out.println("Enter Product ID: ");
					int sellProductId = sp.nextInt();
					sp.nextLine();

					System.out.println("Enter Quantity: ");
					int sellquantity = sp.nextInt();

					try (Connection conn = DriverManager.getConnection(db_url, db_username, db_password)) {
						String query = "SELECT price, quantity FROM product WHERE productId = ?";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setInt(1, sellProductId);
						ResultSet rs = stmt.executeQuery();

						if (rs.next()) {
							int price1 = rs.getInt("price");
							int availablequantity = rs.getInt("quantity");

							if (sellquantity <= availablequantity) {
								int totalPrice = price1 * sellquantity;

								String updateQuery = "UPDATE product SET quantity = ? WHERE productId = ?";
								PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
								updateStmt.setInt(1, availablequantity - sellquantity);
								updateStmt.setInt(2, sellProductId);
								updateStmt.executeUpdate();

								System.out.println("Product sold successfully. Total Price: " + totalPrice);
							} 
							else {
								System.out.println("Insufficient quantity. Available quantity: " + availablequantity);
							}
						} 
						else {
							System.out.println("Product not found.");
						}
					} 
					catch (SQLException e) {
						e.printStackTrace();
						System.out.println("Couldn't connect to the database.");
					}
					break;
	   
				case 3:
					System.out.println("Logout Successfull");
					a = false;
					break;
				 
				default:
					System.out.println("Invalid input. Please Try again...");
					break;
			}
		}
	}
}
