/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Equipo;
import Modelo.Clases.Estadisticas;
import Modelo.Clases.Jugador;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.Clases.Persona;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.EstadisticasDAO;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
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

        //llamadas a los DAO
        List<Estadisticas> listaEstadisticas;
        listaEstadisticas = EstadisticasDAO.obtenerTodasEstadisticasJugador(jugador.getIdPersona());

        List<PartidoJugado> listaPartidosJugados;
        listaPartidosJugados = PartidoJugadoDAO.listarPartidosJugadosEquipo(jugador.getIdEquipo());
        
        String nombre_equipo = EquipoDAO.obtenerNombreEquipo(jugador.getIdEquipo());

        //escritura y recuperacion en/de los campos de la vista
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
        DefaultTableModel modelo = (DefaultTableModel) tablita.getModel();
        
        for (Estadisticas e : listaEstadisticas) {
            for (PartidoJugado p : listaPartidosJugados) {
                Partido par = PartidoDAO.obtenerDatosPartido(p.getIdPartido());
                
                //if (par.getIdPartido() == e.getIdPartido()) {
                    modelo.addRow(new Object[]{par.getNumJornada(), "Rival", par.getFecha(), par.getResultado(), e.getPuntos(), e.getRebotes(), e.getAsistencias()});
                   
                //}

            }

        }

    }
}
