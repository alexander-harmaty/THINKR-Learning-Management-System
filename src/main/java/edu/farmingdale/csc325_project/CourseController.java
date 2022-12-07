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
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.layout.VBox;
import org.controlsfx.control.action.ActionUtils;

public class CourseController extends HomePageController implements Initializable {

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;
    
    @FXML
    private VBox VBox_center;
    
    @FXML
    private VBox VBox_copy;
    
    @FXML
    private MFXButton button_addAnnounce;
    
    @FXML
    private MFXButton button_createAssignment;

    @FXML
    private Label label_classTitle;
    
    @FXML 
    private TextArea newAnnouncement = new TextArea();

   @FXML
    private TableView<Announcement> tableView_Announce;

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
    
    @FXML
    private MFXButton button_confirm = new MFXButton("Confirm");
    
    
    
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
    
        
        switch(App.currentUser.type)
        {
            case "STUDENT":
                button_addAnnounce.setVisible(false);
                button_createAssignment.setVisible(false);
                break; 
                
                
            case "PROFESSOR":
                button_addAnnounce.setVisible(true);
                button_createAssignment.setVisible(true);
                break; 
        }

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
       
        if (App.currentUser.type.equals("STUDENT")){
            setOnMousePressed();
        }
                
       // handleButton_addAnnouncement();
        
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
        tableView_Announce.getItems().clear();
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
                    Submission submission;
                    List<QueryDocumentSnapshot> documents;
                    
                    //get assignment collection
                    ApiFuture<QuerySnapshot> future = App.fstore.collection("submissions").get();
                 
                    try {
                        //add collection into list
                        documents = future.get().getDocuments();

                        //check if empty
                        if (!documents.isEmpty()) {
                            
                            //loop through assignments
                            for (QueryDocumentSnapshot document : documents) {
                                
                                //use assignment document constructor to hold assignment data
                                submission = new Submission(document);

                                //if the CRN of any course matches the selected course CRN...
                                if (submission.getAssignment().equals(selectedAssignTitle) && submission.getStudent().equals(App.currentUser.userID)) {
                                    //set currentAssignment to the selected course
                                    App.currentSubmission = submission;
                                    //change view to course
                                    App.setRoot("Submission");
                                }
                            }
                        }
                    } 
                    catch (InterruptedException | ExecutionException | IOException ex) {}
                }
            }
        });
        
    }
    
    @FXML
    public void handleButton_addAnnouncement()
    {
        
        ObservableList<Node> children = VBox_center.getChildren();
        
        
        newAnnouncement.setMinWidth(611);
        newAnnouncement.setMinHeight(188);

        
        VBox_center.getChildren().add(4, newAnnouncement);
        VBox_center.getChildren().add(5, button_confirm);
        
        button_confirm.setOnAction(event -> {
                    writeNewAnnouncement();
         
                });
        
        
        
        
        
                
    }
    
    @FXML
    public void handleButton_createAssignment() throws IOException
    {
        App.setRoot("Assignment");
        
      
    }
    
    
    public void writeNewAnnouncement()
    {
       
       DocumentReference docRef = App.fstore.collection("announcements").document(UUID.randomUUID().toString());
       Map<String, Object> data = new HashMap<>();
       data.put("announcement", newAnnouncement.getText());
       data.put("postedDate", Timestamp.now());
       ApiFuture<WriteResult> result = docRef.set(data);
       
       
        Course course;
        List<QueryDocumentSnapshot> documents;
        List<String> announcements;

        //get assignment collection
        ApiFuture<QuerySnapshot> future = App.fstore.collection("courses").get();
        try {
            documents = future.get().getDocuments();
            
            if(!documents.isEmpty())
            {
                for(QueryDocumentSnapshot document: documents)
                {
                    course = new Course(document);
                    
                    if(course.CRN.equals(App.currentCourse.CRN))
                    {
                        announcements = App.currentCourse.announcements;
                        announcements.add(docRef.getId());
                        DocumentReference courseRef = App.fstore.collection("courses").document(document.getId());
                        ApiFuture<WriteResult> courseResult = courseRef.update("announcement",announcements);
                        
                        readAnnouncementsIntoTable();
                    }
                }
            }
        } catch (Exception e) {
        }
                 
       
       
       VBox_center.getChildren().remove(4);
       VBox_center.getChildren().remove(4);
       
       
       
    }
    
    
    public void writeNewAssignment()
    {
        
    }
    
}
