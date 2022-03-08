package org.BeKingGo.POS.modelo;
import java.io.Serializable;

public class Producto implements Serializable {

    private int codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    private static  int ultimoCodgio;

    public Producto() {

    }


    public Producto(int codigo, String nombre,String descripcion,int cantidad, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static int getUltimoCodgio() {
        return ultimoCodgio;
    }

    public static void setUltimoCodgio(int ultimoCodgio) {
        Producto.ultimoCodgio = ultimoCodgio;
    }
}
