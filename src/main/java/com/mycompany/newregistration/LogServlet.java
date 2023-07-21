
package com.mycompany.newregistration;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LogServlet extends HttpServlet {
String fname;
String lname;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
      String name=request.getParameter("name");
       String password =request.getParameter("password");
            
            try{
                 
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerdb","root","");
           Statement Stmt = conn.createStatement(); 
           ResultSet rs =Stmt.executeQuery("select * from registertable");
           while(rs.next()){
               fname=rs.getString("fname");
               lname=rs.getString("lname");
           }
            }
            catch(Exception e){
                out.println(e);
            }
          if(name == null || name.equals("") && password == null || password.equals("")){ 
      out.println ("Enter user name or Password!");
      RequestDispatcher rd=request.getRequestDispatcher("login.html"); 
      rd.include(request, response);
        }
        
      else if(name.equals(fname) && password.equals(lname)){

      RequestDispatcher rd=request.getRequestDispatcher("dashboard.html"); 
      rd.include(request, response);
      
      } 
       else
      {    
      out.println ("Error! Enter Correct Username or Password");
      RequestDispatcher rd=request.getRequestDispatcher("login.html");
      rd.include(request, response);
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
