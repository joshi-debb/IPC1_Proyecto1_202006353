package org.BeKingGo.POS.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModVentas extends JFrame {

    public static JFrame ventanaVentas;

    //INSTANCIA DEL PANEL DE LA CLASE NUEVA VENTA
    NuevaVenta panelNuevaVenta;
    //INSTANCIA DEL PANEL DE LA CLASE VENTAS
    Venta panelVenta;
    //INSTANCIA DEL PANEL DE PESTANAS
    JTabbedPane windows;


    public ModVentas() {

        ventanaVentas = new JFrame();

        //RECUADRO MODULO ADMINISTRACION
        ventanaVentas.setLayout(null);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        ventanaVentas.setLocation(ancho / 4, altura / 16);
        Color aux = new Color(91, 126, 179, 223);
        ventanaVentas.getContentPane().setBackground(aux);
        ventanaVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaVentas.setResizable(false);
        ventanaVentas.setVisible(true);

        //TITULO SELECCIONAR CLIENTE
        JLabel lblt1 = new JLabel();
        lblt1.setText("Bienvenido: " + ModAutentic.name + "!!");
        lblt1.setBounds(400, 10, 250, 30);
        ventanaVentas.add(lblt1);

        //BOTON DE CERRAR SESION
        JButton botonCerrar = new JButton(" Cerrar Sesion ");
        botonCerrar.setBounds(new Rectangle(665, 0, 125, 20));
        Color aux2 = new Color(250, 77, 77);
        botonCerrar.setBackground(aux2);
        botonCerrar.addActionListener(regresar);
        ventanaVentas.add(botonCerrar);

        componentes();

        ventanaVentas.pack();
        ventanaVentas.setSize(800, 700);
    }

    private void componentes() {

        windows = new JTabbedPane();
        windows.setBounds(25, 30, 738, 605);

        //PANELES
        panelNuevaVenta = new NuevaVenta();
        Color aux = new Color(156, 154, 154, 255);
        panelNuevaVenta.setBackground(aux);

        panelVenta = new Venta();
        panelVenta.setBackground(aux);

        //PESTAÑAS
        windows.add("Nueva Venta", panelNuevaVenta);
        windows.add("Ventas", panelVenta);
        ventanaVentas.add(windows);
    }

    //ACCION DE CERRAR SESION
    ActionListener regresar = new ActionListener() {
        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {
            Vendedor user = new Vendedor();
            ModAutentic login = new ModAutentic(user);
            login.setVisible(true);
            ventanaVentas.dispose();
        }
    };


}
