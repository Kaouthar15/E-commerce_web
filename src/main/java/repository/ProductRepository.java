package repository;

import model.Category;
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
        String query = "SELECT p.id, p.name, p.price, p.photo, c.id AS category_id, c.name AS category_name "
                     + "FROM products p JOIN categories c ON p.id_category = c.id";

        try {
        	
        	Connection conx = ConnexionDB.Connect();
             Statement stmt = conx.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setPhoto(rs.getString("photo"));
                
                
                Category category = new Category();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("category_name"));
                product.setCategory(category);
                
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error in findAllProducts\n" + e);
            e.printStackTrace();
        }
        
        System.out.println("Size of products : " + products.size());
        return products;
    }

 
    public void addProduct(String name, Double price ,String photo,Long idCat) {
    	System.out.println("ADD  : "); 
		String query="INSERT INTO products(name,price,photo,id_category) VALUES(?,?,?,?)";
		try {
			Connection conx = ConnexionDB.Connect(); 
			PreparedStatement ps=conx.prepareStatement(query);
			ps.setString(1,name); 
			ps.setDouble(2, price); 
			ps.setString(3, photo); 
			ps.setLong(4, idCat);  
			ps.executeUpdate();
			System.out.println("ADD Product"); 
			System.out.println(idCat);
		}catch (SQLException e) {
			System.out.println("Add Product ERROR ------>: \n"+e); 
			}
	}
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, c.name AS category_name FROM products p JOIN categories c ON p.id_category = c.id WHERE p.name LIKE ?";

        try {Connection conx = ConnexionDB.Connect(); 
             PreparedStatement stmt = conx.prepareStatement(query);
             
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPhoto(rs.getString("photo")); 
                product.setPrice(rs.getDouble("price"));
                product.setCategory(new Category(rs.getLong("id_category"), rs.getString("category_name")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
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
