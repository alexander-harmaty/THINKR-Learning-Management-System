module edu.farmingdale.csc325_project {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.farmingdale.csc325_project to javafx.fxml;
    exports edu.farmingdale.csc325_project;
}
