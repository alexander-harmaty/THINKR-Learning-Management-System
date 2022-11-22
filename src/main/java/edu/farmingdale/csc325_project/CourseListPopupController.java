package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;
import io.github.palexdev.materialfx.controls.MFXTableView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CourseListPopupController implements Initializable{

    @FXML
    private MFXTableView<?> tableView_popup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApiFuture<QuerySnapshot> future =  App.fstore.collection("course").get();
            // future.get() blocks on response
            List<QueryDocumentSnapshot> documents;
            
            try 
            {
                documents = future.get().getDocuments();
            
                if(!documents.isEmpty())
                {
                    for (QueryDocumentSnapshot document : documents) 
                    {
                        
                       
     
         
    


        catch (InterruptedException ex) {
            Logger.getLogger(CourseListPopupController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CourseListPopupController.class.getName()).log(Level.SEVERE, null, ex);         
    
                }

