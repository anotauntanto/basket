/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *
 * @author inftel06
 */
public class Organizacion extends Persona{

    public Organizacion(int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
    }

    public Organizacion() {
    }
    
    //Organiza la jornada siguiente
    public void organizarJornada(int numJornada){
        
    }
    //Guardo en la tabla de estadisticas
    public void setEstadisticas(int idJugador, int idPartido){
        
    }
    //Fija el resultado del partido dado y fijo estadisicas
    public void setResultado(int idPartido){
        
    }
    
    
}
