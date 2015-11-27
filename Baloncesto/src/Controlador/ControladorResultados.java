/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import Vistas.VistaOrganizacion;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Clase ControladorResultados
 * @author grupo_baloncesto
 */
public class ControladorResultados {

    private VistaOrganizacion miVista;
    private List<Partido> listarPartidosJornada;
    javax.swing.JTable campoTabla;
    DefaultTableModel modelo;
    
    /**
     * Constructor de la clase ControladorResultados
     * @param miVista 
     */
    public ControladorResultados(VistaOrganizacion miVista) {
        this.miVista = miVista;
        this.listarUltimaJornada();
        /*campoTabla = miVista.getTablaResultados();
        modelo = (DefaultTableModel) campoTabla.getModel();


        /*listarPartidosJornada = PartidoDAO.listarPartidosJornada(PartidoDAO.obtenerJornadaActual()); //compruebo si estoy en liga-->jornada 0 determina que se trata de una liga
        //if (listarPartidosJornada.size() == 0) { //no hay liga

        //} else { //hay liga

            for (Partido p : listarPartidosJornada) {
                List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
                
                int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                modelo.addRow(new Object[]{EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB), p.getResultado()});
            }

        //}*/

    }
    
    /**
     * Metodo para recogerResultados
     */
    public void recogerResultados() {

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String micad = (String) modelo.getValueAt(i, 2);
            String[] parts = micad.split("-");
            int resA = Integer.parseInt(parts[0]);
            int resB = Integer.parseInt(parts[1]);

            
            List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(listarPartidosJornada.get(i).getIdPartido());
            
            listarPartidosJornada.get(i).setResultado(micad);
            PartidoDAO.modificarResultado(listarPartidosJornada.get(i));

            if(resA>resB){//ha ganado equipoA
                listarEquiposporPartido.get(0).setHeGanado(1);
                listarEquiposporPartido.get(1).setHeGanado(0);
            } else {
                listarEquiposporPartido.get(0).setHeGanado(0);
                listarEquiposporPartido.get(1).setHeGanado(1);
            }
            
            PartidoJugadoDAO.modificarResultado(listarEquiposporPartido.get(0));
            PartidoJugadoDAO.modificarResultado(listarEquiposporPartido.get(1));
            
           
        }
        miVista.getjLabelExistoActualizarResultado().setVisible(true);
    }
    
    /**
     * Metodo para listar la Ultima Jornada
     */
    public void listarUltimaJornada () {
        campoTabla = miVista.getTablaResultados();
        modelo = (DefaultTableModel) campoTabla.getModel();
        
        while (modelo.getRowCount()>0){
            modelo.removeRow(0);
        } 
           
        
        listarPartidosJornada = PartidoDAO.listarPartidosJornada(PartidoDAO.obtenerJornadaActual()); //compruebo si estoy en liga-->jornada 0 determina que se trata de una liga

            for (Partido p : listarPartidosJornada) {
                List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
                
                int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                modelo.addRow(new Object[]{EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB), p.getResultado()});
            }

    }

}
