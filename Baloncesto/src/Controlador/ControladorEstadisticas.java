/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Estadisticas;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.EstadisticasDAO;
import Modelo.DAO.JugadorDAO;
import Vistas.VistaEstadisticas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inftel08
 */
public class ControladorEstadisticas {

    
    public ControladorEstadisticas(Jugador jugador, VistaEstadisticas vista) {
        
        List<Estadisticas> listaEstadisticas;
        listaEstadisticas = EstadisticasDAO.obtenerTodasEstadisticasJugador(jugador.getIdPersona());
        
        
        for (Estadisticas e : listaEstadisticas) {
            
            
            e.getAsistencias();
            e.getPuntos();
            e.getRebotes();
        }
        
       
    }
    
    
    
}
