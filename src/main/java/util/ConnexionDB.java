package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConnexionDB {

    private static Connection connexion;

    
    static {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogue", "root", "123456"); 
    	}catch (SQLException ex) {
            System.err.println("Not found or Incorrect Request");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    public static Connection Connect() {
    	return connexion; 
    }



    


}