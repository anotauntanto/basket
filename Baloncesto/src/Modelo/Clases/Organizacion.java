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

    public Organizacion(String nombre, String apellidos, String dni, Date fechaN, String tlf, String email, String contrasena, int idPersona) {
        super(nombre, apellidos, dni, fechaN, tlf, email, contrasena, idPersona);
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
