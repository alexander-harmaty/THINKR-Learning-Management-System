package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable{

    @FXML
    private VBox VBox_leftMenuBar;

    @FXML
    private MFXButton button_home;

    @FXML
    private MFXButton button_settings;

    @FXML
    void handleButton_home(ActionEvent event) {

    }

    @FXML
    void handleButton_settings(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
