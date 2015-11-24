/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *
 * @author grupo_baloncesto, esta clase representa la entidad Jugador
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
     * 
     * @param per Contructor de la clase Jugador con sus atributos
     * @param altura
     * @param dorsal
     * @param idEquipo 
     */
    public Jugador(Persona per, double altura, int dorsal, int idEquipo) {
        super(per.getIdPersona(), per.getNombre(), per.getApellidos(), per.getDni(), per.getFechaN(), per.getEmail(), per.getContrasena(), per.getTlf(), per.getRol());
        this.altura = altura;
        this.dorsal = dorsal;
        this.idEquipo = idEquipo;
    }
        
    /**
     * 
     * @param altura Constructor de la clase Jugador con sus aributos y con los que deriva de Persona
     * @param dorsal
     * @param idEquipo
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
     * @return A partir de aqui tenemos los get and setter de todos los atributos
     */
    public double getAltura() {
        return altura;
    }

    /**
     * 
     * @param altura definimos el parametro altura de jugador para que pueda ser usado por todos los métodos
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * 
     * @return devuelve el dorsal
     */
    public int getDorsal() {
        return dorsal;
    }
    /**
     * 
     * @param dorsal definimos el parametro dorsal de jugador para que pueda ser usado por todos los métodos
     */

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    /**
     * 
     * @return devuelve el id del equipo al que pertenece el jugador
     */

    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * 
     * @param idEquipo definimos el parametro id del equipo de jugador para que pueda ser usado por todos los métodos
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
        return "Jugador{" + "altura=" + altura + ", dorsal=" + dorsal + "}\n";
    }
    
    
}
