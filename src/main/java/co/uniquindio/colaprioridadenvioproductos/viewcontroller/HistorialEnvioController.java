package co.uniquindio.colaprioridadenvioproductos.viewcontroller;

import co.uniquindio.colaprioridadenvioproductos.MainApp;
import co.uniquindio.colaprioridadenvioproductos.controller.ModelFactoryController;
import co.uniquindio.colaprioridadenvioproductos.estructura.ListaSimple;
import co.uniquindio.colaprioridadenvioproductos.estructura.Nodo;
import co.uniquindio.colaprioridadenvioproductos.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;

public class HistorialEnvioController {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Double> valorColumn;
    @FXML
    private TableColumn<Producto, Double> pesoColumn;
    @FXML
    private TableColumn<Producto, Integer> prioridadColumn;
    @FXML
    private TableColumn<Producto, LocalDateTime> fechaColumn;
    @FXML
    private Button atrasButton;
    private MainApp aplicacion;

    public void setAplicacion(MainApp aplicacion) {
        this.aplicacion = aplicacion;
    }

    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        pesoColumn.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        prioridadColumn.setCellValueFactory(new PropertyValueFactory<>("Prioridad"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("Fecha"));

        atrasButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mostrarInicio());

        mostrarHistorialEnvios();
    }

    private void mostrarInicio() {
        this.aplicacion.mostrarVentanaEnvioProducto();
    }

    private void mostrarHistorialEnvios() {
        ListaSimple<Producto> productosEnviados = modelFactoryController.getProductosEnviados();

        Nodo<Producto> nodoActual = productosEnviados.getNodoPrimero();

        while (nodoActual != null) {
            tablaProductos.getItems().add(nodoActual.getValorNodo());
            nodoActual = nodoActual.getSiguienteNodo();
        }
    }

}
