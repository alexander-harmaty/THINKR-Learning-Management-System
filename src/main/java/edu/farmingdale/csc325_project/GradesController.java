package edu.farmingdale.csc325_project;

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
        
        tableColumn_assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        tableColumn_submittedDate.setCellValueFactory(new PropertyValueFactory<>("submittedDate"));
        tableColumn_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableColumn_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        readGradesIntoTable();
        updateMenu();
    }
    
       
    private void readGradesIntoTable() {
        
        
        Submission submission;
        List<QueryDocumentSnapshot> documents;
        
        
        ApiFuture<QuerySnapshot> future = App.fstore.collection("submissions").get();
        
        try {
            
            documents = future.get().getDocuments();

            
            if (!documents.isEmpty()) {
                
                
                for (QueryDocumentSnapshot document : documents) {
                    
                  
                    submission = new Submission(document);
                    
                    
                    if (App.currentUser.userID.equals(submission.student)){
                     
                        listOfSubmissions.add(submission);
                    }
                    
            
                }
                
                tableView_grades.setItems(listOfSubmissions);
            }
        } 
        catch (InterruptedException | ExecutionException ex) {}
    }
    
}
