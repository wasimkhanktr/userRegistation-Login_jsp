package JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String sql = "INSERT INTO user(fullName,email,password,gender,dob,country) VALUES(?,?,?,?,?,?)";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
//        String fullName = request.getParameter("fullname");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String gender = request.getParameter("gender");
//        String dob = request.getParameter("dob");
//        String country = request.getParameter("country");
        
        String fullName = "demo";
        String email = "demo";
        String password = "demo";
        String gender = "demo";
        String dob = "demo";
        String country = "demo";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            // Step 3: Create a PreparedStatement
            ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, dob);
            ps.setString(6, country);

            // Step 4: Execute the query
            int count = ps.executeUpdate();

            if (count == 1) {
                out.println("<h1>Registration Successful!</h1>");
            } else {
                out.println("<h1>Registration failed!</h1>");
            }

        } catch (SQLException e) {
            // Handling SQL Exceptions
            out.println("<h1>Database Error: Registration failed!</h1>");
            out.println("<p>Please contact support.</p>");
            e.printStackTrace(); // This will log the full stack trace in the server logs
        } catch (ClassNotFoundException e) {
            // Handle JDBC Driver not found
            out.println("<h1>Error: JDBC Driver not found!</h1>");
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.close();
    }
}
