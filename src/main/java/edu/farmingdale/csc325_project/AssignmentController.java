package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import io.github.palexdev.materialfx.enums.FloatMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.text.Font;

import java.time.Instant;
import java.util.Date;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.geometry.Insets;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author forha
 */
public class AssignmentController extends HomePageController implements Initializable {

    @FXML
    private HBox HBox_buttons = new HBox();

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
    protected final MFXDatePicker datePicker_dueDate = new MFXDatePicker();

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
    //Professor Post
    //Student Submit, if submission is there just save
//
//    @FXML
//    void handleButton_uploadFile(ActionEvent event) {
//
//    }
//
//    @FXML
//    void handleButton_save(ActionEvent event) {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildAssignment();
        updateMenu();
    }

    /**
     * the professor view when creating an assignment, before submission
     *
     *
     */
    protected void buildAssignment() {
        VBox_left.getChildren().clear();
        //VBox_right.getChildren().clear();
        HBox_buttons.getChildren().clear();

        Font tj = new Font("System", 12);
        Font font = new Font("System", 20);

        textField_title.setFloatingText("Assignment Title");
        textField_title.setFloatMode(FloatMode.BORDER);
        textField_title.getText();
        textField_title.setFont(font);
        textField_title.setPrefHeight(40);
        textField_title.setPrefWidth(268);
        textField_title.setAlignment(Pos.CENTER);
        textField_title.setStyle("-fx-border-color:" + "#4653eb");

        datePicker_dueDate.setFloatingText("Due Date");
        datePicker_dueDate.setFloatMode(FloatMode.BORDER);
        datePicker_dueDate.setFont(tj);
        datePicker_dueDate.setPrefHeight(38);
        datePicker_dueDate.setPrefWidth(290);
        datePicker_dueDate.setAlignment(Pos.CENTER_LEFT);
        datePicker_dueDate.setStyle("-fx-border-color:" + "#4653eb");

        textArea_assignmentDetails.setPromptText("Assignment Details");
        textArea_assignmentDetails.setFont(tj);
        textArea_assignmentDetails.setPrefHeight(194);
        textArea_assignmentDetails.setPrefWidth(268);
        textArea_assignmentDetails.setStyle("-fx-border-color:" + "#4653eb");

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

        VBox_left.getChildren().add(textField_title);

        VBox_left.getChildren().add(datePicker_dueDate);       
        VBox_left.getChildren().add(textArea_assignmentDetails);
        VBox_left.getChildren().add(HBox_buttons);
        VBox_left.setMaxWidth(300);
//        HBox_buttons.getChildren().add(button_uploadFile);
        HBox_buttons.getChildren().add(button_post);
        HBox_buttons.setAlignment(Pos.CENTER);
        HBox_buttons.getChildren().add(button_back);
        HBox_buttons.setSpacing(20);

        button_back.setOnAction(event -> {
            try {
                App.setRoot("Course");
            } catch (IOException ex) {

            }
        });

        button_post.setOnAction(event -> {
            createAssignment();

        });

    }

    /**
     * creates a new assignment and adds it to the database
     *
     * ToDo: add a submission of assignment to every student in the current
     * course
     */
    public void createAssignment() {
        ZonedDateTime zdt = datePicker_dueDate.getCurrentDate().atStartOfDay(ZoneId.of("America/New_York"));
        Instant instant = zdt.toInstant();
        Date d = Date.from(instant);
        Timestamp ts = Timestamp.of(d);

        DocumentReference docRef = App.fstore.collection("assignments").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();

        data.put("title", textField_title.getText());
        data.put("dueDate", ts);
        data.put("detailsText", textArea_assignmentDetails.getText());
        data.put("assignDate", Timestamp.now());//makes the current time the assigned date

        List<String> courses = new ArrayList<>();
        courses.add(App.currentCourse.CRN);
        data.put("course", courses);

        List<String> submissions = new ArrayList<>();
        for (int i = 0; i < App.currentCourse.students.size(); i++) {

            DocumentReference tempRef = App.fstore.collection("submissions").document(UUID.randomUUID().toString());
            Map<String, Object> tempData = new HashMap<>();

            tempData.put("assignment", textField_title.getText());
            tempData.put("CRN", App.currentCourse.CRN);
            tempData.put("grade", 0);
            tempData.put("student", App.currentCourse.students.get(i));
            tempData.put("studentComment", textArea_studentComment.getText());
            tempData.put("submitted", false);
            tempData.put("submittedDate", null);
            tempData.put("teacherFeedback", null);
            tempData.put("submissionDetails", null);
            tempData.put("ID", tempRef.getId());
            ApiFuture<WriteResult> subResult = tempRef.set(tempData);
            submissions.add(tempRef.getId());
        }
        data.put("submissions", submissions);

        //ToDo: needs to add course array, right now adds a string only
        ApiFuture<WriteResult> result = docRef.set(data);
        App.currentCourse.assignments.add(docRef.getId());
        DocumentReference cdata = App.fstore.collection("courses").document(App.currentCourse.getID());

        ApiFuture<WriteResult> cresult = cdata.update("assignments", App.currentCourse.assignments);

    }

}
