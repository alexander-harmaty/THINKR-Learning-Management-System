package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AdminController extends HomePageController implements Initializable {

    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;

    @FXML
    private MFXButton button_date;

    @FXML
    private MFXButton button_register;

    @FXML
    private MFXButton button_update;

    @FXML
    private TableColumn<User,String> tableColumn_dob;

    @FXML
    private TableColumn<User,String> tableColumn_email;

    @FXML
    private TableColumn<User,String> tableColumn_firstName;

    @FXML
    private TableColumn<User,String> tableColumn_lastName;

    @FXML
    private TableColumn<User,String> tableColumn_type;

    @FXML
    private TableView<User> tableView_userTable;

    @FXML
    private MFXTextField textField_dob;

    @FXML
    private MFXTextField textField_email;

    @FXML
    private MFXTextField textField_firstName;

    @FXML
    private MFXTextField textField_lastName;

    @FXML
    private MFXTextField textField_type;

    private ObservableList<User> listOfUsers = FXCollections.observableArrayList();

    public ObservableList<User> getListOfUsers() {
        return listOfUsers;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumn_dob.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tableColumn_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableColumn_firstName.setCellValueFactory(new PropertyValueFactory<>("First Name"));
        tableColumn_lastName.setCellValueFactory(new PropertyValueFactory<>("Last Name"));
        tableColumn_type.setCellValueFactory(new PropertyValueFactory<>("Type"));

        tableView_userTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField_dob.setText(tableView_userTable.getSelectionModel().getSelectedItem().getDOB());
                textField_email.setText(tableView_userTable.getSelectionModel().getSelectedItem().getEmail());
                textField_firstName.setText(tableView_userTable.getSelectionModel().getSelectedItem().getFirstName());
                textField_lastName.setText(tableView_userTable.getSelectionModel().getSelectedItem().getLastName());
                textField_type.setText(tableView_userTable.getSelectionModel().getSelectedItem().getType());
            }
        });

    }
}
