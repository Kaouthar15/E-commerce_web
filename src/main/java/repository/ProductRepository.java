package repository;

import model.Product;
import util.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepository {

    @SuppressWarnings("unused")
	private final ConnexionDB connexionDB;

    public ProductRepository() {
        this.connexionDB = new ConnexionDB();
    }

    public List<Product> findAllProducts() { 
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try{
        	Connection conx = ConnexionDB.Connect();
        
             Statement stmt = conx.createStatement(); 
             ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setPhoto(rs.getString("photo"));
                products.add(product);
            }
        } catch (SQLException e) {
        	System.out.println("Error in findAllProducts\n"+e);
            e.printStackTrace();
        }
        System.out.println("Size of products : "+products.size());
        return products;
    }
 
    public void addProduct(String name, Double price ,String photo) {
		String query="INSERT INTO products(name,price,photo,id_category) VALUES(?,?,?,?)";
		try {
			Connection conx = ConnexionDB.Connect(); 
			PreparedStatement ps=conx.prepareStatement(query);
			ps.setString(1,name); 
			ps.setDouble(2, price); 
			ps.setString(3, photo); 
			ps.setLong(4, 1);
			ps.executeUpdate();
			System.out.println("ADD Product"); 
		}catch (SQLException e) {
			System.out.println("Add Product ERROR ------>: \n"+e); 
		}
	}
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";

        try {
        	Connection conx = ConnexionDB.Connect();
             PreparedStatement stmt = conx.prepareStatement(query);

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("before");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPhoto("file");
                product.setPrice(rs.getDouble("price"));
                products.add(product);
                System.out.println("after "+product.getName()); 
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void save(Product category) {
        String query = "INSERT INTO Products (name) VALUES (?)";

        try {Connection conn = ConnexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        String query = "DELETE FROM Products WHERE id = ?";

        try {
        	Connection conn = ConnexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("DELETE Product");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
