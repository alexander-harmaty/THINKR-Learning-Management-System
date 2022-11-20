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
import javafx.scene.text.Font;

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
                
                button_home = new MFXButton("Home");
                button_courses = new MFXButton("Courses");
                button_grades = new MFXButton("Grades");
                button_calendar = new MFXButton("Calendar");
                button_registrar = new MFXButton("Registrar");
                button_settings = new MFXButton("Settings");
                
                MFXButton buttons[] = {button_home, button_courses, button_grades, button_calendar, button_registrar, button_settings};
                
                for (MFXButton button : buttons) {
                    Font font = new Font("System", 26);
                    button.setFont(font);
                    button.setPrefHeight(50);
                    button.setMaxWidth(Double.MAX_VALUE);
                    button.setMaxHeight(50);
                    VBox_navButtons.getChildren().add(button);
                }
                
                break;

            case "PROFESSOR":
                
                button_home = new MFXButton("Home");
                button_courses = new MFXButton("Courses");
                button_grades = new MFXButton("Grades");
                button_calendar = new MFXButton("Calendar");
                button_settings = new MFXButton("Settings");
                
                VBox_navButtons.getChildren().addAll(
                        button_home, button_courses, button_grades,
                        button_calendar, button_settings
                );
                
                break;
            case "ADMIN":
                
                button_home = new MFXButton("Home");
                button_registrar = new MFXButton("Registrar");
                button_accounts = new MFXButton("Accounts");
                button_settings = new MFXButton("Settings");
                
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
