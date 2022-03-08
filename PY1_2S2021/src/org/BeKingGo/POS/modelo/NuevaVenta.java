package org.BeKingGo.POS.modelo;

import org.BeKingGo.POS.POS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NuevaVenta extends JPanel {

    public Object[][] productoAlmacenado = new Object[POS.contador2][5];
    public int contadorVentas = 0;

    public int total;
    public double subtotal = 0;


    JFrame NuevoCliente;

    private JComboBox combo1;

    JTextField txt1;
    JTextField txt2;
    JTextField txt3;
    JTextField txt4;
    JTextField txt5;
    JTextField txt6;
    JTextField txt7;
    JTextField txt8;
    JTextField txt9;
    JTextField txt10;


    public static int contadorFacturas = 0;

    public static JPanel seleccionarCliente;
    public static JPanel agregarProductos;

    public NuevaVenta() {
        this.setLayout(null);
        componentes();
    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //PANEL PARA SELECCIONAR CLIENTES
        seleccionarCliente = new JPanel();
        seleccionarCliente.setLayout(null);
        seleccionarCliente.setBounds(25, 25, 685, 200);
        seleccionarCliente.setBackground(Color.white);
        seleccionarCliente.setVisible(true);
        seleccionarCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(seleccionarCliente);

        //TITULO SELECCIONAR CLIENTE
        JLabel lblt1 = new JLabel("Seleccionar Cliente");
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

        //TEXTO NOMBRE
        JLabel lbl3 = new JLabel("Nombre");
        lbl3.setBounds(120, 35, 100, 30);
        seleccionarCliente.add(lbl3);
        //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
        txt1 = new JTextField();
        txt1.setBounds(180, 35, 175, 25);
        txt1.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt1);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(120, 75, 100, 30);
        seleccionarCliente.add(lbl4);
        txt2 = new JTextField();
        txt2.setBounds(180, 75, 175, 25);
        txt2.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt2);

        //TEXTO NIT
        JLabel lbl5 = new JLabel("NIT");
        lbl5.setBounds(380, 35, 100, 30);
        seleccionarCliente.add(lbl5);
        txt3 = new JTextField();
        txt3.setBounds(430, 35, 175, 25);
        txt3.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt3);

        //TEXTO GENERO
        JLabel lbl6 = new JLabel("Genero");
        lbl6.setBounds(370, 75, 100, 30);
        seleccionarCliente.add(lbl6);
        txt4 = new JTextField();
        txt4.setBounds(430, 75, 175, 25);
        txt4.setBackground(Color.LIGHT_GRAY);
        seleccionarCliente.add(txt4);

        //BOTON DE APLICAR FILTRO
        JButton aplicarFiltro = new JButton(" Aplicar Filtro ");
        aplicarFiltro.setBounds(new Rectangle(120, 110, 485, 30));
        aplicarFiltro.setBackground(Color.ORANGE);
        aplicarFiltro.addActionListener(AplicarFiltro);
        seleccionarCliente.add(aplicarFiltro);

        //BOTON DE COMBO BOX <-----------------
        JLabel lbl7 = new JLabel("Cliente");
        lbl7.setBounds(120, 150, 100, 30);
        seleccionarCliente.add(lbl7);
        combo1 = new JComboBox(txt1.getActionListeners());
        combo1.setBounds(180, 150, 230, 30);
        combo1.setBackground(Color.ORANGE);
        seleccionarCliente.add(combo1);

        //BOTON DE AGREGAR
        JButton nuevoCliente = new JButton(" Nuevo Cliente ");
        nuevoCliente.setBounds(new Rectangle(430, 150, 175, 30));
        nuevoCliente.setBackground(Color.ORANGE);
        nuevoCliente.addActionListener(cliente);
        seleccionarCliente.add(nuevoCliente);


