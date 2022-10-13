/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class simplifies the connection to the database with the connectDB() method.
 * By using "con = DatabaseConnection.connectDB();", users can save on repeating code.
 * This also offers a way to quickly change database details like local or remote paths, 
 * and login credentials. 
 * @author AlexH
 */
public class DBConnection {
    public static Connection connectDB() {
        
        String ip = "24.45.36.120:1433";
        String databaseName = "PatientPortal";
        String user = "pportal";
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
