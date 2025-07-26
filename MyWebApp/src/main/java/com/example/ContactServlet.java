package com.example;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("myname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB (change database name, user, and password accordingly)
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travelDB", "root", "suryawanshi777"
            );

            String query = "INSERT INTO contacts(name, email, subject, message) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, subject);
            pst.setString(4, message);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("<script>alert('Message sent successfully!'); window.location='contact.html';</script>");
            } else {
                out.println("<script>alert('Failed to send message.'); window.location='contact.html';</script>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}