//-----------------------------------------------------------------------------------------------


        //PANEL PARA AGREGAR PRODUCTOS
        agregarProductos = new JPanel();
        agregarProductos.setLayout(null);
        agregarProductos.setBounds(25, 250, 685, 300);
        agregarProductos.setBackground(Color.white);
        agregarProductos.setBorder(BorderFactory.createLineBorder(Color.black));
        agregarProductos.setVisible(true);
        this.add(agregarProductos);

        //TITULO AGREGAR PRODUCTOS
        JLabel lblt2 = new JLabel("Agregar Productos");
        lblt2.setBounds(0, 0, 150, 30);
        lblt2.setHorizontalAlignment(SwingConstants.CENTER);
        lblt2.setBorder(BorderFactory.createLineBorder(Color.black));
        agregarProductos.add(lblt2);

        //TEXTO CODIGO
        JLabel lbl8 = new JLabel("Codigo");
        lbl8.setBounds(30, 65, 100, 30);
        agregarProductos.add(lbl8);
        txt5 = new JTextField();
        txt5.setBounds(75, 65, 150, 30);
        txt5.setBackground(Color.LIGHT_GRAY);
        agregarProductos.add(txt5);

        //TEXTO CANTIDAD
        JLabel lbl9 = new JLabel("Cantidad");
        lbl9.setBounds(250, 65, 100, 30);
        agregarProductos.add(lbl9);
        txt6 = new JTextField();
        txt6.setBounds(320, 65, 150, 30);
        txt6.setBackground(Color.LIGHT_GRAY);
        agregarProductos.add(txt6);

        //TEXTO TOTAL
        JLabel lbl10 = new JLabel("Total");
        lbl10.setBounds(470, 230, 140, 30);
        agregarProductos.add(lbl10);
        txt7 = new JTextField();
        txt7.setBounds(520, 230, 140, 30);
        txt7.setBackground(Color.LIGHT_GRAY);
        agregarProductos.add(txt7);

        //BOTON DE AGREGAR
        JButton agregar = new JButton(" Agregar ");
        agregar.setBounds(new Rectangle(520, 65, 140, 30));
        agregar.setBackground(Color.ORANGE);
        agregar.addActionListener(agrega);
        agregarProductos.add(agregar);

        //BOTON DE VENDER
        JButton vender = new JButton(" VENDER ");
        vender.setBounds(new Rectangle(30, 230, 400, 30));
        vender.setBackground(Color.GREEN);
        //botonCrear.addActionListener(Crear);
        agregarProductos.add(vender);

        //TEXTO FECHA
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JLabel lbl11 = new JLabel("Fecha: "+ dtf.format(LocalDateTime.now()));
        lbl11.setBounds(350, 12, 120, 25);
        agregarProductos.add(lbl11);

        //TEXTO NUMERO
        JLabel lbl12 = new JLabel("No: " + contadorFacturas);
        lbl12.setBounds(550, 12, 50, 25);
        agregarProductos.add(lbl12);

        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"Codigo", "Nombre", "Cantidad","Precio","Subtotal"};

        Object[][] data = new Object[contadorVentas][5];

            for (int i = 0; i < contadorVentas; i++) {
                data[i][0] = productoAlmacenado[i][0];
                data[i][1] = productoAlmacenado[i][1];
                data[i][2] = productoAlmacenado[i][2];
                data[i][3] = productoAlmacenado[i][3];
                data[i][4] = productoAlmacenado[i][4];
            }

        DefaultTableModel modeloV = new DefaultTableModel(data, titulos);
        JTable tablaV = new JTable(modeloV);
        tablaV.setRowHeight(25);
        tablaV.getTableHeader().setReorderingAllowed(false);
        tablaV.setBounds(30, 120, 630, 100);
        tablaV.setVisible(true);
        agregarProductos.add(tablaV);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaV);
        scroll.setBounds(30, 120, 630, 100);
        scroll.setVisible(true);
        agregarProductos.add(scroll);
    }

    //FUNCION DEL CALCULAR CANTIDAD DE PRODUCTOS
    private boolean cantidadProductos (int codigo, int cantidad ){

        for (int i = 0; i < POS.contador2 ; i++) {

            if (codigo == POS.datosCarga2[i].getCodigo() && cantidad == POS.datosCarga2[i].getCantidad()){

            }
        }
        return true;
    }

    //ACCION DEL BOTON AGREAGAR
    ActionListener agrega = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            tablaVentas(Integer.parseInt(txt5.getText()),(Integer.parseInt(txt6.getText())));
        }
    };

    //TABLA DE VENTAS
    private void tablaVentas(int codigo, int cantidad){

        for (int i = 0; i < POS.contador2; i++) {

            if (codigo == POS.datosCarga2[i].getCodigo()) {

                subtotal = (cantidad * POS.datosCarga2[i].getPrecio());

                productoAlmacenado[contadorVentas][0] = POS.datosCarga2[i].getCodigo();
                productoAlmacenado[contadorVentas][1] = POS.datosCarga2[i].getNombre();
                productoAlmacenado[contadorVentas][2] = POS.datosCarga2[i].getCantidad();
                productoAlmacenado[contadorVentas][3] = POS.datosCarga2[i].getPrecio();
                productoAlmacenado[contadorVentas][4] = Double.toString(subtotal);
            }
        }
        contadorVentas++;
        total += subtotal;
        ModVentas.ventanaVentas.setVisible(false);
        new ModVentas();

    }

    //ACCION DE APLICAR FILTRO A LOS DATOS
    final ActionListener AplicarFiltro = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            ModVentas.ventanaVentas.setVisible(false);

            for (int i = 0; i < POS.contador3; i++) {
                if(txt2.getText().equals("")||txt3.getText().equals("")||txt4.getText().equals("")) {
                    if (txt1.getText().equals(POS.datosCarga3[POS.contador3].getNombre())) {
                        combo1.setSelectedItem(POS.datosCarga3[POS.contador3].getNombre());
                    }
                }
            }
            new ModVentas();
        }
    };

