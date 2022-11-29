package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import io.github.palexdev.materialfx.controls.MFXTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import org.controlsfx.control.action.ActionUtils;

public class CourseController extends HomePageController implements Initializable {

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private Label label_classTitle;

    @FXML
    private MFXTableView<?> tableView_Announce;

    @FXML
    private MFXTableView<?> tableView_Course;

    @FXML
    private TableView<Assignment> tableView_assignments;

    @FXML
    private TableColumn<Assignment, String> tableColumn_due;

    @FXML
    private TableColumn<Assignment, String> tableColumn_title;

    private ObservableList<Assignment> listOfAssignments = FXCollections.observableArrayList();

    public ObservableList<Assignment> getListOfAssignment() {
        return listOfAssignments;
    }

    Assignment assignment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();

        readCourseList();
        tableColumn_due.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        tableColumn_title.setCellValueFactory(new PropertyValueFactory<>("title"));

        ApiFuture<QuerySnapshot> future = App.fstore.collection("assignments").get();

        List<QueryDocumentSnapshot> documents;

        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {

                    assignment = new Assignment(document);
                    //List<Integer> courseIDs = new ArrayList<>();
                    for (int i = 0; i < assignment.getCourse().size(); i++){
                        if (App.currentCourse.getCRN().equals( assignment.getCourse().get(i))) {
                             listOfAssignments.add(assignment);
                        }
                    }
//                            //Integer.parseInt(assignment.getCourse().toString()));
//                    for (int CRN : courseIDs) {
//                        if (App.currentCourse.getCRN() == CRN) {
//                            listOfAssignments.add(assignment);
//                        }
//                    }

                }
                tableView_assignments.setItems(listOfAssignments);

            }
        } catch (InterruptedException ex) {

        } catch (ExecutionException ex) {

            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readCourseList() {
        label_classTitle.setText(App.currentCourse.title);

    }
}
