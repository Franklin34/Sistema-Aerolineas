/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaCrearAerolineas extends JDialog implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaCrearAerolineas(ControlAplicacion nuevogestor,VentanaLogeo v,boolean nodal) {
        super(v,nodal);
        setSize(800,600);
        setResizable(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        this.setContentPane(fondo);

         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
         addWindowListener( new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                                cerrarAplicacion();
                    }
            
        });
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        ajustarComponentes();

        gestor = nuevogestor;
        ventana = v;

    }

      public void cerrarAplicacion(){
           if(JOptionPane.showConfirmDialog(this,
                   "Desea cerrar la aplicación",
                   "Confirmar", 
                   JOptionPane.YES_NO_OPTION ) 
                   == JOptionPane.YES_OPTION){
                            dispose();
           
           }
       
       }
    public void ajustarComponentes() {

        JButton botonatras = new JButton("ATRAS");
        botonatras.setFont(new Font("times new roman", Font.PLAIN, 20));

        constraints.anchor = GridBagConstraints.WEST;
        addComponent(botonatras, 0, 0, 1, 1);
        
        
         botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
              
            }
        });

        JLabel titulo = new JLabel("REGISTRO DE AEROLINEAS");
        constraints.weightx = 0;
        constraints.weighty = 0.2;
         titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("times new roman", Font.PLAIN, 20));

        addComponent(titulo, 0, 1, 1, 1);
        constraints.anchor = GridBagConstraints.CENTER;

        JLabel etiqueta_usuario = new JLabel("INGRESE EL NOMBRE DE LA AEROLINEA: ");
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.NORTHWEST;
        etiqueta_usuario.setFont(new Font("times new roman", Font.PLAIN, 14));
         etiqueta_usuario.setForeground(Color.WHITE);
        addComponent(etiqueta_usuario, 1, 0, 1, 1);

        nombreaerolinea = new JTextField(15);
        addComponent(nombreaerolinea, 1, 1, 1, 1);

        JLabel etiqueta_contrasena = new JLabel("Fecha de Creación: ");
        constraints.fill = GridBagConstraints.NONE;
        etiqueta_contrasena.setFont(new Font("times new roman", Font.PLAIN, 20));
        etiqueta_contrasena.setForeground(Color.white);
        addComponent(etiqueta_contrasena, 2, 0, 1, 1);

        fechaaerolinea = new JTextField(15);
        fechaaerolinea.setEditable(false);
        
         LocalDate date = LocalDate.now(); 
         fechaaerolinea.setText(date.toString());
        
        
        
        addComponent(fechaaerolinea, 2, 1, 1, 1);

        guardar = new JButton("GUARDAR");
        guardar.setFont(new Font("times new roman", Font.PLAIN, 20));
        addComponent(guardar, 3, 1, 1, 1);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (insertarAerolinea(nombreaerolinea.getText(), fechaaerolinea.getText()) == true) {
                    JOptionPane.showMessageDialog(fondo, "Aerolinea Ingresada con exito!");
                    resetear();

                } else {
                    JOptionPane.showMessageDialog(fondo, "Aerolinea ya ingresada...");
                     resetear();
                }
            }
        });

    }

    public void resetear() {
        nombreaerolinea.setText("");
        

    }
    
    public void mostrarventanaVuelos(){
        VentanaMenuVuelos vuelos = new VentanaMenuVuelos(gestor,ventana,this);
        
        vuelos.setVisible(true);
    }

    public void init() {
        setVisible(true);
    }

    public boolean insertarAerolinea(String nombre, String fecha) {
        return gestor.insertarAerolinea(nombre, fecha);
    }

    private void addComponent(Component component, int row, int col, int width, int height) {
        constraints.gridx = col;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        add(component);
    }

    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private JButton guardar;
    private JButton atras;
    private ControlAplicacion gestor;
    private JTextField nombreaerolinea;
    private JTextField fechaaerolinea;
    private VentanaLogeo ventana;

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/aerolinea.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
}
