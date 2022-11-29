package edu.farmingdale.csc325_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    //creates instance of currentUser
    static CurrentUser currentUser;
    static Assignment currentAssignment;
    
    public static Firestore fstore;
    private static FirestoreContext contextFirebase;

    @Override
    public void start(Stage stage) throws IOException {
        contextFirebase = new FirestoreContext();
        fstore = contextFirebase.firebase();
        scene = new Scene(loadFXML("LoginRegister"));
        stage.setTitle("Learning Management System");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}