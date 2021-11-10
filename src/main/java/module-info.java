module com.myprojects.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.myprojects.gameoflife to javafx.fxml;
    exports com.myprojects.gameoflife;
}
