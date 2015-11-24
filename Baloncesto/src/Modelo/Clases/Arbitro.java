/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta es la clase representa una entidad árbitro
 * @author grupo_baloncesto
 * 
 */
public class Arbitro extends Persona{
    
    /**
     * Atributo provincia para la entidad Arbitro
     */
    private String Provincia;

    /**
     * Constructor de la clase árbitro
     * @param Provincia
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
    public Arbitro(String Provincia, int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.Provincia = Provincia;
    }

    /**
     * 
     * @param Provincia
     * @param numColegiado 
     */
    public Arbitro(String Provincia, int numColegiado) {
        this.Provincia = Provincia;
    }
    
    /**
     * 
     */
    public Arbitro(){
        
    }

    /**
     * 
     * @return 
     */
    public String getProvincia() {
        return Provincia;
    }

    /**
     * 
     * @param Provincia 
     */
    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }


    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Arbitro{" + "Provincia=" + Provincia + '}';
    }
    
    
}
