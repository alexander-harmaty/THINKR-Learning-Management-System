/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
import java.net.URL;
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
    }
    
}
