/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectou;

import control.ControlAplicacion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import vista.VentanaConfiguracion;
import vista.VentanaCrearAerolineas;
import vista.VentanaLogeo;
import vista.VentanaMenuAdministrador;
import vista.VentanaMenuClientes;
import vista.VentanaMenuVuelos;

/**
 *
 * @author Usuario
 */
public class ProyectoU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ProyectoU obj = new ProyectoU();

        JOptionPane.showMessageDialog(null, "Usuario: Jeniffer\n" + "Contraseña: 12345678");
        obj.iniciar();
    }

    public void iniciar() {
        ControlAplicacion gestorPrincipal = new ControlAplicacion();
        VentanaLogeo principal = new VentanaLogeo(gestorPrincipal);

        gestorPrincipal.registrar(principal);

        principal.init();

        VentanaConfiguracion config = new VentanaConfiguracion(principal, gestorPrincipal, true);

        VentanaCrearAerolineas v12 = new VentanaCrearAerolineas(gestorPrincipal, principal, true);
        VentanaMenuVuelos v = new VentanaMenuVuelos(gestorPrincipal, principal, v12);
        VentanaMenuAdministrador menuadministrador = new VentanaMenuAdministrador(principal, v, gestorPrincipal);
        v.setAdmin(menuadministrador);

        principal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent we) {
                if (principal.roldeusuario(principal.getusuario(), principal.getContra()) == true) {
                    menuadministrador.init();
                } else {
                    VentanaMenuClientes ventanaClientes = new VentanaMenuClientes(gestorPrincipal, principal);
                    ventanaClientes.setVisible(true);
                }
            }
        });

    }

}
