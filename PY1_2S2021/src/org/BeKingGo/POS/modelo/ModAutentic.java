package org.BeKingGo.POS.modelo;

import org.BeKingGo.POS.POS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.Serializable;

public class ModAutentic extends JFrame implements Serializable {

    File validar = new File("ContadorSucursales.bin");
    File validar1 = new File("Sucursales.bin");
    File validar2 = new File("ContadorProductos.bin");
    File validar3 = new File("Productos.bin");
    File validar4 = new File("ContadorClientes.bin");
    File validar5 = new File("Clientes.bin");
    File validar6 = new File("ContadorVendedores.bin");
    File validar7 = new File("Vendedores.bin");
    File validar8 = new File("ContadorHombres.bin");
    File validar9 = new File("ContadorMujeres.bin");

    JTextField txt1 = new JTextField();
    JPasswordField txt2 = new JPasswordField();

    public static String name;
    public static String pass;

    Vendedor user;

    //VENTANA DE INICIO DE SESION
    public ModAutentic(Vendedor user) {


        //RECUADRO POS
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamano = pantalla.getScreenSize();
        int altura = tamano.height;
        int ancho = tamano.width;
        this.setLocation(ancho / 3, altura / 3);
        Color aux = new Color(114, 148, 206, 223);
        this.getContentPane().setBackground(aux);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        setLayout(null);

        //TEXTO TITULO APP "POS"
        JLabel lbl = new JLabel("POS");
        lbl.setBounds(195, 10, 100, 20);
        this.add(lbl);

        //TEXTO USER
        JLabel lbl1 = new JLabel("Codigo :");
        lbl1.setBounds(30, 40, 100, 20);
        this.add(lbl1);
        txt1.setBounds(130, 40, 200, 20);
        txt1.setBackground(Color.white);
        this.add(txt1);

        //TEXTO PASSWORD
        JLabel lbl2 = new JLabel("Contraseña :");
        lbl2.setBounds(30, 70, 100, 20);
        this.add(lbl2);
        txt2.setBounds(130, 70, 200, 20);
        txt2.setBackground(Color.white);
        this.add(txt2);

        //BOTON DE LOGIN
        JButton botonLogin = new JButton(" Iniciar Sesion ");
        botonLogin.setBackground(Color.green);
        botonLogin.setBounds(new Rectangle(130, 120, 200, 20));
        botonLogin.addActionListener(iniciarSesion);
        this.add(botonLogin);

        this.pack();
        this.setSize(400, 200);
    }


    //ACCION DE INICIAR SESION
    ActionListener iniciarSesion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = txt1.getText();
            pass = txt2.getText();

            if (name.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Para iniciar sesion llene los datos solicitados.", null, 2);
            } else {
                if (name.equals("admin") && pass.equals("admin")) {

                    if (validar.exists() && validar1.exists() && validar2.exists() && validar3.exists() && validar4.exists() && validar5.exists()
                            && validar6.exists() && validar7.exists() && validar8.exists() && validar9.exists()) {
                        POS.contador1 = (int) POS.desSerializacion("ContadorSucursales.bin");
                        POS.datosCarga1 = (Sucursal[]) POS.desSerializacion("Sucursales.bin");
                        POS.contador2 = (int) POS.desSerializacion("ContadorProductos.bin");
                        POS.datosCarga2 = (Producto[]) POS.desSerializacion("Productos.bin");
                        Clientes.contadorHombres = (int) POS.desSerializacion("ContadorHombres.bin");
                        Clientes.contadorMujeres = (int) POS.desSerializacion("ContadorMujeres.bin");
                        POS.contador3 = (int) POS.desSerializacion("ContadorClientes.bin");
                        POS.datosCarga3 = (Cliente[]) POS.desSerializacion("Clientes.bin");
                        POS.contador4 = (int) POS.desSerializacion("ContadorVendedores.bin");
                        POS.datosCarga4 = (Vendedor[]) POS.desSerializacion("Vendedores.bin");

                        JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                        new ModAdmin();
                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                        new ModAdmin();
                        dispose();
                    }

                } else if (Vendedor.loginVendedores(name, pass)) {

                        JOptionPane.showMessageDialog(null, "Bienvenido " + name);
                        new ModVentas();
                        dispose();


                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos.", null, 2);
                }
            }
        }
    };

}

