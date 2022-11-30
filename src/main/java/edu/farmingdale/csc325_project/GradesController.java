package edu.farmingdale.csc325_project;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GradesController {

    @FXML
    private HBox HBox_t;

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private TableColumn<?, ?> tableColumn_assignment;

    @FXML
    private TableColumn<?, ?> tableColumn_course;

    @FXML
    private TableColumn<?, ?> tableColumn_dueDate;

    @FXML
    private TableColumn<?, ?> tableColumn_grade;

    @FXML
    private TableView<?> tableView_grades;

}
