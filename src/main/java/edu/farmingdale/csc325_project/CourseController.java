package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import io.github.palexdev.materialfx.controls.MFXTableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import io.github.palexdev.materialfx.controls.MFXButton;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import com.google.cloud.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.layout.VBox;
import org.controlsfx.control.action.ActionUtils;

public class CourseController extends HomePageController implements Initializable {

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;
    
    @FXML
    private MFXButton button_addAnnounce;
    
    @FXML
    private MFXButton button_createAssihgnment;

    @FXML
    private Label label_classTitle;

   @FXML
    private TableView<Announcement> tableView_Announce;

    @FXML
    private MFXTableView<?> tableView_Course;

    @FXML
    private TableView<Assignment> tableView_assignments;

    @FXML
    private TableColumn<Assignment, Timestamp> tableColumn_due;

    @FXML
    private TableColumn<Assignment, String> tableColumn_title;
    
    @FXML
    private TableColumn<Announcement,Timestamp> tableColumn_posted;
    
    @FXML
    private TableColumn<Announcement, String> tableColumn_announcements;
    
    
    
    private ObservableList<Assignment> listOfAssignments = FXCollections.observableArrayList();
    private ObservableList<Announcement> listOfAnnouncements = FXCollections.observableArrayList();

    public ObservableList<Assignment> getListOfAssignment() {
        return listOfAssignments;
    }
    
    public ObservableList<Announcement> getListOfAnnouncements() {
        return listOfAnnouncements;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //add buttons to menu based on current user
        updateMenu();
        
        //set course title label
        label_classTitle.setText(
                App.currentCourse.subject + "-" + 
                App.currentCourse.code + ": " + 
                App.currentCourse.title
        );
        
        //set columns with cell factory
        tableColumn_due.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        tableColumn_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        tableColumn_posted.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
        tableColumn_announcements.setCellValueFactory(new PropertyValueFactory<>("announcement"));

                
        //set list of assignments
        readAssignmentsIntoTable();
        readAnnouncementsIntoTable();
        
        //set code to switch to assignment view after double clicking a table selection
        setOnMousePressed();
    }

    private void readAssignmentsIntoTable() {
        
        //declare assignment and its documents list
        Assignment assignment;
        List<QueryDocumentSnapshot> documents;
        
        //get assignments collection
        ApiFuture<QuerySnapshot> future = App.fstore.collection("assignments").get();
        
        try {
            //add collection into list
            documents = future.get().getDocuments();

            //check if empty
            if (!documents.isEmpty()) {
                
                //loop through assignments
                for (QueryDocumentSnapshot document : documents) {
                    
                    //use assignment document constructor to hold assignment data
                    assignment = new Assignment(document);
                    
                    //loop through the course field within assignment 
                    for (int i = 0; i < assignment.getCourse().size(); i++) {
                        
                        //if the CRN of the current course matches any CRN assigned within assignment...
                        if (App.currentCourse.getCRN().equals(assignment.getCourse().get(i))) {
                            //add assignment to list
                            listOfAssignments.add(assignment);
                        }
                    }
                }
                //set tableview to assignments list
                tableView_assignments.setItems(listOfAssignments);
            }
        } 
        catch (InterruptedException | ExecutionException ex) {}
    }
    
    private void readAnnouncementsIntoTable() {
        
        //declare announcement and course and its documents list
        Announcement announcement;
        List<QueryDocumentSnapshot> documents_announcement;
       
        List<String> courseAnnouncementIDs=App.currentCourse.announcements; 
        
        //get announcement collection
        ApiFuture<QuerySnapshot> future = App.fstore.collection("announcements").get();
      
        
        try {
            //add collection into list
            documents_announcement = future.get().getDocuments();
           

            //check if empty
            if (!documents_announcement.isEmpty()) {
                
                //loop through announcements
                for (QueryDocumentSnapshot document : documents_announcement) {
                    
                    
                    
                    document.getId();
                    for(String announcementID: App.currentCourse.announcements)
                    {
                        if(announcementID.equals(document.getId()))
                        {
                            //use announcements document constructor to hold assignment data
                            announcement = new Announcement(document);
                            listOfAnnouncements.add(announcement);
                        }
                    }
                    
                   
                }
                //set tableview to assignments list
                tableView_Announce.setItems(listOfAnnouncements);
            }
        } 
        catch (InterruptedException | ExecutionException ex) {}
        
        
        
    }
    private void setOnMousePressed() {
        
        tableView_assignments.setOnMousePressed((MouseEvent event) -> {
            
            //check for primary mouse clicks
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                
                //if click is double click...
                if (event.getClickCount() == 2) {
                    
                    //read selected course CRN
                    String selectedAssignTitle = tableView_assignments.getSelectionModel().getSelectedItem().getTitle();

                    //declare assignment and its list
                    Assignment assignment;
                    List<QueryDocumentSnapshot> documents;
                    
                    //get assignment collection
                    ApiFuture<QuerySnapshot> future = App.fstore.collection("assignments").get();
                 
                    try {
                        //add collection into list
                        documents = future.get().getDocuments();

                        //check if empty
                        if (!documents.isEmpty()) {
                            
                            //loop through assignments
                            for (QueryDocumentSnapshot document : documents) {
                                
                                //use assignment document constructor to hold assignment data
                                assignment = new Assignment(document);

                                //if the CRN of any course matches the selected course CRN...
                                if (assignment.getTitle().equals(selectedAssignTitle)) {
                                    //set currentAssignment to the selected course
                                    App.currentAssignment = assignment;
                                    //change view to course
                                    App.setRoot("Assignment");
                                }
                            }
                        }
                    } 
                    catch (InterruptedException | ExecutionException | IOException ex) {}
                }
            }
        });
        
    }
    
    private void createAssignment()
    {
        
    }
    
    private void createAnnouncement()
    {
        
    }
    
}
