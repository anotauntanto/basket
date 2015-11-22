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

    public Entrenador(int nivel, int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
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

    @Override
    public String toString() {
        return "Entrenador{" + "nivel=" + nivel + '}';
    }
    
    
}
