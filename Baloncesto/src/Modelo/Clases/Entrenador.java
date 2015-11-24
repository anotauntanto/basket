/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta clase representa la entidad Entrenador
 * @author grupo_baloncesto
 */
public class Entrenador extends Persona{
    
    /**
     * Atributos nivel e idEquipo para la entidad Entrenador
     */
    private int nivel;
    private int idEquipo;
    
    /**
     * Constructor de la clase Entrenador con todos sus parámetros, incluidos los derivados de persona
     * @param idEquipo  int identificador del equipo
     * @param nivel int nivel del entrenador
     * @param idPersona int identificador del entrenador que deriva de persona
     * @param nombre String nombre del entrenador
     * @param apellidos String apellidos del entrenador
     * @param dni String dni
     * @param fechaN Date fecha de nacimiento
     * @param email String email del entrenador
     * @param contrasena String contraseña que identifica al entrenador
     * @param tlf String telefono del entrenador
     * @param rol int rol, que identificará al entrenador
     */    
    public Entrenador(int idEquipo ,int nivel, int idPersona, String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.nivel = nivel;
        this.idEquipo = idEquipo;
    }
    
    public Entrenador (Persona per, int nivel, int idEquipo) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.nivel= nivel;
        this.idEquipo = idEquipo;
    }
    public Entrenador (Persona per) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.nivel= 0;
        this.idEquipo = 0;
    }

    /**
     * Constructor de la clase entrenador
     * @param nivel int nivel del entrenador
     * @param idEquipo int identificador del equipo
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
     * @return int nivel, devuelve el nivel del entrenador
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * definimos el parametro nivel del entrenador para que pueda ser usado por todos los métodos
     * @param nivel int nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * 
     * @return int idEquipo, devuelve el id del equipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * definimos el parametro id del quipo para que pueda ser usado por todos los métodos
     * @param idEquipo int id del equipo
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    

    /**
     * 
     * @return la clase Entrenador
     */
    @Override
    public String toString() {
        return "Entrenador{" + "nivel=" + nivel + '}';
    }
    
    
}
