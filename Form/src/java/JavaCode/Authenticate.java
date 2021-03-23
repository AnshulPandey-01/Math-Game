/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaCode;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author anshu
 */
@WebServlet(name = "Authenticate", urlPatterns = {"/SignIn"})
public class Authenticate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String inputPassword = request.getParameter("password");

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math_game", "root", "anshul");
            String a = "select name, password, high_score, photo from user where email = ?";
            PreparedStatement st = conn.prepareStatement(a);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString(2);
                StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
                boolean isPasswordCorrect = encryptor.checkPassword(inputPassword, storedPassword);

                if (isPasswordCorrect) {
                    String username = rs.getString(1);
                    String highScore = rs.getString(3);
                    InputStream image = rs.getBinaryStream(4);

                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("userName", username);
                    session.setAttribute("highScore", highScore);
                    session.setAttribute("photo", image);
                    response.sendRedirect("MathGame.jsp");
                    System.out.println("Login successfully");

                } else {
                    throw new Exception("Incorrect password");
                }
            } else {
                throw new Exception("User not registered");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR in closing: " + ex.getMessage());
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
