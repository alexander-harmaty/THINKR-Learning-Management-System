package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable{

    @FXML
    private VBox VBox_navBar;
    
    @FXML
    private VBox VBox_navButtons;

    @FXML
    private MFXButton button_home, button_settings, 
            button_calendar, button_courses, button_grades, 
            button_registrar, button_accounts;

    @FXML
    void handleButton_home(ActionEvent event) {

    }

    @FXML
    void handleButton_settings(ActionEvent event) {
        
    }
    
    @FXML
    void handleButton_calendar(ActionEvent event) {
        
    }
    
    @FXML
    void handleButton_courses(ActionEvent event) {
        
    }
    
    @FXML
    void handleButton_grades(ActionEvent event) {
        
    }
    
    @FXML
    void handleButton_registrar(ActionEvent event) {
        
    }
    
    @FXML
    void handleButton_accounts(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox_navButtons.getChildren().clear();
        switch(App.currentUser.type) {
            case "STUDENT":
                
                button_home = new MFXButton("Home", 190, 55);
                button_courses = new MFXButton("Courses", 190, 55);
                button_grades = new MFXButton("Grades", 190, 55);
                button_calendar = new MFXButton("Calendar", 190, 55);
                button_registrar = new MFXButton("Registrar", 190, 55);
                button_settings = new MFXButton("Settings", 190, 55);
                
                VBox_navButtons.getChildren().addAll(
                        button_home, button_courses, button_grades,
                        button_calendar, button_registrar, button_settings
                );
                
                break;
            case "PROFESSOR":
                
                button_home = new MFXButton("Home", 190, 55);
                button_courses = new MFXButton("Courses", 190, 55);
                button_grades = new MFXButton("Grades", 190, 55);
                button_calendar = new MFXButton("Calendar", 190, 55);
                button_settings = new MFXButton("Settings", 190, 55);
                
                VBox_navButtons.getChildren().addAll(
                        button_home, button_courses, button_grades,
                        button_calendar, button_settings
                );
                
                break;
            case "ADMIN":
                
                button_home = new MFXButton("Home", 190, 55);
                button_registrar = new MFXButton("Registrar", 190, 55);
                button_accounts = new MFXButton("Accounts", 190, 55);
                button_settings = new MFXButton("Settings", 190, 55);
                
                VBox_navButtons.getChildren().addAll(
                        button_home, button_registrar, 
                        button_accounts, button_settings
                );
                
                break;
            default:
                break;
        }
        
    }

}
