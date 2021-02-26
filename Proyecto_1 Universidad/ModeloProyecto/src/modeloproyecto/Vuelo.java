/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloproyecto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class Vuelo {

    public Vuelo() {
        this.aerolinea = "";
        this.NumeroVuelo = "";
        this.desde = "";
        this.hasta = "";
        this.llegada = "";
        this.asientos = "";
        LocalDate date = LocalDate.now();
        this.fechaCreacion = date.toString();

        this.fechaModificacion = "";
    }

    public Vuelo(String aerolinea, String NumeroVuelo, String desde, String fechahorasalida, String hasta, String llegada, String asientos) {
        this.aerolinea = aerolinea;
        this.NumeroVuelo = NumeroVuelo;
        this.desde = desde;
        this.hasta = hasta;
        this.llegada = llegada;
        this.Fechahorasalida = fechahorasalida;
        this.asientos = asientos;

        LocalDate date = LocalDate.now();
        this.fechaCreacion = date.toString();

        a = Integer.parseInt(asientos) / 9;

         d = (int) (a % 9);

        matrizbotones = new JButton[a][9];

        if (d == 0) {
            matrizbotones = new JButton[a][9];
        } else {
            matrizbotones = new JButton[a + 1][9];
        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < 9; j++) {
                matrizbotones[i][j] = new JButton();
               // matrizbotones[i][j].addActionListener(new BotonPulsadoListener());
                matrizbotones[i][j].setBackground(Color.GREEN);
            }
        }

        if (d != 0) {
            for (int q = 0; q < d; q++) {
                matrizbotones[a][q] = new JButton();
                //matrizbotones[a][q].addActionListener(new BotonPulsadoListener());
                matrizbotones[a][q].setBackground(Color.GREEN);
               
            }
        }
    }

    @Override
    public String toString() {
         return "Aerolinea: " + aerolinea + ", " + "NumeroVuelo: " + NumeroVuelo + ", " + "Desde: " + desde + ", " + "Fechahorasalida: " + Fechahorasalida + 
           ", " + "Hasta: " + hasta + ", " + "Llegada: " + llegada + ", " + "Cantidad de Asientos: " + asientos;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getNumeroVuelo() {
        return NumeroVuelo;
    }

    public void setNumeroVuelo(String NumeroVuelo) {
        this.NumeroVuelo = NumeroVuelo;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getFechahorasalida() {
        return Fechahorasalida;
    }

    public void setFechahorasalida(String Fechahorasalida) {
        this.Fechahorasalida = Fechahorasalida;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    private String aerolinea;
    private String NumeroVuelo;
    private String desde;
    private String Fechahorasalida;
    private String hasta;
    private String llegada;
    private String asientos;
    private JButton[][] matrizbotones;
    private int a;
    private int d;

    public int getD() {
        return d;
    }
    
    public JButton[][] getMatrizbotones() {
        return matrizbotones;
    }

    public int getA() {
        return a;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    private String fechaCreacion;
    private String fechaModificacion;
    private int k=0;

    public void setMatrizbotones(JButton[][] matrizbotones) {
        this.matrizbotones = matrizbotones;
    }

    public int getK() {
        return k;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    
      class BotonPulsadoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            k++;
            String a1 = String.valueOf(k);
           // cajapasajeros.setText(a1);

            //System.out.println("A:" + a);
           // System.out.println("B:" + b);

            JButton bx = (JButton) ae.getSource();
            for (int i = 0; i < a + 1; i++) {
                for (int j = 0; j < 9; j++) {
                    if (matrizbotones[i][j] == bx) {
                        bx.setBackground(Color.RED);
                        bx.setEnabled(false);
                    }
                }
            }

            if (d != 0) {
                for (int q = 0; q < d; q++) {
                    if (matrizbotones[a][q] == bx) {
                        bx.setBackground(Color.RED);
                        bx.setEnabled(false);
                    }
                }
            }
        }

    }
}
