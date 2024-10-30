package repository;

import model.Category;
import util.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import form.CategoryFormBean;

public class CategoryRepository {

    private final ConnexionDB connexionDB;

    public CategoryRepository() {
        this.connexionDB = new ConnexionDB();
    }

    public List<Category> findAllCategories() { 
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (Connection conx = connexionDB.Connect();
             Statement stmt = conx.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Category> searchByName(String name) {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category WHERE name LIKE ?";

        try (Connection conn = connexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void save(Category category) {
        String query = "INSERT INTO Category (name) VALUES (?)";

        try (Connection conn = connexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        String query = "DELETE FROM Category WHERE id = ?";

        try (Connection conn = connexionDB.Connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
