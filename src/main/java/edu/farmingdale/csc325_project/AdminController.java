package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private MFXButton button_delete;

    @FXML
    private MFXButton button_register;

    @FXML
    private MFXButton button_update;

    @FXML
    private MFXButton button_clear;

    @FXML
    private TableColumn<User, String> tableColumn_dob;

    @FXML
    private TableColumn<User, String> tableColumn_email;

    @FXML
    private TableColumn<User, String> tableColumn_firstName;

    @FXML
    private TableColumn<User, String> tableColumn_lastName;

    @FXML
    private TableColumn<User, String> tableColumn_type;

    @FXML
    private TableView<User> tableView_userTable = new TableView();

    @FXML
    private MFXTextField textField_dob;

    @FXML
    private MFXTextField textField_email;

    @FXML
    private MFXTextField textField_firstName;

    @FXML
    private MFXTextField textField_lastName;

    private String currentID = "";

    @FXML
    private MFXTextField textField_type;

    private ObservableList<User> listOfUsers = FXCollections.observableArrayList();

    public ObservableList<User> getListOfUsers() {
        return listOfUsers;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();
        VBox_navButtons.getChildren().remove(0);

        tableColumn_dob.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tableColumn_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumn_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumn_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumn_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableView_userTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField_dob.setText(tableView_userTable.getSelectionModel().getSelectedItem().getDOB());
                textField_email.setText(tableView_userTable.getSelectionModel().getSelectedItem().getEmail());
                textField_firstName.setText(tableView_userTable.getSelectionModel().getSelectedItem().getFirstName());
                textField_lastName.setText(tableView_userTable.getSelectionModel().getSelectedItem().getLastName());
                textField_type.setText(tableView_userTable.getSelectionModel().getSelectedItem().getType());
                currentID = tableView_userTable.getSelectionModel().getSelectedItem().getUserID();
            }
        });
        textField_dob.clear();
        textField_email.clear();
        textField_firstName.clear();
        textField_lastName.clear();
        textField_type.clear();
        tableView_userTable.getItems().clear();
        readRecords();
    }

    @FXML
    private void handle_writeRecord(ActionEvent event) throws InterruptedException, ExecutionException {

        writeRecord();
        handle_readRecords(event);

    }

    @FXML
    private void handle_readRecords(ActionEvent event) {

        textField_dob.clear();
        textField_email.clear();
        textField_firstName.clear();
        textField_lastName.clear();
        textField_type.clear();
        tableView_userTable.getItems().clear();
        readRecords();

    }

    @FXML
    private void handle_updateRecord(ActionEvent event) throws InterruptedException, ExecutionException {

        updateRecord();
        handle_readRecords(event);

    }

    @FXML
    private void handle_removeRecord(ActionEvent event) throws InterruptedException, ExecutionException {

        removeRecord();
        handle_readRecords(event);

    }

    @FXML
    private void handle_clearRecord(ActionEvent event) throws InterruptedException, ExecutionException {

        clearRecord();
        handle_readRecords(event);
    }

    public void clearRecord() {
        textField_dob.clear();
        textField_email.clear();
        textField_firstName.clear();
        textField_lastName.clear();
        textField_type.clear();
        tableView_userTable.getItems().clear();
        readRecords();
    }

    public void writeRecord() throws InterruptedException, ExecutionException {

        if (!"".equals(textField_dob.getText()) || !"".equals(textField_email.getText()) || !"".equals(textField_firstName.getText()) || !"".equals(textField_lastName.getText()) || !"".equals(textField_type.getText())) {
            DocumentReference docRef = App.fstore.collection("users").document(UUID.randomUUID().toString());
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("DOB", textField_dob.getText());
            data.put("email", textField_email.getText());
            data.put("firstName", textField_firstName.getText());
            data.put("lastName", textField_lastName.getText());
            data.put("type", textField_type.getText());

            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
            // ...

        }

    }

    public void readRecords() {

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = App.fstore.collection("users").get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;

        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    User user = new User(document);

                    listOfUsers.add(user);

                }
            } else {
                System.out.println("No data");
            }

        } catch (InterruptedException | ExecutionException ex) {
        }
        tableView_userTable.setItems(listOfUsers);
    }

    public void updateRecord() throws InterruptedException, ExecutionException {

        if (!"".equals(textField_dob.getText()) || !"".equals(textField_email.getText()) || !"".equals(textField_firstName.getText()) || !"".equals(textField_lastName.getText()) || !"".equals(textField_type.getText())) {
            DocumentReference docRef = App.fstore.collection("users").document(currentID);

            Map<String, Object> updates = new HashMap<>();
            updates.put("DOB", textField_dob.getText());
            updates.put("email", textField_email.getText());
            updates.put("firstName", textField_firstName.getText());
            updates.put("lastName", textField_lastName.getText());
            updates.put("type", textField_type.getText());

            ApiFuture<WriteResult> writeResult = docRef.update(updates);

        }

    }

    public void removeRecord() throws InterruptedException, ExecutionException {

        ApiFuture<WriteResult> writeResult = App.fstore.collection("users").document(currentID).delete();

    }
}
