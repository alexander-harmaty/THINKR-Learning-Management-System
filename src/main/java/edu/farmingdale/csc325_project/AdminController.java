package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;

public class AdminController {

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
    private TableColumn<?, ?> tableColumn_dob;

    @FXML
    private TableColumn<?, ?> tableColumn_email;

    @FXML
    private TableColumn<?, ?> tableColumn_firstName;

    @FXML
    private TableColumn<?, ?> tableColumn_lastName;

    @FXML
    private TableColumn<?, ?> tableColumn_type;

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

}
