package org.BeKingGo.POS.modelo;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable {

    private int numero;
    private String nit;
    private String nombre;
    private Date fecha;
    private double total;


    public Factura(int numero, String nit, String nombre, Date fecha, double total) {
        this.numero = numero;
        this.nit = nit;
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
