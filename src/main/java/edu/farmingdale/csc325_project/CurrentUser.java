/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import java.time.LocalDate;

/**
 *
 * @author AlexH
 */
public class CurrentUser {
    protected String userID;
    protected String email;
    protected String type;
    protected String firstName;
    protected String lastName;
    protected String status;
    protected String DOB;
    

    public CurrentUser(String email, String type) {
        this.userID = "userID";
        this.email = email;
        this.type = type;
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.status = "status";
        this.DOB = "date";
//LocalDate.now();
    }

    public CurrentUser(String userID, String email, String type, 
            String firstName, String lastName, String DOB, String status) {
        this.userID = userID;
        this.email = email;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.DOB = DOB;
    }

    public String getUserID() { return userID; }
    public String getEmail() { return email; }
    public String getType() { return type; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStatus() { return status; }
    public String getDOB() { return DOB; }

    public void setUserID(String userID) { this.userID = userID; }
    public void setEmail(String email) { this.email = email; }
    public void setType(String type) { this.type = type; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStatus(String status) { this.status = status; }
    public void setDOB(String DOB) { this.DOB = DOB; }
}
