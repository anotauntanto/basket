/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import Modelo.Clases.Arbitro;
import Modelo.Clases.Entrenador;
import Modelo.Clases.Equipo;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.ArbitroDAO;
import Modelo.DAO.Conexion;
import Modelo.DAO.EntrenadorDAO;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.JugadorDAO;
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
        
        //Persona p = new Persona (0, "miguel", "Fernandez", "11111111A", fecha, "miguel@gmail.com", "migue", "999999999", 0);
        
        
       /* Equipo eq = new Equipo (1,"moscardo23","sevilla",1);
        EquipoDAO.insertarEquipo(eq);
        int id=EquipoDAO.obtenerIdEquipo(eq.getNombre());
        
        Jugador j= new Jugador (1.70, 15, id, 0, "Paloma", "Martin", "22222222A", fecha, "paloma@gmail.com", "palo", "88888888", 1);
        //PersonaDAO.insertarPersona(p);*/
        
        //System.out.println (PersonaDAO.obtenerTodasPersonas());
        //Arbitro a1 = new Arbitro ("Malaga",0, "Jesus", "Jaldo", "98989898H", fecha, "jesus@gmail.com", "jesu", "33333333", 3);
        //ArbitroDAO.insertarArbitro(a1);
        //int id=EquipoDAO.obtenerIdEquipo("moscardo22");
        //Jugador j= new Jugador (1.70, 15, id, 0, "Paloma", "Martin", "22222222A", fecha, "paloma@gmail.com", "palo", "88888888", 1);
        //JugadorDAO.insertarJugador(j);
        
        //System.out.println(ArbitroDAO.obtenerArbitroPorDni("98989898H"));
        //System.out.println(JugadorDAO.obtenerJugadorPorDni("22222222A"));
        //System.out.println(ArbitroDAO.obtenerTodosArbitros());
        //System.out.println(JugadorDAO.obtenerTodosJugadores());
        //System.out.println (PersonaDAO.obtenerTodasPersonas());
        int id=EquipoDAO.obtenerIdEquipo("moscardo22");
        Entrenador e = new Entrenador(id, 2, 0, "Anna", "Herrera", "76674642a", fecha, "ana@gmail.com", "an", "7777777", 2);
        //EntrenadorDAO.insertarEntrenador(e);
        //System.out.println(EntrenadorDAO.obtenerEntrenadorPorDni("76674642a"));
        EntrenadorDAO.modificarEntrenador(e);
        
//JugadorDAO.insertarJugador(j);
        
        
        
        
  
        
        
    }
    
}
