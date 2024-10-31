package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnexionDB {

    private static Connection connexion;

    
    static {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connexion = DriverManager.getConnection("jdbc:mysql://localhost/catalogue", "root", "123456"); 
    	}catch (SQLException ex) {
            System.err.println("Server/DB Not found or Incorrect Request");
        } catch (ClassNotFoundException e) {
        	 System.err.println("Driver Not found or Incorrect Request");
			e.printStackTrace();
		}
    }
    public static  Connection Connect() { 
    	return connexion; 
    }
}