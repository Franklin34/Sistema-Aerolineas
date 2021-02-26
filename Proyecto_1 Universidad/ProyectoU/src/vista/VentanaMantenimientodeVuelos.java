/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modeloproyecto.Modelo;
import modeloproyecto.Vuelo;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaMantenimientodeVuelos extends JFrame implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaMantenimientodeVuelos(ControlAplicacion nuevogestor, VentanaLogeo v) {

        setSize(1200, 810);
        setResizable(true);
        setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(fondo);
        setLayout(new FlowLayout());
        ajustarcomponentes();

        gestor = nuevogestor;
        ventana = v;
        ventanaAgregarVuelo = new VentanaAgregarVuelo(nuevogestor, this);

        gestor.registrar(this);
        tabladefault = new DefaultTableModel();

        listadefault = new DefaultListModel();
        listbox = new JList();

        tabla = gestor.retornarTabla();
        System.out.println("hola");
        filas = 0;
    }

    public void ajustarcomponentes() {

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        JButton botonatras = new JButton("ATRAS");
        botonatras.setFont(new Font("times new roman", Font.PLAIN, 20));

        botonatras.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                mostrarventanaAdm();
            }
        });

        botonconfig = new JButton("CONFIGURACION");
        botonconfig.setFont(new Font("times new roman", Font.PLAIN, 14));

        botonconfig.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel1.add(Box.createVerticalStrut(120));

        panel1.add(Box.createVerticalStrut(120));

        botonVuelos = new JButton("VUELOS");
        botonVuelos.setFont(new Font("times new roman", Font.PLAIN, 20));

        botonVuelos.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel1.add(botonVuelos);
        panel1.add(Box.createVerticalStrut(120));
        panel1.add(botonatras);
        panel1.add(Box.createVerticalStrut(120));
        panel1.setMinimumSize(new Dimension(150, 650));
        panel1.setMaximumSize(new Dimension(150, 650));
        panel1.setPreferredSize(new Dimension(150, 650));

        ///////////////////////////////////////////////////
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setMinimumSize(new Dimension(900, 600));
        panel2.setMaximumSize(new Dimension(900, 600));
        panel2.setPreferredSize(new Dimension(900, 600));

        JSplitPane splitpane = new JSplitPane(SwingConstants.VERTICAL, panel1, panel2);

        splitpane.setMinimumSize(new Dimension(1000, 750));
        splitpane.setMaximumSize(new Dimension(1000, 750));
        splitpane.setPreferredSize(new Dimension(1000, 750));

        splitpane.setOneTouchExpandable(true);
        add(Box.createVerticalStrut(200));

        getContentPane().add(splitpane);

        botonVuelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                listbox.setSelectedIndex(-1);
                panel2.removeAll();
                panel2.invalidate();
                panel2.revalidate();
                panel2.repaint();

                JPanel panelArriba = new JPanel();
                panelArriba.setLayout(new FlowLayout());

                /////////////////////////////////////////////////////////////////////
                JPanel panel_arriba1 = new JPanel();
                panel_arriba1.setLayout(new BoxLayout(panel_arriba1, BoxLayout.PAGE_AXIS));

                JPanel panel1 = new JPanel();

                panel1.setLayout(new FlowLayout());

                JLabel etiq1 = new JLabel("Nombre de la aerolínea:");

                etiq1.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel1.add(etiq1);

                JTextField aero = new JTextField(15);

                aero.setEditable(false);

                panel1.add(aero);

                panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel_arriba1.add(panel1);

                panel1.add(Box.createVerticalStrut(50));

                ///////////////////////////////////////////////////////////
                JPanel panel21 = new JPanel();

                panel21.setLayout(new FlowLayout());

                JLabel etiq2 = new JLabel(" Numero de vuelo:");

                etiq2.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel21.add(etiq2);

                JTextField numvuelo = new JTextField(15);

                numvuelo.setEditable(false);

                panel21.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel21.add(numvuelo);

                panel_arriba1.add(panel21);

                panel21.add(Box.createVerticalStrut(60));

                panelArriba.add(panel_arriba1);

                ////////////////////////////////////////////////
                JPanel panel3 = new JPanel();

                panel3.setLayout(new FlowLayout());

                JLabel etiq3 = new JLabel("        Desde: ");

                etiq3.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel3.add(etiq3);

                JTextField desde = new JTextField(15);

                panel3.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel3.add(desde);

                panel3.add(Box.createVerticalStrut(25));

                panel_arriba1.add(panel3);

                //////////////////////////////////////////////////////////
                JPanel panelarriba2 = new JPanel();

                panelarriba2.setLayout(new BoxLayout(panelarriba2, BoxLayout.PAGE_AXIS));

                //////////////////////////////////////////////////////////
                JPanel panel4 = new JPanel();

                panel4.setLayout(new FlowLayout());

                JLabel etiq4 = new JLabel("Fecha/hora de sallida:");

                etiq4.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel4.add(etiq4);

                JTextField salida = new JTextField(15);

                panel4.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel4.add(salida);

                panelarriba2.add(panel4);

                panel4.add(Box.createVerticalStrut(5));

                ////////////////////////////////////////////////////////
                JPanel panel5 = new JPanel();

                panel5.setLayout(new FlowLayout());

                JLabel etiq5 = new JLabel(" Hasta:");

                etiq5.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel5.add(etiq5);

                JTextField hasta = new JTextField(15);

                panel5.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel5.add(hasta);

                panelarriba2.add(panel5);

                panel5.add(Box.createVerticalStrut(50));

                JPanel panel78 = new JPanel();
                panel78.setLayout(new FlowLayout());

                JLabel et1 = new JLabel("Fecha de Creacion: ");
                etiq5.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel78.add(et1);

                JTextField fechacreacion = new JTextField(15);
                fechacreacion.setEditable(false);

                panel78.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel78.add(fechacreacion);

                panelarriba2.add(panel78);

                panel78.add(Box.createVerticalStrut(50));

                panelArriba.add(panelarriba2);

                ////////////////////////////////////////////////////////
                JPanel panelarriba3 = new JPanel();

                panelarriba3.setLayout(new BoxLayout(panelarriba3, BoxLayout.PAGE_AXIS));

                JPanel panel6 = new JPanel();

                panel6.setLayout(new FlowLayout());

                JLabel etiq6 = new JLabel("Fecha/hora de llegada:");

                etiq6.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel6.add(etiq6);

                JTextField llegada = new JTextField(15);

                panel6.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel6.add(llegada);

                panelarriba3.add(panel6);

                panel6.add(Box.createVerticalStrut(25));

                /////////////////////////////////////////////
                JPanel panel7 = new JPanel();

                panel7.setLayout(new FlowLayout());

                JLabel etiq7 = new JLabel("Cantidad de asientos:");

                etiq7.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel7.add(etiq7);

                JTextField asientos = new JTextField(15);

                panel7.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel7.add(asientos);

                panelarriba3.add(panel7);

                panel7.add(Box.createVerticalStrut(85));

                JPanel panel77 = new JPanel();
                panel77.setLayout(new FlowLayout());

                JLabel et2 = new JLabel("Modificacion: ");
                et2.setFont(new Font("times new roman", Font.PLAIN, 15));

                panel77.add(et2);

                JTextField fechamodi = new JTextField(15);
                fechamodi.setEditable(false);

                panel77.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel77.add(fechamodi);

                panelarriba3.add(panel77);

                panel77.add(Box.createVerticalStrut(50));

                panelArriba.add(panelarriba3);

                ///////////////////////////////////////////
                panel2.add(panelArriba, BorderLayout.NORTH);

                /////////////////////////////////////////////////////////////////////////////////////
                tabla.setEnabled(true);
                        
                JScrollPane scrollpane = new JScrollPane(tabla);

                scrollpane.setEnabled(false);

                tabla.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        int seleccion = tabla.getSelectedRow();
                        aero.setText((String) tabla.getValueAt(seleccion, 0).toString());
                        numvuelo.setText((String) tabla.getValueAt(seleccion, 1).toString());
                        desde.setText((String) tabla.getValueAt(seleccion, 2).toString());
                        salida.setText((String) tabla.getValueAt(seleccion, 3).toString());
                        hasta.setText((String) tabla.getValueAt(seleccion, 4).toString());
                        llegada.setText((String) tabla.getValueAt(seleccion, 5).toString());
                        asientos.setText((String) tabla.getValueAt(seleccion, 6).toString());

                        filas = seleccion;

                        fechacreacion.setText(gestor.getCreacion(tabla.getValueAt(seleccion, 1).toString()));

                    }
                });

                panel2.add(scrollpane, BorderLayout.CENTER);

                JPanel panelAbajo = new JPanel();
                panelAbajo.setLayout(new FlowLayout());

                JButton botonAgregarVuelo = new JButton("Agregar Vuelo");
                botonAgregarVuelo.setFont(new Font("times new roman", Font.PLAIN, 20));

                panelAbajo.add(botonAgregarVuelo);

                botonAgregarVuelo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ventanaAgregarVuelo.init();
                    }
                });

                JButton modificar = new JButton("Modificar");
                modificar.setFont(new Font("times new roman", Font.PLAIN, 20));
                panelAbajo.add(modificar);
               

                modificar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        try {

                            String[] info = new String[7];

                            info[0] = aero.getText();
                            info[1] = numvuelo.getText();
                            info[2] = desde.getText();
                            info[3] = salida.getText();
                            info[4] = hasta.getText();
                            info[5] = llegada.getText();
                            info[6] = asientos.getText();

                            for (int i = 0; i < tabla.getColumnCount(); i++) {
                                modelo.setValueAt(info[i], filas, i);
                                
                                Vuelo a = new Vuelo(aero.getText(), numvuelo.getText(), desde.getText(), salida.getText(), hasta.getText(), llegada.getText(), asientos.getText());

                                LocalDate date = LocalDate.now();

                                gestor.cambiarVuelo(a, date.toString());
                                fechamodi.setText(date.toString());
                            }
                        } catch (Exception e) {
                              JOptionPane.showMessageDialog(fondo, "Debe existir un vuelo para modificar");
                        }
                    }
                });

                JButton eliminar = new JButton("Eliminar");
                eliminar.setFont(new Font("times new roman", Font.PLAIN, 20));
                panelAbajo.add(eliminar);

                eliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        int fila1 = tabla.getSelectedRow();

                        if (fila1 >= 0) {
                            modelo.removeRow(fila1);

                            Vuelo b = new Vuelo(aero.getText(), numvuelo.getText(), desde.getText(), salida.getText(), hasta.getText(), llegada.getText(), asientos.getText());

                            gestor.eliminarVuelo(b);

                            aero.setText("");
                            numvuelo.setText("");
                            desde.setText("");
                            salida.setText("");
                            hasta.setText("");
                            llegada.setText("");
                            asientos.setText("");
                            fechacreacion.setText("");
                            fechamodi.setText("");

                        } else {
                            JOptionPane.showMessageDialog(fondo, "Seleccione un Vuelo");
                        }
                    }
                });

                panel2.add(panelAbajo, BorderLayout.SOUTH);
            }
        });

    }

    public void init() {
        setVisible(true);
    }

    public void mostrarventanaAdm() {

        VentanaMenuAdministrador admin = new VentanaMenuAdministrador(ventana, this, gestor);

        admin.setVisible(true);
    }

    public void activarBoton() {

    }

    private ControlAplicacion gestor;
    private VentanaLogeo ventana;
    private VentanaAgregarVuelo ventanaAgregarVuelo;
    private DefaultTableModel tabladefault;
    private JTable tabla;
    private JButton botonVuelos;
    private JButton botonconfig;
    private DefaultListModel listadefault;
    private JList listbox;
    private int filas;
    DefaultTableModel modelo;

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("entramos mae!!");
        Modelo m = (Modelo) o;

        gestor.setName(m.retornarVuelo().getAerolinea());
        modelo = (DefaultTableModel) tabla.getModel();

        String[] vector = new String[7];

        vector[0] = m.retornarVuelo().getAerolinea();
        vector[1] = m.retornarVuelo().getNumeroVuelo();
        vector[2] = m.retornarVuelo().getDesde();
        vector[3] = m.retornarVuelo().getFechahorasalida();
        vector[4] = m.retornarVuelo().getHasta();
        vector[5] = m.retornarVuelo().getLlegada();
        vector[6] = m.retornarVuelo().getAsientos();

        if (!"".equals(m.retornarVuelo().getAerolinea())) {
            modelo.addRow(vector);

            tabla.setModel(modelo);
            gestor.setTabla(tabla);
            gestor.setHola("Hola");
        }

    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/mante.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

}
