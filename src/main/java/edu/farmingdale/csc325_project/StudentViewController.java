package edu.farmingdale.csc325_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StudentViewController {

    @FXML
    private Button button_add;

    @FXML
    private Button button_remove;

    @FXML
    private HBox hbox_classList;

    @FXML
    private HBox hbox_name;

    @FXML
    private HBox hbox_name1;

    @FXML
    private HBox hbox_name2;

    @FXML
    private HBox hbox_name3;

    @FXML
    private HBox hbox_name4;

    @FXML
    private HBox hbox_name5;

    @FXML
    private ImageView imageview_studentPicture;

    @FXML
    private Label label_calendar;

    @FXML
    private Label label_courseList;

    @FXML
    private Label label_dobSettings;

    @FXML
    private Label label_emailSettings;

    @FXML
    private Label label_grades;

    @FXML
    private Label label_nameSettings;

    @FXML
    private Label label_passwordSettings;

    @FXML
    private Label label_phoneSettings;

    @FXML
    private Label label_profileSettings;

    @FXML
    private Label label_studentName;

    @FXML
    private Label label_userSettings;

    @FXML
    private ListView<?> listview_courseList;

    @FXML
    private Tab tab_calendar;

    @FXML
    private Tab tab_courses;

    @FXML
    private Tab tab_grades;

    @FXML
    private Tab tab_registrar;

    @FXML
    private Tab tab_settings;

    @FXML
    private TableColumn<?, ?> tablecolumn_;

    @FXML
    private TableColumn<?, ?> tablecolumn_course;

    @FXML
    private TableColumn<?, ?> tablecolumn_credit;

    @FXML
    private TableColumn<?, ?> tablecolumn_crn;

    @FXML
    private TableColumn<?, ?> tablecolumn_section;

    @FXML
    private TableColumn<?, ?> tablecolumn_subject;

    @FXML
    private TableView<?> tableview_classList;

    @FXML
    private TextField textfield_dobSettings;

    @FXML
    private TextField textfield_emailSettings;

    @FXML
    private TextField textfield_nameSettings;

    @FXML
    private TextField textfield_passwordSettings;

    @FXML
    private TextField textfield_phoneSettings;

    @FXML
    private TextField textfield_userSettings;

    @FXML
    void handleButton_add(ActionEvent event) {

    }

    @FXML
    void handleButton_remove(ActionEvent event) {

    }

}
