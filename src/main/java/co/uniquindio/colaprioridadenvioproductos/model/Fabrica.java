package co.uniquindio.colaprioridadenvioproductos.model;

import co.uniquindio.colaprioridadenvioproductos.estructura.ColaPrioridad;
import co.uniquindio.colaprioridadenvioproductos.estructura.ListaSimple;

public class Fabrica {
    private ColaPrioridad<Producto> cola;
    private ListaSimple<Producto> productosEnviados;


    public Fabrica() {
        this.cola = new ColaPrioridad<>();
        this.productosEnviados = new ListaSimple<>();
    }

    public void agregarProducto(Producto producto) {
        cola.encolar(producto);
    }

    public Producto enviarProducto() {
        return cola.desencolar();
    }

    public boolean hayProductos() {
        return cola.hayElementos();
    }


    public ColaPrioridad<Producto> getCola() {
        return cola;
    }
    public ListaSimple<Producto> getProductosEnviados() {
        return productosEnviados;
    }

}
