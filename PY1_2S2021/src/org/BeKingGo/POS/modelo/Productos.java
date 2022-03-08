package org.BeKingGo.POS.modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import org.BeKingGo.POS.POS;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.Date;

public class Productos extends JPanel implements Serializable {

    public static Producto producto1;
    public static Producto producto2;
    public static Producto producto3;


    public static JFrame crearProductos;
    public static JFrame actualizarProductos;

    //VARIABLES PARA RECIBIR TEXTO EN VENTANAS
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;

    //TABLA DE DATOS OFICIALES
    public static JTable tablaProductos;
    public static DefaultTableModel modelProductos;

    //METODO PRINCIPAL QUE MUESTRA LA VENTANA
    public Productos() {

        this.setLayout(null);
        componentes();

    }

    //COMPONENTES DE LA VENTANA PRINCIPAL
    private void componentes() {

        //TABLA DEL LISTADO OFICIAL
        String[] titulos = {"Codigo", "Nombre", "Descripcion", "Cantidad", "Precio"};
        Object[][] data = new Object[POS.contador2][5];

        for (int i = 0; i < POS.contador2; i++) {

            data[i][0] = POS.datosCarga2[i].getCodigo();
            data[i][1] = POS.datosCarga2[i].getNombre();
            data[i][2] = POS.datosCarga2[i].getDescripcion();
            data[i][3] = POS.datosCarga2[i].getCantidad();
            data[i][4] = POS.datosCarga2[i].getPrecio();

        }

        modelProductos = new DefaultTableModel(data, titulos);
        tablaProductos = new JTable(modelProductos);
        tablaProductos.setRowHeight(25);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.setBounds(20, 50, 400, 505);
        tablaProductos.setVisible(true);
        this.add(tablaProductos);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tablaProductos);
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
        JPanel graficoBarras = new JPanel();
        graficoBarras.setLayout(null);
        graficoBarras.setBounds(440, 290, 275, 265);
        graficoBarras.setBackground(Color.white);
        this.add(graficoBarras);

        ordenarProductos();

        DefaultCategoryDataset dataProductos = new DefaultCategoryDataset();
        try {
            if (producto1 != null && producto2 != null && producto3 != null) {

                dataProductos.setValue(producto1.getCantidad(), producto1.getNombre(), String.valueOf(producto1.getCantidad()));
                dataProductos.setValue(producto2.getCantidad(), producto2.getNombre(), String.valueOf(producto2.getCantidad()));
                dataProductos.setValue(producto3.getCantidad(), producto3.getNombre(), String.valueOf(producto3.getCantidad()));

            }
        }catch (Exception e){

        }

