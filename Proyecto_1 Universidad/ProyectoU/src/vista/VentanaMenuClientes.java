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
public class VentanaMenuClientes extends JFrame implements Observer{

    
    public VentanaMenuClientes(ControlAplicacion nuevogestor,VentanaLogeo ven){
        super("");
        setSize(1600, 851);
        setResizable(true);
        setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        
        
        
        gestor = nuevogestor;
        ventana = ven;
        agregarcliente = new VentanaAgregarCliente(ven, nuevogestor);
        agregarcliente.setMenuClientes(this);
        adminCliente = new VentanaAdministrarCliente(ven, nuevogestor);
        ajustarComponentes();
        
    }
    
    public void ajustarComponentes(){
        
        
    
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(new BoxLayout(panelbotones, BoxLayout.PAGE_AXIS));
        
        JLabel titulo = new JLabel("MENU ADMINISTRACION DE CLIENTES");       
        titulo.setFont(new Font("times new roman",Font.PLAIN,20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(titulo);
        
        panelbotones.add(Box.createVerticalStrut(130));

        
        boton1 = new JButton("REGISTRAR CLIENTES"); 
        boton1.setFont(new Font("times new roman",Font.PLAIN,20));
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(boton1);
        panelbotones.add(Box.createVerticalStrut(90));
                
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
               agregarcliente.setVisible(true);
            }
        });
        
        
        boton2 = new JButton("ADMINISTRACION DE CLIENTES");
        boton2.setFont(new Font("times new roman",Font.PLAIN,20));
        boton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelbotones.add(boton2);
        
         boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             adminCliente.setVisible(true);
            }
        });
        panelbotones.add(Box.createVerticalStrut(90));
      
        
        JButton boton4 = new JButton("ATRAS");
        boton4.setFont(new Font("times new roman",Font.PLAIN,20));
        boton4.setAlignmentX(Component.CENTER_ALIGNMENT);
         panelbotones.add(Box.createVerticalStrut(90));
        panelbotones.add(boton4);
       
        
         boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
               ventana.resetear();
               ventana.setVisible(true);        
            }
        });
         
         add(panelbotones);
         
        ImageIcon imagen = new ImageIcon("clientes.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setSize(1217, 810);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        
        
        
        add(etiqueta2);
    }

    private JButton boton1;
    private JButton boton2;
    private VentanaLogeo ventana;
    private ControlAplicacion gestor;
    private VentanaAgregarCliente agregarcliente;
    private VentanaAdministrarCliente adminCliente;
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
