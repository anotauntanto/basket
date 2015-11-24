/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *,Esta clase representa la entidad Jugador
 * @author grupo_baloncesto
 */
public class Jugador extends Persona{
    
    /**
     * Atributos de la clase Jugador
     */
    private double altura;
    private int dorsal;
    private int idEquipo;

    /**
     * 
     */
    public Jugador() {
    }

    /**
     * Contructor de la clase Jugador con sus atributos
     * @param per 
     * @param altura double altura del jugador
     * @param dorsal int dorsal del jugador
     * @param idEquipo int identificador del quipo
     */
    public Jugador(Persona per, double altura, int dorsal, int idEquipo) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.altura = altura;
        this.dorsal = dorsal;
        this.idEquipo = idEquipo;
    }
        
    /**
     * Constructor de la clase Jugador con sus aributos y con los que deriva de Persona
     * @param altura double altura del jugador
     * @param dorsal int dorsal del jugador
     * @param idEquipo int identificador del equipo
     * @param idPersona int identificador del jugador derivado de persona
     * @param nombre String nombre del jugador
     * @param apellidos String apellidos del jugador
     * @param dni String dni del jugador
     * @param fechaN Date fecha de nacimiento
     * @param email String email del jugador
     * @param contrasena String contraseña del jugador
     * @param tlf String telefono del jugador
     * @param rol int rol identificador del jugador
     */
    public Jugador(double altura, int dorsal, int idEquipo,int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        //super(nombre, apellidos, dni, fechaN, tlf, email, contrasena, idPersona);
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.altura = altura;
        this.dorsal = dorsal;
        this.idEquipo = idEquipo;
    }

    /*public Jugador(double altura, int dorsal, int idEquipo) {
        this.altura = altura;
        this.dorsal = dorsal;
        this.idEquipo = idEquipo;
    }
    
    public Jugador(){
        
    }*/

    /**
     * 
     * @return double altura del jugador
     */
    public double getAltura() {
        return altura;
    }

    /**
     * definimos el parametro altura de jugador para que pueda ser usado por todos los métodos
     * @param altura double altura del jugador
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * 
     * @return int dorsal
     */
    public int getDorsal() {
        return dorsal;
    }
    /**
     * definimos el parametro dorsal de jugador para que pueda ser usado por todos los métodos
     * @param dorsal int dorsal del jugador
     */

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    /**
     * 
     * @return int id, devuelve el id del equipo al que pertenece el jugador
     */

    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * definimos el parametro id del equipo de jugador para que pueda ser usado por todos los métodos
     * @param idEquipo int identificador del equipo
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    /**
     * 
     * @return Jugador
     */
    
    @Override
    public String toString() {
        return "Jugador{" + "altura=" + altura + ", dorsal=" + dorsal + "}\n";
    }
    
    
}
