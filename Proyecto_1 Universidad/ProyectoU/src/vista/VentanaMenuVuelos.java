/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaMenuVuelos extends JFrame implements Observer {



    public VentanaMenuVuelos(ControlAplicacion nuevogestor, VentanaLogeo v, VentanaCrearAerolineas vc) {
        super("");
        setSize(1515, 851);
        setResizable(true);
        setLocationRelativeTo(null);

        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        ajustarComponentes();

        gestor = nuevogestor;
        ventana = v;
        ventanaCrearAerolineas = vc;


        ventanaImprimiraero = new VentanaImprimirAerolinea(nuevogestor, v,true);
        ventanaModifica = new VentanaModificarAerolinea(nuevogestor, v);
    }

    public VentanaMenuVuelos(ControlAplicacion nuevogestor, VentanaLogeo v, VentanaImprimirAerolinea vc) {
        super("Ventana Menu Vuelos");
        setSize(1515, 851);
        setResizable(true);
        setLocationRelativeTo(null);


       setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        ajustarComponentes();

        gestor = nuevogestor;
        ventana = v;
        ventanaImprimiraero = vc;

        ventanaCrearAerolineas = new VentanaCrearAerolineas(nuevogestor, v,true);
        ventanaModifica = new VentanaModificarAerolinea(nuevogestor, v);

    }

    public VentanaMenuVuelos(ControlAplicacion nuevogestor, VentanaLogeo v, VentanaModificarAerolinea vm) {
        super("Ventana Menu Vuelos");
        setSize(1515, 851);
        setResizable(true);
        setLocationRelativeTo(null);


       setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        ajustarComponentes();

        gestor = nuevogestor;
        ventana = v;
        ventanaModifica = vm;

        ventanaCrearAerolineas = new VentanaCrearAerolineas(nuevogestor, v,true);
        ventanaImprimiraero = new VentanaImprimirAerolinea(nuevogestor, v,true);
    }

    public void setAdmin(VentanaMenuAdministrador ventana){
        ventanamenu = ventana;
    }
    
    public void ajustarComponentes() {
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        JLabel titulo = new JLabel("Administracion de Aerolineas");
        titulo.setFont(new Font("times new roman", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(titulo);       
        panel1.add(Box.createVerticalStrut(130));
        
        

        boton1 = new JButton("CREAR AEROLINEA");
        boton1.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(boton1);
        panel1.add(Box.createVerticalStrut(90));

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventanaCrearAerolineas.setVisible(true);

            }
        });

        boton2 = new JButton("VER AEROLINEAS");
        boton2.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(boton2);
        panel1.add(Box.createVerticalStrut(90));


        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventanaImprimiraero.setVisible(true);

            }
        });

        boton3 = new JButton("MODIFICAR AEROLINEAS");
        boton3.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(boton3);
        panel1.add(Box.createVerticalStrut(90));
        
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventanaModifica.setVisible(true);

            }
        });
        

        boton4 = new JButton("ATRAS");
        boton4.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(boton4);
        panel1.add(Box.createVerticalStrut(90));

        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                mostrarventanaAdm();
            }
        });
        
        add(panel1);
        
        ImageIcon imagen = new ImageIcon("MenuVuelos.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setSize(1217, 810);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        
        
        
        add(etiqueta2);

    }

    public void init() {
        setVisible(true);
    }

    public void mostrarventanaAdm() {      

        ventanamenu.setVisible(true);
    }

    private void addComponent(Component component, int row, int col, int width, int height) {
        constraints.gridx = col;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        add(component);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private ControlAplicacion gestor;
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private VentanaLogeo ventana;
    private VentanaCrearAerolineas ventanaCrearAerolineas;
    private VentanaImprimirAerolinea ventanaImprimiraero;
    private VentanaModificarAerolinea ventanaModifica;
    private VentanaMenuAdministrador ventanamenu;
    

}
