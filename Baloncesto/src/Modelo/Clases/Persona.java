/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *Esta clase representa la identidad persona
 * @author grupo_baloncesto
 */
public class Persona {
    /**
     * Atributos de Persona
     */
    private String nombre;
    private String apellidos;
    private String dni;
    private Date fechaN;  
    private String tlf;
    private String email;
    private String contrasena;
    private int idPersona;
    private int rol;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

    /**
     * Constructor de Persona
     * @param idPersona int identificador de persona
     * @param nombre String nombre de la persona
     * @param apellidos String apellidos de la persona
     * @param dni String dni de persona
     * @param fechaN Date fecha de nacimiento
     * @param email String email de persona
     * @param contrasena String contraseña de persona
     * @param tlf String telefono
     * @param rol int rol, que identificará a la persona
     */
    public Persona(int idPersona, String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaN = fechaN;
        this.tlf = tlf;
        this.email = email;
        this.contrasena = contrasena;
        this.idPersona = idPersona;
        this.rol = rol;
    }
    
    /**
     * 
     */
    
    public Persona(){
        
    }
    /**
     * 
     * @return String nombre de la persona
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * definimos el parametro nombre de persona para que pueda ser usado por todos los métodos
     * @param nombre String nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @return String apellidos
     */

    public String getApellidos() {
        return apellidos;
    }
    /**
     * definimos el parametro apellidos de persona para que pueda ser usado por todos los métodos
     * @param apellidos String apellidos de la persona
     */

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    /**
     * 
     * @return String dni de la persona
     */

    public String getDni() {
        return dni;
    }
    /**
     * definimos el parametro dni de persona para que pueda ser usado por todos los métodos
     * @param dni String dni de la persona
     */

    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * 
     * @return Date fecha de nacimiento de la persona
     */

    public Date getFechaN() {
        return fechaN;
    }
    /**
     * definimos el parametro fecha de nacimiento de persona para que pueda ser usado por todos los métodos
     * @param fechaN Date fecha de nacimiento
     */

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }
    /**
     * 
     * @return String telefono
     */

    public String getTlf() {
        return tlf;
    }
    /**
     * 
     * @param tlf String telefono de la persona
     */

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    /**
     * 
     * @return String email
     */

    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email String email de la persona
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 
     * @return String contraseña
     */

    public String getContrasena() {
        return contrasena;
    }
    /**
     * 
     * @param contrasena String contraseña de la persona
     */

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    /**
     * 
     * @return int id, devuelve el id de la persona
     */

    public int getIdPersona() {
        return idPersona;
    }
    /**
     * 
     * @param idPersona int identificador de la persona
     */

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    /**
     * 
     * @return int rol
     */
    
    public int getRol() {
        return rol;
    }
    /**
     * 
     * @param rol inr rol de la persona
     */

    public void setRol(int rol) {
        this.rol = rol;
    }
    /**
     * 
     * @return Persona
     */
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fechaN=" + formatter.format(fechaN) + ", tlf=" + tlf + ", email=" + email + ", contrasena=" + contrasena + ", idPersona=" + idPersona + ", rol=" + rol + "}\n";
    }
    
    
    
}
