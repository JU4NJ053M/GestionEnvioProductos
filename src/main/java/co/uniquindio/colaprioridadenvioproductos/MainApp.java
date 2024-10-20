package co.uniquindio.colaprioridadenvioproductos;

import co.uniquindio.colaprioridadenvioproductos.viewcontroller.EnvioProductoController;
import co.uniquindio.colaprioridadenvioproductos.viewcontroller.HistorialEnvioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mostrarVentanaEnvioProducto();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void mostrarVentanaEnvioProducto(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/colaprioridadenvioproductos/EnvioProductoView.fxml"));
            Parent root = loader.load();

            EnvioProductoController envioController = loader.getController();
            envioController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestión de Envíos de Productos");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaHistorialEnvio(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/colaprioridadenvioproductos/HistorialEnvioView.fxml"));
            Parent root = loader.load();

            HistorialEnvioController historialController = loader.getController();
            historialController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Historial de Envíos");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
