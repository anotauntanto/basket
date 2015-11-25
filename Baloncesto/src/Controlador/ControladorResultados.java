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
import Vistas.VistaOrganizacionOK;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inftel08
 */
public class ControladorResultados {

    private VistaOrganizacionOK miVista;
    private List<Partido> listarPartidosJornada;
    javax.swing.JTable campoTabla;
    DefaultTableModel modelo;

    public ControladorResultados(VistaOrganizacionOK miVista) {
        this.miVista = miVista;
        campoTabla = miVista.getTablaResultados();
        modelo = (DefaultTableModel) campoTabla.getModel();

        listarPartidosJornada = PartidoDAO.listarPartidosJornada(0); //compruebo si estoy en liga
        if (listarPartidosJornada.size() == 0) { //no hay liga

        } else { //hay liga

            for (Partido p : listarPartidosJornada) {
                List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
                
                int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                modelo.addRow(new Object[]{EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB), p.getResultado()});
            }

        }

    }

    public void recogerResultados() {

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String micad = (String) modelo.getValueAt(i, 2);
            String[] parts = micad.split("-");
            int resA = Integer.parseInt(parts[0]);
            int resB = Integer.parseInt(parts[1]);

            System.out.println("id " + listarPartidosJornada.get(i).getIdPartido() + "resultado " + resA + "-" + resB);
            
            List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(listarPartidosJornada.get(i).getIdPartido());
            
            /*listarEquiposporPartido.get(0).
            listarEquiposporPartido.get(1).*/
            listarPartidosJornada.get(i).setResultado(micad);
            PartidoDAO.modificarResultado(listarPartidosJornada.get(i));
            /*        
            int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
            int equipoB = listarEquiposporPartido.get(1).getIdEquipo();*/

        }

    }

}
