/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

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

    public User(String userID, String DOB, String email, String firstName, String lastName, String password, String status, String type) {
        this.userID = userID;
        this.DOB = DOB;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
        this.type = type;
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
