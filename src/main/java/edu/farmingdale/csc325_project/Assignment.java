/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author trintydarbouze
 */
public class Assignment {
    
    protected int ID; 
    protected String assignDate; 
    protected String details; 
    protected String grade; 
    protected String professor; 
    protected List<String> students;

    public Assignment(int ID, String assignDate, String details, String grade, String professor, List<String> students) {
        this.ID = ID;
        this.assignDate = assignDate;
        this.details = details;
        this.grade = grade;
        this.professor = professor;
        this.students = students;
    }
    
    
    
    
    
    
}
