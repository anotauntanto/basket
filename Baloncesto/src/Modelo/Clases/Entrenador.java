/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta clase representa la entidad Entrenador
 * @author grupo baloncesto, esta clase representa la entidad Entrenador
 */
public class Entrenador extends Persona{
    
    /**
     * Atributos nivel e idEquipo para la entidad Entrenador
     */
    private int nivel;
    private int idEquipo;
    
    /**
     * 
     * @param idEquipo Constructor de la clase Entrenador con todos sus parámetros, incluidos los derivados de persona
     * @param nivel
     * @param idPersona
     * @param nombre
     * @param apellidos
     * @param dni
     * @param fechaN
     * @param email
     * @param contrasena
     * @param tlf
     * @param rol 
     */

    public Entrenador(int idEquipo ,int nivel, int idPersona, String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.nivel = nivel;
        this.idEquipo = idEquipo;
    }

    /**
     * 
     * @param nivel Constructor de la clase entrenador con los atributos nivel e idEquipo
     * @param idEquipo 
     */
    public Entrenador(int nivel, int idEquipo) {
        this.nivel = nivel;
        this.idEquipo = idEquipo;
    }
    
    /**
     * 
     */
    public Entrenador(){
        
    }
    
    /**
     * 
     * @return devuelve el nivel del entrenador
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * 
     * @param nivel definimos el parametro nivel del entrenador para que pueda ser usado por todos los métodos
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * 
     * @return devuelve el id del equipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * 
     * @param idEquipo definimos el parametro id del quipo para que pueda ser usado por todos los métodos
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Entrenador{" + "nivel=" + nivel + '}';
    }
    
    
}
