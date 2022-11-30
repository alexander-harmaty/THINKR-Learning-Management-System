package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
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
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
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
    protected final MFXDatePicker datePicker_dueDate = new MFXDatePicker();

    @FXML
    protected final TextArea textArea_studentComment = new TextArea("Student Comment");

    @FXML
    protected final TextArea textArea_professorFeedback = new TextArea("Professor Feedback");

    @FXML
    protected final TextArea textArea_assignmentDetails = new TextArea("Assignment Details");

    @FXML
    protected final MFXButton button_uploadFile = new MFXButton("Upload Details");
    @FXML
    protected final MFXButton button_uploadF = new MFXButton("Upload Submission");

    @FXML
    protected final MFXButton button_save = new MFXButton("Save");

    @FXML
    protected final MFXButton button_post = new MFXButton("Post");
    //Professor Post
    //Student Submit, if submission is there just save

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

        Font tj = new Font("System", 12);
        textField_grade.setFont(tj);
        textField_grade.setPrefHeight(38);
        textField_grade.setPrefWidth(290);
        textField_grade.setAlignment(Pos.CENTER_LEFT);
        textField_grade.setStyle("-fx-border-color:" + "#4653eb");

        datePicker_dueDate.setFont(tj);
        datePicker_dueDate.setPrefHeight(38);
        datePicker_dueDate.setPrefWidth(290);
        datePicker_dueDate.setAlignment(Pos.CENTER_LEFT);
        datePicker_dueDate.setStyle("-fx-border-color:" + "#4653eb");

        textArea_assignmentDetails.setFont(tj);
        textArea_assignmentDetails.setPrefHeight(194);
        textArea_assignmentDetails.setPrefWidth(268);
        textArea_assignmentDetails.setStyle("-fx-border-color:" + "#4653eb");

        textArea_studentComment.setFont(tj);
        textArea_studentComment.setPrefHeight(200);
        textArea_studentComment.setPrefWidth(200);
        textArea_studentComment.setStyle("-fx-border-color:" + "#4653eb");

        textArea_professorFeedback.setFont(tj);
        textArea_professorFeedback.setPrefHeight(200);
        textArea_professorFeedback.setPrefWidth(200);
        textArea_professorFeedback.setStyle("-fx-border-color:" + "#4653eb");

        button_uploadFile.setFont(tj);
        button_uploadFile.setPrefHeight(USE_COMPUTED_SIZE);
        button_uploadFile.setPrefWidth(USE_COMPUTED_SIZE);
        button_uploadFile.setStyle("-fx-text-fill:" + "#4653eb");

        button_save.setFont(tj);
        button_save.setPrefHeight(USE_COMPUTED_SIZE);
        button_save.setPrefWidth(USE_COMPUTED_SIZE);
        button_save.setStyle("-fx-text-fill:" + "#4653eb");

        button_post.setFont(tj);
        button_post.setPrefHeight(USE_COMPUTED_SIZE);
        button_post.setPrefWidth(USE_COMPUTED_SIZE);
        button_post.setStyle("-fx-text-fill:" + "#4653eb");

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
                VBox_right.getChildren().add(textField_grade);
                VBox_left.getChildren().add(datePicker_dueDate);
                VBox_right.getChildren().add(textArea_studentComment);
                VBox_left.getChildren().add(textArea_assignmentDetails);
                VBox_right.getChildren().add(textArea_professorFeedback);

                HBox_buttons.getChildren().add(button_uploadF);
                HBox_buttons.getChildren().add(button_save);

                break;

            case "PROFESSOR":
                VBox_left.getChildren().add(textField_title);
                VBox_right.getChildren().add(textField_grade);
                VBox_left.getChildren().add(datePicker_dueDate);
                VBox_right.getChildren().add(textArea_studentComment);
                VBox_left.getChildren().add(textArea_assignmentDetails);
                VBox_right.getChildren().add(textArea_professorFeedback);

                HBox_buttons.getChildren().add(button_uploadFile);
                HBox_buttons.getChildren().add(button_post);

                break;

            default:

                break;
        }

    }

}
