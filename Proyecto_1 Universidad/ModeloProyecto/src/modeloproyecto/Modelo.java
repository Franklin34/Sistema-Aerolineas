/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloproyecto;

import static java.nio.file.Files.delete;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Modelo extends Observable {

    public Modelo() {
        listaCliente = new ArrayList<Cliente>();

        listaUsuario = new ArrayList<Usuario>();

        listaAerolineas = new ArrayList<Aerolineas>();

        listaUsuario.add(new Usuario(true,"Jeniffer","12345678"));
        listaAerolineas.add(new Aerolineas("Avianca","2020-30-09"));
        listaUsuario.add(new Usuario(true, "Franklin", "ZXCvbn1218"));
        listaUsuario.add(new Usuario(false,"Sofia","12345678"));

        listaVuelos = new ArrayList<Vuelo>();
        
        
       

        vuelo = new Vuelo();
        
        tabla = new JTable();

        tabladefault = new DefaultTableModel();
        tabla = new JTable();
        tabladefault.addColumn("Aerolinea");
        tabladefault.addColumn("N de Vuelo");
        tabladefault.addColumn("Desde");
        tabladefault.addColumn("Fecha/Hora Salida");
        tabladefault.addColumn("Hasta");
        tabladefault.addColumn("Fecha/Hora Llegada");
        tabladefault.addColumn("Cantidad de asientos");
        tabla.setModel(tabladefault);
        
        names = new ArrayList<String>();

        
    }

    public boolean login(String usuario, String contrasena) {
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuario().equals(usuario) && listaUsuario.get(i).getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public boolean insertarUsuario(boolean rol, String usuario, String contrasena) {
        Usuario user = new Usuario(rol, usuario, contrasena);

        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuario().equals(usuario)) {
                return false;
            }
        }
        listaUsuario.add(user);
        return true;
    }

    public boolean insertarAerolinea(String nombre, String fecha) {
        Aerolineas aerolinea = new Aerolineas(nombre, fecha);

        for (int i = 0; i < listaAerolineas.size(); i++) {
            if (listaAerolineas.get(i).getNombre().equals(nombre)) {
                return false;
            }
        }
        listaAerolineas.add(aerolinea);
        return true;

    }

    public void activarBoton2(JTextField usuario, JTextField contrasena, JTextField verificar, JButton boton) {
        String contr;
        contr = contrasena.getText();

        String vcontr;
        vcontr = verificar.getText();

        if (usuario.getText().equals("") || contrasena.getText().equals("") || verificar.getText().equals("") || contr.length() < 8 || !contr.equals(vcontr)) {
            boton.setEnabled(false);

        } else {
            boton.setEnabled(true);

        }
    }

    @SuppressWarnings("StringEquality")
    public void activarBoton(JTextField usuario, JTextField contrasena, JButton boton) {
        if (usuario.getText().equals("") || contrasena.getText().equals("")) {

            boton.setEnabled(false);
        } else {

            boton.setEnabled(true);
        }
    }

    public boolean roldeusuario(JTextField usuario, JTextField contrasena) {
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuario().equals(usuario.getText())) {
                return listaUsuario.get(i).getRoldeUsuario();
            }
        }
        return false;
    }

    public int tamanolista() {
        return listaAerolineas.size();
    }

    public int tamanoVuelos() {
        return listaVuelos.size();
    }

    public String retornarNombreAerolinea(int i) {

        if (listaAerolineas.isEmpty() == true) {
            return "vacia";
        }
        if (i < listaAerolineas.size()) {
            return listaAerolineas.get(i).getNombre();
        }

        return "";

    }
    
    

    public String retornarNumerodeVuelo(int i) {
        if (listaVuelos.isEmpty() == true) {
            return "vacia";
        }
        if (i < listaVuelos.size()) {
            return listaVuelos.get(i).getNumeroVuelo();
        }

        return "";
    }

    public void ingregarAerolinea(DefaultListModel list) {

        if (listaAerolineas.isEmpty() == true) {
            return;
        }

        for (int i = 0; i < listaAerolineas.size(); i++) {
            list.addElement(listaAerolineas.get(i).getNombre());
        }
    }

    public boolean modificarAero(String aero, String nuevaaero, String nuevafecha) {

        for (int i = 0; i < listaAerolineas.size(); i++) {
            if (listaAerolineas.get(i).getNombre().equals(nuevaaero) == true) {
                return false;
            }
        }

        for (int i = 0; i < listaAerolineas.size(); i++) {
            if (listaAerolineas.get(i).getNombre().equals(aero)) {
                listaAerolineas.get(i).setNombre(nuevaaero);
                listaAerolineas.get(i).setFecha(nuevafecha);

                return true;
            }
        }
        return false;
    }

    public String imprimirAerolineas() {
        return listaAerolineas.toString();
    }

    public String retornarAerolinea(String num) {

        if (!listaVuelos.isEmpty()) {
            for (int i = 0; i < listaVuelos.size(); i++) {
                if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                    return listaVuelos.get(i).getAerolinea();
                }
            }
        }

        return "";
    }

    public String retornarDesde(String num) {
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                return listaVuelos.get(i).getDesde();
            }
        }
        return "";
    }

    public String retornarSalida(String num) {
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                return listaVuelos.get(i).getFechahorasalida();
            }
        }
        return "";
    }

    public String retornarHasta(String num) {
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                return listaVuelos.get(i).getHasta();
            }
        }
        return "";
    }

    public String retornarllegada(String num) {
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                return listaVuelos.get(i).getLlegada();
            }
        }
        return "";
    }

    public String retornarAsientos(String num) {
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (num.equals(listaVuelos.get(i).getNumeroVuelo())) {
                return listaVuelos.get(i).getAsientos();
            }
        }
        return "";
    }

    public boolean agregarVuelo(Vuelo a) {

        for (int i = 0; i < listaAerolineas.size(); i++) {
            if (a.getAerolinea().equals(listaAerolineas.get(i).getNombre()) == true) {
                 if (!listaVuelos.isEmpty()) {
                for (int j = 0; j < listaVuelos.size(); j++) {                   
                        if (!a.getNumeroVuelo().equals(listaVuelos.get(j).getNumeroVuelo())) {
                            vuelo = a;
                            setChanged();
                            notifyObservers(a);
                  
                            listaVuelos.add(a);
                            return true;
                        }
                        else if(a.getNumeroVuelo().equals(listaVuelos.get(j).getNumeroVuelo())){
                            return false;
                        }
                        else{
                            return false;
                        }
                    }
                }
                if (listaVuelos.isEmpty()) {
                    vuelo = a;
                    setChanged();
                    notifyObservers(a);
                    listaVuelos.add(a);
                    return true;
                }

            }
        }
        return false;
    }

    public Vuelo retornarVuelo() {
        return vuelo;
    }
    
    
    public boolean agregarCliente(Cliente cliente){
        for(int i =0;i<listaCliente.size();i++){
            if(cliente.getCedula().equals(listaCliente.get(i).getCedula())==true){
               return false;
            }
        }
        listaCliente.add(cliente);
        System.out.println(listaCliente.toString());
        return true;
    }
    
    public int getA(String vuelo){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                return listaVuelos.get(i).getA();
            }
        }
        return 0;
    }
     public int getK(String vuelo){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                return listaVuelos.get(i).getK();
            }
        }
        return 0;
    }
    public int getD(String vuelo){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                return listaVuelos.get(i).getD();
            }
        }
        return 0;
    }
    
    public JButton[][] getMatriz(String vuelo){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                return listaVuelos.get(i).getMatrizbotones();
            }
        }
        return null;
    }
    
    public void setAsientos(String vuelo,String asientosmodi){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                listaVuelos.get(i).setAsientos(asientosmodi);
                System.out.println(listaVuelos.get(i).getAsientos());
            }
        }
    }
    
    public void setMatriz(String vuelo,JButton[][] matriz){
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(vuelo)){
                listaVuelos.get(i).setMatrizbotones(matriz);
            }
        }
    }
    
    public void cambiarVuelo(Vuelo a,String modificacion){
        for(int i =0;i<listaVuelos.size();i++){
            if(a.getNumeroVuelo().equals(listaVuelos.get(i).getNumeroVuelo())){
                listaVuelos.get(i).setAsientos(a.getAsientos());
                listaVuelos.get(i).setDesde(a.getDesde());
                listaVuelos.get(i).setFechahorasalida(a.getFechahorasalida());
                listaVuelos.get(i).setHasta(a.getHasta());
                listaVuelos.get(i).setLlegada(a.getLlegada());
                
                listaVuelos.get(i).setFechaModificacion(modificacion);
                
            }
        }
    }
    
    public String getFechacreacion(String numvuelo){
        
        for(int i =0;i<listaVuelos.size();i++){
            if(listaVuelos.get(i).getNumeroVuelo().equals(numvuelo)){
                System.out.println("aqqud");
                return listaVuelos.get(i).getFechaCreacion();
            }
        }
        System.out.println("qui");
        return "";
        
    }
    
    public void eliminarVuelo(Vuelo a){
        for(int i =0;i<listaVuelos.size();i++){
            if(a.getNumeroVuelo().equals(listaVuelos.get(i).getNumeroVuelo())){
                listaVuelos.remove(i);
            }
        }
        System.out.println(listaVuelos.toString());
    }
    
    public void setName(String name){
        names.add(name);
    }
    
    public String retornartoString(String cedula){
        for(int i=0;i<listaCliente.size();i++){
            if(listaCliente.get(i).getCedula().equals(cedula)){
                return listaCliente.get(i).toString();
            }
        }
        return "";
    }
    public String retornarVuelosdelCliente(String cedula){
         for(int i=0;i<listaCliente.size();i++){
            if(listaCliente.get(i).getCedula().equals(cedula)){
                return listaCliente.get(i).getListaVuelosCliente();
            }
        }
        return "";
    }
    
    public String getName(int i){
        return names.get(i);
    }
    public int sizeName(){
        return names.size();
    }

    public Vuelo retornarVuelos(int i) {
        return listaVuelos.get(i);
    }

    private List<Cliente> listaCliente;
    private List<Usuario> listaUsuario;
    private List<Aerolineas> listaAerolineas;
    private List<Vuelo> listaVuelos;
    private DefaultTableModel tabladefault;
    private Vuelo vuelo;
    private JTable tabla;
    private List<String> names;
    private String hola;

    public String getHola() {
        return hola;
    }

    public void setHola(String hola) {
        this.hola = hola;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
