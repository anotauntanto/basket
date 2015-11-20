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
public class Entrenador extends Persona{
    private int nivel;

    public Entrenador(int nivel, String nombre, String apellidos, String dni, Date fechaN, String tlf, String email, String contrasena, int idPersona) {
        super(nombre, apellidos, dni, fechaN, tlf, email, contrasena, idPersona);
        this.nivel = nivel;
    }

    public Entrenador(int nivel) {
        this.nivel = nivel;
    }
    
    public Entrenador(){
        
    }
    
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
