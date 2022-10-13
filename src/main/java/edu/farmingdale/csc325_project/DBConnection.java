/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class simplifies the database connection by calling the connectDB() method.
 * The specific DB credentials can be hard-coded into the variables, then users can
 * call "Connection con = DatabaseConnection.connectDB();" to reduce the use of repeating code.
 * @author AlexH
 */
public class DBConnection {
    public static Connection connectDB() {
        
        String ip = "69.123.55.71";
        String databaseName = "Thinkr";
        String user = "admin";
        String pass = "admin";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            Connection con = DriverManager.getConnection("jdbc:sqlserver://" 
                    + ip + ";databaseName=" + databaseName 
                    + ";encrypt=true;trustServerCertificate=true;", user, pass);
            return con;
        }
        catch (Exception e) {
            return null;
        }
        
    }
}
