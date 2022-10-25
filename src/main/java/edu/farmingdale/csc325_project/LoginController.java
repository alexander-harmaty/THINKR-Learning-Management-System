/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private Button button_login, button_register;

    @FXML
    private TextField textField_email, textField_password;
    
    public void handleButton_register() throws IOException {
        App.setRoot("Register");
    }
    
    
    
    public void handleButton_login() throws IOException {
        //connect to database
        //compare textFields to logins table
        //if an email and password match, 
            //read account type, then switch to appropriate view
            
        //App.setRoot("");
       
        Connection con = null;
        ResultSet rs = null;
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
                con = DBConnection.connectDB();
                Statement st = (Statement) con.createStatement();
                rs = st.executeQuery("Select * FROM USERS WHERE Email = '" + email + "' AND Password = '" + pass + "';");

                if (rs.next() && rs.getString(1).equals(email) && rs.getString(2).equals(pass)) 
                {
                    App.currentUser = new CurrentUser(rs.getString(1), rs.getString(2));

                    switch (rs.getString(3).toUpperCase()) {
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
            } catch (Exception e) {
                //javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            }
        } 
    }
}