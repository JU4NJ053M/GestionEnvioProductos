package co.uniquindio.colaprioridadenvioproductos.model;

import java.time.LocalDateTime;

public class Producto implements Comparable<Producto> , Priorizable {
    private String nombre;
    private double peso;
    private double valor;
    private int prioridad;
    private String fechaEnvio;

    public Producto(String nombre, double peso, double valor, int prioridad) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
        this.prioridad = prioridad;
        this.fechaEnvio = null;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public int getPrioridad() {
        return prioridad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Producto other) {
        return Integer.compare(this.prioridad, other.prioridad);
    }

    @Override
    public String toString() {
        return "\tNombre : " + nombre +
                "\n\tPeso : " + peso + " Kg" +
                "\n\tValor : $" + valor +
                "\n\tPrioridad : " + prioridad;
    }

    public String getFecha() {
        return fechaEnvio;
    }

    public void setFecha(String fecha) {
        this.fechaEnvio = fecha;
    }
}