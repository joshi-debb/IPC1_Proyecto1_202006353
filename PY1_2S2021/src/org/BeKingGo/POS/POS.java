package org.BeKingGo.POS;

import org.BeKingGo.POS.modelo.*;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class POS implements Serializable {

    //OBJETO DE LA CLASE PRINCIPAL SUCURSALES
    public static Sucursal[] datosCarga1 = new Sucursal[50];
    public static int contador1 = 0;

    //OBJETO DE LA CLASE PRINCIPAL PRODUCTOS
    public static Producto[] datosCarga2 = new Producto[200];
    public static int contador2 = 0;

    //OBJETO DE LA CLASE PRINCIPAL PRODUCTOS
    public static Cliente[] datosCarga3 = new Cliente[100];
    public static int contador3 = 0;

    //OBJETO DE LA CLASE PRINCIPAL VENDEDORES
    public static Vendedor[] datosCarga4 = new Vendedor[400];
    public static int contador4 = 0;

    public static void main(String[] args) {

        //new ModVentas();
        //new ModAdmin();

        Vendedor users = new Vendedor();
        ModAutentic principal = new ModAutentic(users);
        principal.setVisible(true);


        //CICLO QUE RECORRE LA CANTIDAD DE FILAS Y CREA LOS OBJETOS SUCURSALES
        for (int i = 0; i < 50; i++) {
            datosCarga1[i] = new Sucursal();
        }

        //CICLO QUE RECORRE LA CANTIDAD DE FILAS Y CREA LOS OBJETOS PRODUCTOS
        for (int i = 0; i < 200; i++) {
            datosCarga2[i] = new Producto();
        }

        //CICLO QUE RECORRE LA CANTIDAD DE FILAS Y CREA LOS OBJETOS CLIENTES
        for (int i = 0; i < 100; i++) {
            datosCarga3[i] = new Cliente();
        }

        //CICLO QUE RECORRE LA CANTIDAD DE FILAS Y CREA LOS OBJETOS CLIENTES
        for (int i = 0; i < 400; i++) {
            datosCarga4[i] = new Vendedor();
        }

    }

    //METODO PARA LA CARGA MASIVA SEGUN LOS ESPACIOS LO PERMITAN
    public static void CargaMasivaSucursales(Sucursal nueva) {
        if (contador1 < datosCarga1.length) {
            datosCarga1[contador1] = nueva;
            contador1++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 50 sucursales:" +
                    " Se eliminaran las sobrantes.", null, 2);
        }
    }

    //METODO PARA LA CARGA MASIVA SEGUN LOS ESPACIOS LO PERMITAN
    public static void CargaMasivaProductos(Producto nuevo) {
        if (contador2 < datosCarga2.length) {
            datosCarga2[contador2] = nuevo;
            contador2++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 200 Productos:" +
                    " Se eliminaran los sobrantes.", null, 2);
        }
    }

    //METODO PARA LA CARGA MASIVA SEGUN LOS ESPACIOS LO PERMITAN
    public static void CargaMasivaClientes(Cliente nuevos) {
        if (contador3 < datosCarga3.length) {
            datosCarga3[contador3] = nuevos;

            if (Objects.equals(nuevos.getGenero(), "m")){
                Clientes.contadorHombres++;
            }

            if (Objects.equals(nuevos.getGenero(), "f")){
                Clientes.contadorMujeres++;
            }

            contador3++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 100 Clientes:" +
                    " Se eliminaran los sobrantes.", null, 2);
        }
    }

    //METODO PARA LA CARGA MASIVA SEGUN LOS ESPACIOS LO PERMITAN
    public static void CargaMasivaVendedores(Vendedor nuevos2) {
        if (contador4 < datosCarga4.length) {
            datosCarga4[contador4] = nuevos2;
            contador4++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 400 Vendedores:" +
                    " Se eliminaran los sobrantes.", null, 2);
        }
    }

    //------------------SERIALIZACION---------------------------------

    public static void serializacion(String path, Object object) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object desSerializacion(String path) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Object data = objectInputStream.readObject();
            objectInputStream.close();

            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //----------------------------------------------------------------

}

