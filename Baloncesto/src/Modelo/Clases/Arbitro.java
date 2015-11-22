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
public class Arbitro extends Persona{
    private String Provincia;

    public Arbitro(String Provincia, int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.Provincia = Provincia;
    }

    public Arbitro(String Provincia, int numColegiado) {
        this.Provincia = Provincia;
    }
    
    public Arbitro(){
        
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }



    @Override
    public String toString() {
        return "Arbitro{" + "Provincia=" + Provincia + '}';
    }
    
    
}
