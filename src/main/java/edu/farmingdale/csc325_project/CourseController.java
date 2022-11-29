package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.ActionUtils;

public class CourseController extends HomePageController implements Initializable{
    

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private Label label_classTitle;

    @FXML
    private MFXTableView<?> tableView_Announce;
    
    @FXML
    private TableView<?> tableView_assignments;
    
    @FXML
    private TableColumn<?, ?> tableColumn_due;

    @FXML
    private TableColumn<?, ?> tableColumn_title; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();
        
        readCourseList();
    
    }
    
    public void readCourseList() {
        label_classTitle.setText(App.currentCourse.title);
    }
}
