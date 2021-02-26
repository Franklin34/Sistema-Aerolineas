/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import modeloproyecto.Cliente;
import modeloproyecto.Modelo;
import modeloproyecto.Vuelo;

/**
 *
 * @author Usuario
 */
public class ControlAplicacion {
     public ControlAplicacion(Modelo nuevoModelo) {
        datos = nuevoModelo;
    }
    
    public ControlAplicacion() {
        this(new Modelo());
    }
    
    public boolean ingresarCliente(Cliente a){
        return datos.agregarCliente(a);
    }
    public void registrar(Observer nuevoObservador) {
        datos.addObserver(nuevoObservador);
    }

    public boolean login(String usuario,String contrasena) {
        System.out.println("Se delega el login al modelo..");
        return  datos.login(usuario,contrasena);
    }
    public void activarboton(JTextField usuario,JTextField contrasena,JButton boton){
        datos.activarBoton(usuario,contrasena,boton);
    }
    
    public boolean insertarUsuario(boolean rol,String usuario,String contrasena){
        return datos.insertarUsuario(rol, usuario, contrasena);
    }
     public void activarBoton2(JTextField usuario,JTextField contrasena,JTextField verificar,JButton boton){
        datos.activarBoton2(usuario, contrasena, verificar, boton);
    }
     
     public int geta(String vuelo){
         return datos.getA(vuelo);
     }
     public String retornarTostring(String cedula){
         return datos.retornartoString(cedula);
     }
     public String retornarVuelosCliente(String cedula){
         return datos.retornarVuelosdelCliente(cedula);
     }
     
     public void setMatriz(String vuelo,JButton[][] matriz){
         datos.setMatriz(vuelo, matriz);
     }
     
      public int getk(String vuelo){
         return datos.getK(vuelo);
     }
     public int getd(String vuelo){
         return datos.getD(vuelo);
     }
     
     public void setAsientos(String vuelo,String asientosmodi){
         datos.setAsientos(vuelo, asientosmodi);
     }
     
     public JButton[][] getMatriz(String vuelo){
         return datos.getMatriz(vuelo);
     }
     
     public boolean roldeusuario(JTextField usuario,JTextField contrasena){
         return datos.roldeusuario(usuario, contrasena);
     }
     
     public boolean insertarAerolinea(String nombre, String fecha){
         return datos.insertarAerolinea(nombre, fecha);
     }
    
     
     public String imprimirAerolineas(){
         return datos.imprimirAerolineas();
    }
     
     
    public int tamanolista(){
        return datos.tamanolista();
    }
    public int tamanoVuelos(){
        return datos.tamanoVuelos();
    }
    
    public void ingresarAerolinea(DefaultListModel list){
        datos.ingregarAerolinea(list);
    }
    
     public String retornarNombreAerolinea(int i){
          return datos.retornarNombreAerolinea(i);
     }
     public String retornarNumeroVuelo(int i){
         return datos.retornarNumerodeVuelo(i);
     }
     
     public void setHola(String hola){
         datos.setHola(hola);
     }
     
     public String getHola(){
         return datos.getHola();
     }
     
     public boolean modificaraero(String aero,String nuevaaero,String nuevafecha){
         return datos.modificarAero(aero, nuevaaero, nuevafecha);
     }
     
     public boolean agregarVuelo(Vuelo a){
         return datos.agregarVuelo(a);
     }
     
      public String retornarAerolinea(String num){
        return datos.retornarAerolinea(num);
    }
    public String retornarDesde(String num){
        return datos.retornarDesde(num);
    }
     public String retornarSalida(String num){
        return datos.retornarSalida(num);
    }
      public String retornarHasta(String num){
        return datos.retornarHasta(num);
    }
       public String retornarllegada(String num){
        return datos.retornarllegada(num);
    }
     public String retornarAsientos(String num){
        return datos.retornarAsientos(num);
    }
     public Vuelo retornarVuelos(int i){
        return datos.retornarVuelos(i);
    }
     
     public void cambiarVuelo(Vuelo a,String modificacion){
         datos.cambiarVuelo(a,modificacion);
     }
     public void eliminarVuelo(Vuelo a){
         datos.eliminarVuelo(a);
     }
     public String getCreacion(String creacion){
         return datos.getFechacreacion(creacion);
     }
     
     public JTable retornarTabla(){
         return datos.getTabla();
     }
       
     public void setTabla(JTable table){
         datos.setTabla(table);
     }
     
     public void setName(String name){
         datos.setName(name);
     }
     public String getName(int i){
         return datos.getName(i);
     }
     
     public int sizeName(){
         return datos.sizeName();
     }
    private Modelo datos;
}
