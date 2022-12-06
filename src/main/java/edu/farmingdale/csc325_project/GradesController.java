package edu.farmingdale.csc325_project;

import java.util.ArrayList;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Submission, String> tableColumn_assignment;

    @FXML
    private TableColumn<Submission, String> tableColumn_course;

    @FXML
    private TableColumn<Submission, Timestamp> tableColumn_submittedDate;

    @FXML
    private TableColumn<Submission, Integer> tableColumn_grade;

    @FXML
    private TableView<Submission> tableView_grades;

    private ObservableList<Submission> listOfSubmissions = FXCollections.observableArrayList();

    public ObservableList<Submission> getListOfSubmissions() {
        return listOfSubmissions;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set column with cell factory
        tableColumn_assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        tableColumn_submittedDate.setCellValueFactory(new PropertyValueFactory<>("submittedDate"));
        tableColumn_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableColumn_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
       
        //set list of grades
        readGradesIntoTable();
        //adds buttons to menu based on user
        updateMenu();
    }
    
       
    private void readGradesIntoTable() {
        
        //declare submission and its document list
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
                    
                    //if the currentUser ID is found in the submissions
                    if (App.currentUser.userID.equals(submission.student)){
                        //add to the list of submissions
                        listOfSubmissions.add(submission);
                    }
                    
            
                }
                //set the tableview to submission list
                tableView_grades.setItems(listOfSubmissions);
            }
        } 
        catch (InterruptedException | ExecutionException ex) {}
    }

    public List<Integer> gradesListBuilder() {
        List<Integer> grades = new ArrayList<>();
        for (Submission sub:listOfSubmissions) {
            grades.add(sub.grade);
        }
        return grades;
    }
    
    public double classAvgCalculator(List<Integer> grades) {
        int count = grades.size();
        int sum = 0;
        for( int i =0; i<grades.size();i++)
        {
           int  x = grades.get(i);
            sum = sum +x; 
        }

        return sum/count;

    }
    
}
