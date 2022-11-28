package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

    @FXML
    private VBox VBox_navBar;
    
    @FXML
    private VBox VBox_navButtons;
    
    @FXML
    protected final MFXButton button_home = new MFXButton("Home");
    
    @FXML
    protected final MFXButton button_courses = new MFXButton("Courses");
    
    @FXML
    protected final MFXButton button_grades = new MFXButton("Grades");
    
    @FXML
    protected final MFXButton button_calendar = new MFXButton("Calendar");
    
    @FXML
    protected final MFXButton button_registrar = new MFXButton("Registrar");
    
    @FXML
    protected final MFXButton button_accounts = new MFXButton("Accounts");
    
    @FXML
    protected final MFXButton button_settings = new MFXButton("Settings");
    
    @FXML
    private TableView<Course> tableView_popup;
    
    @FXML
    private TableColumn<Course, Integer> CRNCol, codeCol;
     
    @FXML
    private TableColumn<Course, String> subjectCol, titleCol;
     
    private ObservableList<Course> listOfCourses = FXCollections.observableArrayList();
    
    private Course course;

    public ObservableList<Course> getListOfCourses()
    {
        return listOfCourses;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();
        
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        CRNCol.setCellValueFactory(new PropertyValueFactory<>("CRN"));
        
        ApiFuture<QuerySnapshot> future =  App.fstore.collection("courses").get();
            // future.get() blocks on response
            List<QueryDocumentSnapshot> documents;
            
            try 
            {
                documents = future.get().getDocuments();
            
                if(!documents.isEmpty())
                {
                    for (QueryDocumentSnapshot document : documents) 
                    {
                        //DocumentReference currentDocument = document.getReference();
                        course = new Course(document);
                       // this.code = (int) document.getData().get("code");
                        
                        for(String student: course.students)
                        {
                            if(App.currentUser.userID.equals(student))
                            {
                                listOfCourses.add(course);
                            }
                        }
                        
                    }
                   tableView_popup.setItems(listOfCourses);
                    
                }
            } catch (InterruptedException ex) {
            
        } catch (ExecutionException ex) {
           
        }
            
        tableView_popup.setVisible(false);
    }
    
    void showCourseListPopup() throws IOException {
        
        tableView_popup.setVisible(true);
        
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseListPopup.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Course List");
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {}
        
    }
    
    protected void updateMenu() {
        VBox_navButtons.getChildren().clear();
        
        List<MFXButton> buttons = new ArrayList<>();
        
        switch(App.currentUser.type) {
            case "STUDENT":
                
                button_courses.setOnAction(event -> {
                    try { showCourseListPopup(); } 
                    catch (IOException ex) {}
                });
                
//                button_grades.setOnAction(event -> {
//                    try { App.setRoot("Grades.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_calendar.setOnAction(event -> {
//                    try { App.setRoot("Calendar.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_registrar.setOnAction(event -> {
//                    try { App.setRoot("Registrar.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_settings.setOnAction(event -> {
//                    try { App.setRoot("Settings.fxml"); } 
//                    catch (IOException ex) {}
//                });
                
                buttons.add(button_home);
                buttons.add(button_courses);
                buttons.add(button_grades);
                buttons.add(button_calendar);
                buttons.add(button_registrar);
                buttons.add(button_settings);
                
                break;

            case "PROFESSOR":
                
                button_courses.setOnAction(event -> {
                    try { showCourseListPopup(); } 
                    catch (IOException ex) {}
                });
                
//                button_grades.setOnAction(event -> {
//                    try { App.setRoot("Grades.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_calendar.setOnAction(event -> {
//                    try { App.setRoot("Calendar.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_settings.setOnAction(event -> {
//                    try { App.setRoot("Settings.fxml"); } 
//                    catch (IOException ex) {}
//                });
                
                buttons.add(button_home);
                buttons.add(button_courses);
                buttons.add(button_grades);
                buttons.add(button_calendar);
                buttons.add(button_settings);
                
                break;
                
            case "ADMIN":
                
//                button_registrar.setOnAction(event -> {
//                    try { App.setRoot("Registrar.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_accounts.setOnAction(event -> {
//                    try { App.setRoot("Accounts.fxml"); } 
//                    catch (IOException ex) {}
//                });
//                
//                button_settings.setOnAction(event -> {
//                    try { App.setRoot("Settings.fxml"); } 
//                    catch (IOException ex) {}
//                });
                
                buttons.add(button_home);
                buttons.add(button_registrar);
                buttons.add(button_accounts);
                buttons.add(button_settings);
                
                break;
                
            default:
                
                break;
                
        }
        
        for (MFXButton button : buttons) {
            Font font = new Font("System", 26);
            button.setFont(font);
            button.setPrefHeight(50);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(50);
            VBox_navButtons.getChildren().add(button);
        }
        
    }

    
}
