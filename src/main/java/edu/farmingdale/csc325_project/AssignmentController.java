package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author forha
 */
public class AssignmentController implements Initializable {

    @FXML
    private HBox HBox_buttons;

    @FXML
    private HBox HBox_holder;
    
    @FXML
    private VBox VBox_left;

    @FXML
    private VBox VBox_right;

//    @FXML
//    private MFXButton button_save;
//
//    @FXML
//    private MFXButton button_upload;
//
//    @FXML
//    private MFXCheckbox checkBox_file;
//
//    @FXML
//    private MFXCheckbox checkBox_text;
//
//    @FXML
//    private TextArea textArea_assignmentDetails;
//
//    @FXML
//    private TextArea textArea_professorFeedback;
//
//    @FXML
//    private TextArea textArea_studentComment;
//
//    @FXML
//    private MFXTextField textField_dueDate;
//
//    @FXML
//    private MFXTextField textField_grade;
//
//    @FXML
//    private MFXTextField textField_title;

    @FXML
    protected final Label label_title = new Label();
    
    @FXML
    protected final MFXTextField textField_title = new MFXTextField();
    
    @FXML
    protected final MFXTextField textField_grade = new MFXTextField("Grade");
    
    @FXML
    protected final MFXTextField textField_dueDate = new MFXTextField();
    
    @FXML
    protected final TextArea textArea_studentComment = new TextArea("Student Comment");
    
    @FXML
    protected final TextArea textArea_professorFeedback = new TextArea("Professor Feedback");
    
    @FXML
    protected final TextArea textArea_assignmentDetails = new TextArea("Assignment Details");
    
    @FXML
    protected final MFXButton button_uploadFile = new MFXButton("Upload");
    
    @FXML
    protected final MFXButton button_save = new MFXButton("Save");
    //Professor Post
    //Student Submit, if submission is there just save

    

    @FXML
    void handleButton_save(ActionEvent event) {

    }

    @FXML
    void handleButton_uploadFile(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateAssignment();
    }

    protected void updateAssignment() {
        VBox_left.getChildren().clear();
        VBox_right.getChildren().clear();
        HBox_buttons.getChildren().clear();
       
        textField_title.setText(App.currentAssignment.title);
        Font ti = new Font("System", 20);
        textField_title.setFont(ti);
        textField_title.setPrefHeight(40);
        textField_title.setPrefWidth(268);
        textField_title.setAlignment(Pos.CENTER);
        textField_title.setStyle("-fx-border-color:" + "#4653eb");
        
        label_title.setText(App.currentAssignment.title);
        Font font = new Font("System", 20);
        label_title.setFont(font);
        label_title.setPrefHeight(40);
        label_title.setPrefWidth(268);
        label_title.setAlignment(Pos.CENTER);
        label_title.setStyle("-fx-border-color:" + "#4653eb");
        
        
        switch (App.currentUser.type) {
            
            case "STUDENT":
                VBox_left.getChildren().add(label_title);
                VBox_left.getChildren().add(textField_grade);
                VBox_left.getChildren().add(textField_dueDate);
                VBox_left.getChildren().add(textArea_studentComment);
                VBox_left.getChildren().add(label_title);
                
                HBox_buttons.getChildren().add(button_uploadFile);
                HBox_buttons.getChildren().add(button_save);
                

                break;

            case "PROFESSOR":
                
                
                break;

            default:

                break;
        }

    }

}
