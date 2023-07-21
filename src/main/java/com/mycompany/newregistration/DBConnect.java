
package com.mycompany.newregistration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnect {
    
    
    void connectAndinsert(String fname,String lname,String email,String city,String country,String gender){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerdb","root","");
           String query = "insert into registertable(fname,lname,email,city,country,gender)values(?,?,?,?,?,?)";
           PreparedStatement preparedStmt = conn.prepareStatement(query);
           preparedStmt.setString(1,fname);
           preparedStmt.setString(2,lname);
           preparedStmt.setString(3,email);
           preparedStmt.setString(4,city);
              preparedStmt.setString(5,country);
                 preparedStmt.setString(6,gender);
           int i = preparedStmt.executeUpdate();
           
           if(i>1){
               System.out.println("Data is inserted");
           }
           else{
               System.out.println("No data");
           }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}


