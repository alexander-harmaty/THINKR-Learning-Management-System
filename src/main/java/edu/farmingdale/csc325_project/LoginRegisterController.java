package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class LoginRegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private MFXButton button_existing_login;

    @FXML
    private MFXButton button_new_register;

    @FXML
    private MFXComboBox<?> comboBox_new_type;

    @FXML
    private MFXDatePicker datePicker_new_DOB;

    @FXML
    private MFXTextField textField_existing_email;

    @FXML
    private MFXPasswordField textField_existing_pass;

    @FXML
    private MFXTextField textField_new_email1;

    @FXML
    private MFXTextField textField_new_email2;

    @FXML
    private MFXTextField textField_new_fName;

    @FXML
    private MFXTextField textField_new_lName;

    @FXML
    private MFXPasswordField textField_new_pass1;

    @FXML
    private MFXPasswordField textField_new_pass2;

    @FXML
    private VBox vBox_existingAccount;

    @FXML
    private VBox vBox_newAccount;

    @FXML
    void handleButton_login(ActionEvent event) {

    }

    @FXML
    void handleButton_register(ActionEvent event) {

    }

}
