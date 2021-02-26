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
public class Usuario {
    
      public Usuario(boolean roldeUsuario, String usuario,String contrasena) {
        this.roldeUsuario = roldeUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario() {
        this.roldeUsuario = false;
        this.usuario = "";
        this.contrasena ="";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public void setRoldeUsuario(boolean roldeUsuario) {
        this.roldeUsuario = roldeUsuario;
    }

    public boolean getRoldeUsuario() {
        return roldeUsuario;
    }
    
    public String getContrasena() {
       return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    private boolean roldeUsuario;
    private String usuario;
    private String contrasena;

  
    //si el rol es 1 es administrador
    //si el rol es 0 es de plataforma

  
    
}
