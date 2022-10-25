/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class simplifies the database connection by calling the connectDB() method.
 * The specific DB credentials can be hard-coded into the variables, then users can
 * call "Connection con = DatabaseConnection.connectDB();" to reduce the use of repeating code.
 * @author AlexH
 */
public class DBConnection {
    public static Connection connectDB() {
        
        //change between remote and local
        boolean remote = false;
        
        //Connection
        Connection con = null;
        
        //remote connection stats and credentials
        String ip = "69.123.55.71";
        String databaseName = "Capstone";
        String user = "admin";
        String pass = "admin";
        String dbRemoteURL = "jdbc:sqlserver://" + ip + ";databaseName=" 
                + databaseName + ";encrypt=true;trustServerCertificate=true;";
        
        //local connection stats and credentials
        String dbLocalURL = "jdbc:ucanaccess://.//LMS.accdb";
        
        try{
            if (remote) {
                //Remote DB connection
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
                con = DriverManager.getConnection(dbRemoteURL, user, pass);
                return con;
            }
            else {
                //local DB connection
                con = DriverManager.getConnection(dbLocalURL);
                return con;
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            return null;
        }
        
    }
}