//------------------------------------------------------------------------

    //ACCION DEL BOTON CREAR
    ActionListener cliente = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearCliente();
        }
    };

    //METODO PARA LA CREACION
    private void crearCliente() {

        NuevoCliente = new JFrame();

        Toolkit pantalla2 = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla2.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        NuevoCliente.setLocation(ancho / 3, altura / 4);
        NuevoCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        NuevoCliente.getContentPane().setBackground(aux);
        NuevoCliente.setResizable(false);
        NuevoCliente.setVisible(true);
        NuevoCliente.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Crear Clientes");
        lbl.setBounds(150, 15, 100, 20);
        NuevoCliente.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        NuevoCliente.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        NuevoCliente.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        NuevoCliente.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        NuevoCliente.add(txt2);

        //TEXTO NIT
        JLabel lbl3 = new JLabel("NIT");
        lbl3.setBounds(20, 150, 100, 30);
        NuevoCliente.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        NuevoCliente.add(txt3);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(20, 200, 100, 30);
        NuevoCliente.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        NuevoCliente.add(txt4);

        //TEXTO GENERO
        JLabel lbl5 = new JLabel("Genero");
        lbl5.setBounds(20, 250, 100, 30);
        NuevoCliente.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        NuevoCliente.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Agregar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(agregar);
        NuevoCliente.add(botonAgregar);

        NuevoCliente.pack();
        NuevoCliente.setSize(350, 400);

    }


    //ACCION DE GURADAR LOS DATOS
    ActionListener agregar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);
                } else if (POS.contador3 <= 200) {

                    POS.datosCarga3[POS.contador3].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga3[POS.contador3].setNombre(txt2.getText());
                    POS.datosCarga3[POS.contador3].setNit(Integer.parseInt(txt3.getText()));
                    POS.datosCarga3[POS.contador3].setCorreo(txt4.getText());
                    POS.datosCarga3[POS.contador3].setGenero(txt5.getText());


                    if (Objects.equals(txt5.getText(), "m")){
                        Clientes.contadorHombres++;
                    }

                    if (Objects.equals(txt5.getText(), "f")){
                        Clientes.contadorMujeres++;
                    }

                    POS.contador3++;

                    ModVentas.ventanaVentas.setVisible(false);

                    new ModVentas();

                    NuevoCliente.setVisible(false);

                    POS.serializacion("ContadorHombres.bin", Clientes.contadorHombres);
                    POS.serializacion("ContadorMujeres.bin", Clientes.contadorMujeres);
                    POS.serializacion("ContadorClientes.bin", POS.contador3);
                    POS.serializacion("Clientes.bin", POS.datosCarga3);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "No se puede crear mas de 200 Productos", null, 2);
                NuevoCliente.setVisible(false);
                ModVentas.ventanaVentas.setVisible(true);
            } catch (NullPointerException pointerException) {
                NuevoCliente.setVisible(false);
                ModVentas.ventanaVentas.setVisible(true);
            }
        }
    };

}
