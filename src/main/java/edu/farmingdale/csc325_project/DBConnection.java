/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * This class simplifies the connection to the database with the connectDB() method.
 * By using "con = DatabaseConnection.connectDB();", users can save repeating code.
 * This also offers a way to quickly change database details like local or remote paths, 
 * and login credentials. 
 * @author AlexH
 */
public class DBConnection {
    public static Connection connectDB() {
        //String user = "pportal";
        //String pass = "admin";
        try{
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            //Connection con = DriverManager.getConnection("jdbc:sqlserver://24.45.36.120:1433;"
            //            + "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pass);
            //return con;
        }
        catch (Exception e) {
            //javax.swing.JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
