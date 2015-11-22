/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import Modelo.Clases.Persona;
import Modelo.DAO.Conexion;
import Modelo.DAO.PersonaDAO;
import java.sql.Connection;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author inftel06
 */
public class Baloncesto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1985,0,1);
        Date fecha = cal.getTime();
        
        Persona p = new Persona (0, "nombre", "apellidos", "dni", fecha, "email", "contrasena", "tlf", 0);
        PersonaDAO.insertarPersona(p);
        System.out.println (PersonaDAO.ObtenerTodasPersonas());
  
        
        
    }
    
}
