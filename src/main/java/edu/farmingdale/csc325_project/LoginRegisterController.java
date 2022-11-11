package edu.farmingdale.csc325_project;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class LoginRegisterController implements Initializable {
    
    @FXML //declare Pane for animated menu
    private Pane pane_box;
    
    @FXML //declare Rectangle for animated menu
    private Rectangle rectangle;
    
    @FXML //declare Buttons
    private MFXButton button_existing_login, button_new_register,
                    button_existingAccount, button_newAccount;

    @FXML //declare ComboBox for user input
    private MFXComboBox<String> comboBox_new_type;

    @FXML //declare DatePicker for user input
    private MFXDatePicker datePicker_new_DOB;

    @FXML //declare TextFields for user input
    private MFXTextField textField_existing_email, 
        textField_new_email1, textField_new_email2, 
        textField_new_fName, textField_new_lName;
    
    @FXML //declare PasswordFields for user input
    private MFXPasswordField textField_existing_pass, 
            textField_new_pass1, textField_new_pass2;

    @FXML //declare VBoxs for animated menu
    private VBox vBox_existing_fields, vBox_new_fields,
                vBox_existing_box, vBox_new_box;
    
    /**
     * Initializes the controller class & ComboBox.Account types combo box options must be initialized.
     * 
     *
     * @param url 
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize ComboBox options
        String accountTypes[] = {"STUDENT", "PROFESSOR", "ADMIN"};
        ObservableList<String> options = FXCollections.observableArrayList(accountTypes);
        comboBox_new_type.setItems(options);
        
        //showExisting();
        
    }
    
    public void showExisting() {
                
        FadeTransition fadeIn_fields = new FadeTransition();
        fadeIn_fields.setDuration(Duration.seconds(1.5));
        fadeIn_fields.setNode(vBox_existing_fields);
        fadeIn_fields.setFromValue(0.0);
        fadeIn_fields.setToValue(1.0);
        
        FadeTransition fadeIn_box = new FadeTransition();
        fadeIn_box.setDuration(Duration.seconds(1.5));
        fadeIn_box.setNode(vBox_existing_box);
        fadeIn_box.setFromValue(0.0);
        fadeIn_box.setToValue(1.0);
        
        FadeTransition fadeOut_fields = new FadeTransition();
        fadeOut_fields.setDuration(Duration.seconds(1.5));
        fadeOut_fields.setNode(vBox_new_fields);
        fadeOut_fields.setFromValue(1.0);
        fadeOut_fields.setToValue(0.0);
        
        FadeTransition fadeOut_box = new FadeTransition();
        fadeOut_box.setDuration(Duration.seconds(1.5));
        fadeOut_box.setNode(vBox_new_box);
        fadeOut_box.setFromValue(1.0);
        fadeOut_box.setToValue(0.0);
        
        Path leftPath = new Path();
        leftPath.getElements().add(new MoveTo(510,200));
        leftPath.getElements().add(new LineTo(170,200));
        PathTransition leftPathTransition = new PathTransition();
        leftPathTransition.setDuration(Duration.seconds(1.25));
        leftPathTransition.setPath(leftPath);
        leftPathTransition.setNode(pane_box);
        
        ParallelTransition showExisting = 
                new ParallelTransition(leftPathTransition,
                fadeIn_fields, fadeIn_box, fadeOut_fields, fadeOut_box);
        vBox_existing_fields.setVisible(true);
        vBox_existing_box.setVisible(true);
        showExisting.play();
        vBox_new_fields.setVisible(false);
        vBox_new_box.setVisible(false);
    }
    
    public void showNew() {
        
        FadeTransition fadeIn_fields = new FadeTransition();
        fadeIn_fields.setDuration(Duration.seconds(1.5));
        fadeIn_fields.setNode(vBox_new_fields);
        fadeIn_fields.setFromValue(0.0);
        fadeIn_fields.setToValue(1.0);
        
        FadeTransition fadeIn_box = new FadeTransition();
        fadeIn_box.setDuration(Duration.seconds(1.5));
        fadeIn_box.setNode(vBox_new_box);
        fadeIn_box.setFromValue(0.0);
        fadeIn_box.setToValue(1.0);
        
        FadeTransition fadeOut_fields = new FadeTransition();
        fadeOut_fields.setDuration(Duration.seconds(1.5));
        fadeOut_fields.setNode(vBox_existing_fields);
        
        fadeOut_fields.setFromValue(1.0);
        fadeOut_fields.setToValue(0.0);
        
        FadeTransition fadeOut_box = new FadeTransition();
        fadeOut_box.setDuration(Duration.seconds(1.5));
        fadeOut_box.setNode(vBox_existing_box);
        fadeOut_box.setFromValue(1.0);
        fadeOut_box.setToValue(0.0);
                
        Path rightPath = new Path();
        rightPath.getElements().add(new MoveTo(170,200));
        rightPath.getElements().add(new LineTo(510,200));
        PathTransition rightPathTransition = new PathTransition();
        rightPathTransition.setDuration(Duration.seconds(1.25));
        rightPathTransition.setPath(rightPath);
        rightPathTransition.setNode(pane_box);
        
        ParallelTransition showExisting = 
                new ParallelTransition(rightPathTransition,
                fadeIn_fields, fadeIn_box, fadeOut_fields, fadeOut_box);
        
        vBox_new_fields.setVisible(true);
        vBox_new_box.setVisible(true);
        showExisting.play();
        vBox_existing_fields.setVisible(false);
        vBox_existing_box.setVisible(false);
        
    }

    /**
     * Handle function for login button.
     * 
     * Takes text from text fields for email and password.
     * Then compares email and passwords to logins table to find a match.
     * If match is found, account type is read and switched to
     * 
     * @throws IOException 
     */
    public void handleButton_login() throws IOException {
                
        String email = textField_existing_email.getText();
        String pass = textField_existing_pass.getText();
        
        if ("".equals(email) && "".equals(pass))
        {
            javax.swing.JOptionPane.showMessageDialog( null, 
                    "Please fill in all fields" , "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try 
            {
                Connection con = DBConnection.connectDB();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from users where email = '"
                        + email + "' and password = '" + pass + "';");
                
                if (rs.next() && rs.getString(2).equals(email) && rs.getString(3).equals(pass)) 
                {
                    App.currentUser = new CurrentUser(rs.getString(1), rs.getString(2), 
                            rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8));
                    System.out.println(rs.getString(4));

                    switch (rs.getString(4)) {
                        case "STUDENT":           
                            App.setRoot("StudentView");
                            break;
                        case "PROFESSOR":
                            App.setRoot("ProfessorView");
                            System.out.println("Professor");
                            break;
                        case "ADMIN":
                            App.setRoot("AdminView");
                            break;
                        default:
                            break;
                    }
                }
                else
                { 
                    javax.swing.JOptionPane.showMessageDialog( null, 
                            "Incorrect username or password." , "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE );     
                }
            } catch (IOException | SQLException e) {
                javax.swing.JOptionPane.showMessageDialog( null, 
                        "Please fill in all fields" , "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE );
            }
        } 
    }

    /**
     * Handle function for register button.Makes a connection to the DB and creates a record in the users table.
     * 
     * Change view to login screen & auto fill fields.
     * 
     * @throws IOException 
     * @throws java.sql.SQLException 
     */
    public void handleButton_register() throws IOException, SQLException{
        
        try {
            String email1 = textField_new_email1.getText();
            String email2 = textField_new_email2.getText();
            String pass1 = textField_new_pass1.getText();
            String pass2 = textField_new_pass2.getText();
            String fName = textField_new_fName.getText();
            String lName = textField_new_lName.getText();
            LocalDate birthday = datePicker_new_DOB.getValue();
            String DOB = birthday.toString();
            String type = comboBox_new_type.getValue();

            //check for missmatching email & pass
            if (!email1.equals(email2) || !pass1.equals(pass2)) {
                System.out.println("Please assure email and password match");
                javax.swing.JOptionPane.showMessageDialog( null, 
                        "Please assure email and password match" , "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE );
            }
            //check for any blank fields
            else if ("".equals(email1) || "".equals(email2) || "".equals(pass1) || "".equals(pass2)
                    || "".equals(fName) || "".equals(lName) || "".equals(type)) {
                System.out.println("Please assure all fields are filled");
                javax.swing.JOptionPane.showMessageDialog( null, 
                        "Please assure all fields are filled" , "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE );
            }
            //add user
            else {
                Connection con = DBConnection.connectDB();
                String query = "insert into users (email, password, type, firstName, lastName, DOB) values (?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setString(1, email1);
                ps.setString(2, pass1);
                ps.setString(3, type);
                ps.setString(4, fName);
                ps.setString(5, lName);
                ps.setString(6, DOB);
                
                ps.executeUpdate();
                

                App.setRoot("Login");
            }
        } catch (NullPointerException e){
            javax.swing.JOptionPane.showMessageDialog( null, 
                    "Please assure a date is selected" , "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE );
        }
    }

}
