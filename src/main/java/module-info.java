module co.uniquindio.colaprioridadenvioproductos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens co.uniquindio.colaprioridadenvioproductos to javafx.fxml;
    exports co.uniquindio.colaprioridadenvioproductos;
    exports co.uniquindio.colaprioridadenvioproductos.viewcontroller;
    opens co.uniquindio.colaprioridadenvioproductos.viewcontroller to javafx.fxml;
    opens co.uniquindio.colaprioridadenvioproductos.model to javafx.base;
}