module com.project {

    requires javafx.controls;
    requires javafx.fxml;

    requires com.opencsv;
    requires commons.math3;

    opens com.project.ui to javafx.fxml;

    exports com.project;
}