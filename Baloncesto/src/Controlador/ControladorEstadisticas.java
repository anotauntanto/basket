/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Equipo;
import Modelo.Clases.Estadisticas;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.EstadisticasDAO;
import Modelo.DAO.JugadorDAO;
import Vistas.VistaEstadisticas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author inftel08
 */
public class ControladorEstadisticas {

    public ControladorEstadisticas(Jugador jugador, VistaEstadisticas vista) {

        List<Estadisticas> listaEstadisticas;
        listaEstadisticas = EstadisticasDAO.obtenerTodasEstadisticasJugador(jugador.getIdPersona());
        String nombre_equipo = EquipoDAO.obtenerNombreEquipo(jugador.getIdEquipo());

        javax.swing.JTextField campo = vista.getCampoNombre();
        campo.setText(jugador.getNombre());
        campo = vista.getCampoApellidos();
        campo.setText(jugador.getApellidos());
        campo = vista.getCampoEquipo();
        campo.setText(nombre_equipo);
        campo = vista.getCampoDorsal();
        Integer int_ = jugador.getDorsal();
        campo.setText(int_.toString());

        
        javax.swing.JTable tablita = vista.getTabla();
        DefaultTableModel modelo =(DefaultTableModel) tablita.getModel();
        
        for (Estadisticas e : listaEstadisticas) {

            //TableColumn col = tablita.getColumn(1);
            
            modelo.addRow(new Object[]{jugador.getDorsal(), "Column 2", "Column 3", "", "", "", ""});
            
            e.getAsistencias();
            e.getPuntos();
            e.getRebotes();
        }

    }

}
