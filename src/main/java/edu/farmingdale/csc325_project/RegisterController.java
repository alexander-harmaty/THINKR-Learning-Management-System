/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class RegisterController implements Initializable {

    //Declare FXML textField elements
    @FXML
    private TextField textField_email1, textField_email2, textField_password1, 
            textField_password2, textField_firstName, textField_lastName;
    
    //Declare FXML DatePicker element
    @FXML
    private DatePicker datePicker_DOB;
    
    //Declare FXML ComboBox element
    @FXML
    private ComboBox<String> comboBox_accountType;
        
    /**
     * Initializes the controller class & ComboBox.Account types combo box options must be initialized.
     * 
     *
     * @param url 
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize ComboBox options
        String accountTypes[] = {"STUDENT", "PROFESSOR", "ADMIN"};
        ObservableList<String> options = FXCollections.observableArrayList(accountTypes);
        comboBox_accountType.setItems(options);
    }
    
    /**
     * Handle function for back button.
     * 
     * Returns user to login screen.
     * 
     * @throws IOException 
     */
    public void handleButton_back() throws IOException{
        App.setRoot("Login");
    }
    
    /**
     * Handle function for register button.Makes a connection to the DB and creates a record in the users table.
     * 
     * Change view to login screen & auto fill fields.
     * 
     * @throws IOException 
     * @throws java.sql.SQLException 
     */
    public void handleButton_register() throws IOException, SQLException{
        
        try {
            String email1 = textField_email1.getText();
            String email2 = textField_email2.getText();
            String pass1 = textField_password1.getText();
            String pass2 = textField_password2.getText();
            String fName = textField_firstName.getText();
            String lName = textField_lastName.getText();
            LocalDate birthday = datePicker_DOB.getValue();
            String DOB = birthday.toString();
            String type = comboBox_accountType.getValue();

            //check for missmatching email & pass
            if (!email1.equals(email2) || !pass1.equals(pass2)) {
                System.out.println("Please assure email and password match");
                //javax.swing.JOptionPane.showMessageDialog( null, "Please assure email and password match" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            }
            //check for any blank fields
            else if ("".equals(email1) || "".equals(email2) || "".equals(pass1) || "".equals(pass2)
                    || "".equals(fName) || "".equals(lName) || "".equals(type)) {
                System.out.println("Please assure all fields are filled");
                //javax.swing.JOptionPane.showMessageDialog( null, "Please assure all fields are filled" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            }
            //add user
            else {
                Connection con = DBConnection.connectDB();
                Statement st = con.createStatement();
                String query = "insert into users (email, password, type, firstName, lastName, DOB) values"
                        + "(" + email1 + ",'" + pass1 + "','" + type + "','" + fName + "','" + lName + "','" + DOB + "')";
                st.executeQuery(query);

                App.setRoot("Login");
            }
        } catch (NullPointerException e){
            System.out.println("Please assure a date is selected");
        }
    }
}