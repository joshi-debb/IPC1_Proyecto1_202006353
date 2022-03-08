package org.BeKingGo.POS.modelo;

import org.BeKingGo.POS.POS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Venta extends JPanel {

    public static JPanel seleccionarCliente;

    public Venta() {
        this.setLayout(null);
        componentes();
    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //PANEL PARA SELECCIONAR CLIENTES
        seleccionarCliente = new JPanel();
        seleccionarCliente.setLayout(null);
        seleccionarCliente.setBounds(25, 25, 685, 525);
        seleccionarCliente.setBackground(Color.white);
        seleccionarCliente.setVisible(true);
        seleccionarCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(seleccionarCliente);

        //TITULO SELECCIONAR CLIENTE
        JLabel lblt1 = new JLabel("Listado Oficial");
        lblt1.setBounds(0, 0, 150, 30);
        lblt1.setHorizontalAlignment(SwingConstants.CENTER);
        lblt1.setBorder(BorderFactory.createLineBorder(Color.black));
        seleccionarCliente.add(lblt1);

        //TEXTO FILTRAR POR
        JLabel lbl1 = new JLabel("Filtar por:");
        lbl1.setBounds(30, 35, 100, 30);
        seleccionarCliente.add(lbl1);

        //TEXTO FILTRADOS
        JLabel lbl2 = new JLabel("Filtrados:");
        lbl2.setBounds(30, 150, 100, 30);
        seleccionarCliente.add(lbl2);

        //TEXTO NO FACTURA
        JLabel lbl3 = new JLabel("No. Factura");
        lbl3.setBounds(115, 35, 100, 30);
        seleccionarCliente.add(lbl3);
        //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
        JTextField txt1 = new JTextField();
        txt1.setBounds(190, 35, 175, 25);
        txt1.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl4 = new JLabel("Nombre");
        lbl4.setBounds(115, 75, 100, 30);
        seleccionarCliente.add(lbl4);
        JTextField txt2 = new JTextField();
        txt2.setBounds(190, 75, 175, 25);
        txt2.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt2);

        //TEXTO NIT
        JLabel lbl5 = new JLabel("NIT");
        lbl5.setBounds(390, 35, 100, 30);
        seleccionarCliente.add(lbl5);
        JTextField txt3 = new JTextField();
        txt3.setBounds(430, 35, 175, 25);
        txt3.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt3);

        //TEXTO FECHA
        JLabel lbl6 = new JLabel("Fecha");
        lbl6.setBounds(380, 75, 100, 30);
        seleccionarCliente.add(lbl6);
        JTextField txt4 = new JTextField();
        txt4.setBounds(430, 75, 175, 25);
        txt4.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt4);

        //BOTON DE APLICAR FILTRO
        JButton aplicarFiltro = new JButton(" Aplicar Filtro ");
        aplicarFiltro.setBounds(new Rectangle(120, 110, 485, 30));
        aplicarFiltro.setBackground(Color.ORANGE);
        //botonCrear.addActionListener(Crear);
        seleccionarCliente.add(aplicarFiltro);


        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"No. Factura", "NIT", "Nombre","Fecha","Total","Acciones"};
        Object[][] data = new Object[POS.contador2][5];

        for (int i = 0; i < POS.contador2; i++) {


        }

        DefaultTableModel modeloV = new DefaultTableModel(data, titulos);
        JTable tablaV = new JTable(modeloV);
        tablaV.setRowHeight(25);
        tablaV.getTableHeader().setReorderingAllowed(false);
        tablaV.setBounds(30, 195, 630, 300);
        tablaV.setVisible(true);
        seleccionarCliente.add(tablaV);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaV);
        scroll.setBounds(30, 195, 630, 300);
        scroll.setVisible(true);
        seleccionarCliente.add(scroll);

    }






}
