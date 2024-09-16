package techzn.regsterLogin.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform database validation for email and password here
        String fullname = getFullname(email, password);

        if (fullname != null) {
        	 // If valid, create a session and store the full name
            HttpSession session = request.getSession();
            session.setAttribute("fullname", fullname);  // Store full name in session

            
            RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
            rd.include(request, response);
            
            
        } else {
            response.sendRedirect("Login.jsp?error=Invalid email or password");
        }
    }

    // Method to check if the user is valid and retrieve the full name
    private String getFullname(String email, String password) {
        String fullname = null;
        
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        
        // SQL query to validate email and password
        String sql = "SELECT fullname FROM user WHERE email = ? AND password = ?";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Create a PreparedStatement for the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a user exists with the provided email and password
            if (resultSet.next()) {
                fullname = resultSet.getString("fullname");  // Retrieve the full name of the user
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fullname;  // Return the full name if user is valid, else null
    }
}
