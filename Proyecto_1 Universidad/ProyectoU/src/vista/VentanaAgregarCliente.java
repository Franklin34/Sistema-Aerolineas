/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modeloproyecto.Cliente;
import modeloproyecto.Vuelo;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaAgregarCliente extends JFrame implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaAgregarCliente(VentanaLogeo v, ControlAplicacion nuevogestor) {
        super("");
        setSize(1500, 925);
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

        filas = 0;
        gestor = nuevogestor;
        tabla = gestor.retornarTabla();
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

        JLabel titulo = new JLabel("                                                   REGISTRO DE CLIENTES");
        titulo.setFont(new Font("times new roman", Font.PLAIN, 40));

        add(titulo, BorderLayout.NORTH);

        JPanel panelprinicipal1 = new JPanel();
        panelprinicipal1.setOpaque(false);
        panelprinicipal1.setLayout(new FlowLayout());

        JPanel panelArriba = new JPanel();
        panelArriba.setLayout(new FlowLayout());
        panelArriba.setOpaque(false);

        JPanel panel_arriba1 = new JPanel();
        panel_arriba1.setOpaque(false);
        panel_arriba1.setLayout(new BoxLayout(panel_arriba1, BoxLayout.PAGE_AXIS));
        ///////////////////////////////////////////////////////////////////////////////////
        JPanel panelNombre = new JPanel();
        panelNombre.setOpaque(false);
        panelNombre.setLayout(new FlowLayout());

        JLabel etiqueteNombre = new JLabel("Nombre: ");
        etiqueteNombre.setFont(new Font("times new roman", Font.PLAIN, 20));
        cajanombre = new JTextField(15);

        panelNombre.add(etiqueteNombre);
        panelNombre.add(cajanombre);

        ///////////////////////////////////
        panel_arriba1.add(panelNombre);
        panelNombre.add(Box.createVerticalStrut(50));
        ///////////////////////////////////
        JPanel panelApellidos = new JPanel();
        panelApellidos.setOpaque(false);
        panelApellidos.setLayout(new FlowLayout());

        JLabel etiqueteApellido = new JLabel("Apellidos: ");
        etiqueteApellido.setFont(new Font("times new roman", Font.PLAIN, 20));
        cajaapellidos = new JTextField(15);

        panelApellidos.add(etiqueteApellido);
        panelApellidos.add(cajaapellidos);

        ///////////////////////////////////
        panel_arriba1.add(panelApellidos);
        panelApellidos.add(Box.createVerticalStrut(50));
        ///////////////////////////////////
        JPanel panelCedula = new JPanel();
        panelCedula.setOpaque(false);
        panelCedula.setLayout(new FlowLayout());

        JLabel etiqueteCedula = new JLabel("Cedula: ");
        etiqueteCedula.setFont(new Font("times new roman", Font.PLAIN, 20));
        cajacedula = new JTextField(15);

        panelCedula.add(etiqueteCedula);
        panelCedula.add(cajacedula);

        ////////////////////////////////////
        panel_arriba1.add(panelCedula);
        panelCedula.add(Box.createVerticalStrut(50));
        panelArriba.add(panel_arriba1);
        ///////////////////////////////////

        JPanel panel_arriba2 = new JPanel();
        panel_arriba2.setLayout(new BoxLayout(panel_arriba2, BoxLayout.PAGE_AXIS));
        panel_arriba2.setOpaque(false);

        JPanel panelCantidadpasajeros = new JPanel();
        panelCantidadpasajeros.setOpaque(false);
        panelCantidadpasajeros.setLayout(new FlowLayout());

        JLabel cantidadpasa = new JLabel("Cantidad de Asientos: ");
        cantidadpasa.setFont(new Font("times new roman", Font.PLAIN, 20));
        cajapasajeros = new JTextField(15);
        cajapasajeros.setEditable(false);
        panelCantidadpasajeros.add(cantidadpasa);
        panelCantidadpasajeros.add(cajapasajeros);

        panel_arriba2.add(panelCantidadpasajeros);
        panelCantidadpasajeros.add(Box.createVerticalStrut(50));
        //////////////////////////////////////////
        JPanel panelnumero = new JPanel();
        panelnumero.setOpaque(false);
        panelnumero.setLayout(new FlowLayout());

        JLabel etinumerovuelo = new JLabel("N de Vuelo: ");
        etinumerovuelo.setFont(new Font("times new roman", Font.PLAIN, 20));
        cajavuelo = new JTextField(15);
        cajavuelo.setEditable(false);

        panelnumero.add(etinumerovuelo);
        panelnumero.add(cajavuelo);

        panel_arriba2.add(panelnumero);
        panelnumero.add(Box.createVerticalStrut(50));
        panel_arriba2.setOpaque(false);
        panelArriba.add(panel_arriba2);

        panelArriba.add(Box.createVerticalStrut(250));

        panelArriba.setOpaque(false);

        panelprinicipal1.add(panelArriba);

        add(panelprinicipal1, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        JPanel abajoprincipal = new JPanel();
        abajoprincipal.setOpaque(false);
        abajoprincipal.setLayout(new BoxLayout(abajoprincipal, BoxLayout.PAGE_AXIS));

        JPanel panelTabla = new JPanel();
        panelTabla.setOpaque(false);
        panelTabla.setLayout(new FlowLayout());
        JScrollPane scrollpane = new JScrollPane(gestor.retornarTabla());

        gestor.retornarTabla().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("UnusedAssignment")
            public void mouseClicked(MouseEvent me) {

                k = 0;
                int seleccion = tabla.getSelectedRow();

                filas = seleccion;

                String[] info = new String[7];

                info[0] = (String) tabla.getValueAt(seleccion, 0).toString();
                info[1] = (String) tabla.getValueAt(seleccion, 1).toString();
                info[2] = (String) tabla.getValueAt(seleccion, 2).toString();
                info[3] = (String) tabla.getValueAt(seleccion, 3).toString();
                info[4] = (String) tabla.getValueAt(seleccion, 4).toString();
                info[5] = (String) tabla.getValueAt(seleccion, 5).toString();
                info[6] = (String) tabla.getValueAt(seleccion, 6).toString();

                vuelo = new Vuelo(info[0], info[1], info[2], info[3], info[4], info[5], info[6]);

                vuelonumero = info[1];
                cantAsientos = info[6];

                String h = String.valueOf(gestor.getk(info[1]));
                cajapasajeros.setText("");

                if (panelderecha != null) {
                    panelprinicipal1.remove(panelderecha);
                    panelprinicipal1.invalidate();
                    panelprinicipal1.revalidate();
                    panelprinicipal1.repaint();

                    System.out.println("entramos");
                }

                panelderecha = new JPanel();
                panelderecha.setOpaque(false);

                cajavuelo.setText((String) tabla.getValueAt(seleccion, 1).toString());

                panelderecha.setLayout(new GridLayout(gestor.geta(info[1]), 9, 6, 6));
                matrizdebotonesA = gestor.getMatriz(info[1]);

                for (int i = 0; i < gestor.geta(info[1]); i++) {
                    for (int j = 0; j < 9; j++) {
                        matrizdebotonesA[i][j].addActionListener(new BotonPulsadoListener());
                        panelderecha.add(matrizdebotonesA[i][j]);
                    }
                }

                if (gestor.getd(info[1]) != 0) {
                    for (int q = 0; q < gestor.getd(info[1]); q++) {
                        matrizdebotonesA[gestor.geta(info[1])][q].addActionListener(new BotonPulsadoListener());
                        panelderecha.add(matrizdebotonesA[gestor.geta(info[1])][q]);
                    }
                }

                panelprinicipal1.add(panelderecha);

                panelderecha.setMinimumSize(new Dimension(600, 400));
                panelderecha.setMaximumSize(new Dimension(600, 400));
                panelderecha.setPreferredSize(new Dimension(600, 400));

                panelprinicipal1.invalidate();
                panelprinicipal1.revalidate();
                panelprinicipal1.repaint();
            }
        });
        panelTabla.add(scrollpane);

        scrollpane.setMinimumSize(new Dimension(850, 300));
        scrollpane.setMaximumSize(new Dimension(850, 300));
        scrollpane.setPreferredSize(new Dimension(850, 300));

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new FlowLayout());
       
        abajoprincipal.add(panelTabla);
        /////////////////////////////////////////////////////////////////////

        JPanel panelAbajo = new JPanel();
        panelAbajo.setOpaque(false);
        panelAbajo.setLayout(new FlowLayout());
        panelAbajo.setOpaque(false);

        boton1 = new JButton("RESERVAR VUELO");
        boton1.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton1.setEnabled(false);

        cajanombre.getDocument().addDocumentListener(new DocumentListener() {
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

        cajacedula.getDocument().addDocumentListener(new DocumentListener() {
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
        cajaapellidos.getDocument().addDocumentListener(new DocumentListener() {
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
        cajapasajeros.getDocument().addDocumentListener(new DocumentListener() {
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
        cajavuelo.getDocument().addDocumentListener(new DocumentListener() {
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

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Cliente cliente = new Cliente(cajanombre.getText(), cajacedula.getText(), cajaapellidos.getText(), cajapasajeros.getText());

                int hj = Integer.parseInt(vuelo.getAsientos());

                try {
                    cliente.AgregarVuelo(vuelo);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(fondo, "Debe seleccionar un vuelo");
                }

                modelo = (DefaultTableModel) tabla.getModel();

                if (gestor.ingresarCliente(cliente) == true) {
                
                    VentanadeCodigo ventanacodigo = new VentanadeCodigo();
                    ventanacodigo.init();
 

                    /////////////////////////////////////////////////////////////////////////////
                    int y = Integer.parseInt(cantAsientos);

                    int modi = y - k;

                    String modifasiento = String.valueOf(modi);

                    System.out.println(modifasiento);
                    gestor.setAsientos(vuelonumero, modifasiento);

                    for (int i = 0; i < tabla.getColumnCount(); i++) {
                        modelo.setValueAt(modifasiento, filas, 6);
                    }

                    cajanombre.setText("");
                    cajacedula.setText("");
                    cajaapellidos.setText("");
                    cajapasajeros.setText("");
                    cajavuelo.setText("");

                    k = 0;
                    gestor.setMatriz(vuelonumero, matrizdebotonesA);

                } else {
                    JOptionPane.showMessageDialog(fondo, "Hubo un problema al ingresar el cliente...");
                    cajanombre.setText("");
                    cajacedula.setText("");
                    cajaapellidos.setText("");
                    cajapasajeros.setText("");
                    cajavuelo.setText("");
                    k = 0;
                }

            }
        });

        JButton boton2 = new JButton("ATRAS");
        boton2.setFont(new Font("times new roman", Font.PLAIN, 20));

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                mostrarventanaAdm();
            }
        });

        panelAbajo.add(boton2);
        panelAbajo.add(boton1);
        panelAbajo.add(Box.createVerticalStrut(100));
        abajoprincipal.add(panelAbajo);
        add(abajoprincipal, BorderLayout.SOUTH);
    }

    public void init() {
        setVisible(true);
    }

    public void activarBoton() {
        if (cajanombre.getText().equals("") || cajavuelo.getText().equals("") || cajaapellidos.getText().equals("") || cajacedula.getText().equals("") || cajapasajeros.getText().equals("")) {
            boton1.setEnabled(false);

        } else {
            boton1.setEnabled(true);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMenuClientes(VentanaMenuClientes v) {
        menuclientes = v;
    }

    public void mostrarventanaAdm() {
        menuclientes.setVisible(true);
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    private VentanaLogeo ventana;
    private ControlAplicacion gestor;
    private JTable tabla;
    private int filas;
    private JPanel panelderecha;
    private JButton[][] matrizdebotonesA;
    private JButton[][] matrizdebotonesB;
    private VentanaMenuClientes menuclientes;
    private JTextField cajapasajeros;
    private int k = 0;
    private int a;
    private int b;
    private int d;
    private Vuelo vuelo;
    private JButton boton1;
    private JTextField cajanombre;
    private JTextField cajaapellidos;
    private JTextField cajacedula;
    private JTextField cajavuelo;
    private String vuelonumero;
    private String cantAsientos;
    private DefaultTableModel modelo;

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/USERS.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

    class VentanadeCodigo extends JFrame {

       FondoPanel fondo = new FondoPanel();
       
        public VentanadeCodigo() {
            setVisible(true);
            setSize(1000, 500);
            setLocationRelativeTo(null);
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
            this.setContentPane(fondo);
            setLayout(new BorderLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ajustarComponentes();
        }

        public void init(){
            setVisible(true);
        }
        public void ajustarComponentes() {
            JPanel panel_titulo = new JPanel();
           
            panel_titulo.setLayout(new BoxLayout(panel_titulo, BoxLayout.PAGE_AXIS));
            panel_titulo.setOpaque(false);
            JLabel titulo2 = new JLabel("                              Datos de reservacion");
            titulo2.setFont(new Font("times new roman", Font.PLAIN, 40));
            titulo2.setForeground(Color.WHITE);
  
            panel_titulo.add(titulo2);

            JTextArea datosdelcliente = new JTextArea();
            datosdelcliente.setOpaque(false);
            datosdelcliente.setForeground(Color.WHITE);
            datosdelcliente.setFont(new Font("times new roman", Font.PLAIN, 15));
            datosdelcliente.setEditable(false);
            datosdelcliente.setSize(500, 500);

            datosdelcliente.setText(gestor.retornarTostring(cajacedula.getText()));

            panel_titulo.add(datosdelcliente);
            
            add(panel_titulo,BorderLayout.NORTH);
            
            /////////////////////////////////////////////////////
            JPanel panel_centro = new JPanel();
            panel_centro.setOpaque(false);
            panel_centro.setLayout(new FlowLayout());
            JTextArea datosdelvuelo = new JTextArea();
            datosdelvuelo.setOpaque(false);
            datosdelvuelo.setForeground(Color.WHITE);
            datosdelvuelo.setFont(new Font("times new roman", Font.PLAIN, 15));
            datosdelvuelo.setEditable(false);
            datosdelvuelo.setSize(500, 500);

            datosdelvuelo.setText(gestor.retornarVuelosCliente(cajacedula.getText()));

            panel_centro.add(datosdelvuelo);
            add(panel_centro,BorderLayout.CENTER);

            
            JPanel panel_abajo = new JPanel();
            panel_abajo.setOpaque(false);
            panel_abajo.setLayout(new FlowLayout());
            JLabel datosdelcodigo = new JLabel();
            datosdelcodigo.setSize(500, 500);

            Random rnd = new Random();
            String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            String cadena = "";

            int numero = 0;
            int forma;

            forma = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);

            numero = (int) (rnd.nextDouble() * 99 + 100);
            cadena = cadena + abecedario.charAt(forma) + numero;
            datosdelcodigo.setText("Codigo de reservacion: " + cadena);
            datosdelcodigo.setForeground(Color.WHITE);
            datosdelcodigo.setFont(new Font("times new roman", Font.PLAIN, 40));
            panel_abajo.add(datosdelcodigo);
            add(panel_abajo,BorderLayout.SOUTH);
            panel_abajo.add(Box.createVerticalStrut(180));
        }
        
        class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/ultima4.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
    }

    class BotonPulsadoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            k++;
            String a1 = String.valueOf(k);
            cajapasajeros.setText(a1);

            System.out.println("A:" + a);
            System.out.println("B:" + b);

            JButton bx = (JButton) ae.getSource();

            for (int i = 0; i < gestor.geta(vuelonumero) + 1; i++) {
                for (int j = 0; j < 9; j++) {
                    if (matrizdebotonesA[i][j] == bx) {
                        bx.setBackground(Color.RED);
                        bx.setEnabled(false);
                        matrizdebotonesA[i][j].setBackground(Color.RED);
                        matrizdebotonesA[i][j].setEnabled(false);
                    }
                }
            }

            if (gestor.getd(vuelonumero) != 0) {
                for (int q = 0; q < gestor.getd(vuelonumero); q++) {
                    if (matrizdebotonesA[gestor.geta(vuelonumero)][q] == bx) {
                        bx.setBackground(Color.RED);
                        bx.setEnabled(false);
                        matrizdebotonesA[gestor.geta(vuelonumero)][q].setBackground(Color.RED);
                        matrizdebotonesA[gestor.geta(vuelonumero)][q].setEnabled(false);
                    }
                }
            }
        }

    }

}
