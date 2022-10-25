/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class LoginController implements Initializable {
    
    //Declare FXML textField elements
    @FXML
    private TextField textField_email, textField_password;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Handle function for register button.
     * 
     * Changes view to register page.
     * 
     * @throws IOException 
     */
    public void handleButton_register() throws IOException {
        App.setRoot("Register");
    }
    
    /**
     * Handle function for login button.
     * 
     * Takes text from text fields for email and password.
     * Then compares email and passwords to logins table to find a match.
     * If match is found, account type is read and switched to
     * 
     * @throws IOException 
     */
    public void handleButton_login() throws IOException {
                
        String email = textField_email.getText();
        String pass = textField_password.getText();
        
        if ("".equals(email) && "".equals(pass))
        {
            //javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try 
            {
                Connection con = DBConnection.connectDB();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from users where email = '"
                        + email + "' and password = '" + pass + "';");
                
                if (rs.next() && rs.getString(2).equals(email) && rs.getString(3).equals(pass)) 
                {
                    App.currentUser = new CurrentUser(rs.getString(1), rs.getString(2), 
                            rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8));

                    switch (rs.getString(4).toUpperCase()) {
                        case "STUDENT":           
                            App.setRoot("StudentView");
                            break;
                        case "PROFESSOR":
                            App.setRoot("ProfessorView");
                            break;
                        case "ADMIN":
                            App.setRoot("AdminView");
                            break;
                        default:
                            break;
                    }
                }
                else
                { 
                    //javax.swing.JOptionPane.showMessageDialog( null, "Incorrect username or password." , "Error", 
                    //javax.swing.JOptionPane.ERROR_MESSAGE );     
                }
            } catch (IOException | SQLException e) {
                //javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            }
        } 
    }
}