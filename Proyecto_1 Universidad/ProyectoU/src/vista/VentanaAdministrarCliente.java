/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaAdministrarCliente extends JDialog implements Observer {

    FondoPanel fondo = new FondoPanel();
    
    public VentanaAdministrarCliente(VentanaLogeo v, ControlAplicacion nuevogestor) {
        super(v, true);
        setTitle("");
        setSize(900, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        
        this.setContentPane(fondo);

        setLayout(new BorderLayout());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }

        });

        gestor = nuevogestor;
        ventana = v;

        ajustarComponentes();
    }

    public void cerrarAplicacion() {
        if (JOptionPane.showConfirmDialog(this,
                "Desea cerrar la aplicación",
                "Confirmar",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            dispose();

        }

    }

    public void ajustarComponentes() {

        JLabel titulo = new JLabel("                      ADMINISTRACION DE CLIENTES");
        titulo.setFont(new Font("times new roman", Font.PLAIN, 25));
        titulo.setForeground(Color.WHITE);

        titulo.add(Box.createVerticalStrut(100));
        add(titulo, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new FlowLayout());

        JLabel eticedula = new JLabel("Ingrese el N° de cedula del Cliente: ");
        eticedula.setForeground(Color.WHITE);
        eticedula.setFont(new Font("times new roman", Font.PLAIN, 20));
        panel1.add(eticedula);

        JTextField cajacedula = new JTextField(15);
        panel1.add(cajacedula);
        panel1.add(Box.createVerticalStrut(400));
        JButton botonok = new JButton("OK");
        botonok.setFont(new Font("times new roman", Font.PLAIN, 20));
        panel1.add(botonok);

        add(panel1, BorderLayout.CENTER);

        botonok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (panel2 != null) {
                    remove(panel2);
                    invalidate();
                    revalidate();
                    repaint();
                }

                panel2 = new JPanel();
                panel2.setOpaque(false);
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

                panel2.setBorder(BorderFactory.createTitledBorder("Informacion"));
                
                JTextArea areatexto = new JTextArea();
                areatexto.setEditable(false);
                areatexto.setSize(500, 500);
                
                areatexto.setText(gestor.retornarTostring(cajacedula.getText()));
                
                panel2.add(areatexto);
                
                JTextArea areatexto2 = new JTextArea();
                areatexto2.setSize(600, 300);
                areatexto2.setEditable(false);
                
                areatexto2.setText(gestor.retornarVuelosCliente(cajacedula.getText()));
                panel2.add(areatexto2);
                
                panel2.add(Box.createVerticalStrut(400));
                add(panel2,BorderLayout.SOUTH); 
                
                invalidate();
                revalidate();
                repaint();
            }
        });

    }

    private VentanaLogeo ventana;
    private ControlAplicacion gestor;
    private JPanel panel2;

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/adminclientes.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

}
