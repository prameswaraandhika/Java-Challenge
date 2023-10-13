package org.example.challenge3.sql;

import org.example.challenge3.model.Merchant;
import org.example.challenge3.model.Order;
import org.example.challenge3.model.Product;
import org.example.challenge3.model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andhika Prameswara <prameswaara@gmail.com>
 */
public class ConnectionManager {

	private static final String USER = "postgres";
	private static final String PASSWORD = "dikatampan";


	public static Connection getConnection(String db) {
		String driver = "org.postgresql.Driver";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db, USER, PASSWORD);
			System.out.println("Database connection succed!");
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, null, e);
			System.out.println("Database connection failed!");
		}
		return con;
	}

	public static void main(String[] args) {
//		createUser(new Users(UUID.randomUUID(), "dhikap", "prameswaara@gmail.com", "dikatampan"));
//		createMerchant(new Merchant(UUID.randomUUID(), "AzelFood", "Jakarta", true));
//		createProduct(new Product(UUID.randomUUID(), "Nasi Goreng",  15000.0, UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349")));
//		createProduct(new Product(UUID.randomUUID(), "Mie Goreng", 13000.0, UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349")));
//		createProduct(new Product(UUID.randomUUID(), "Nasi + Ayam", 18000.0, UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349")));
//		createProduct(new Product(UUID.randomUUID(), "Es Teh Manis", 3000.0, UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349")));
//		createProduct(new Product(UUID.randomUUID(), "Es Jeruk", 5000.0, UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349")));

	}

	private static void createProduct(Product product) {
		String insertSql = "INSERT INTO products (id, product_name, price, merchant_id) VALUES (?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
			preparedStatement.setObject(1, product.getId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setObject(4, product.getMerchantId());
			preparedStatement.executeUpdate();
			System.out.println("Product inserted successfully.");
		} catch (SQLException e) {
			throw new RuntimeException("Failed to insert product into the database.", e);
		}
	}

	private static void createMerchant(Merchant merchant) throws SQLException {
		String query = "INSERT INTO public.merchants(id, merchant_name, merchant_location, is_open) VALUES (?, ?, ?, ?);";

		try (Connection conn = ConnectionManager.getConnection("binarfud"); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setObject(1, merchant.getId());
			preparedStatement.setString(2, merchant.getMerchantName());
			preparedStatement.setString(3, merchant.getMerchantLocation());
			preparedStatement.setBoolean(4, merchant.isOpen());
			preparedStatement.executeUpdate();
			System.out.println("Merchant created successfully.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	private static void createUser(Users users) throws SQLException {
		String query = "INSERT INTO public.users(id, username, email_address, password) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setObject(1, users.getId());
			preparedStatement.setString(2, users.getUsername());
			preparedStatement.setString(3, users.getEmailAdress());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.executeUpdate();
			System.out.println("User created successfully.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
