/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloproyecto;

/**
 *
 * @author Usuario
 */
public class Aerolineas {
    
    public Aerolineas() {
        nombre = "";
        fecha = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Aerolineas(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + "Fecha: " + fecha + "\n";
    }
    
    private String nombre;
    private String fecha;
}
