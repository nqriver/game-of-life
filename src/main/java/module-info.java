module com.myprojects.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.myprojects.gameoflife to javafx.fxml;
    exports com.myprojects.gameoflife;

    exports com.myprojects.gameoflife.controller to javafx.fxml;
    opens com.myprojects.gameoflife.controller to javafx.fxml;
}
