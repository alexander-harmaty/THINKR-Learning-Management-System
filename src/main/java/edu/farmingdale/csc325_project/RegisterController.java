/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private TextField textField_email1, textField_email2, textField_password1, 
            textField_password2, textField_firstName, textField_lastName;
    
    @FXML
    private DatePicker datePicker_DOB;
    
    @FXML
    private TextField textField_accountType;
    
    public void handleButton_back() throws IOException{
        App.setRoot("Login");
    }
    
    public void handleButton_register() throws IOException{
        //connect to database
        //create records in logins and users table
        //setRoot to login screen, & autofill fields
        
        App.setRoot("Login");
    }
}