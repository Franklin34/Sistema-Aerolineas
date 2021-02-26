/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaImprimirAerolinea extends JDialog implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaImprimirAerolinea(ControlAplicacion nuevogestor, VentanaLogeo v,boolean nodal) {
        super(v,nodal);
        setSize(800, 600);
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

        JLabel titulo = new JLabel("AEROLINEAS");
        constraints.weightx = 0;
        constraints.weighty = 0.1;
        titulo.setFont(new Font("times new roman", Font.PLAIN, 24));
        titulo.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.CENTER;
        addComponent(titulo, 0, 1, 1, 1);

        areadetexto = new JTextArea(10, 20);
        JScrollPane scroll = new JScrollPane(areadetexto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        areadetexto.setEditable(false);
        areadetexto.setText("PRESIONE EL BOTON PARA VER LAS AEROLINEAS");
        areadetexto.setLineWrap(true);
        areadetexto.setWrapStyleWord(true);
        scroll.setMinimumSize(new Dimension(600, 400));
        scroll.setMaximumSize(new Dimension(600, 400));
        scroll.setPreferredSize(new Dimension(600, 400));
        
        
        
        addComponent(scroll, 2, 0, 2, 2);
        
        JButton veraerolinea = new JButton("VER AEROLINEAS");
        veraerolinea.setFont(new Font("times new roman", Font.PLAIN, 20));
        
        addComponent(veraerolinea, 4, 1, 1, 1);
        
        veraerolinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               imprimirAerolineas();
            }
        });
        
        

    }

    private void addComponent(Component component, int row, int col, int width, int height) {
        constraints.gridx = col;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        add(component);

    }

    public void imprimirAerolineas() {
         areadetexto.setText(gestor.imprimirAerolineas());
    }

    public void mostrarventanaVuelos() {
        VentanaMenuVuelos vuelos = new VentanaMenuVuelos(gestor, ventana, this);

        vuelos.setVisible(true);
    }

    public void init() {
        setVisible(true);
    }

    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private VentanaLogeo ventana;
    private ControlAplicacion gestor;
    JTextArea areadetexto;

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/veraero.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
}
