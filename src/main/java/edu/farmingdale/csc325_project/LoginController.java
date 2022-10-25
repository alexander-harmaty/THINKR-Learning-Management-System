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
    
    
    Connection con = null;
    Connection con3 = null;
    Connection con2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    public void handleButton_login() throws IOException {
        //connect to database
        //compare textFields to logins table
        //if an email and password match, 
            //read account type, then switch to appropriate view
            
        //App.setRoot("");
       
    
        String userName = textField_email.getText();
        String passWord = textField_password.getText();
        
        if ("".equals(userName) && "".equals(passWord))
        {
            //javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try 
            {
                con = DBConnection.connectDB();
                Statement st = (Statement) con.createStatement();
                rs = st.executeQuery( "Select * FROM LOGIN WHERE Username = '" + userName + "' AND Password = '" + passWord + "';");

                con2 = DBConnection.connectDB();
                Statement st2 = (Statement) con2.createStatement();
                rs2 = st2.executeQuery( "Select * FROM LOGIN WHERE Email = '" + userName + "' AND Password = '" + passWord + "';");

                if (rs.next() && rs.getString(2).equals(userName) && rs.getString(3).equals(passWord)) 
                {
                    App.currentUser = new CurrentUser(rs.getString(1), rs.getString(5));

                    if (rs.getString(5).toUpperCase().equals("PATIENT"))
                    {           
                            App.setRoot("patientDashboard");
                            //JOptionPane.showMessageDialog(null, "Your login was successful.");
                            //button_login.getScene().getWindow().hide();
                            //Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
                            //Stage mainStage = new Stage();
                            //Scene scene = new Scene(root);
                            //mainStage.setScene(scene);
                            //mainStage.show();
                    }
                    else if (rs.getString(5).toUpperCase().equals("DOCTOR")) 
                    {
                            App.setRoot("doctorDashboard");
                    }
                    else if (rs.getString(5).toUpperCase().equals("LAB")) 
                    {
                            App.setRoot("servicesDashboard");
                    }
                    else if (rs.getString(5).toUpperCase().equals("PHARMACY")) 
                    {
                            App.setRoot("servicesDashboard");
                    }
                    else if (rs.getString(5).toUpperCase().equals("OFFICE")) 
                    {
                            App.setRoot("servicesDashboard");
                    }
                }
                else if (rs2.next() && rs2.getString(4).equals(userName) && rs2.getString(3).equals(passWord)) 
                {
                    App.currentUser = new CurrentUser(rs2.getString(1), rs.getString(5));

                    if (rs2.getString(5).toUpperCase().equals("PATIENT"))
                    {       
                            App.setRoot("patientDashboard");   
                    }
                    else if (rs2.getString(5).toUpperCase().equals("DOCTOR")) 
                    {
                            App.setRoot("doctorDashboard");  
                    }
                    else if (rs2.getString(5).toUpperCase().equals("LAB")) 
                    {
                            App.setRoot("servicesDashboard"); 
                    }
                    else if (rs2.getString(5).toUpperCase().equals("PHARMACY")) 
                    {
                            App.setRoot("servicesDashboard"); 
                    }
                    else if (rs2.getString(5).toUpperCase().equals("OFFICE")) 
                    {
                            App.setRoot("servicesDashboard"); 
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