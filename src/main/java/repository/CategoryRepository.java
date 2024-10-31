package repository;

import model.Category;
import util.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryRepository {

    @SuppressWarnings("unused")
	private final ConnexionDB connexionDB;

    public CategoryRepository() {
        this.connexionDB = new ConnexionDB();
    }

    public List<Category> findAllCategories() { 
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try{
        	Connection conx = ConnexionDB.Connect();
        
             Statement stmt = conx.createStatement(); 
             ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
        	System.out.println("Error in findAllCategories\n"+e);
            e.printStackTrace();
        }
        System.out.println("Size of categories : "+categories.size());
        return categories;
    }

    public void addCategory(String name, String description) {
		String query="INSERT INTO categories(name,description) VALUES(?,?)";
		try {
			Connection conx = ConnexionDB.Connect(); 
			PreparedStatement ps=conx.prepareStatement(query);
			ps.setString(1,name);
			ps.setString(2, description); 
			ps.executeUpdate();
			System.out.println("ADD CATEGORY"); 
		}catch (SQLException e) {
			System.out.println("Add Categorie ERROR ------>: \n"+e);
		}
	}
    public List<Category> searchByName(String name) {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Categories WHERE name LIKE ?";

        try {
        	Connection conx = ConnexionDB.Connect();
             PreparedStatement stmt = conx.prepareStatement(query);

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("before");
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
                System.out.println("after "+category.getName()); 
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void save(Category category) {
        String query = "INSERT INTO Categories (name) VALUES (?)";

        try {Connection conn = ConnexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        String query = "DELETE FROM Categories WHERE id = ?";

        try {
        	Connection conn = ConnexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("DELETE CATEGORY");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
