package techzn.regsterLogin.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	public static void main(String[] args) {
		
		try {
            // Correct JDBC Driver class name
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("<h1>JDBC Driver Loaded Successfully</h1>");
            
            // Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            System.out.println("<h1>JDBC Connection Established</h1>");
		}
		catch (ClassNotFoundException e) {
			 System.out.println("<h1>JDBC Driver Not Found</h1>");
        e.printStackTrace();
    } catch (SQLException e) {
    	 System.out.println("<h1>JDBC Connection Failed</h1>");
        e.printStackTrace();
    } 
    }
		

}
