package techzn.regsterLogin.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String sql = "INSERT INTO user(fullName,email,password,gender,dob,country) VALUES(?,?,?,?,?,?)";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve form parameters
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String country = request.getParameter("country");

        Connection con = null;
        PreparedStatement ps = null;
        RequestDispatcher rd=null;
        
        out.println("<h1>" + fullName + " " + email + "</h1>");

        try {
            // Correct JDBC Driver class name
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<h1>JDBC Driver Loaded Successfully</h1>");
            
            // Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            out.println("<h1>JDBC Connection Established</h1>");
            
            // Create a PreparedStatement
            ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, dob);
            ps.setString(6, country);

            // Execute the query
            int count = ps.executeUpdate();

            if (count == 1) {
//            	req.getRequestDispatcher("Login.jsp").forward(req, resp);
            	response.sendRedirect("Login.jsp");


                out.println("<h1>Registration Successful!</h1>");
      
            } else {
                out.println("<h1>Registration Failed!</h1>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
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
