/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.io.IOException;

/**
 *
 * @author Alexander
 */
public class User {
    protected String userID;
    protected String DOB;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String status;
    protected String type;
    
    public User() {
        this.userID = "";
        this.DOB = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.status = "";
        this.type = "";
    }

    public User(QueryDocumentSnapshot document) {
       
        this.DOB = String.valueOf(document.getData().get("DOB"));
        this.email = String.valueOf(document.getData().get("email"));
        this.firstName = String.valueOf(document.getData().get("firstName"));
        this.firstName = String.valueOf(document.getData().get("lastName"));
        this.lastName = String.valueOf(document.getData().get("type"));
        this.password = String.valueOf(document.getData().get("password"));
        this.userID = String.valueOf(document.getData().get("userID"));
        this.status = String.valueOf(document.getData().get("status"));


    }
    
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
