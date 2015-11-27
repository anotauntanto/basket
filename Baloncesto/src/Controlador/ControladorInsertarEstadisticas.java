/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Estadisticas;
import Modelo.Clases.Jugador;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.EstadisticasDAO;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import Vistas.VistaOrganizacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inftel08
 */
public class ControladorInsertarEstadisticas {

    private VistaOrganizacion miVista;
    private Vector<String> nombre_equipos;
    private Vector<String> nombre_jugadores;
    private int id_partido;
    private int id_jugador;
    private int id_equipo;
    private DefaultTableModel modelo;

    public ControladorInsertarEstadisticas(VistaOrganizacion miVista) {
        this.miVista = miVista;
        //this.GenerarListaEquipos();
    }

    public void GenerarListaEquipos() {
        nombre_equipos = new Vector<>();
        List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(PartidoDAO.obtenerJornadaActual());
        for (Partido p : listarPartidosJornada) {
            List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
            int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
            int equipoB = listarEquiposporPartido.get(1).getIdEquipo();
            nombre_equipos.add(EquipoDAO.obtenerNombreEquipo(equipoA) + " contra " + EquipoDAO.obtenerNombreEquipo(equipoB) + " " + listarEquiposporPartido.get(0).getIdPartido());
            nombre_equipos.add(EquipoDAO.obtenerNombreEquipo(equipoB) + " contra " + EquipoDAO.obtenerNombreEquipo(equipoA) + " " + listarEquiposporPartido.get(1).getIdPartido());
        }

        DefaultComboBoxModel model = new DefaultComboBoxModel(nombre_equipos);
        miVista.getComboEquipos().setModel(model);

    }

    public void recogerEquipo() {
        
        nombre_equipos = new Vector<>();
        String contrincantes = miVista.getComboEquipos().getSelectedItem().toString();
        String[] parts = contrincantes.split(" ");

        this.id_partido = Integer.parseInt(parts[3]);
        this.id_equipo = EquipoDAO.obtenerIdEquipo(parts[0]);

        List<Jugador> obtenerTodosJugadoresEquipo = JugadorDAO.obtenerTodosJugadoresEquipo(this.id_equipo);
        List<String> nombre_jugadores_string = new ArrayList<>();

        for (Jugador j : obtenerTodosJugadoresEquipo) {
            nombre_jugadores_string.add(Integer.toString(j.getDorsal()));
        }
        nombre_jugadores = new Vector(nombre_jugadores_string);
        DefaultComboBoxModel model = new DefaultComboBoxModel(nombre_jugadores);
        miVista.getComboJugador().setModel(model);

    }

    public void recogerJugador() {
        int num_dorsal = Integer.parseInt(miVista.getComboJugador().getSelectedItem().toString());
        //System.out.println(nu);
        //System.out.println(num_dorsal);
        Jugador jug = JugadorDAO.obtenerJugadorPorDorsal(num_dorsal, id_equipo);
        this.id_jugador = jug.getIdPersona();
        Estadisticas es = EstadisticasDAO.obtenerTodasEstadisticasJugadorPartido(jug.getIdPersona(), id_partido);

        modelo = (DefaultTableModel) miVista.getTablaInsertarEstadisticas().getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        if (es != null) {
            modelo.addRow(new Object[]{es.getPuntos(), es.getRebotes(), es.getAsistencias()});
        } else {
            modelo.addRow(new Object[]{"", "", ""});
        }

    }

    public void recogerEstadisticas() {
        Estadisticas es = new Estadisticas();

        es.setIdJugador(id_jugador);
        es.setIdPartido(id_partido);
        es.setPuntos(Integer.parseInt((String) modelo.getValueAt(0, 0)));
        es.setRebotes(Integer.parseInt((String) modelo.getValueAt(0, 1)));
        es.setAsistencias(Integer.parseInt((String) modelo.getValueAt(0, 2)));

        EstadisticasDAO.insertarEstadisticasJugador(es);
        miVista.getCampoEstadisticasInsertadasConExisto().setVisible(true);
    }
}
