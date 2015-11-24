/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 * Esta clase representa la entidad Organización
 * @author grupo_baloncesto
 */
public class Organizacion extends Persona{
    /**
     * contructor de la clase Organización con los atributos que deriva de la clase persona
     * @param idPersona int identificador de persona
     * @param nombre String nombre
     * @param apellidos String apellidos
     * @param dni String dni
     * @param fechaN Date fecha de nacimiento
     * @param email String email de la organización
     * @param contrasena String contraseña de la organización
     * @param tlf String telefono
     * @param rol int rol, identificador de la clase organización
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
     * @param numJornada int numero de jornada
     */
    
    //Organiza la jornada siguiente
    public void organizarJornada(int numJornada){
        
    }
    /**
     * 
     * @param idJugador int identificador de jugador
     * @param idPartido int identificador de partido
     */
    
    //Guardo en la tabla de estadisticas
    public void setEstadisticas(int idJugador, int idPartido){
        
    }
    /**
     * 
     * @param idPartido int identificador de partido
     */
    //Fija el resultado del partido dado y fijo estadisicas
    public void setResultado(int idPartido){
        
    }
    
    
}
