/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloproyecto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Cliente {

    public Cliente(String nombre, String cedula, String apellidos, String cantdePasajeros) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.cantdePasajeros = cantdePasajeros;
        listaVuelos = new ArrayList<Vuelo>();
    }
    
    public Cliente(){
        this.nombre = "";
        this.cedula = "";
        this.apellidos = "";
        this.cantdePasajeros = "";
    }

    @Override
    public String toString() {
        return "            Nombre: " + nombre + "\n" + "            Cedula: " + cedula + "\n" + "            Apellidos: " + apellidos + "\n"+  "            Pasajeros: " + cantdePasajeros + "\n\n\n"  ;
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public void setCantdePasajeros(String cantdePasajeros) {
        this.cantdePasajeros = cantdePasajeros;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public String getCantdePasajeros() {
        return cantdePasajeros;
    }
    
    public void AgregarVuelo(Vuelo a){
        listaVuelos.add(a);
        System.out.println(a.getNumeroVuelo());
    }
    
    public String getListaVuelosCliente(){
        return listaVuelos.toString();
    }

    private String nombre;
    private String cedula;
    private String apellidos;
    private String pasaporte;
    private String cantdePasajeros;
    
    
    
    private List<Vuelo> listaVuelos;

}
