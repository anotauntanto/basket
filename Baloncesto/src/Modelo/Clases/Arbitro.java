/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta clase representa la entidad Árbitro
 * @author grupo_baloncesto
 * 
 */
public class Arbitro extends Persona{
    
    /**
     * Atributo provincia para la entidad Arbitro
     */
    private String provincia;

    /**
     * Constructor de la clase árbitro
     * 
     * @param Provincia String provincia a la que pertenece el árbitro
     * @param idPersona int identidicador del árbitro que deriva de persona
     * @param nombre String nombre del árbitro
     * @param apellidos String apellidos del árbitro
     * @param dni String dni del árbitro
     * @param fechaN Date, fecha de nacimiento del árbitro
     * @param email String email del árbitro
     * @param contrasena String contraseña que identifica al árbitro
     * @param tlf String telefono
     * @param rol int rol, identifica al árbitro 
     */
    public Arbitro(String Provincia, int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.provincia = Provincia;
    }

    /**
     * Constructor de Arbitro con su provincia sólamente
     * @param Provincia String Provincia del árbitro
     */
    public Arbitro(String Provincia) {
        this.provincia = Provincia;
    }
    /**
     * Contructor de la clase árbitro
     * @param per persona
     * @param provincia String provincia a la que pertenece el árbitro
     */
    public Arbitro (Persona per, String provincia) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.provincia= provincia;
        
    }
    /**
     * Constructor de la clase árbitro 
     * @param per persona
     */
    public Arbitro (Persona per) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.provincia= "";
        
    }
    
    
    /**
     * 
     */
    public Arbitro(){
        
    }

    /**
     * 
     * @return provincia, devuelve la provincia 
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * definimos el parametro provincia del arbitro para que pueda ser usado por todos los métodos
     * @param Provincia String Provincia
     */
    public void setProvincia(String Provincia) {
        this.provincia = Provincia;
    }


    /**
     * 
     * @return clase Arbitro
     */
    @Override
    public String toString() {
        return "Arbitro{" + "Provincia=" + provincia + '}';
    }
    
    
}
