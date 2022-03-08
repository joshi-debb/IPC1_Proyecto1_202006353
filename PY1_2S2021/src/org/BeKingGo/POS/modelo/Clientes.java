package org.BeKingGo.POS.modelo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.BeKingGo.POS.POS;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Clientes extends JPanel implements Serializable {

    //CONTADORES DE SEXO
    public static int contadorHombres = 0;
    public static int contadorMujeres = 0;

    //VENTANAS PARA LA CREACION Y ACTUALIZACION
    public static JFrame crearClientes;
    public static JFrame actualizarClientes;

    //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;

    //TABLA DE DATOS OFICIALES
    public static JTable tablaClientes;
    public static DefaultTableModel modelClientes;

    //METODO PRINCIPAL QUE MUESTRA LA VENTANA
    public Clientes() {

        this.setLayout(null);
        componentes();

    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"Codigo", "Nombre", "NIT", "Correo", "Genero"};
        Object[][] data = new Object[POS.contador3][5];

        for (int i = 0; i < POS.contador3; i++) {

            data[i][0] = POS.datosCarga3[i].getCodigo();
            data[i][1] = POS.datosCarga3[i].getNombre();
            data[i][2] = POS.datosCarga3[i].getNit();
            data[i][3] = POS.datosCarga3[i].getCorreo();
            data[i][4] = POS.datosCarga3[i].getGenero();

        }

        modelClientes = new DefaultTableModel(data, titulos);
        tablaClientes = new JTable(modelClientes);
        tablaClientes.setRowHeight(25);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        tablaClientes.setBounds(20, 50, 400, 505);
        tablaClientes.setVisible(true);
        this.add(tablaClientes);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaClientes);
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

        //GRAFICO DE PIE
        JPanel graficoPie = new JPanel();
        graficoPie.setLayout(null);
        graficoPie.setBounds(440, 290, 275, 265);
        graficoPie.setBackground(Color.white);
        this.add(graficoPie);

        DefaultPieDataset dataClientes = new DefaultPieDataset();
        dataClientes.setValue("Femenimos",contadorMujeres);
        dataClientes.setValue("Masculinos", contadorHombres);

        JFreeChart chartPie = ChartFactory.createPieChart("Genero Clientes",dataClientes,true,true,false);
        chartPie.setBackgroundPaint(Color.YELLOW);
        ChartPanel frame = new ChartPanel(chartPie);
        graficoPie.add(frame,BorderLayout.CENTER);
        frame.setBounds(0,0,275,265);

    }

    //----------------------------------------------------------------


    //------------CREAR-----------------------------------------------

    //ACCION DEL BOTON CREAR
    ActionListener Crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearCliente();
        }
    };

    //METODO PARA LA CREACION
    private void crearCliente () {

        ModAdmin.ventanaPrincipal.setVisible(false);

        crearClientes = new JFrame();

        Toolkit pantalla2 = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla2.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        crearClientes.setLocation(ancho / 3, altura / 4);
        crearClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        crearClientes.getContentPane().setBackground(aux);
        crearClientes.setResizable(false);
        crearClientes.setVisible(true);
        crearClientes.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Crear Clientes");
        lbl.setBounds(150, 15, 100, 20);
        crearClientes.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        crearClientes.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        crearClientes.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        crearClientes.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        crearClientes.add(txt2);

        //TEXTO NIT
        JLabel lbl3 = new JLabel("NIT");
        lbl3.setBounds(20, 150, 100, 30);
        crearClientes.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        crearClientes.add(txt3);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(20, 200, 100, 30);
        crearClientes.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        crearClientes.add(txt4);

        //TEXTO GENERO
        JLabel lbl5 = new JLabel("Genero");
        lbl5.setBounds(20, 250, 100, 30);
        crearClientes.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        crearClientes.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Agregar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(agregar);
        crearClientes.add(botonAgregar);

        crearClientes.pack();
        crearClientes.setSize(350, 400);

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

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    crearClientes.setVisible(false);

                    POS.serializacion("ContadorHombres.bin", contadorHombres);
                    POS.serializacion("ContadorMujeres.bin", contadorMujeres);
                    POS.serializacion("ContadorClientes.bin", POS.contador3);
                    POS.serializacion("Clientes.bin", POS.datosCarga3);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "No se puede crear mas de 200 Productos", null, 2);
                crearClientes.setVisible(false);
                ModAdmin.ventanaPrincipal.setVisible(true);
            } catch (NullPointerException pointerException) {
                crearClientes.setVisible(false);
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

            int seleccionar = tablaClientes.getSelectedRow();

            if (seleccionar < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para actualizar.", null, 2);
            } else {
                actualizarProducto();

            }
        }
    };

    //METODO PARA LA ACTUALIZACION
    private void actualizarProducto() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        actualizarClientes = new JFrame();

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        actualizarClientes.setLocation(ancho / 3, altura / 4);
        actualizarClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        actualizarClientes.getContentPane().setBackground(aux);
        actualizarClientes.setResizable(false);
        actualizarClientes.setVisible(true);
        actualizarClientes.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Actualizar Datos Productos");
        lbl.setBounds(100, 15, 200, 20);
        actualizarClientes.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        actualizarClientes.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        actualizarClientes.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        actualizarClientes.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        actualizarClientes.add(txt2);

        //TEXTO NIT
        JLabel lbl3 = new JLabel("NIT");
        lbl3.setBounds(20, 150, 100, 30);
        actualizarClientes.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        actualizarClientes.add(txt3);

        //TEXTO CORREO
        JLabel lbl4 = new JLabel("Correo");
        lbl4.setBounds(20, 200, 100, 30);
        actualizarClientes.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        actualizarClientes.add(txt4);

        //TEXTO GENERO
        JLabel lbl5 = new JLabel("Genero");
        lbl5.setBounds(20, 250, 100, 30);
        actualizarClientes.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        actualizarClientes.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Actualizar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(actualizar);
        actualizarClientes.add(botonAgregar);

        actualizarClientes.pack();
        actualizarClientes.setSize(350, 400);

    }

    //ACCION DE ACTUALIZAR DATOS
    ActionListener actualizar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);

                } else if (POS.contador3 <= 50) {

                    int seleccionar = tablaClientes.getSelectedRow();

                    POS.datosCarga3[seleccionar].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga3[seleccionar].setNombre(txt2.getText());
                    POS.datosCarga3[seleccionar].setNit(Integer.parseInt(txt3.getText()));
                    POS.datosCarga3[seleccionar].setCorreo(txt4.getText());
                    POS.datosCarga3[seleccionar].setGenero(txt5.getText());

                    if (Objects.equals(txt5.getText(), "m")){
                        Clientes.contadorHombres++;
                    }

                    if (Objects.equals(txt5.getText(), "f")){
                        Clientes.contadorMujeres++;
                    }

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    actualizarClientes.setVisible(false);

                    POS.serializacion("ContadorHombres.bin", contadorHombres);
                    POS.serializacion("ContadorMujeres.bin", contadorMujeres);
                    POS.serializacion("ContadorClientes.bin", POS.contador3);
                    POS.serializacion("Clientes.bin", POS.datosCarga3);
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
            int nit = objetos.get("nit").getAsInt();
            String correo = objetos.get("correo").getAsString();
            String genero = objetos.get("genero").getAsString();
            Cliente nuevoCliente = new Cliente(codigo, nombre, nit, correo, genero);

            POS.CargaMasivaClientes(nuevoCliente);

        }
        ModAdmin.ventanaPrincipal.setVisible(false);
        new ModAdmin();

        POS.serializacion("ContadorHombres.bin", contadorHombres);
        POS.serializacion("ContadorMujeres.bin", contadorMujeres);
        POS.serializacion("ContadorClientes.bin", POS.contador3);
        POS.serializacion("Clientes.bin", POS.datosCarga3);

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

            FileOutputStream archivoPDF = new FileOutputStream("Listado Oficial Clientes" + ".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, archivoPDF);
            document.open();

            Paragraph Empresa = new Paragraph("BLUE MALL");
            Empresa.setAlignment(1);
            document.add(Empresa);

            document.add(Chunk.NEWLINE);

            Paragraph titulo = new Paragraph("LISTADO OFICIAL OFICIAL DE CLIENTES");
            titulo.setAlignment(1);
            document.add(titulo);

            document.add(Chunk.NEWLINE);

            PdfPTable tablaPDF = new PdfPTable(5);
            tablaPDF.setWidthPercentage(80);
            PdfPCell codigo = new PdfPCell(new Phrase("Codigo"));
            codigo.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
            nombre.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell nit = new PdfPCell(new Phrase("NIT"));
            nit.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell correo = new PdfPCell(new Phrase("Correo"));
            correo.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell genero = new PdfPCell(new Phrase("Genero"));
            genero.setBackgroundColor(BaseColor.CYAN.brighter());

            tablaPDF.addCell(codigo);
            tablaPDF.addCell(nombre);
            tablaPDF.addCell(nit);
            tablaPDF.addCell(correo);
            tablaPDF.addCell(genero);

            for (int i = 0; i < (POS.contador3); i++) {

                tablaPDF.addCell(new Paragraph(POS.datosCarga3[i].getCodigo() + ""));
                tablaPDF.addCell(new Paragraph(String.valueOf(POS.datosCarga3[i].getNombre())));
                tablaPDF.addCell(new Paragraph(POS.datosCarga3[i].getNit() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga3[i].getCorreo()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga3[i].getGenero()));
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
                int eliminar = tablaClientes.getSelectedRow();
                modelClientes.removeRow(eliminar);
                POS.contador3--;

                POS.serializacion("ContadorHombres.bin", contadorHombres);
                POS.serializacion("ContadorMujeres.bin", contadorMujeres);
                POS.serializacion("ContadorClientes.bin", POS.contador3);
                POS.serializacion("Clientes.bin", POS.datosCarga3);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", null, 2);
            }
        }
    };
}