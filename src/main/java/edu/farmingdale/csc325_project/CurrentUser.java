/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.io.IOException;
//import java.time.LocalDate;

/**
 * CurrentUser class holds the data of the current user from the DB.
 * 
 * Objects of this class should only be declared in the App.java,
 * and initialized in the LoginController.
 * 
 * @author AlexH
 */
public class CurrentUser extends User {

    public CurrentUser() {
        this.userID = "";
        this.DOB = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.status = "";
        this.type = "";
    }

    public CurrentUser(User user) {
        this.userID = user.getUserID();
        this.DOB = user.getDOB();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = "PROTECTED";
        this.status = user.getStatus();
        this.type = user.getType();
    }
    
    
    
    public CurrentUser(String userID, String DOB, String email, String firstName, String lastName, String status, String type) {
        this.userID = userID;
        this.DOB = DOB;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = "PROTECTED";
        this.status = status;
        this.type = type;
    }
    
    public void logOut() throws IOException {
        this.userID = "";
        this.DOB = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.status = "";
        this.type = "";
        
        //switches user to login controller
        App.setRoot("LoginRegisterController");
    }
}
