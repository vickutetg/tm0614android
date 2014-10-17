
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductManager {
	private Connection connection;
	private Statement statement;
	String url = "jdbc:mysql://localhost:3306/testjava";
	String user = "root";
	String pass = "";
	public ProductManager() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, pass);
		statement = connection.createStatement();
	}
	//get alll product in data
	public void listProduct(ArrayList<Product> productlist) throws Exception{
		String sql = "select * from tbl_product";
		ResultSet rs = statement.executeQuery(sql);
		System.out.println("\nOur product:");
		while(rs.next()){
			Product product = new Product();
			product.setId(rs.getInt("Id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("Price"));
			productlist.add(product);
			System.out.println(product.getId() + "- " + product.getName() + "\t\t" + product.getPrice() + "$");
		}
	}
	//get product and put it on cart
	public void getProduct(int id, ArrayList<Product> productbilling, int amount) throws Exception{
		String sql = "select * from tbl_product where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			Product product = new Product();
			product.setId(rs.getInt("Id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("Price"));
			product.setAmount(amount);
			productbilling.add(product);
		}
	}
	
	//close connection
	public void closeConnection() throws Exception{
		connection.close();
		statement.close();
	}
}
