/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.io.IOException;

/**
 *
 * @author skysn
 */
public class Course {
    protected int CRN;
    protected int code;
    protected int credits;
    protected int room;
    protected String building;
    protected String professor;
    protected String section;
    protected String[] students;
    protected String subject;
    protected String title;

    public Course(int CRN, int code, int credits, int room, String building, String professor, String section, String[] students, String subject, String title) {
        this.CRN = CRN;
        this.code = code;
        this.credits = credits;
        this.room = room;
        this.building = building;
        this.professor = professor;
        this.section = section;
        this.students = students;
        this.subject = subject;
        this.title = title;
    }
    
    public Course(QueryDocumentSnapshot document)
    {
        this.CRN = (int) document.get("CRN");
        this.code = (int) document.get("code");
        this.credits = (int) document.get("credits");
        this.room = (int) document.get("room");
        this.building = (String)document.get("building");
        this.professor = (String)document.get("professor");
        this.section = (String)document.get("section");
        this.students = (String[])document.get("students");
        this.subject = (String)document.get("subject");
        this.title = (String)document.get("title");
    }
    
    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String[] getStudents() {
        return students;
    }

    public void setStudents(String[] students) {
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
            
}
