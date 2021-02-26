/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import datetimepicker.CalendarWindow;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modeloproyecto.Vuelo;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DateFormatter;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaAgregarVuelo extends JFrame implements Observer, PropertyChangeListener {

    FondoPanel fondo = new FondoPanel();

    public VentanaAgregarVuelo(ControlAplicacion nuevogestor, VentanaMantenimientodeVuelos v) {
        setSize(600, 650);
        setResizable(true);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setContentPane(fondo);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }

        });

        ajustarComponentes();
        gestor = nuevogestor;
    }

    public void cerrarAplicacion() {
        if (JOptionPane.showConfirmDialog(this,
                "Desea cerrar la aplicación",
                "Confirmar",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            dispose();
            try {
                cwindow.dispose();
            } catch (Exception e) {

            }

        }

    }

    public void ajustarComponentes() {

        JLabel titulo = new JLabel("AGREGAR VUELO");
        titulo.setFont(new Font("times new roman", Font.PLAIN, 20));
        titulo.setForeground(Color.WHITE);
        add(titulo);

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new FlowLayout());
        JLabel etiq1 = new JLabel("Ingrese el nombre de la aerolínea: ");
        etiq1.setFont(new Font("times new roman", Font.PLAIN, 17));
        etiq1.setForeground(Color.WHITE);
        panel1.add(etiq1);
        aero = new JTextField(15);
        panel1.setBackground(Color.BLACK);
        panel1.setForeground(Color.WHITE);
        panel1.add(aero);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel1);
        panel1.add(Box.createVerticalStrut(50));
        ///////////////////////////////////////////////////////////       
        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.setLayout(new FlowLayout());
        JLabel etiq2 = new JLabel("Ingrese el numero de vuelo:");
        etiq2.setFont(new Font("times new roman", Font.PLAIN, 17));
        etiq2.setForeground(Color.WHITE);
        panel2.add(etiq2);
        vuelo = new JTextField(15);
        panel2.add(vuelo);
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.setBackground(Color.BLACK);
        panel2.setForeground(Color.WHITE);
        add(panel2);

        panel2.add(Box.createVerticalStrut(50));
        //////////////////////////////////////////////////////////
        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setLayout(new FlowLayout());
        JLabel etiq3 = new JLabel("Desde: ");
        etiq3.setForeground(Color.WHITE);
        etiq3.setFont(new Font("times new roman", Font.PLAIN, 17));
        panel3.add(etiq3);
        desde = new JTextField(15);
        panel3.add(desde);
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.setBackground(Color.BLACK);
        panel3.setForeground(Color.WHITE);
        add(panel3);
        panel3.add(Box.createVerticalStrut(50));
        //////////////////////////////////////////////////////////
        JPanel panel4 = new JPanel();
        panel4.setOpaque(false);
        panel4.setLayout(new FlowLayout());
        JLabel etiq4 = new JLabel("Fecha/hora de sallida:");
        etiq4.setFont(new Font("times new roman", Font.PLAIN, 17));
        etiq4.setForeground(Color.WHITE);
        panel4.add(etiq4);

        cwindow = new CalendarWindow();
        textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
        FlowLayout flowLayout = new FlowLayout();
        textField.setValue(new Date());
        textField.setPreferredSize(new Dimension(100, 23));

        label56 = new JPanel();
        label56.setOpaque(false);
        label56.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textField.setFont(new Font("arial", Font.BOLD, 15));
        label56.setSize(350, 40);
        label56.setLayout(flowLayout);
        label56.add(textField);
        cwindow.setUndecorated(true);
        button = new JButton("Definir Fecha");
        label56.add(button);
        initializeUI();
        label56.add(spinner);

        panel4.add(label56);

        salida = new JTextField(15);
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.setBackground(Color.BLACK);
        panel4.setForeground(Color.WHITE);
        add(panel4);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cwindow.setLocation(label56.getLocationOnScreen().x, label56.getLocationOnScreen().y + (label56.getHeight() - 5));

                Date selectedDate = (Date) textField.getValue();
                cwindow.resetSelection(selectedDate);

                cwindow.init();
                fhsalida.setText(textField.getText() + texthora);

            }
        });

        fhsalida = new JLabel();
        fhllegada = new JLabel();
        
        
        
        panel4.add(Box.createVerticalStrut(50));
        ////////////////////////////////////////////////////////
        JPanel panel5 = new JPanel();
        panel5.setOpaque(false);
        panel5.setLayout(new FlowLayout());
        JLabel etiq5 = new JLabel("Hasta:");
        etiq5.setFont(new Font("times new roman", Font.PLAIN, 17));
        etiq5.setForeground(Color.WHITE);
        panel5.add(etiq5);
        hasta = new JTextField(15);
        panel5.add(hasta);
        panel5.setBackground(Color.BLACK);
        panel5.setForeground(Color.WHITE);
        panel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel5);
        panel5.add(Box.createVerticalStrut(50));
        ////////////////////////////////////////////////////////
        JPanel panel6 = new JPanel();
        panel6.setOpaque(false);
        panel6.setLayout(new FlowLayout());
        JLabel etiq6 = new JLabel("Fecha/hora de llegada:");
        etiq6.setForeground(Color.WHITE);
        etiq6.setFont(new Font("times new roman", Font.PLAIN, 17));
        panel6.add(etiq6);

        ///////////////////////////////////////////////////////////
        cwindow1 = new CalendarWindow();
        textField1 = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
        FlowLayout flowLayout1 = new FlowLayout();
        textField1.setValue(new Date());
        textField1.setPreferredSize(new Dimension(100, 23));

        label57 = new JPanel();
        label57.setOpaque(false);
        label57.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textField1.setFont(new Font("arial", Font.BOLD, 15));
        label57.setSize(350, 40);
        label57.setLayout(flowLayout1);
        label57.add(textField1);
        cwindow1.setUndecorated(true);
        button1 = new JButton("Definir Fecha");
        label57.add(button1);
        initializeUI();
        label57.add(spinner);

        
        
        panel6.add(label57);

        llegada = new JTextField(15);

        panel6.setBackground(Color.BLACK);
        panel6.setForeground(Color.WHITE);
        panel6.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel6);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cwindow1.setLocation(label57.getLocationOnScreen().x, label57.getLocationOnScreen().y + (label57.getHeight() - 5));

                Date selectedDate = (Date) textField1.getValue();
                cwindow1.resetSelection(selectedDate);

                cwindow1.init();
            }
        });
        panel6.add(Box.createVerticalStrut(50));

        JPanel panel7 = new JPanel();
        panel7.setOpaque(false);
        panel7.setLayout(new FlowLayout());
        JLabel etiq7 = new JLabel("Asientos: ");
        etiq7.setForeground(Color.WHITE);
        etiq7.setFont(new Font("times new roman", Font.PLAIN, 17));
        panel7.add(etiq7);
        asientos = new JTextField(15);

        asientos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char validar = ke.getKeyChar();

                if (Character.isLetter(validar)) {
                    getToolkit().beep();
                    ke.consume();

                    JOptionPane.showMessageDialog(fondo, "Ingresar solo numeros");
                }
            }

        });
        panel7.add(asientos);
        panel7.setBackground(Color.BLACK);
        panel7.setForeground(Color.WHITE);
        panel7.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel7);
        panel7.add(Box.createVerticalStrut(50));

        guardar = new JButton("GUARDAR");
        guardar.setFont(new Font("times new roman", Font.PLAIN, 20));

        add(Box.createVerticalStrut(50));
        guardar.setEnabled(false);
      

        add(guardar);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agregarvuelo() == true) {
                    JOptionPane.showMessageDialog(fondo, "Vuelo ingresado con Exito...");
                    resetear();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(fondo, "Vuelo no Ingresado...");
                    resetear();
                    dispose();
                }
            }
        });

        aero.getDocument().addDocumentListener(new DocumentListener() {
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

        vuelo.getDocument().addDocumentListener(new DocumentListener() {
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

        desde.getDocument().addDocumentListener(new DocumentListener() {
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

        hasta.getDocument().addDocumentListener(new DocumentListener() {
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

        asientos.getDocument().addDocumentListener(new DocumentListener() {
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

        cwindow.addPropertyChangeListener(this);

        cwindow1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                if (pce.getPropertyName().equals("Date")) {
                    java.util.Calendar cal = (java.util.Calendar) pce.getNewValue();
                    Date setDate = cal.getTime();
                    textField1.setValue(setDate);
                }
            }
        });
    }

    public void activarBoton() {
        if (aero.getText().equals("") || vuelo.getText().equals("") || desde.getText().equals("") || textField.getText().equals("") || hasta.getText().equals("") || textField1.getText().equals("") || asientos.getText().equals("")) {
            guardar.setEnabled(false);

        } else {
            guardar.setEnabled(true);
        }
    }

    public void init() {
        setVisible(true);
    }

    public boolean agregarvuelo() {
        fhsalida.setText(textField.getText() +" " + texthora.getText());
        fhllegada.setText(textField1.getText() + " " + texthora.getText());
        
        Vuelo a = new Vuelo(aero.getText(), vuelo.getText(), desde.getText(), fhsalida.getText(), hasta.getText(), fhllegada.getText(), asientos.getText());
        
        return gestor.agregarVuelo(a);
    }

    public void resetear() {
        aero.setText("");
        vuelo.setText("");
        desde.setText("");
        hasta.setText("");
        llegada.setText("");
        asientos.setText("");
    }

    public void DateTimePicker() {
        cwindow = new CalendarWindow();
        textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
        FlowLayout flowLayout = new FlowLayout();
        textField.setValue(new Date());
        textField.setPreferredSize(new Dimension(228, 23));
        label56.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        label56.setSize(350, 40);
        textField.setFont(new Font("arial", Font.BOLD, 15));

        label56.setLayout(flowLayout);
        label56.add(textField);
        cwindow.setUndecorated(true);
        button = new JButton("Definir Fecha");
        label56.add(button);
    }

    private void initializeUI() {

        SpinnerDateModel model = new SpinnerDateModel(new Date(), null,null, Calendar.HOUR_OF_DAY);

        spinner = new JSpinner(model);

        texthora = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        DefaultFormatterFactory dff = (DefaultFormatterFactory) texthora.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) dff.getDefaultFormatter();
        formatter.setFormat(new SimpleDateFormat("hh:mm a"));

    }
 
    private JSpinner spinner;
    private  JFormattedTextField texthora;
    private JButton button;
    private JButton button1;
    private JPanel label56;
    private JPanel label57;
    private CalendarWindow cwindow = null;
    private CalendarWindow cwindow1 = null;
    private JFormattedTextField textField;
    private JFormattedTextField textField1;
    private ControlAplicacion gestor;
    private JTextField aero;
    private JTextField vuelo;
    private JTextField desde;
    private JTextField salida;
    private JTextField hasta;
    private JTextField llegada;
    private JButton guardar;
    private JTextField asientos;
    private JLabel fhsalida;
    private JLabel fhllegada;

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/agregar1.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        if (event.getPropertyName().equals("Date")) {
            java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
            Date setDate = cal.getTime();
            textField.setValue(setDate);
        }

    }

}
