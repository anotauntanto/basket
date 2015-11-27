/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Equipo;
import Modelo.Clases.FicherosTipo;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import Vistas.VistaOrganizacionOK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inftel08
 */
public class ControladorClasificacion {

    private VistaOrganizacionOK miVista;
    javax.swing.JTable campoTabla;
    DefaultTableModel modelo;
    private JTable campoTabla1;
    private DefaultTableModel modelito;

    public ControladorClasificacion(VistaOrganizacionOK miVista) {
        this.miVista = miVista;
        listarClasificacion();
    }

    public void listarClasificacion() {

        String tipo_competicion = FicherosTipo.leerFichero();

        if (tipo_competicion.equals("Copa")) {

            miVista.getjScrollCopa().setVisible(true);
            miVista.getjScrollLiga().setVisible(false);
            campoTabla = miVista.getTablaClasificacionCopa();
            modelo = (DefaultTableModel) campoTabla.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            int jornada_maxima = PartidoDAO.obtenerJornadaActual();

            for (int i = jornada_maxima; i > 0; i--) {
                List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(i);
                for (Partido p : listarPartidosJornada) {

                    List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());

                    int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                    int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                    modelo.addRow(new Object[]{i, EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB), p.getResultado()});

                }
            }

        } else if (tipo_competicion.equals("Liga")) {

            miVista.getjScrollLiga().setVisible(true);
            miVista.getjScrollCopa().setVisible(false);
            
            campoTabla = miVista.getTablaClasificacionLiga();
            modelo = (DefaultTableModel) campoTabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(0);
            List<Equipo> obtenerTodosEquipos = EquipoDAO.obtenerTodosEquipos();
            Collections.sort(obtenerTodosEquipos);
            System.out.println(obtenerTodosEquipos);
            for (Equipo p : obtenerTodosEquipos) {
                modelo.addRow(new Object[]{p.getNombre()});

            }

        } else {
            /*
            miVista.getjScrollLiga().setVisible(true);
            miVista.getjScrollCopa().setVisible(true);

            campoTabla1 = miVista.getTablaClasificacionCopa();
            modelito = (DefaultTableModel) campoTabla1.getModel();
            while (modelito.getRowCount() > 0) {
                modelito.removeRow(0);
            }

            campoTabla = miVista.getTablaClasificacionLiga();
            modelo = (DefaultTableModel) campoTabla.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            
            //pintar liga
            List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(0);
            List<Equipo> obtenerTodosEquipos = new ArrayList<>();
            
            for (Partido p : listarPartidosJornada) {
                /*for (Equipo obtenerTodosEquipos) {
                }
                List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
                listarEquiposporPartido.get(0).getIdEquipo();
                listarEquiposporPartido.get(1).getIdEquipo();
                
                obtenerTodosEquipos.add(null);
                
                
            }
            Collections.sort(obtenerTodosEquipos);
            System.out.println(obtenerTodosEquipos);
            for (Equipo p : obtenerTodosEquipos) {
                modelo.addRow(new Object[]{p.getNombre()});

            }*/
            

        }

    }

}
