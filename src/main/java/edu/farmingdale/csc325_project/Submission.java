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
    
    protected String ID; 
    protected int grade; 
    protected String student; 
    protected String studentComment; 
    protected boolean submitted; 
    protected Timestamp submittedDate;
    protected String teacherFeedback;

    public Submission(String ID, int grade, String student, String studentComment, boolean submitted, Timestamp submittedDate, String teacherFeedback) {
        this.ID = ID;
        this.grade = grade;
        this.student = student;
        this.studentComment = studentComment;
        this.submitted = submitted;
        this.submittedDate = submittedDate;
        this.teacherFeedback = teacherFeedback;
    }
    
    public Submission(QueryDocumentSnapshot document)
    {
        
        this.ID = (String)document.getData().get("dueDate");
        this.grade = Integer.parseInt(document.getData().get("grade").toString());
        this.student = (String)document.getData().get("detailsText");
        this.studentComment = (String)document.getData().get("studentComment");
        this.submitted = (Boolean)document.getData().get("detailsFile");
        this.submittedDate = (Timestamp)document.getData().get("title");
        this.teacherFeedback =(String)document.getData().get("course");
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
    
    
    
}
