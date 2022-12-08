package edu.farmingdale.csc325_project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 *
 * @author trintydarbouze
 */
public class SubmissionController extends HomePageController implements Initializable {

    @FXML
    private HBox HBox_buttons;

    @FXML
    private HBox HBox_bottombuttons;

    @FXML
    private HBox HBox_holder;

    @FXML
    private VBox VBox_left;

    @FXML
    private VBox VBox_right;

    @FXML
    protected final Label label_title = new Label();

    @FXML
    protected final MFXTextField textField_title = new MFXTextField();

    @FXML
    protected final MFXTextField textField_grade = new MFXTextField();

    @FXML
    protected final MFXTextField textField_dueDate = new MFXTextField();

    @FXML
    protected final TextArea textArea_studentComment = new TextArea();

    @FXML
    protected final TextArea textArea_professorFeedback = new TextArea();

    @FXML
    protected final TextArea textArea_assignmentDetails = new TextArea();

    @FXML
    protected final MFXButton button_uploadFile = new MFXButton();

    @FXML
    protected final MFXButton button_uploadF = new MFXButton("Upload Submission");

    @FXML
    protected final MFXButton button_save = new MFXButton("Save");

    @FXML
    protected final MFXButton button_post = new MFXButton("Post");

    @FXML
    protected final MFXButton button_back = new MFXButton("Back");

    Font font;
    Font tj;

    @FXML
    void handleButton_save(ActionEvent event) {

    }

    @FXML
    void handleButton_uploadFile(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildSubmission();
        readSubmission();
        updateMenu();
    }

