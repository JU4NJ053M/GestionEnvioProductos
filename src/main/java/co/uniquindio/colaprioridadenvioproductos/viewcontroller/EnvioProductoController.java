package co.uniquindio.colaprioridadenvioproductos.viewcontroller;

import co.uniquindio.colaprioridadenvioproductos.MainApp;
import co.uniquindio.colaprioridadenvioproductos.controller.ModelFactoryController;
import co.uniquindio.colaprioridadenvioproductos.estructura.ColaPrioridad;
import co.uniquindio.colaprioridadenvioproductos.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnvioProductoController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField pesoField;
    @FXML
    private TextField valorField;
    @FXML
    private TextField prioridadField;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Double> pesoColumn;
    @FXML
    private TableColumn<Producto, Integer> prioridadColumn;
    @FXML
    private TableColumn<Producto, Double> valorColumn;
    @FXML
    private Button agregarButton;
    @FXML
    private Button enviarButton;
    @FXML
    private Button enviadosButton;
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

        agregarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> agregarProducto());
        enviarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> enviarProducto());
        enviadosButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> irAHistorial());

        actualizarTabla();

    }

    private void irAHistorial() {
        this.aplicacion.mostrarVentanaHistorialEnvio();
    }

    private void agregarProducto() {
        String nombre = nombreField.getText().trim();
        String pesoTexto = pesoField.getText().trim();
        String prioridadTexto = prioridadField.getText().trim();
        String valorTexto = valorField.getText().trim();

        String mensajeError = validarCampos(nombre, pesoTexto, prioridadTexto, valorTexto);
        if (mensajeError != null) {
            JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double peso = Double.parseDouble(pesoTexto);
        int prioridad = Integer.parseInt(prioridadTexto);
        double valor = Double.parseDouble(valorTexto);

        modelFactoryController.agregarProducto(nombre, peso, valor, prioridad);

        actualizarTabla();
        clearFields();
    }

    private void actualizarTabla() {
        tablaProductos.getItems().clear();

        ColaPrioridad<Producto> productosCola = modelFactoryController.getProductosCola();

        ColaPrioridad<Producto> colaCopia = new ColaPrioridad<>(productosCola);

        while (colaCopia.hayElementos()) {
            tablaProductos.getItems().add(colaCopia.desencolar());
        }
    }

    private void enviarProducto() {
        Producto productoEnviado = modelFactoryController.enviarProducto();
        if (productoEnviado != null) {
            tablaProductos.getItems().remove(productoEnviado);
            productoEnviado.setFecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            modelFactoryController.getProductosEnviados().agregarInicio(productoEnviado);
            JOptionPane.showMessageDialog(null, "El producto:\n" + productoEnviado.toString() + "\nHa sido enviado Correctamente", "Producto Enviado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La lista de espera está vacía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearFields() {
        nombreField.clear();
        pesoField.clear();
        valorField.clear();
        prioridadField.clear();
    }

    private String validarCampos(String nombre, String pesoTexto, String prioridadTexto, String valorTexto) {
        if (nombre.isEmpty()) {
            return "El campo del nombre no puede estar vacío";
        }

        if (pesoTexto.isEmpty()) {
            return "El campo del peso no puede estar vacío";
        }
        try {
            double peso = Double.parseDouble(pesoTexto);
            if (peso < 0) {
                return "El peso debe ser mayor a 0";
            }
        } catch (NumberFormatException e) {
            return "Por favor, ingrese un peso válido";
        }

        if (valorTexto.isEmpty()) {
            return "El campo del valor no puede estar vacío.";
        }
        try {
            double valor = Double.parseDouble(valorTexto);
            if (valor < 0) {
                return "El valor debe ser mayor a 0";
            }
        } catch (NumberFormatException e) {
            return "Por favor, ingrese un valor válido.";
        }

        if (prioridadTexto.isEmpty()) {
            return "El campo de la Prioridad no puede estar vacío";
        }

        try {
            int prioridad = Integer.parseInt(prioridadTexto);
            if (prioridad < 1) {
                return "La prioridad debe ser mayor o igual a 1";
            }
        } catch (NumberFormatException e) {
            return "Por favor, ingrese una prioridad válida";
        }

        return null;
    }
}
