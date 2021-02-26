/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import java.awt.Color;
import java.awt.Component;
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
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Franklin/Sofia
 */
public class VentanaConfiguracion extends JDialog implements Observer {

    FondoPanel fondo = new FondoPanel();

    public VentanaConfiguracion(VentanaLogeo v, ControlAplicacion nuevogestor, boolean modal) {
        super(v, modal);
        setTitle("");
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setContentPane(fondo);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }

        });

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
        botonatras.setBounds(10, 5, 20, 10);

        addComponent(botonatras, 0, 0, 1, 1);

        JLabel titulo = new JLabel("REGISTRAR USUARIO");
        titulo.setForeground(Color.WHITE);

        constraints.weightx = 0;
        constraints.weighty = 1.0;

        titulo.setFont(new Font("times new roman", Font.PLAIN, 20));
        addComponent(titulo, 0, 1, 1, 1);

        constraints.weightx = 0;

        JLabel etiqueta_usuario = new JLabel("Usuario:");
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.NORTHWEST;
        etiqueta_usuario.setFont(new Font("times new roman", Font.PLAIN, 20));
        etiqueta_usuario.setForeground(Color.WHITE);
        addComponent(etiqueta_usuario, 1, 0, 1, 1);

        usuario = new JTextField(15);
        addComponent(usuario, 1, 1, 1, 1);

        JLabel etiqueta_contrasena = new JLabel("Contraseña: ");
        constraints.fill = GridBagConstraints.NONE;
        etiqueta_contrasena.setFont(new Font("times new roman", Font.PLAIN, 20));
        etiqueta_contrasena.setForeground(Color.WHITE);
        addComponent(etiqueta_contrasena, 2, 0, 1, 1);

        contrasena = new JPasswordField(15);
        contrasena.setToolTipText("La contraseña debe ser de 8 caracteres");
        addComponent(contrasena, 2, 1, 1, 1);

        JLabel etiqueta_verificacontra = new JLabel("Verificar Contraseña: ");
        constraints.fill = GridBagConstraints.NONE;
        etiqueta_verificacontra.setFont(new Font("times new roman", Font.PLAIN, 20));
        etiqueta_verificacontra.setForeground(Color.WHITE);
        addComponent(etiqueta_verificacontra, 3, 0, 1, 1);

        verifica_contrasena = new JPasswordField(15);
        addComponent(verifica_contrasena, 3, 1, 1, 1);

        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.NONE;

        JCheckBox administrador = new JCheckBox("Administrador");
        administrador.setFont(new Font("times new roman", Font.PLAIN, 20));
        JCheckBox plataforma = new JCheckBox("Plataforma");
        plataforma.setFont(new Font("times new roman", Font.PLAIN, 20));

        JButton boton_aceptar = new JButton("GUARDAR");
        boton_aceptar.setFont(new Font("times new roman", Font.PLAIN, 20));
        boton_aceptar.setEnabled(false);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(administrador);
        grupo.add(plataforma);

        addComponent(administrador, 6, 0, 1, 1);
        addComponent(plataforma, 6, 1, 1, 1);
        addComponent(boton_aceptar, 6, 2, 1, 1);

        botonatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        boton_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (plataforma.isSelected() == true) {
                    if (prueba(false, usuario.getText(), contrasena.getText()) == true) {
                        JOptionPane.showMessageDialog(fondo, "Usuario Ingresado con exito!");
                        resetear();

                    } else if (prueba(false, usuario.getText(), contrasena.getText()) == false) {
                        JOptionPane.showMessageDialog(fondo, "Usuario ya ingresado!");
                        resetear();
                    }

                } else if (administrador.isSelected() == true) {
                    if (prueba(true, usuario.getText(), contrasena.getText()) == true) {
                        JOptionPane.showMessageDialog(fondo, "Usuario Ingresado con exito!");
                        resetear();
                    } else if (prueba(true, usuario.getText(), contrasena.getText()) == false) {
                        JOptionPane.showMessageDialog(fondo, "Usuario ya ingresado!");
                        resetear();
                    }
                }
            }
        });

        usuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }
        });

        contrasena.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }
        });
        verifica_contrasena.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                activarBoton(usuario, contrasena, verifica_contrasena, boton_aceptar);
            }
        });

    }

    public void activarBoton(JTextField usuario, JTextField contrasena, JTextField verificar, JButton boton) {
        gestor.activarBoton2(usuario, contrasena, verificar, boton);
    }

    public void resetear() {
        usuario.setText("");
        contrasena.setText("");
        verifica_contrasena.setText("");
    }

    public boolean prueba(boolean rol, String usuario, String contrasena) {
        return gestor.insertarUsuario(rol, usuario, contrasena);
    }

    public void init() {
        setVisible(true);
    }

    private void addComponent(Component component, int row, int col, int width, int height) {
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

    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private ControlAplicacion gestor;
    private JTextField usuario;
    private JPasswordField contrasena;
    private JPasswordField verifica_contrasena;
    private VentanaLogeo ventana;

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/usuarios2.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

}
