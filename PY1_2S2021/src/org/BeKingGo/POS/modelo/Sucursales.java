package org.BeKingGo.POS.modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.BeKingGo.POS.POS;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sucursales extends JPanel implements Serializable {

    public static JFrame crearSucursales;
    public static JFrame actualizarSucursal;

    //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;

    //TABLA DE DATOS OFICIALES
    public static JTable tablaSucursales;
    public static DefaultTableModel modelSucursales;

    //METODO PRINCIPAL QUE MUESTRA LA VENTANA
    public Sucursales() {
        this.setLayout(null);
        componentes();
    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"Codigo", "Nombre", "Direccion", "Correo", "Telefono"};
        Object[][] data = new Object[POS.contador1][5];

        for (int i = 0; i < POS.contador1; i++) {

            data[i][0] = POS.datosCarga1[i].getCodigo();
            data[i][1] = POS.datosCarga1[i].getNombre();
            data[i][2] = POS.datosCarga1[i].getDireccion();
            data[i][3] = POS.datosCarga1[i].getCorreo();
            data[i][4] = POS.datosCarga1[i].getTelefono();

        }

        modelSucursales = new DefaultTableModel(data, titulos);
        tablaSucursales = new JTable(modelSucursales);
        tablaSucursales.setRowHeight(25);
        tablaSucursales.getTableHeader().setReorderingAllowed(false);
        tablaSucursales.setBounds(20, 50, 400, 505);
        tablaSucursales.setVisible(true);
        this.add(tablaSucursales);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaSucursales);
        scroll.setBounds(20, 50, 400, 505);
        scroll.setVisible(true);
        this.add(scroll);

        //BOTON DE CREAR
        JButton botonCrear = new JButton(" Crear ");
        botonCrear.setBounds(new Rectangle(440, 50, 125, 55));
        botonCrear.setBackground(Color.ORANGE);
        botonCrear.addActionListener(Crear);
        this.add(botonCrear);

        //BOTON DE CARGA MASIVA
        JButton botonCargaMasiva = new JButton(" Carga Masiva ");
        botonCargaMasiva.setBounds(new Rectangle(590, 50, 125, 55));
        botonCargaMasiva.setBackground(Color.ORANGE);
        botonCargaMasiva.addActionListener(CargaMasiva);
        this.add(botonCargaMasiva);

        //BOTON DE ACTUALIZAR
        JButton botonActualizar = new JButton(" Actualizar ");
        botonActualizar.setBounds(new Rectangle(440, 130, 125, 55));
        botonActualizar.setBackground(Color.ORANGE);
        botonActualizar.addActionListener(Actualizar);
        this.add(botonActualizar);

        //BOTON DE ELIMINAR
        JButton botonEliminar = new JButton(" Eliminar ");
        botonEliminar.setBounds(new Rectangle(590, 130, 125, 55));
        botonEliminar.setBackground(Color.ORANGE);
        botonEliminar.addActionListener(eliminar);
        this.add(botonEliminar);

        //BOTON DE EXPORTAR LISTADO A PDF
        JButton botonExportarPdf = new JButton(" Exportar Listado a PDF ");
        botonExportarPdf.setBounds(new Rectangle(440, 210, 275, 55));
        botonExportarPdf.setBackground(Color.ORANGE);
        botonExportarPdf.addActionListener(exportarPDF);
        this.add(botonExportarPdf);

    }

    //----------------------------------------------------------------


    //------------CREAR-----------------------------------------------

    //ACCION DEL BOTON CREAR
    ActionListener Crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearSucursal();
        }
    };

    //METODO PARA LA CREACION
    private void crearSucursal() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        crearSucursales = new JFrame();

        Toolkit pantalla2 = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla2.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        crearSucursales.setLocation(ancho / 3, altura / 4);
        crearSucursales.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        crearSucursales.setResizable(false);
        Color aux = new Color(156, 154, 154, 255);
        crearSucursales.getContentPane().setBackground(aux);
        crearSucursales.setVisible(true);
        crearSucursales.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Crear Sucursal");
        lbl.setBounds(150, 15, 100, 20);
        crearSucursales.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        crearSucursales.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        crearSucursales.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        crearSucursales.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        crearSucursales.add(txt2);

        //TEXTO DIRECCION
        JLabel lbl3 = new JLabel("Direccion");
        lbl3.setBounds(20, 150, 100, 30);
        crearSucursales.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        crearSucursales.add(txt3);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(20, 200, 100, 30);
        crearSucursales.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        crearSucursales.add(txt4);

        //TEXTO TELEFONO
        JLabel lbl5 = new JLabel("Telefono");
        lbl5.setBounds(20, 250, 100, 30);
        crearSucursales.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        crearSucursales.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Agregar ");
        botonAgregar.setBackground(Color.green);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(agregar);
        crearSucursales.add(botonAgregar);

        crearSucursales.pack();
        crearSucursales.setSize(350, 400);

    }


    //ACCION DE GURADAR LOS DATOS
    ActionListener agregar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);
                } else if (POS.contador1 <= 50) {

                    POS.datosCarga1[POS.contador1].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga1[POS.contador1].setNombre(txt2.getText());
                    POS.datosCarga1[POS.contador1].setDireccion(txt3.getText());
                    POS.datosCarga1[POS.contador1].setCorreo(txt4.getText());
                    POS.datosCarga1[POS.contador1].setTelefono(Integer.parseInt(txt5.getText()));

                    POS.contador1++;

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    crearSucursales.setVisible(false);

                    POS.serializacion("ContadorSucursales.bin", POS.contador1);
                    POS.serializacion("Sucursales.bin", POS.datosCarga1);

                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "No se puede crear mas de 50 Sucursales", null, 2);
                crearSucursales.setVisible(false);
                ModAdmin.ventanaPrincipal.setVisible(true);
            } catch (NullPointerException pointerException) {
                crearSucursales.setVisible(false);
                ModAdmin.ventanaPrincipal.setVisible(true);
            }
        }
    };

    //----------------------------------------------------------------


    //------------ACTUALIZAR-----------------------------------------------

    //ACCION DEL BOTON ACTUALIZAR
    ActionListener Actualizar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            int seleccionar = tablaSucursales.getSelectedRow();

            if (seleccionar < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para actualizar.", null, 2);
            } else {
                actualizarSucursal();

            }
        }
    };

    //METODO PARA LA ACTUALIZACION
    private void actualizarSucursal() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        actualizarSucursal = new JFrame();

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        actualizarSucursal.setLocation(ancho / 3, altura / 4);
        actualizarSucursal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        actualizarSucursal.getContentPane().setBackground(aux);
        actualizarSucursal.setResizable(false);
        actualizarSucursal.setVisible(true);
        actualizarSucursal.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Actualizar Datos Sucursales");
        lbl.setBounds(100, 15, 200, 20);
        actualizarSucursal.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        actualizarSucursal.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        actualizarSucursal.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        actualizarSucursal.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        actualizarSucursal.add(txt2);

        //TEXTO DIRECCION
        JLabel lbl3 = new JLabel("Direccion");
        lbl3.setBounds(20, 150, 100, 30);
        actualizarSucursal.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        actualizarSucursal.add(txt3);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(20, 200, 100, 30);
        actualizarSucursal.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        actualizarSucursal.add(txt4);

        //TEXTO TELEFONO
        JLabel lbl5 = new JLabel("Telefono");
        lbl5.setBounds(20, 250, 100, 30);
        actualizarSucursal.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        actualizarSucursal.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Actualizar ");
        botonAgregar.setBackground(Color.green);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(actualizar);
        actualizarSucursal.add(botonAgregar);

        actualizarSucursal.pack();
        actualizarSucursal.setSize(350, 400);

    }

    //ACCION DE ACTUALIZAR DATOS
    ActionListener actualizar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);

                } else if (POS.contador1 <= 50) {

                    int seleccionar = tablaSucursales.getSelectedRow();

                    POS.datosCarga1[seleccionar].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga1[seleccionar].setNombre(txt2.getText());
                    POS.datosCarga1[seleccionar].setDireccion(txt3.getText());
                    POS.datosCarga1[seleccionar].setCorreo(txt4.getText());
                    POS.datosCarga1[seleccionar].setTelefono(Integer.parseInt(txt5.getText()));

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    actualizarSucursal.setVisible(false);

                    POS.serializacion("ContadorSucursales.bin", POS.contador1);
                    POS.serializacion("Sucursales.bin", POS.datosCarga1);

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            }
        }
    };

    //----------------------------------------------------------------


    //------------CARGA MASIVA----------------------------------------


    //ACCION CARGA MASIVA
    ActionListener CargaMasiva = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            try {

                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                file.showOpenDialog(null);
                File archivo = file.getSelectedFile();
                String ruta = (archivo.getAbsolutePath());

                upLoad(ruta);

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "No se ha podido cargar el archivo", null, 2);
            }
        }
    };

    //LEER JSON
    public static void upLoad(String ruta) {

        String contenido = getCountentOfFile(ruta);
        JsonParser parser = new JsonParser();
        JsonArray arreglo = parser.parse(contenido).getAsJsonArray();

        for (int i = 0; i < arreglo.size(); i++) {

            JsonObject objetos = arreglo.get(i).getAsJsonObject();

            int codigo = objetos.get("codigo").getAsInt();
            String nombre = objetos.get("nombre").getAsString();
            String direccion = objetos.get("direccion").getAsString();
            String correo = objetos.get("correo").getAsString();
            int telefono = objetos.get("telefono").getAsInt();

            Sucursal nuevaSucursal = new Sucursal(codigo, nombre, direccion, correo, telefono);

            POS.CargaMasivaSucursales(nuevaSucursal);
        }
        ModAdmin.ventanaPrincipal.setVisible(false);
        new ModAdmin();

        POS.serializacion("ContadorSucursales.bin", POS.contador1);
        POS.serializacion("Sucursales.bin", POS.datosCarga1);

    }


    public static String getCountentOfFile(String ruta) {

        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String contenido = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
            return contenido;
        } catch (FileNotFoundException exception) {
            System.out.println("No se encontro el archivo: Intente de nuevo");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    //----------------------------------------------------------------


    //------------LISTADO PDF-----------------------------------------

    //ACCION DEL BOTON CREAR LISTADO PDF
    ActionListener exportarPDF = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                exportarListadoPDF();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Verifique que sus datos sean validos", null, 2);
            }
        }
    };

    //METODO DE LA CREACION DE LISTADO OFICIAL EN PDF
    private void exportarListadoPDF() {

        try {

            FileOutputStream archivoPDF = new FileOutputStream("Listado Oficial Sucursales" + ".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, archivoPDF);
            document.open();


            Paragraph Empresa = new Paragraph("BLUE MALL");
            Empresa.setAlignment(1);
            document.add(Empresa);

            document.add(Chunk.NEWLINE);

            Paragraph titulo = new Paragraph("LISTADO OFICIAL OFICIAL DE SUCURSALES");
            titulo.setAlignment(1);
            document.add(titulo);

            document.add(Chunk.NEWLINE);

            PdfPTable tablaPDF = new PdfPTable(5);
            tablaPDF.setWidthPercentage(80);
            PdfPCell codigo = new PdfPCell(new Phrase("Codigo"));
            codigo.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
            nombre.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell direccion = new PdfPCell(new Phrase("Direccion"));
            direccion.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell correo = new PdfPCell(new Phrase("Correo"));
            correo.setBackgroundColor(BaseColor.ORANGE);
            PdfPCell telefono = new PdfPCell(new Phrase("Telefono"));
            telefono.setBackgroundColor(BaseColor.ORANGE);

            tablaPDF.addCell(codigo);
            tablaPDF.addCell(nombre);
            tablaPDF.addCell(direccion);
            tablaPDF.addCell(correo);
            tablaPDF.addCell(telefono);

            for (int i = 0; i < (POS.contador1); i++) {

                tablaPDF.addCell(new Paragraph(POS.datosCarga1[i].getCodigo() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga1[i].getNombre()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga1[i].getDireccion()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga1[i].getCorreo()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga1[i].getTelefono() + ""));
            }

            document.add(tablaPDF);

            document.add(Chunk.NEWLINE);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Paragraph fecha = new Paragraph("Listado actualizado al dia de hoy: " + dtf.format(LocalDateTime.now()));
            fecha.setAlignment(0);
            document.add(fecha);

            document.close();

            JOptionPane.showMessageDialog(null, "Listado Oficial en formato PDF ha sido creado");

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        } catch (ArrayStoreException e) {
            JOptionPane.showMessageDialog(null, "Listado Oficial en formato PDF ha sido creado: vacio", null, 2);
        }
    }

    //----------------------------------------------------------------


    //----------------------------------------------------------------

    //ACCION DEL BOTON ELIMINAR
    ActionListener eliminar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int eliminar = tablaSucursales.getSelectedRow();
                modelSucursales.removeRow(eliminar);
                POS.contador1--;

                POS.serializacion("ContadorSucursales.bin", POS.contador1);
                POS.serializacion("Sucursales.bin", POS.datosCarga1);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", null, 2);
            }
        }
    };

}






