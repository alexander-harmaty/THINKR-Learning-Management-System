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
public class Submission {

    protected int grade; 
    protected String student; 
    protected String studentComment; 
    protected boolean submitted; 
    protected Timestamp submittedDate;
    protected String teacherFeedback;
    protected String assignment;
    protected String CRN;
    protected String ID;


    public Submission(int grade, String student, String studentComment, boolean submitted, Timestamp submittedDate, String teacherFeedback, String assignment, String course, String ID) {
        
        this.grade = grade;
        this.student = student;
        this.studentComment = studentComment;
        this.submitted = submitted;
        this.submittedDate = submittedDate;
        this.teacherFeedback = teacherFeedback;
        this.assignment = assignment;
        this.CRN = course;
        this.ID = ID;
    }
    
    public Submission(QueryDocumentSnapshot document)
    {
    
        this.grade = Integer.parseInt(document.getData().get("grade").toString());
        this.student = (String)document.getData().get("student");
        this.studentComment = (String)document.getData().get("studentComment");
        this.submitted = (Boolean)document.getData().get("submitted");
        this.submittedDate = (Timestamp)document.getData().get("submittedDate");
        this.teacherFeedback =(String)document.getData().get("teacherFeedback");
        this.assignment = (String)document.getData().get("assignment");
        this.CRN = (String)document.getData().get("CRN");
        this.ID = document.getId();
    }

  

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public Timestamp getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Timestamp submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getTeacherFeedback() {
        return teacherFeedback;
    }

    public void setTeacherFeedback (String teacherFeedback) {
        this.teacherFeedback = teacherFeedback;
    }
    
     public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getCRN() {
        return CRN;
    }

    public void setCRN(String CRN) {
        this.CRN = CRN;
    }
    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
