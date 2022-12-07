package edu.farmingdale.csc325_project;

import java.util.ArrayList;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GradesController extends HomePageController implements Initializable {

    @FXML
    private HBox HBox_t;

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private VBox VBox_tables;

    @FXML
    private TableColumn<Submission, String> tableColumn_assignment;

    @FXML
    private TableColumn<Submission, String> tableColumn_course;

    @FXML
    private TableColumn<Submission, Timestamp> tableColumn_submittedDate;

    @FXML
    private TableColumn<Submission, Integer> tableColumn_grade;

    @FXML
    private TableView<Submission> tableView_grades;

    @FXML
    private TableView<CourseGradeInfo> tableView_courseAvg;

    @FXML
    private TableColumn<CourseGradeInfo, Double> tableColumn_avg;

    @FXML
    private TableColumn<CourseGradeInfo, String> tableColumn_course2;

    @FXML
    private TableColumn<CourseGradeInfo, String> tableColumn_subject;

    private ObservableList<CourseGradeInfo> listOfCourseGradeInfo = FXCollections.observableArrayList();

    private List<CourseGradeInfo> courseGradeInfos = new ArrayList<>();

    private ObservableList<Submission> listOfSubmissions = FXCollections.observableArrayList();

    private List<Submission> submissions = new ArrayList<>();

    public ObservableList<Submission> getListOfSubmissions() {
        return listOfSubmissions;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (App.currentUser.type.equals("PROFESSOR")) {
            VBox_tables.getChildren().remove(0);
        }

        //adds buttons to menu based on user
        updateMenu();

        //set column with cell factory
        tableColumn_assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        tableColumn_submittedDate.setCellValueFactory(new PropertyValueFactory<>("submittedDate"));
        tableColumn_course.setCellValueFactory(new PropertyValueFactory<>("CRN"));
        tableColumn_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        readSubmissionIntoTable();

        tableColumn_subject.setCellValueFactory(new PropertyValueFactory<>("subjectAndCode"));
        tableColumn_course2.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumn_avg.setCellValueFactory(new PropertyValueFactory<>("grade"));

        courseGradeInfos = gradesListBuilder();
        listOfCourseGradeInfo.addAll(courseGradeInfos);
        tableView_courseAvg.setItems(listOfCourseGradeInfo);

        if (App.currentUser.type.equals("PROFESSOR")) {
            setOnMousePressed();
        }
    }

    private void readSubmissionIntoTable() {

        switch (App.currentUser.type) {
            case "STUDENT":
                //if the currentUser ID is found in the submissions
                Submission submission;
                List<QueryDocumentSnapshot> documents;

                //get submissions collection
                ApiFuture<QuerySnapshot> future = App.fstore.collection("submissions").get();

                try {
                    //add collection into list
                    documents = future.get().getDocuments();

                    //checks if document is empty
                    if (!documents.isEmpty()) {

                        //loop through submissions
                        for (QueryDocumentSnapshot document : documents) {

                            //use submission doucment constructor to hold a submission
                            submission = new Submission(document);

                        }
                        //set the tableview to submission list
                        tableView_grades.setItems(listOfSubmissions);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                }
                break;
            case "PROFESSOR":
//                            
                break;

        }

        //declare submission and its document list
    }

    private void setOnMousePressed() {

        tableView_grades.setOnMousePressed((MouseEvent event) -> {

            //check for primary mouse clicks
            if (event.getButton().equals(MouseButton.PRIMARY)) {

                //if click is double click...
                if (event.getClickCount() == 2) {

                    //read selected course CRN
                    Submission submission = tableView_grades.getSelectionModel().getSelectedItem();

                    App.currentSubmission = submission;
                    try {
                        //change view to course
                        App.setRoot("Submission");
                    } catch (IOException ex) {
                    }
//                    
//                    
//                    //declare assignment and its list
//                    Submission submission;
//                    List<QueryDocumentSnapshot> documents;
//                    
//                    //get assignment collection
//                    ApiFuture<QuerySnapshot> future = App.fstore.collection("submissions").get();
//                 
//                    try {
//                        //add collection into list
//                        documents = future.get().getDocuments();
//
//                        //check if empty
//                        if (!documents.isEmpty()) {
//                            
//                            //loop through assignments
//                            for (QueryDocumentSnapshot document : documents) {
//                                
//                                //use assignment document constructor to hold assignment data
//                                submission = new Submission(document);
//
//                                //if the CRN of any course matches the selected course CRN...
//                                if (submission.getAssignment().equals(selectedAssignTitle) && submission.getStudent().equals(App.currentUser.userID)) {
//                                    //set currentAssignment to the selected course
//                                    App.currentSubmission = submission;
//                                    //change view to course
//                                    App.setRoot("Submission");
//                                }
//                            }
//                        }
//                    } 
//                    catch (InterruptedException | ExecutionException | IOException ex) {}
                }
            }
        });

    }

    public List<CourseGradeInfo> gradesListBuilder() {
        List<CourseGradeInfo> classAverages = new ArrayList<>();

        for (Course course : App.currentListOfCourses) {
            CourseGradeInfo gradeInfo = new CourseGradeInfo();
            List<Integer> grades = new ArrayList<>();
            for (Submission sub : submissions) {
                if (course.CRN.equals(sub.CRN) && sub.student.equals(App.currentUser.userID)) {
                    grades.add(sub.grade);
                }
            }
            gradeInfo.title = course.title;
            gradeInfo.subjectAndCode = course.subject + " " + course.section;
            gradeInfo.grade = classAvgCalculator(grades);
            classAverages.add(gradeInfo);
        }
        return classAverages;
    }

    public double classAvgCalculator(List<Integer> grades) {
        int count = grades.size();
        if (count == 0) {
            return 0;
        } else {
            int sum = 0;
            for (int i = 0; i < grades.size(); i++) {
                int x = grades.get(i);
                sum = sum + x;
            }
            return sum / count;
        }
    }

}
