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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaMenuAdministrador extends JFrame implements Observer{

    


    
    public VentanaMenuAdministrador(VentanaLogeo v,VentanaMenuVuelos vm,ControlAplicacion nuevogestor){
        super("");
        setSize(1600, 851);
        setResizable(true);
        setLocationRelativeTo(null);

        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        ventanalogeo = v;
        ventanaVuelos = vm;
        ventanaConfig = new VentanaConfiguracion(v, nuevogestor,true);
        ventanaMantenimiento = new VentanaMantenimientodeVuelos(nuevogestor, v);
        
        x = false;
        ajustarComponentes();
        
        gestor = nuevogestor;
    }
    
      public VentanaMenuAdministrador(VentanaLogeo v,VentanaMantenimientodeVuelos vman,ControlAplicacion nuevogestor){
         super("Ventana Menu Aministrador");
        setSize(1600, 851);
        setResizable(true);
        setLocationRelativeTo(null);

        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        
        ajustarComponentes();
        
        gestor = nuevogestor;
        ventanalogeo = v;
        ventanaMantenimiento = vman;
        
        
        ventanaConfig = new VentanaConfiguracion(v, nuevogestor,true);
        ventanaCrear = new VentanaCrearAerolineas(nuevogestor,v,true);
        ventanaVuelos = new VentanaMenuVuelos(nuevogestor, v,ventanaCrear);
        ventanaVuelos.setAdmin(this);
      }
    
    public void ajustarComponentes(){
        
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(new BoxLayout(panelbotones, BoxLayout.PAGE_AXIS));
        
        JLabel titulo = new JLabel("MENU USUARIO ADMINISTRADOR");       
        titulo.setFont(new Font("times new roman",Font.PLAIN,20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(titulo);
        
        panelbotones.add(Box.createVerticalStrut(130));

        
        boton1 = new JButton("REGISTRAR USUARIOS"); 
        boton1.setFont(new Font("times new roman",Font.PLAIN,20));
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(boton1);
        panelbotones.add(Box.createVerticalStrut(90));
                
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventanaConfig.setVisible(true);
                x = true;
                retornarbool();
            }
        });
        
        
        boton2 = new JButton("MANTENIMIENTO DE AEROLINEAS");
        boton2.setFont(new Font("times new roman",Font.PLAIN,20));
        boton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(boton2);
        
         boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
               ventanaVuelos.setVisible(true);
            }
        });
        panelbotones.add(Box.createVerticalStrut(90));
         
        boton3 = new JButton("MANTENIMIENTO DE VUELOS");
        boton3.setFont(new Font("times new roman",Font.PLAIN,20));
        boton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(boton3);
        panelbotones.add(Box.createVerticalStrut(160));
        
          boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
               ventanaMantenimiento.setVisible(true);
            }
        });
        
        boton4 = new JButton("ATRAS");
        boton4.setFont(new Font("times new roman",Font.PLAIN,20));
        boton4.setAlignmentX(Component.CENTER_ALIGNMENT);
         panelbotones.add(Box.createVerticalStrut(90));
        panelbotones.add(boton4);
       
        
         boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
               ventanalogeo.resetear();
               ventanalogeo.setVisible(true);        
            }
        });
         
         add(panelbotones);
         
        ImageIcon imagen = new ImageIcon("admin.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setSize(1217, 810);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        
        
        
        add(etiqueta2);
    }
    
 
      public void init(){
        setVisible(true);
    }
    
      public boolean retornarbool(){
          
          return x;
      }
    
       private void addComponent(Component component, int row, int col, int width, int height){
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
    private ControlAplicacion gestor;
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private JButton boton4;
    
    private boolean x;
    
    private VentanaLogeo ventanalogeo;
    private VentanaConfiguracion ventanaConfig;
    private VentanaMenuVuelos ventanaVuelos;
    public VentanaMantenimientodeVuelos ventanaMantenimiento;
    public VentanaCrearAerolineas ventanaCrear;
    
    

    
}
