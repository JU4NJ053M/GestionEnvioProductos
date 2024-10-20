package co.uniquindio.colaprioridadenvioproductos.estructura;

import co.uniquindio.colaprioridadenvioproductos.model.Priorizable;

public class ColaPrioridad<T extends Priorizable> {

    private Nodo<T> frente;
    private Nodo<T> fin;

    public ColaPrioridad() {
        this.frente = null;
        this.fin = null;
    }

    public ColaPrioridad(ColaPrioridad<T> otraCola) {
        if (otraCola != null && otraCola.frente != null) {
            Nodo<T> actual = otraCola.frente;
            while (actual != null) {
                encolar(actual.getValorNodo());
                actual = actual.getSiguienteNodo();
            }
        }
    }

    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);

        if (frente == null) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            if (elemento.getPrioridad() < frente.getValorNodo().getPrioridad()) {
                nuevoNodo.setSiguienteNodo(frente);
                frente = nuevoNodo;
            } else {
                Nodo<T> actual = frente;
                while (actual.getSiguienteNodo() != null && actual.getSiguienteNodo().getValorNodo().getPrioridad() <= elemento.getPrioridad()) {
                    actual = actual.getSiguienteNodo();
                }
                nuevoNodo.setSiguienteNodo(actual.getSiguienteNodo());
                actual.setSiguienteNodo(nuevoNodo);

                if (nuevoNodo.getSiguienteNodo() == null) {
                    fin = nuevoNodo;
                }
            }
        }
    }

    public T desencolar() {
        if (frente == null) {
            return null;
        }

        T elemento = frente.getValorNodo();
        frente = frente.getSiguienteNodo();

        if (frente == null) {
            fin = null;
        }

        return elemento;
    }

    public boolean hayElementos() {
        return frente != null;
    }

    public T verFrente() {
        return (frente != null) ? frente.getValorNodo() : null;
    }
}
