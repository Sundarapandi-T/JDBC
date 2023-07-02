package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
	private static final String db_url = "jdbc:mysql://localhost:3306/inventorymanagement";
	private static final String db_username = "root";
	private static final String db_password = "root";
	
	public boolean Login(String username,String password) {
		try {
			Connection con=DriverManager.getConnection(db_url, db_username, db_password);
			String query = "select * from login where username = ? and password = ? and level='admin'";

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, username); 
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
		}
		catch(SQLException e){
			e.printStackTrace();
	        System.out.print("Couldn't Connect");
		}
		return false;
	}
	
	// To add a product

    public void addProduct(int productId, String productName, int minSellQuantity, int price, int quantity) {
        try (Connection con2 = DriverManager.getConnection(db_url, db_username, db_password)) {
            String query = "INSERT INTO product VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = con2.prepareStatement(query);

            stmt.setInt(1, productId);
            stmt.setString(2, productName);
            stmt.setInt(3, minSellQuantity);
            stmt.setInt(4, price);
            stmt.setInt(5, quantity);

            stmt.executeUpdate();

            System.out.println("**Product is added Successfully**");
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Couldn't Connect");

        }

    }

    //To view a product details

    public void viewAllProducts() {
        try (Connection con3 = DriverManager.getConnection(db_url, db_username, db_password)) {

            String query = "SELECT * FROM product";
            PreparedStatement stmt = con3.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            System.out.println();
            System.out.println("------ PRODUCTS ------");
         
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                int minSellQuantity = rs.getInt("minSellQuantity");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");

                System.out.print(productId + "| ");
                System.out.print(productName + "| ");
                System.out.print(minSellQuantity + "| ");
                System.out.print(price+"| ");
                System.out.println(quantity);

                System.out.println("-----------------------");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't Connect");
        }
    }
}
