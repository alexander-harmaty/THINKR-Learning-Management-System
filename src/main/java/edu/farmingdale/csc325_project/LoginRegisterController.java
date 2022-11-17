package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
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
import javax.swing.JOptionPane;

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
        
        showExisting();
        
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
     * Handle function for login button.Takes text from text fields for email and password.Then compares email and passwords to logins table to find a match.
     * 
     * If match is found, account type is read and switched to
     * 
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     * @throws java.util.concurrent.ExecutionException 
     */
    public void handleButton_login() throws IOException, InterruptedException, ExecutionException {
                
        String inputEmail = textField_existing_email.getText();
        String inputPass = textField_existing_pass.getText();
        String docEmail;
        String docPass;
        String docType;
        Boolean userFound = false;
        
        if (!"".equals(inputEmail) && !"".equals(inputPass))
        {
            //asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future =  App.fstore.collection("users").get();
            // future.get() blocks on response
            List<QueryDocumentSnapshot> documents;
            
            try 
            {
                documents = future.get().getDocuments();
            
                if(!documents.isEmpty())
                {
                    for (QueryDocumentSnapshot document : documents) 
                    {
                        docEmail = String.valueOf(document.getData().get("email"));
                        docPass = String.valueOf(document.getData().get("password"));
                        docType = String.valueOf(document.getData().get("type"));
                        
                        if (docEmail.equals(inputEmail) && docPass.equals(inputPass)) 
                        {
                            userFound = true;
                            App.currentUser = new CurrentUser();

                            switch(docType) 
                            {
                                case "STUDENT":           
                                    App.setRoot("StudentView");
                                    break;
                                case "PROFESSOR":
                                    App.setRoot("ProfessorView");
                                    break;
                                case "ADMIN":
                                    App.setRoot("AdminView");
                                    break;
                                default:
                                    break;
                            }
                            
                        }
                    }
                    if (!userFound) {
                        javax.swing.JOptionPane.showMessageDialog( null, "Incorrect username or password." , "Error", javax.swing.JOptionPane.ERROR_MESSAGE );  
                    }
                }
                else
                {
                   javax.swing.JOptionPane.showMessageDialog( null, "EMPTY DATABASE" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
                }
                
            } 
            catch (IOException e) {}
        } 
        else
        {
            javax.swing.JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
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
                DocumentReference docRef = App.fstore.collection("users").document(UUID.randomUUID().toString()); //generate number ID instead?
                
                Map<String, Object> data = new HashMap<>();
                data.put("DOB", DOB);
                data.put("email", email1);
                data.put("firstName", fName);
                data.put("lastName", lName);
                data.put("password", pass1);
                data.put("type", type);
                
                //asynchronously write data
                ApiFuture<WriteResult> result = docRef.set(data);
                
                System.out.println(docRef.getId());
                
                JOptionPane.showMessageDialog(null, 
                        "Account successfully created! \n Please log in..." , "Success!", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                showExisting();
            }
        } catch (NullPointerException e){
            //just for datePicker
            javax.swing.JOptionPane.showMessageDialog( null, 
                    "Please assure all fields are filled" , "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE );
        }
    }

}
