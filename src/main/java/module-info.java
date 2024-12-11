module com.example.bagistakipsistemi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bagistakipsistemi to javafx.fxml;
    exports com.example.bagistakipsistemi;
}