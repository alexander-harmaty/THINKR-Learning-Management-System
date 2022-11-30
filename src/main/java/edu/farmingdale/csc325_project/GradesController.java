package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GradesController extends HomePageController implements Initializable {

    @FXML
    private HBox HBox_t;

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private TableColumn<Assignment, String> tableColumn_assignment;

    @FXML
    private TableColumn<Assignment, String> tableColumn_course;

    @FXML
    private TableColumn<Assignment, String> tableColumn_dueDate;

    @FXML
    private TableColumn<Assignment, Integer> tableColumn_grade;

    @FXML
    private TableView<Assignment> tableView_grades;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableColumn_dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        tableColumn_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableColumn_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        readSubmissionsIntoTable();
    }
    
    
}
