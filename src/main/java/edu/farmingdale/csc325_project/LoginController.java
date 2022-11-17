/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
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
     * Handle function for login button.Takes text from text fields for email and password.Then compares email and passwords to logins table to find a match.
     * 
     * If match is found, account type is read and switched to
     * 
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     * @throws java.util.concurrent.ExecutionException 
     */
    public void handleButton_login() throws IOException, InterruptedException, ExecutionException {
                
        String inputEmail = textField_email.getText();
        String inputPass = textField_password.getText();
        String docEmail;
        String docPass;
        String docType;
        
        if (!"".equals(inputEmail) || !"".equals(inputPass))
        {
            //asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future =  App.fstore.collection("users").get();
            // future.get() blocks on response
            List<QueryDocumentSnapshot> documents;
            
            try 
            {
                documents = future.get().getDocuments();
            
                if(!documents.isEmpty())
                {
                    for (QueryDocumentSnapshot document : documents) 
                    {
                        docEmail = String.valueOf(document.getData().get("email"));
                        docPass = String.valueOf(document.getData().get("password"));
                        docType = String.valueOf(document.getData().get("type"));
                        
                        if (docEmail.equals(inputEmail) && docPass.equals(inputPass)) 
                        {
                            //App.currentUser = new CurrentUser();

                            switch(docType) 
                            {
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
                    }
                    javax.swing.JOptionPane.showMessageDialog( null, "Incorrect username or password." , "Error", javax.swing.JOptionPane.ERROR_MESSAGE );  
                }
                else
                {
                   System.out.println("No data"); 
                }
                
            } 
            catch (IOException e) {}
        } 
        else
        {
            javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
        }
    }
}