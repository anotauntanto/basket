/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import baloncesto.FicherosTipo;
import baloncesto.Generar;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import static Modelo.DAO.PartidoDAO.listarPartidosJornada;
import Modelo.DAO.PartidoJugadoDAO;
import static Modelo.DAO.PartidoJugadoDAO.listarEquiposporPartido;
import Vistas.VistaOrganizacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *Clase ControladorCampeonato
 * @author grupo_baloncesto
 */
public class ControladorCampeonato {
    /**
     * Atributos de la clase ControladorCampeonato
     */

    private VistaOrganizacion miVista;
    private Vector<String> num_equipos;
    private javax.swing.JRadioButton tipoLiga;
    private javax.swing.JRadioButton tipoMixto;
    private javax.swing.JRadioButton tipoCopa;
    private int tipoCompeticion;
    private javax.swing.ButtonGroup grupo_botones;
    private javax.swing.JComboBox numPlayoff;

    
    /**
     * Constructor GenerarLista para generar una lista con los equipos para poder generar el campeonato
     */
    public void GenerarLista() {

        //contar equipos de la bbdd
        int contador_equipos = EquipoDAO.contarEquipos();
        num_equipos = new Vector<>();

        int exp = 1;
        double valor = Math.pow(2, exp);

        while (valor < contador_equipos) {
            num_equipos.add(Integer.toString((int) valor));
            exp++;
            valor = Math.pow(2, exp);
        }

        //System.out.println(num_equipos);
        //generar array
    }
    
    /**
     * Método ControladorCampeonato para escribir en una tabla los equipos obtenidos anteriormente
     * @param miVista Vista organizacion
     */

    public ControladorCampeonato(VistaOrganizacion miVista) {
        this.miVista = miVista;
        this.GenerarLista();
        DefaultComboBoxModel model = new DefaultComboBoxModel(num_equipos);
        miVista.getCampoListaNumeros().setModel(model);

    }

    /**
     * Método tipoCampeonato que me devuelve el tipo de campeonato que quiero generar y lo genera
     * @param miVista VistaOrganizacion
     * @return int que indica el tipo de campeonato que queremos generar
     * @throws IOException se lanza si
     */
    public int tipoCampeonato(VistaOrganizacion miVista) throws IOException {
        javax.swing.JTable miTabla = miVista.getTablaGenerar();
        Generar miGen = new Generar();
        int miJornada = 0; //Valor para ver la primera jornada generada

        tipoLiga = miVista.getCampoLiga();
        tipoCopa = miVista.getCampoCopa();
        tipoMixto = miVista.getCampoMixto();

        grupo_botones = miVista.getGrupoBotones();
        grupo_botones.add(tipoLiga);
        grupo_botones.add(tipoCopa);
        grupo_botones.add(tipoMixto);

        int campeonatoVacio = PartidoDAO.obtenerIdActual();
        if (campeonatoVacio != 0) {
            miVista.getCampoErrorGenerar().setVisible(true);
            
            return -1;
        }
        if (tipoLiga.isSelected()) {
            miGen.generarLiga();
            tipoCompeticion = 1;
            FicherosTipo.escribirFichero(1, 0);

        } else if (tipoCopa.isSelected()) {
            miGen.generarSigJornada();
            tipoCompeticion = 2;
            FicherosTipo.escribirFichero(2, 0);
            miJornada = 1;
        } else {
            numPlayoff = miVista.getCampoListaNumeros();
            int numEquipo = Integer.parseInt((String) numPlayoff.getSelectedItem());
            tipoCompeticion = 3;
            miGen.generarLiga();
            FicherosTipo.escribirFichero(3, numEquipo);
            //Que hago si tengo Mixto
        }
        DefaultTableModel modelo = (DefaultTableModel) miTabla.getModel();

        List<Partido> listaPartidos;
        listaPartidos = PartidoDAO.listarPartidosJornada(miJornada);

        
        String nombreJ = "Liga";
        //String nombreB;

        for (Partido e : listaPartidos) {
            List<PartidoJugado> listaPJ = PartidoJugadoDAO.listarEquiposporPartido(e.getIdPartido());
            int equipoA = listaPJ.get(0).getIdEquipo();
            int equipoB = listaPJ.get(1).getIdEquipo();
            if (tipoCompeticion == 2) {
                nombreJ = "Copa";
            }
            modelo.addRow(new Object[]{nombreJ, EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB)});

            
        }
        return tipoCompeticion;
    }

    /**
     * Método generarSigJornada que me generará la siguiente jornada del campeonato seleccionado
     * @param miVista VistaOrganización
     * @return la siguiente jornada almacenada en una tabla
     */
    public int generarSigJornada(VistaOrganizacion miVista) {
        
        Generar miGen = new Generar();
        int jornadaActual = miGen.sigJornadaN();
        javax.swing.JTable miTabla = miVista.getTablaSigJornada();
        DefaultTableModel modelo = (DefaultTableModel) miTabla.getModel();

           
        

        List<Partido> listaPartidos;
        listaPartidos = PartidoDAO.listarPartidosJornada(jornadaActual);

        //Leo de Ficheros para saber tipo
        String tipoCompeticion = FicherosTipo.leerFichero();
        //Si Liga no hace nada
        if (tipoCompeticion.equals("Liga")) {
            

        } //Si Copa
        else if (tipoCompeticion.equals("Copa") && jornadaActual!=0) {
            
            jornadaActual = jornadaActual + 1;
            miGen.generarSigJornada();
            
            listaPartidos = PartidoDAO.listarPartidosJornada(jornadaActual);
            
            for (Partido e : listaPartidos) {
                List<PartidoJugado> listaPJ = PartidoJugadoDAO.listarEquiposporPartido(e.getIdPartido());
                int equipoA = listaPJ.get(0).getIdEquipo();
                int equipoB = listaPJ.get(1).getIdEquipo();

                modelo.addRow(new Object[]{jornadaActual, EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB)});

                
            }

        } else {

            
            if (jornadaActual > 0) {
                miGen.generarJornadaPlayoffN(jornadaActual);
                jornadaActual = jornadaActual + 1;
                
                
            } else if (jornadaActual == 0){
                jornadaActual = jornadaActual + 1;
                String[] num = tipoCompeticion.split(":");
                int numEq = Integer.parseInt(num[1]);
                miGen.generarPlayoff(numEq);
            }

            
        }
        
        listaPartidos = PartidoDAO.listarPartidosJornada(jornadaActual);
        if (listaPartidos.size() == 0) {
            miVista.getEtiquetaFinCampeonato().setVisible(true);
        }


        while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

        for (Partido e : listaPartidos) {
            List<PartidoJugado> listaPJ = PartidoJugadoDAO.listarEquiposporPartido(e.getIdPartido());
            int equipoA = listaPJ.get(0).getIdEquipo();
            int equipoB = listaPJ.get(1).getIdEquipo();
            modelo.addRow(new Object[]{jornadaActual, EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB)});

            
        }
        
        
        return 0;
    }

}
