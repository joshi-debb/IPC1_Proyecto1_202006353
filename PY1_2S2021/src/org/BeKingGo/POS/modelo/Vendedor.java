package org.BeKingGo.POS.modelo;

import org.BeKingGo.POS.POS;

import java.io.Serializable;

public class Vendedor implements Serializable {

    private int codigo;
    private String nombre;
    private int caja;
    private double ventas;
    private String genero;
    private String password;

    public Vendedor() {

    }

    public Vendedor(int codigo, String nombre, int caja, double ventas, String genero,String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.caja = caja;
        this.ventas = ventas;
        this.genero = genero;
        this.password = password;
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

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean loginVendedores(String nombre, String password){

        for (int i = 0; i < POS.contador4; ++i) {
            if (POS.datosCarga4[i].getNombre().equals(nombre) && POS.datosCarga4[i].getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
