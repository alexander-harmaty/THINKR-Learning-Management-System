/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.io.IOException;
import com.google.cloud.Timestamp;
import java.util.List;

/**
 *
 * @author trintydarbouze
 */
public class Assignment {
    
    protected String title; 
    protected Timestamp dueDate;
    protected Timestamp assignedDate;
    protected String detailsText; 
    protected String detailsFile;
    protected List<String> submissions;
    protected List<String> course; 
 
    

    public Assignment(String title, Timestamp dueDate, Timestamp assignedDate, String detailsText, String detailsFile, List<String> submissions, List<String> course) {
        this.title = title;
        this.dueDate = dueDate;
        this.assignedDate = assignedDate;
        this.detailsText = detailsText;
        this.detailsFile = detailsFile;
        this.submissions = submissions;
        this.course = course;
    } 
    public Assignment(QueryDocumentSnapshot document)
    {
        
        this.dueDate = (Timestamp)document.getData().get("dueDate");
        this.assignedDate = (Timestamp)document.getData().get("assignDate");
        this.detailsText = (String)document.getData().get("detailsText");
        this.submissions = (List<String>)document.getData().get("submissions");
        this.detailsFile = (String)document.getData().get("detailsFile");
        this.title = (String)document.getData().get("title");
        this.course=(List<String>)document.getData().get("course");
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate  = dueDate;
    }

    public Timestamp getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Timestamp assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getDetailsText() {
        return detailsText;
    }

    public void setDetailsText(String detailsText) {
        this.detailsText = detailsText;
    }

    public String getDetailsFile() {
        return detailsFile;
    }

    public void setDetailsFile(String detailsFile) {
        this.detailsFile = detailsFile;
    }
    
    public List<String> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<String> submissions) {
        this.submissions = submissions;
    }
    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }
}
