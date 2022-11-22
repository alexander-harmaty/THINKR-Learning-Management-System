package edu.farmingdale.csc325_project;

import com.google.api.core.ApiFuture;
import java.util.Arrays;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import java.io.IOException;
import java.lang.ArrayStoreException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;





public class CourseListPopupController implements Initializable{

    @FXML
    private MFXTableView<Course> tableView_popup;
    
     @FXML
    private MFXTableColumn<Integer> CRNCol;
     
     @FXML
    private MFXTableColumn<Integer> codeCol;
     
    @FXML
    private MFXTableColumn<String> subjectCol;
    
     @FXML
    private MFXTableColumn<String> titleCol;
     
    private ObservableList<Course> listOfCourses = FXCollections.observableArrayList();
    
    private Course course;

    public ObservableList<Course> getListOfCourses()
    {
        return listOfCourses;
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
       // CRNCol.cellFactoryProperty();
        
        
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
                        //copy document array into students
                        course = new Course(document);
                        
                        for(String student: course.students)
                        {
                            if(App.currentUser.userID == student)
                            {
                                listOfCourses.add(course);
                            }
                        }
                        
                    }
                   tableView_popup.setItems(listOfCourses);
                    
                }
            } catch (InterruptedException ex) {
            Logger.getLogger(CourseListPopupController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CourseListPopupController.class.getName()).log(Level.SEVERE, null, ex);         
    
//                } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(CourseListPopupController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    }
}
            

