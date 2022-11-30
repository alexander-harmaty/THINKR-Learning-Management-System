/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author trintydarbouze
 */
public class Announcement {
    
    protected String announcement; 
    protected Timestamp postedDate;

    public Announcement(String announcement, Timestamp postedDate) {
        this.announcement = announcement;
        this.postedDate = postedDate;
    }
    
    public Announcement(QueryDocumentSnapshot document) {
        this.announcement = (String)document.getData().get("announcement");
        this.postedDate = (Timestamp)document.getData().get("postedDate");
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Timestamp getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Timestamp postedDate) {
        this.postedDate = postedDate;
    }
    
    

    
}