        JFreeChart chartBar = ChartFactory.createBarChart("Top 3 - Productos con mas disponibilidad",
                "Productos", "Cantidad",dataProductos, PlotOrientation.VERTICAL,true,
                true,false);
        chartBar.setBackgroundPaint(Color.YELLOW);
        ChartPanel frame = new ChartPanel(chartBar);
        graficoBarras.add(frame, BorderLayout.CENTER);
        frame.setBounds(0,0,275,265);

    }

    public static void ordenarProductos (){

        if (POS.datosCarga2[0] != null){
            producto1 = POS.datosCarga2[0];
            producto2 = POS.datosCarga2[0];
            producto3 = POS.datosCarga2[0];

            for (int i = 0; i < POS.contador2; i++) {
                if (POS.datosCarga2[i].getCantidad() > producto1.getCantidad()){
                    producto1 = POS.datosCarga2[i];
                }
            }
            for (int i = 0; i < POS.contador2; i++) {
                if (POS.datosCarga2[i].getCantidad() > producto2.getCantidad() &&
                        POS.datosCarga2[i].getCantidad() != producto1.getCantidad()){
                    producto2 = POS.datosCarga2[i];
                }
            }
            for (int i = 0; i < POS.contador2; i++) {
                if (POS.datosCarga2[i].getCantidad() > producto3.getCantidad() &&
                        POS.datosCarga2[i].getCantidad() != producto1.getCantidad() &&
                        POS.datosCarga2[i].getCantidad() != producto2.getCantidad()){
                    producto3 = POS.datosCarga2[i];
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
            crearProducto();
        }
    };

    //METODO PARA LA CREACION
    private void crearProducto() {

        ModAdmin.ventanaPrincipal.setVisible(false);

        crearProductos = new JFrame();

        Toolkit pantalla2 = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla2.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        crearProductos.setLocation(ancho / 3, altura / 4);
        crearProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        crearProductos.getContentPane().setBackground(aux);
        crearProductos.setResizable(false);
        crearProductos.setVisible(true);
        crearProductos.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Crear Producto");
        lbl.setBounds(150, 15, 100, 20);
        crearProductos.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        crearProductos.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        crearProductos.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        crearProductos.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        crearProductos.add(txt2);

        //TEXTO DESCRIPCION
        JLabel lbl3 = new JLabel("Descripcion");
        lbl3.setBounds(20, 150, 100, 30);
        crearProductos.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        crearProductos.add(txt3);

        //TEXTO CANTIDAD
        JLabel lbl4 = new JLabel("Cantidad");
        lbl4.setBounds(20, 200, 100, 30);
        crearProductos.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        crearProductos.add(txt4);

        //TEXTO PRECIO
        JLabel lbl5 = new JLabel("Precio");
        lbl5.setBounds(20, 250, 100, 30);
        crearProductos.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        crearProductos.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Agregar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(agregar);
        crearProductos.add(botonAgregar);

        crearProductos.pack();
        crearProductos.setSize(350, 400);

    }


    //ACCION DE GURADAR LOS DATOS
    ActionListener agregar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);
                } else if (POS.contador2 <= 200) {

                    POS.datosCarga2[POS.contador2].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga2[POS.contador2].setNombre(txt2.getText());
                    POS.datosCarga2[POS.contador2].setDescripcion(txt3.getText());
                    POS.datosCarga2[POS.contador2].setCantidad(Integer.parseInt(txt4.getText()));
                    POS.datosCarga2[POS.contador2].setPrecio(Integer.parseInt(txt5.getText()));

                    POS.contador2++;

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    crearProductos.setVisible(false);


                    POS.serializacion("ContadorProductos.bin", POS.contador2);
                    POS.serializacion("Productos.bin", POS.datosCarga2);


                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Asegurese de que los datos sean validos", null, 2);
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "No se puede crear mas de 200 Productos", null, 2);
                crearProductos.setVisible(false);
                ModAdmin.ventanaPrincipal.setVisible(true);
            } catch (NullPointerException pointerException) {
                crearProductos.setVisible(false);
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

            int seleccionar = tablaProductos.getSelectedRow();

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

        actualizarProductos = new JFrame();

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        actualizarProductos.setLocation(ancho / 3, altura / 4);
        actualizarProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color aux = new Color(156, 154, 154, 255);
        actualizarProductos.getContentPane().setBackground(aux);
        actualizarProductos.setResizable(false);
        actualizarProductos.setVisible(true);
        actualizarProductos.setLayout(null);

        //TEXTO TITULO
        JLabel lbl = new JLabel("Actualizar Datos Productos");
        lbl.setBounds(100, 15, 200, 20);
        actualizarProductos.add(lbl);

        //TEXTO CODIGO
        JLabel lbl1 = new JLabel("Codigo");
        lbl1.setBounds(20, 50, 100, 30);
        actualizarProductos.add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(105, 50, 200, 30);
        txt1.setBackground(Color.white);
        actualizarProductos.add(txt1);

        //TEXTO NOMBRE
        JLabel lbl2 = new JLabel("Nombre");
        lbl2.setBounds(20, 100, 100, 30);
        actualizarProductos.add(lbl2);
        txt2 = new JTextField();
        txt2.setBounds(105, 100, 200, 30);
        txt2.setBackground(Color.white);
        actualizarProductos.add(txt2);

        //TEXTO DESCRIPCION
        JLabel lbl3 = new JLabel("Descripcion");
        lbl3.setBounds(20, 150, 100, 30);
        actualizarProductos.add(lbl3);
        txt3 = new JTextField();
        txt3.setBounds(105, 150, 200, 30);
        txt3.setBackground(Color.white);
        actualizarProductos.add(txt3);

        //TEXTO CANTIDAD
        JLabel lbl4 = new JLabel("Cantidad");
        lbl4.setBounds(20, 200, 100, 30);
        actualizarProductos.add(lbl4);
        txt4 = new JTextField();
        txt4.setBounds(105, 200, 200, 30);
        txt4.setBackground(Color.white);
        actualizarProductos.add(txt4);

        //TEXTO PRECIO
        JLabel lbl5 = new JLabel("Precio");
        lbl5.setBounds(20, 250, 100, 30);
        actualizarProductos.add(lbl5);
        txt5 = new JTextField();
        txt5.setBounds(105, 250, 200, 30);
        txt5.setBackground(Color.white);
        actualizarProductos.add(txt5);

        //BOTON AGREGAR Y GUARDAR
        JButton botonAgregar = new JButton(" Actualizar ");
        botonAgregar.setBackground(Color.GREEN);
        botonAgregar.setBounds(new Rectangle(20, 300, 300, 35));
        botonAgregar.addActionListener(actualizar);
        actualizarProductos.add(botonAgregar);

        actualizarProductos.pack();
        actualizarProductos.setSize(350, 400);

    }

    //ACCION DE ACTUALIZAR DATOS
    ActionListener actualizar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") ||
                        txt4.getText().equals("") || txt5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llene todos los datos requeridos", null, 2);

                } else if (POS.contador2 <= 50) {

                    int seleccionar = tablaProductos.getSelectedRow();

                    POS.datosCarga2[seleccionar].setCodigo(Integer.parseInt(txt1.getText()));
                    POS.datosCarga2[seleccionar].setNombre(txt2.getText());
                    POS.datosCarga2[seleccionar].setDescripcion(txt3.getText());
                    POS.datosCarga2[seleccionar].setCantidad(Integer.parseInt(txt4.getText()));
                    POS.datosCarga2[seleccionar].setPrecio(Integer.parseInt(txt5.getText()));

                    ModAdmin.ventanaPrincipal.setVisible(false);

                    new ModAdmin();

                    actualizarProductos.setVisible(false);


                    POS.serializacion("ContadorProductos.bin", POS.contador2);
                    POS.serializacion("Productos.bin", POS.datosCarga2);
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
            String descripcion = objetos.get("descripcion").getAsString();
            int cantidad = objetos.get("cantidad").getAsInt();
            double precio = objetos.get("precio").getAsDouble();

            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, cantidad, precio);

            POS.CargaMasivaProductos(nuevoProducto);
        }
        ModAdmin.ventanaPrincipal.setVisible(false);
        new ModAdmin();

        POS.serializacion("ContadorProductos.bin", POS.contador2);
        POS.serializacion("Productos.bin", POS.datosCarga2);

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

            FileOutputStream archivoPDF = new FileOutputStream("Listado Oficial Productos" + ".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, archivoPDF);
            document.open();

            Paragraph Empresa = new Paragraph("BLUE MALL");
            Empresa.setAlignment(1);
            document.add(Empresa);

            document.add(Chunk.NEWLINE);

            Paragraph titulo = new Paragraph("LISTADO OFICIAL OFICIAL DE PRODUCTOS");
            titulo.setAlignment(1);
            document.add(titulo);

            document.add(Chunk.NEWLINE);

            PdfPTable tablaPDF = new PdfPTable(5);
            tablaPDF.setWidthPercentage(80);
            PdfPCell codigo = new PdfPCell(new Phrase("Codigo"));
            codigo.setBackgroundColor(BaseColor.GREEN.brighter());
            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
            nombre.setBackgroundColor(BaseColor.GREEN.brighter());
            PdfPCell descripcion = new PdfPCell(new Phrase("Descripcion"));
            descripcion.setBackgroundColor(BaseColor.GREEN.brighter());
            PdfPCell cantidad = new PdfPCell(new Phrase("Cantidad"));
            cantidad.setBackgroundColor(BaseColor.GREEN.brighter());
            PdfPCell precio = new PdfPCell(new Phrase("Precio"));
            precio.setBackgroundColor(BaseColor.GREEN.brighter());

            tablaPDF.addCell(codigo);
            tablaPDF.addCell(nombre);
            tablaPDF.addCell(descripcion);
            tablaPDF.addCell(cantidad);
            tablaPDF.addCell(precio);

            for (int i = 0; i < (POS.contador2); i++) {

                tablaPDF.addCell(new Paragraph(POS.datosCarga2[i].getCodigo() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga2[i].getNombre()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga2[i].getDescripcion()));
                tablaPDF.addCell(new Paragraph(POS.datosCarga2[i].getCantidad() + ""));
                tablaPDF.addCell(new Paragraph(POS.datosCarga2[i].getPrecio() + ""));
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
                int eliminar = tablaProductos.getSelectedRow();
                modelProductos.removeRow(eliminar);
                POS.contador2--;

                POS.serializacion("ContadorProductos.bin", POS.contador2);
                POS.serializacion("Productos.bin", POS.datosCarga2);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", null, 2);
            }
        }
    };

}
