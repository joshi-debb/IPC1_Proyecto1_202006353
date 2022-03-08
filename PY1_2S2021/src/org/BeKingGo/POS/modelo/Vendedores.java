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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vendedores extends JPanel implements Serializable {

    public static Vendedor vendedor1;
    public static Vendedor vendedor2;
    public static Vendedor vendedor3;

    public static JFrame crearVendedores;
    public static JFrame actualizarVendedor;

    public static JPanel graficoBarras;

    //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JTextField txt6;

    //TABLA DE DATOS OFICIALES
    public static JTable tablaVendedores;
    public static DefaultTableModel modelVendedores;

    //METODO PRINCIPAL QUE MUESTRA LA VENTANA
    public Vendedores() {

        this.setLayout(null);
        componentes();

    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"Codigo", "Nombre", "Caja", "Ventas", "Genero","Contraseña"};
        Object[][] data = new Object[POS.contador4][6];

        for (int i = 0; i < POS.contador4; i++) {

            data[i][0] = POS.datosCarga4[i].getCodigo();
            data[i][1] = POS.datosCarga4[i].getNombre();
            data[i][2] = POS.datosCarga4[i].getCaja();
            data[i][3] = POS.datosCarga4[i].getVentas();
            data[i][4] = POS.datosCarga4[i].getGenero();
            data[i][5] = POS.datosCarga4[i].getPassword();

        }

        modelVendedores = new DefaultTableModel(data, titulos);
        tablaVendedores = new JTable(modelVendedores);
        tablaVendedores.setRowHeight(25);
        tablaVendedores.getTableHeader().setReorderingAllowed(false);
        tablaVendedores.setBounds(20, 50, 400, 505);
        tablaVendedores.setVisible(true);
        this.add(tablaVendedores);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaVendedores);
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

        //GRAFICO DE BARRAS
        graficoBarras = new JPanel();
        graficoBarras.setLayout(null);
        graficoBarras.setBounds(440, 290, 275, 265);
        graficoBarras.setBackground(Color.white);
        this.add(graficoBarras);

        ordenarVendedores();

        DefaultCategoryDataset dataVendedores = new DefaultCategoryDataset();
        try {
        if (vendedor1 != null && vendedor2 != null && vendedor3 != null){
            dataVendedores.setValue(vendedor1.getVentas(),vendedor1.getNombre(),"");
            dataVendedores.setValue(vendedor2.getVentas(),vendedor2.getNombre(),"");
            dataVendedores.setValue(vendedor3.getVentas(),vendedor3.getNombre(),"");
        }
        }catch (Exception e){

        }

        JFreeChart chartBar = ChartFactory.createBarChart("Top 3 - Vendedores con mas ventas",
                "Vendedores", "Cantidad de ventas",dataVendedores, PlotOrientation.VERTICAL,true,
                true,false);
        chartBar.setBackgroundPaint(Color.YELLOW);
        ChartPanel frame = new ChartPanel(chartBar);
        graficoBarras.add(frame, BorderLayout.CENTER);
        frame.setBounds(0,0,275,265);
    }

    //----------------------------------------------------------------


    //-------METODO ORDEDANIENTO DE VENDEDORES PARA LA GRAFICA--------
    public static void ordenarVendedores (){

        if (POS.datosCarga4[0] != null){
            vendedor1 = POS.datosCarga4[0];
            vendedor2 = POS.datosCarga4[0];
            vendedor3 = POS.datosCarga4[0];

            for (int i = 0; i < POS.contador4; i++) {
                if (POS.datosCarga4[i].getVentas() > vendedor1.getVentas()){
                    vendedor1 = POS.datosCarga4[i];
                }
            }
            for (int i = 0; i < POS.contador4; i++) {
                if (POS.datosCarga4[i].getVentas() > vendedor2.getVentas() &&
                        POS.datosCarga4[i].getVentas() != vendedor1.getVentas()){
                    vendedor2 = POS.datosCarga4[i];
                }
            }
            for (int i = 0; i < POS.contador4; i++) {
                if (POS.datosCarga4[i].getVentas() > vendedor3.getVentas() &&
                        POS.datosCarga4[i].getVentas() != vendedor1.getVentas() &&
                        POS.datosCarga4[i].getVentas() != vendedor2.getVentas()){
                    vendedor3 = POS.datosCarga4[i];
                }
            }
        }
    }


    //----------------------------------------------------------------



    //------------CREAR-----------------------------------------------

    //ACCION DEL BOTON CREAR
    ActionListener Crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            crearVendedores();
        }
    };

    //METODO PARA LA CREACION
    private void crearVendedores() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        crearVendedores = new JFrame();

        Toolkit pantalla2 = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla2.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        crearVendedores.setLocation(ancho / 3, altura / 4);
        crearVendedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        crearVendedores.getContentPane().setBackground(aux);
        crearVendedores.setResizable(false);
        crearVendedores.setVisible(true);
        crearVendedores.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Crear Vendedores");
        lbl.setBounds(125, 15, 150, 20);
        crearVendedores.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        crearVendedores.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        crearVendedores.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        crearVendedores.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        crearVendedores.add(txt2);

        //TEXTO CAJA
        JLabel lbl3 = new JLabel("Caja");
        lbl3.setBounds(20, 150, 100, 30);
        crearVendedores.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        crearVendedores.add(txt3);

        //TEXTO VENTAS
        JLabel lbl4 = new JLabel("Ventas");
        lbl4.setBounds(20, 200, 100, 30);
        crearVendedores.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        crearVendedores.add(txt4);

        //TEXTO GENERO
        JLabel lbl5 = new JLabel("Genero");
        lbl5.setBounds(20, 250, 100, 30);
        crearVendedores.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        crearVendedores.add(txt5);


        //TEXTO PASSWORD
        JLabel lbl6 = new JLabel("Passwrod");
        lbl6.setBounds(20, 300, 100, 30);
        crearVendedores.add(lbl6);
        txt6 = new JTextField();
        txt6.setBounds(105, 300, 200, 30);
        txt6.setBackground(Color.white);
        crearVendedores.add(txt6);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Agregar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 350, 300, 35));
        botonAgregar.addActionListener(agregar);
        crearVendedores.add(botonAgregar);

        crearVendedores.pack();
        crearVendedores.setSize(350, 450);

    }


    //ACCION DE GURADAR LOS DATOS
    ActionListener agregar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);
                } else if (POS.contador4 <= 50) {

                    POS.datosCarga4[POS.contador4].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga4[POS.contador4].setNombre(txt2.getText());
                    POS.datosCarga4[POS.contador4].setCaja(Integer.parseInt(txt3.getText()));
                    POS.datosCarga4[POS.contador4].setVentas(Integer.parseInt(txt4.getText()));
                    POS.datosCarga4[POS.contador4].setGenero(txt5.getText());
                    POS.datosCarga4[POS.contador4].setPassword(txt6.getText());

                    POS.contador4++;

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    crearVendedores.setVisible(false);

                    POS.serializacion("ContadorVendedores.bin", POS.contador4);
                    POS.serializacion("Vendedores.bin", POS.datosCarga4);

                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "No se puede crear mas de 50 Sucursales", null, 2);
                crearVendedores.setVisible(false);
                ModAdmin.ventanaPrincipal.setVisible(true);
            } catch (NullPointerException pointerException) {
                crearVendedores.setVisible(false);
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

            int seleccionar = tablaVendedores.getSelectedRow();

            if (seleccionar < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para actualizar.", null, 2);
            } else {
                actualizarVendedor();

            }
        }
    };

    //METODO PARA LA ACTUALIZACION
    private void actualizarVendedor() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        actualizarVendedor = new JFrame();

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        actualizarVendedor.setLocation(ancho / 3, altura / 4);
        actualizarVendedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        actualizarVendedor.getContentPane().setBackground(aux);
        actualizarVendedor.setResizable(false);
        actualizarVendedor.setVisible(true);
        actualizarVendedor.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Actualizar Datos Sucursales");
        lbl.setBounds(100, 15, 200, 20);
        actualizarVendedor.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        actualizarVendedor.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        actualizarVendedor.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        actualizarVendedor.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        actualizarVendedor.add(txt2);

        //TEXTO CAJA
        JLabel lbl3 = new JLabel("Caja");
        lbl3.setBounds(20, 150, 100, 30);
        actualizarVendedor.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        actualizarVendedor.add(txt3);

        //TEXTO VENTAS
        JLabel lbl4 = new JLabel("Ventas");
        lbl4.setBounds(20, 200, 100, 30);
        actualizarVendedor.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        actualizarVendedor.add(txt4);

        //TEXTO GENERO
        JLabel lbl5 = new JLabel("Genero");
        lbl5.setBounds(20, 250, 100, 30);
        actualizarVendedor.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        actualizarVendedor.add(txt5);

        //TEXTO PASSWORD
        JLabel lbl6 = new JLabel("Passwrod");
        lbl6.setBounds(20, 300, 100, 30);
        crearVendedores.add(lbl6);
        txt6 = new JTextField();
        txt6.setBounds(105, 300, 200, 30);
        txt6.setBackground(Color.white);
        crearVendedores.add(txt6);


        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Actualizar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 350, 300, 35));
        botonAgregar.addActionListener(actualizar);
        actualizarVendedor.add(botonAgregar);

        actualizarVendedor.pack();
        actualizarVendedor.setSize(350, 450);

    }

    //ACCION DE ACTUALIZAR DATOS
    ActionListener actualizar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);

                } else if (POS.contador4 <= 50) {

                    int seleccionar = tablaVendedores.getSelectedRow();

                    POS.datosCarga4[seleccionar].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga4[seleccionar].setNombre(txt2.getText());
                    POS.datosCarga4[seleccionar].setCaja(Integer.parseInt(txt3.getText()));
                    POS.datosCarga4[seleccionar].setVentas(Integer.parseInt(txt4.getText()));
                    POS.datosCarga4[seleccionar].setGenero(txt5.getText());
                    POS.datosCarga4[seleccionar].setPassword(txt6.getText());

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    actualizarVendedor.setVisible(false);

                    POS.serializacion("ContadorVendedores.bin", POS.contador4);
                    POS.serializacion("Vendedores.bin", POS.datosCarga4);

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
            int caja = objetos.get("caja").getAsInt();
            int ventas = objetos.get("ventas").getAsInt();
            String genero = objetos.get("genero").getAsString();
            String password = objetos.get("password").getAsString();

            Vendedor nuevoVendedor = new Vendedor(codigo, nombre, caja, ventas, genero, password);

            POS.CargaMasivaVendedores(nuevoVendedor);
        }
        ModAdmin.ventanaPrincipal.setVisible(false);
        new ModAdmin();

        POS.serializacion("ContadorVendedores.bin", POS.contador4);
        POS.serializacion("Vendedores.bin", POS.datosCarga4);
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

            FileOutputStream archivoPDF = new FileOutputStream("Listado Oficial Vendedores" + ".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, archivoPDF);
            document.open();

            Paragraph Empresa = new Paragraph("BLUE MALL");
            Empresa.setAlignment(1);
            document.add(Empresa);

            document.add(Chunk.NEWLINE);

            Paragraph titulo = new Paragraph("LISTADO OFICIAL OFICIAL DE VENDEDORES");
            titulo.setAlignment(1);
            document.add(titulo);

            document.add(Chunk.NEWLINE);

            PdfPTable tablaPDF = new PdfPTable(6);
            tablaPDF.setWidthPercentage(80);
            PdfPCell codigo = new PdfPCell(new Phrase("Codigo"));
            codigo.setBackgroundColor(BaseColor.YELLOW.brighter());
            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
            nombre.setBackgroundColor(BaseColor.YELLOW.brighter());
            PdfPCell caja = new PdfPCell(new Phrase("Caja"));
            caja.setBackgroundColor(BaseColor.YELLOW.brighter());
            PdfPCell ventas = new PdfPCell(new Phrase("Ventas"));
            ventas.setBackgroundColor(BaseColor.YELLOW.brighter());
            PdfPCell genero = new PdfPCell(new Phrase("Genero"));
            genero.setBackgroundColor(BaseColor.YELLOW.brighter());
            PdfPCell password = new PdfPCell(new Phrase("Contraseña"));
            password.setBackgroundColor(BaseColor.YELLOW.brighter());

            tablaPDF.addCell(codigo);
            tablaPDF.addCell(nombre);
            tablaPDF.addCell(caja);
            tablaPDF.addCell(ventas);
            tablaPDF.addCell(genero);
            tablaPDF.addCell(password);

            for (int i = 0; i < (POS.contador4); i++) {

                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getCodigo() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getNombre()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getCaja() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getVentas() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getGenero()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga4[i].getPassword()));

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
                int eliminar = tablaVendedores.getSelectedRow();
                modelVendedores.removeRow(eliminar);
                POS.contador4--;

                POS.serializacion("ContadorVendedores.bin", POS.contador4);
                POS.serializacion("Vendedores.bin", POS.datosCarga4);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", null, 2);
            }
        }
    };

}
