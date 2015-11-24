/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta clase representa la identidad periodista
 * @author grupo_baloncesto
 */
public class Periodista extends Persona{
    
    /**
     * Construcor de la clase periodista
     * @param idPersona int identificador de periodista que deriva de persona
     * @param nombre String nombre del periodista
     * @param apellidos String apellidos del periodista
     * @param dni String dni
     * @param fechaN Date fecha de nacimiento
     * @param email String email 
     * @param contrasena String contraseña del periodista
     * @param tlf String telefono del periodista
     * @param rol int rol que identifica al periodista
     */

    public Periodista(int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
    }

    /**
     * 
     */
    public Periodista() {
    }
    
}
