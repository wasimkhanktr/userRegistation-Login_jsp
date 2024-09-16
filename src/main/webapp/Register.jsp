<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration Form</title>
    <link rel="stylesheet" href="./register.css">
</head>
<body>
    <h1>User Registration Form</h1>
    
    <form action="register" method="post">
        <table>
            <tr>
                <td>Full Name:</td>
                <td><input type="text" name="fullname" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                    <input type="radio" name="gender" value="Male" required> Male
                    <input type="radio" name="gender" value="Female" required> Female
                </td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td>
                    <input type="date" name="dob" id="">
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    <select name="country" required>
                        <option value="India">India</option>
                        <option value="USA">USA</option>
                        <option value="UK">UK</option>
                        <option value="Canada">Canada</option>
                        <option value="Australia">Australia</option>
                        <option value="Germany">Germany</option>
                        <option value="France">France</option>
                        <option value="Japan">Japan</option>
                        <option value="China">China</option>
                        <option value="Brazil">Brazil</option>
                        <option value="South Africa">South Africa</option>
                        <option value="Mexico">Mexico</option>
                        <option value="Russia">Russia</option>
                        <option value="Italy">Italy</option>
                    </select>                    
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Register"></td>
            </tr>
        </table>
    </form>

    <!-- Add login button below the form -->
    <div class="login-redirect">
        <p>Already have an account? <a href="login.jsp">Login Here</a></p>
    </div>

</body>
</html>
