package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();
    }
    
    void showCourseListPopup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseListPopup"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Course List");
        stage.setScene(new Scene(root));
        stage.show();
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
                
                buttons.add(button_home);
                buttons.add(button_courses);
                buttons.add(button_grades);
                buttons.add(button_calendar);
                buttons.add(button_registrar);
                buttons.add(button_settings);
                
                break;

            case "PROFESSOR":
                
                buttons.add(button_home);
                buttons.add(button_courses);
                buttons.add(button_grades);
                buttons.add(button_calendar);
                buttons.add(button_settings);
                
                break;
                
            case "ADMIN":
                
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
