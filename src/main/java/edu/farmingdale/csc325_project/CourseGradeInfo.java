package edu.farmingdale.csc325_project;

/**
 *
 * @author AlexH
 */
public class CourseGradeInfo {
    public String subjectAndCode;
    public String title;
    public double grade;
    
    public CourseGradeInfo() {
        this.subjectAndCode = "";
        this.title = "";
        this.grade = 0;
    }

    public CourseGradeInfo(String subjectAndCode, String title, double grade) {
        this.subjectAndCode = subjectAndCode;
        this.title = title;
        this.grade = grade;
    }

    public String getSubjectAndCode() {
        return subjectAndCode;
    }

    public void setSubjectAndCode(String subjectAndCode) {
        this.subjectAndCode = subjectAndCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    
}
