/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaModificarAerolinea extends JDialog implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaModificarAerolinea(ControlAplicacion nuevogestor, VentanaLogeo v) {

        super(v, true);
        setSize(1100, 650);
        setResizable(true);
        setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        this.setContentPane(fondo);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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

        JButton botonatras = new JButton("ATRAS");
        botonatras.setFont(new Font("times new roman", Font.PLAIN, 20));

        constraints.anchor = GridBagConstraints.NORTHWEST;
        addComponent(botonatras, 0, 0, 1, 1);

        botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JButton botonveraero = new JButton("VER AEROLINEAS");
        botonveraero.setFont(new Font("times new roman", Font.PLAIN, 14));

        listadefault = new DefaultListModel();

        botonveraero.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(90));
        panel1.add(botonveraero);
        panel1.add(Box.createVerticalStrut(90));

        JButton botonmodificar = new JButton("MODIFICAR AEROLINEA");
        botonmodificar.setFont(new Font("times new roman", Font.PLAIN, 14));

        botonmodificar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel1.add(botonmodificar);
        panel1.add(Box.createVerticalStrut(90));

        listadefault = new DefaultListModel();

        listBox = new JList(listadefault);

        panel1.setMinimumSize(new Dimension(200, 400));
        panel1.setMaximumSize(new Dimension(200, 400));
        panel1.setPreferredSize(new Dimension(200, 400));

        /////////////////////////////////////
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        guardar = new JButton("GUARDAR");
        panel2.setMinimumSize(new Dimension(500, 400));
        panel2.setMaximumSize(new Dimension(500, 400));
        panel2.setPreferredSize(new Dimension(500, 400));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setOneTouchExpandable(true);
        addComponent(splitPane, 1, 1, 3, 3);

        botonmodificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel2.removeAll();
                panel2.invalidate();
                panel2.revalidate();
                panel2.repaint();

                JLabel titulo = new JLabel("Modificar Aerolinea");
                titulo.setFont(new Font("times new roman", Font.PLAIN, 20));
                panel2.add(titulo);

                JPanel panel_box1 = new JPanel();
                panel_box1.setLayout(new FlowLayout());
                JLabel etiqueta_usuario = new JLabel("  NUEVO NOMBRE DE LA AEROLINEA: ");
                etiqueta_usuario.setFont(new Font("times new roman", Font.PLAIN, 11));
                panel_box1.add(etiqueta_usuario);

                nombreaerolinea = new JTextField(15);

                nombreaerolinea.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent de) {
                        activarBoton();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent de) {
                        activarBoton();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent de) {
                        activarBoton();
                    }
                });

                panel_box1.add(nombreaerolinea);

                panel2.add(panel_box1);

                JPanel panel_box2 = new JPanel();
                panel_box2.setLayout(new FlowLayout());

                JLabel etiqueta_contrasena = new JLabel("Fecha de Modificación: ");

                etiqueta_contrasena.setFont(new Font("times new roman", Font.PLAIN, 14));

                panel_box2.add(etiqueta_contrasena);

                fechaaerolinea = new JTextField(15);
                fechaaerolinea.setEditable(false);

                LocalDate date = LocalDate.now();
                fechaaerolinea.setText(date.toString());

                panel_box2.add(fechaaerolinea);

                panel2.add(panel_box2);

                guardar.setEnabled(false);
                guardar.setFont(new Font("times new roman", Font.PLAIN, 20));

                panel2.add(guardar);

            }
        });

        //////////////////////////////////////////////////////
        j=0;
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modificarAero() == true) {
                    JOptionPane.showMessageDialog(fondo, "Aerolinea modificada con exito");
                    nombreaerolinea.setText("");

                } else {
                    JOptionPane.showMessageDialog(fondo, "Hubo un error al modificar...");
                }
            }
        });

        botonveraero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                panel2.removeAll();
                panel2.invalidate();
                panel2.revalidate();
                panel2.repaint();
                
                JLabel tit = new JLabel("Seleccione la aerolinea a modificar: ");
                tit.setFont(new Font("times new roman", Font.PLAIN, 20));
                tit.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel2.add(tit);
                panel2.add(Box.createVerticalStrut(30));

                listadefault = new DefaultListModel();
                for (int i = 0; i < gestor.tamanolista(); i++) {
                    listadefault.addElement(gestor.retornarNombreAerolinea(i));
                    
                }
                
                listBox = new JList(listadefault);
                panel2.add(listBox);

            }
        });
      listBox = new JList();
    }

    public boolean modificarAero() {
        return gestor.modificaraero(listBox.getSelectedValue().toString(), nombreaerolinea.getText(), fechaaerolinea.getText());
    }

    public void activarBoton() {

        if (nombreaerolinea.getText().equals("") || listBox.getSelectedIndex() == -1) {
            guardar.setEnabled(false);
        } else {
            guardar.setEnabled(true);
        }

    }

    public void mostrarventanaVuelos() {
        VentanaMenuVuelos vuelos = new VentanaMenuVuelos(gestor, ventana, this);

        vuelos.setVisible(true);
    }

    public void init() {
        setVisible(true);
    }

    public void retornarAerolinea() {
        gestor.ingresarAerolinea(listadefault);
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
    private VentanaLogeo ventana;
    private ControlAplicacion gestor;
    private DefaultListModel listadefault;
    private JList listBox;
    private JButton guardar;
    private JTextField nombreaerolinea;
    private JTextField fechaaerolinea;
    private int j;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("entramos");

    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondomodi.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
}
