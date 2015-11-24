/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *
 * @author grupo_baloncesto, esta clase representa la entidad Organización
 */
public class Organizacion extends Persona{
    /**
     * 
     * @param idPersona contructor de la clase Organización con los atributos que deriva de la clase persona
     * @param nombre
     * @param apellidos
     * @param dni
     * @param fechaN
     * @param email
     * @param contrasena
     * @param tlf
     * @param rol 
     */

    public Organizacion(int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
    }

    
    /**
     * 
     */
    public Organizacion() {
    }
    /**
     * 
     * @param numJornada 
     */
    
    //Organiza la jornada siguiente
    public void organizarJornada(int numJornada){
        
    }
    /**
     * 
     * @param idJugador
     * @param idPartido 
     */
    
    //Guardo en la tabla de estadisticas
    public void setEstadisticas(int idJugador, int idPartido){
        
    }
    /**
     * 
     * @param idPartido 
     */
    //Fija el resultado del partido dado y fijo estadisicas
    public void setResultado(int idPartido){
        
    }
    
    
}
