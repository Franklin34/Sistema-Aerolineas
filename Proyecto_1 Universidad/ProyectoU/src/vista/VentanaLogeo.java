/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaLogeo extends JFrame implements Observer{
    
      
    public VentanaLogeo(ControlAplicacion nuevogestor){
        super("");
        setSize(1519, 945);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        
        ajustarComponentes();
        
        gestor = nuevogestor;
    }
    
    private void ajustarComponentes(){  
        
        
        
        JPanel panel1 = new JPanel();
        
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        JLabel titulo = new JLabel("Sistema Aeropuertario");
        titulo.setFont(new Font("times new roman",Font.PLAIN,20));

        panel1.add(titulo);
        panel1.add(Box.createVerticalStrut(280));
        
        JPanel panel2_1 = new JPanel();
        panel2_1.setLayout(new FlowLayout());

        JLabel etiqueta_usuario = new JLabel("Usuario:");

        etiqueta_usuario.setFont(new Font("times new roman",Font.PLAIN,20));
        panel2_1.add(etiqueta_usuario);
        
        usuario = new JTextField(10);
        panel2_1.add(usuario);
        
        panel1.add(panel2_1);
         panel1.add(Box.createVerticalStrut(70));
        
        JPanel panel2_2 = new JPanel();
        panel2_2.setLayout(new FlowLayout());
  
        JLabel etiqueta_contrasena = new JLabel("Contraseña: ");
        etiqueta_contrasena.setFont(new Font("times new roman",Font.PLAIN,20));
        
        panel2_2.add(etiqueta_contrasena);

        contrasena = new JPasswordField(10);

        panel2_2.add(contrasena);
        
        
        
        panel1.add(panel2_2);
         panel1.add(Box.createVerticalStrut(300));
        
        botonlogin = new JButton("INGRESAR");
        botonlogin.setEnabled(false);
        botonlogin.setFont(new Font("times new roman",Font.PLAIN,20));

        
        panel1.add(botonlogin);
         panel1.add(Box.createVerticalStrut(70));
        add(panel1);
        
        ImageIcon imagen = new ImageIcon("login8.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setSize(1210, 910);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        
        
        
        add(etiqueta2);
        

        usuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                  activarBoton(usuario,contrasena,botonlogin);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activarBoton(usuario,contrasena,botonlogin);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                activarBoton(usuario,contrasena,botonlogin);
            }
        });
        
         contrasena.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                activarBoton(usuario,contrasena,botonlogin);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                   activarBoton(usuario,contrasena,botonlogin);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                 activarBoton(usuario,contrasena,botonlogin);
            }
        });
        
        
        botonlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               if(prueba(usuario.getText(),contrasena.getText()) == false){
                   JOptionPane.showMessageDialog(null, "Usuario no encontrado...");
               }else{
                    dispose();
               }
               
            }
        });      
    }
    
    public JTextField getusuario(){
        return usuario;
    }
    
    public JTextField getContra(){
        return contrasena;
    }
    
    public boolean roldeusuario(JTextField usuario,JTextField contrasena){
        return gestor.roldeusuario(usuario, contrasena);
    }
    
    public void activarBoton(JTextField usuario,JTextField contrasena,JButton boton){
        gestor.activarboton(usuario,contrasena,boton);
    }
    public boolean prueba(String usuario,String Contrasena){
        return gestor.login(usuario,Contrasena);
    }
    
    public void init(){
            setVisible(true);
    }
    
    public void resetear(){
        usuario.setText("");
        contrasena.setText("");
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Este metodo update esta enlazado con las algunas ventanas");
        
     
    }
    
    private void addComponent(Component component, int row, int col, int width, int height){
        constraints.gridx = col;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        add(component);
    }
    
    
    private ControlAplicacion gestor;
    private JTextField usuario;
    private JPasswordField contrasena;
    private JButton botonlogin;
    private GridBagConstraints constraints;
    private GridBagLayout layout;

}