    private void readSubmission() {

        label_title.setText(App.currentSubmission.assignment);
        textField_grade.setText(String.valueOf(App.currentSubmission.grade));
        textArea_studentComment.setText(App.currentSubmission.studentComment);
        textArea_professorFeedback.setText(App.currentSubmission.teacherFeedback);

        List<QueryDocumentSnapshot> documents;
        ApiFuture<QuerySnapshot> future = App.fstore.collection("assignments").get();

        try {

            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {
                    Assignment assignment = new Assignment(document);

                    if (assignment.title.equals(App.currentSubmission.assignment)) {
                        textField_dueDate.setText(assignment.dueDate.toSqlTimestamp().toLocalDateTime().toString());
                        textArea_assignmentDetails.setText(assignment.detailsText);
                    }

                }
            }
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    private void updateSubmission() {

        DocumentReference docRef = App.fstore.collection("submissions").document(App.currentSubmission.getID());

        ApiFuture<WriteResult> cresult = docRef.update("studentComment", textArea_studentComment.getText());
        ApiFuture<WriteResult> cresult2 = docRef.update("submitted", true);
        ApiFuture<WriteResult> cresult3 = docRef.update("teacherFeedback", textArea_professorFeedback.getText());
        ApiFuture<WriteResult> cresult4 = docRef.update("grade", textField_grade.getText());

    }

    protected void buildSubmission() {
        VBox_left.getChildren().clear();
        VBox_right.getChildren().clear();
        HBox_buttons.getChildren().clear();

        tj = new Font("System", 12);
        font = new Font("System", 20);

        switch (App.currentUser.type) {

            case "STUDENT":

                label_title.setText(App.currentSubmission.assignment);
                label_title.setFont(font);
                label_title.setPrefHeight(40);
                label_title.setPrefWidth(268);
                label_title.setAlignment(Pos.CENTER);
                label_title.setStyle("-fx-border-color:" + "#4653eb");

                textField_grade.setFloatingText("Grade");
                textField_grade.setFont(tj);
                textField_grade.setPrefHeight(38);
                textField_grade.setPrefWidth(290);
                textField_grade.setAlignment(Pos.CENTER_LEFT);
                textField_grade.setStyle("-fx-border-color:" + "#4653eb");
                textField_grade.setEditable(false);

                textField_dueDate.setFloatingText("Due Date");
                textField_dueDate.setFont(tj);
                textField_dueDate.setPrefHeight(38);
                textField_dueDate.setPrefWidth(290);
                textField_dueDate.setAlignment(Pos.CENTER_LEFT);
                textField_dueDate.setStyle("-fx-border-color:" + "#4653eb");
                textField_dueDate.setEditable(false);

//                textArea_assignmentDetails.setPromptText("Assignment Details");
//                textArea_assignmentDetails.setText(App.currentAssignment.detailsText);
//                textArea_assignmentDetails.setFont(tj);
//                textArea_assignmentDetails.setPrefHeight(194);
//                textArea_assignmentDetails.setPrefWidth(268);
//                textArea_assignmentDetails.setStyle("-fx-border-color:" + "#4653eb");
                
                textArea_studentComment.setPromptText("Student Comment");
                textArea_studentComment.setFont(tj);
                textArea_studentComment.setPrefHeight(200);
                textArea_studentComment.setPrefWidth(200);
                textArea_studentComment.setStyle("-fx-border-color:" + "#4653eb");

                textArea_professorFeedback.setPromptText("Professor Feedback");
                textArea_professorFeedback.setFont(tj);
                textArea_professorFeedback.setPrefHeight(200);
                textArea_professorFeedback.setPrefWidth(200);
                textArea_professorFeedback.setStyle("-fx-border-color:" + "#4653eb");
                textArea_professorFeedback.setEditable(false);

                button_uploadFile.setFont(tj);
                button_uploadFile.setPrefHeight(USE_COMPUTED_SIZE);
                button_uploadFile.setPrefWidth(USE_COMPUTED_SIZE);
                button_uploadFile.setStyle("-fx-text-fill:" + "#4653eb");
                button_uploadFile.setAlignment(Pos.CENTER);

                button_save.setFont(tj);
                button_save.setPrefHeight(USE_COMPUTED_SIZE);
                button_save.setPrefWidth(USE_COMPUTED_SIZE);
                button_save.setStyle("-fx-text-fill:" + "#4653eb");
                button_save.setAlignment(Pos.CENTER);

                button_post.setFont(tj);
                button_post.setPrefHeight(USE_COMPUTED_SIZE);
                button_post.setPrefWidth(USE_COMPUTED_SIZE);
                button_post.setStyle("-fx-text-fill:" + "#4653eb");
                button_post.setAlignment(Pos.CENTER);

                VBox_left.getChildren().add(label_title);
                VBox_right.getChildren().add(textField_grade);
                VBox_left.getChildren().add(textField_dueDate);
                VBox_right.getChildren().add(textArea_studentComment);
                //VBox_left.getChildren().add(textArea_assignmentDetails);
                VBox_right.getChildren().add(textArea_professorFeedback);

                button_back.setFont(tj);
                button_back.setPrefHeight(USE_COMPUTED_SIZE);
                button_back.setPrefWidth(USE_COMPUTED_SIZE);
                button_back.setStyle("-fx-text-fill:" + "#4653eb");
                button_back.setAlignment(Pos.CENTER);

                HBox_buttons.getChildren().add(button_uploadF);
                HBox_bottombuttons.getChildren().add(button_save);
                HBox_bottombuttons.getChildren().add(button_back);

                button_save.setOnAction(event -> {
                    updateSubmission();
                });

                button_back.setOnAction(event -> {
                    try {
                        App.setRoot("Course");
                    } catch (IOException ex) {

                    }
                });
                break;

            case "PROFESSOR":

                label_title.setText(App.currentSubmission.assignment);
                label_title.setFont(font);
                label_title.setPrefHeight(40);
                label_title.setPrefWidth(268);
                label_title.setAlignment(Pos.CENTER);
                label_title.setStyle("-fx-border-color:" + "#4653eb");

                textField_grade.setFloatingText("Grade");
                textField_grade.setFont(tj);
                textField_grade.setPrefHeight(38);
                textField_grade.setPrefWidth(290);
                textField_grade.setAlignment(Pos.CENTER_LEFT);
                textField_grade.setStyle("-fx-border-color:" + "#4653eb");

                textField_dueDate.setFloatingText("Due Date");
                textField_dueDate.setFont(tj);
                textField_dueDate.setPrefHeight(38);
                textField_dueDate.setPrefWidth(290);
                textField_dueDate.setAlignment(Pos.CENTER_LEFT);
                textField_dueDate.setStyle("-fx-border-color:" + "#4653eb");
                textField_dueDate.setEditable(false);

                textArea_assignmentDetails.setPromptText("Assignment Details");
                textArea_assignmentDetails.setFont(tj);
                textArea_assignmentDetails.setPrefHeight(194);
                textArea_assignmentDetails.setPrefWidth(268);
                textArea_assignmentDetails.setStyle("-fx-border-color:" + "#4653eb");
                textArea_assignmentDetails.setEditable(false);

                textArea_studentComment.setPromptText("Student Comment");
                textArea_studentComment.setFont(tj);
                textArea_studentComment.setPrefHeight(200);
                textArea_studentComment.setPrefWidth(200);
                textArea_studentComment.setStyle("-fx-border-color:" + "#4653eb");
                textArea_studentComment.setEditable(false);

                textArea_professorFeedback.setPromptText("Professor Feedback");
                textArea_professorFeedback.setFont(tj);
                textArea_professorFeedback.setPrefHeight(200);
                textArea_professorFeedback.setPrefWidth(200);
                textArea_professorFeedback.setStyle("-fx-border-color:" + "#4653eb");

                button_uploadFile.setFont(tj);
                button_uploadFile.setPrefHeight(USE_COMPUTED_SIZE);
                button_uploadFile.setPrefWidth(USE_COMPUTED_SIZE);
                button_uploadFile.setStyle("-fx-text-fill:" + "#4653eb");
                button_uploadFile.setAlignment(Pos.CENTER);

                button_save.setFont(tj);
                button_save.setPrefHeight(USE_COMPUTED_SIZE);
                button_save.setPrefWidth(USE_COMPUTED_SIZE);
                button_save.setStyle("-fx-text-fill:" + "#4653eb");
                button_save.setAlignment(Pos.CENTER);

                button_post.setFont(tj);
                button_post.setPrefHeight(USE_COMPUTED_SIZE);
                button_post.setPrefWidth(USE_COMPUTED_SIZE);
                button_post.setStyle("-fx-text-fill:" + "#4653eb");
                button_post.setAlignment(Pos.CENTER);
                
                button_back.setFont(tj);
                button_back.setPrefHeight(USE_COMPUTED_SIZE);
                button_back.setPrefWidth(USE_COMPUTED_SIZE);
                button_back.setStyle("-fx-text-fill:" + "#4653eb");
                button_back.setAlignment(Pos.CENTER);


                VBox_left.getChildren().add(label_title);
                VBox_right.getChildren().add(textField_grade);
                VBox_left.getChildren().add(textField_dueDate);
                VBox_right.getChildren().add(textArea_studentComment);
                VBox_left.getChildren().add(textArea_assignmentDetails);
                VBox_right.getChildren().add(textArea_professorFeedback);

                HBox_buttons.getChildren().add(button_uploadFile);
                HBox_bottombuttons.getChildren().add(button_save);
                HBox_bottombuttons.getChildren().add(button_back);

                button_save.setOnAction(event -> {
                    updateSubmission();
                });
                
                button_back.setOnAction(event -> {
                    try {
                        App.setRoot("Grades");
                    } catch (IOException ex) {

                    }
                });

                break;

            default:

                break;
        }

    }

}
