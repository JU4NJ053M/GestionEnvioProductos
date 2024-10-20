package co.uniquindio.colaprioridadenvioproductos.controller;

import co.uniquindio.colaprioridadenvioproductos.estructura.ColaPrioridad;
import co.uniquindio.colaprioridadenvioproductos.estructura.ListaSimple;
import co.uniquindio.colaprioridadenvioproductos.model.Fabrica;
import co.uniquindio.colaprioridadenvioproductos.model.Producto;

public class ModelFactoryController {
    private Fabrica fabrica;

    public ModelFactoryController() {
        this.fabrica = new Fabrica();
    }

    public void agregarProducto(String nombre, double peso, double valor, int prioridad) {
        Producto producto = new Producto(nombre, peso, valor, prioridad);
        fabrica.agregarProducto(producto);
    }

    public Producto enviarProducto() {
        return fabrica.enviarProducto();
    }

    public boolean hayProductos() {
        return fabrica.hayProductos();
    }

    public ColaPrioridad<Producto> getProductosCola() {
        return fabrica.getCola();
    }
    public ListaSimple<Producto> getProductosEnviados() {
        return fabrica.getProductosEnviados();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
}

