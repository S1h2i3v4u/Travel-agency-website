package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get form values
            String name = request.getParameter("myname1");
            String email = request.getParameter("myemail");
            String phone = request.getParameter("myphone");
            String age = request.getParameter("myage");
            String gender = request.getParameter("mygender");
            String departure = request.getParameter("departuredate");
            String ret = request.getParameter("returndate");
            String pkg = request.getParameter("locations");

            // Handle multiple checkbox selections for destination
            String[] destinations = request.getParameterValues("td");
            String destinationStr = "";
            if (destinations != null) {
                destinationStr = String.join(", ", destinations);
            }

            // Connect to DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travelDB", "root", "suryawanshi777");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO registrations(name, email, phone, age, gender, departure, ret, destination, package) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, age);
            ps.setString(5, gender);
            ps.setString(6, departure);
            ps.setString(7, ret);
            ps.setString(8, destinationStr);
            ps.setString(9, pkg);

            int status = ps.executeUpdate();
            if (status > 0) {
                out.println("<h2>Registration Successful!</h2>");
            } else {
                out.println("<h2>Registration Failed.</h2>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
