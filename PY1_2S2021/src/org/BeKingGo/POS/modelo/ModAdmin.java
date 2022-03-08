package org.BeKingGo.POS.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModAdmin extends JFrame {

    public static JFrame ventanaPrincipal;

    //INSTANCIA DEL PANEL DE LA CLASE SUCURSALES
    Sucursales panelSucursales;
    //INSTANCIA DEL PANEL DE LA CLASE PRODUCTOS
    Productos panelProductos;
    //INSTANCIA DEL PANEL DE LA CLASE PRODUCTOS
    Clientes panelClientes;
    //INSTANCIA DEL PANEL DE LA CLASE VENDEDORES
    Vendedores panelVendedores;

    //INSTANCIA DEL PANEL DE PESTANAS
    JTabbedPane windows;

    public ModAdmin() {

        ventanaPrincipal = new JFrame();

        //RECUADRO MODULO ADMINISTRACION
        ventanaPrincipal.setLayout(null);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        ventanaPrincipal.setLocation(ancho / 4, altura / 16);
        Color aux = new Color(91, 126, 179, 223);
        ventanaPrincipal.getContentPane().setBackground(aux);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setVisible(true);

        //BOTON DE CERRAR SESION
        JButton botonCerrar = new JButton(" Cerrar Sesion ");
        botonCerrar.setBounds(new Rectangle(665, 0, 125, 20));
        Color aux2 = new Color(250, 77, 77);
        botonCerrar.setBackground(aux2);
        botonCerrar.addActionListener(regresar);
        ventanaPrincipal.add(botonCerrar);

        componentes();

        ventanaPrincipal.pack();
        ventanaPrincipal.setSize(800, 700);
    }

    private void componentes() {

        windows = new JTabbedPane();
        windows.setBounds(25, 30, 738, 605);



        //PANELES
        panelSucursales = new Sucursales();
        Color aux3 = new Color(156, 154, 154, 255);
        panelSucursales.setBackground(aux3);
        panelProductos = new Productos();
        panelProductos.setBackground(aux3);
        panelClientes = new Clientes();
        panelClientes.setBackground(aux3);
        panelVendedores = new Vendedores();
        panelVendedores.setBackground(aux3);

        //PESTAÑAS
        windows.add("Sucursales", panelSucursales);
        windows.add("Productos", panelProductos);
        windows.add("Clientes", panelClientes);
        windows.add("Vendedores", panelVendedores);
        ventanaPrincipal.add(windows);
    }

    //ACCION DE CERRAR SESION
    ActionListener regresar = new ActionListener() {
        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {
            Vendedor user = new Vendedor();
            ModAutentic login = new ModAutentic(user);
            login.setVisible(true);
            ventanaPrincipal.dispose();
        }
    };

}
