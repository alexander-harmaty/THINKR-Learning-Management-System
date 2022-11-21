package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    private MFXButton button_home = new MFXButton("Home");
    
    @FXML
    private MFXButton button_courses = new MFXButton("Courses");
    
    @FXML
    private MFXButton button_grades = new MFXButton("Grades");
    
    @FXML
    private MFXButton button_calendar = new MFXButton("Calendar");
    
    @FXML
    private MFXButton button_registrar = new MFXButton("Registrar");
    
    @FXML
    private MFXButton button_accounts = new MFXButton("Accounts");
    
    @FXML
    private MFXButton button_settings = new MFXButton("Settings");
    
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
        
        List<MFXButton> buttons = new ArrayList<>();
        
        switch(App.currentUser.type) {
            case "STUDENT":
                
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
                buttons.add(button_settings);
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
