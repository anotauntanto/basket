/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Generar;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import Vistas.VistaOrganizacionOK;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inftel08
 */
public class ControladorCampeonato {
    private VistaOrganizacionOK miVista;
    private Vector<String> num_equipos;
    private javax.swing.JRadioButton tipoLiga;
    private javax.swing.JRadioButton tipoMixto;
    private javax.swing.JRadioButton tipoCopa;
    private int tipoCompeticion;
    private javax.swing.ButtonGroup grupo_botones;
    private javax.swing.JComboBox numPlayoff;
    
    
    public void GenerarLista() {
        
        //contar equipos de la bbdd
        int contador_equipos = EquipoDAO.contarEquipos();
        System.out.println("Numero de equipos " + contador_equipos);
        num_equipos = new Vector<>();
        
        int exp = 1;
        double valor = Math.pow(2,exp);
        
        while (valor < contador_equipos) { 
            num_equipos.add(Integer.toString((int)valor));
            exp++;
            valor = Math.pow(2,exp);
        }
        
        //System.out.println(num_equipos);
        //generar array
        
    }

    public ControladorCampeonato(VistaOrganizacionOK miVista) {
            this.miVista = miVista;
            this.GenerarLista();
            DefaultComboBoxModel model = new DefaultComboBoxModel(num_equipos);
            miVista.getCampoListaNumeros().setModel(model);
            
    }
    
    public int tipoCampeonato(VistaOrganizacionOK miVista){
        javax.swing.JTable miTabla = miVista.getTablaGenerar();
        Generar miGen=new Generar();
        int miJornada=0; //Valor para ver la primera jornada generada
        
        tipoLiga=miVista.getCampoLiga();
        tipoCopa=miVista.getCampoCopa();
        tipoMixto=miVista.getCampoMixto();
        
        grupo_botones=miVista.getGrupoBotones();
        grupo_botones.add(tipoLiga);
        grupo_botones.add(tipoCopa);
        grupo_botones.add(tipoMixto);
        
        if (tipoLiga.isSelected()){
            miGen.generarLiga();
            tipoCompeticion=1;
            
        }
        else if(tipoCopa.isSelected()){
            miGen.generarSigJornada();
            tipoCompeticion=2;
            miJornada=1;
        }
        else {
            numPlayoff=miVista.getCampoListaNumeros();
            int numEquipo=Integer.parseInt((String) numPlayoff.getSelectedItem());
            tipoCompeticion=3;
            //Que hago si tengo Mixto
        }
        DefaultTableModel modelo = (DefaultTableModel) miTabla.getModel();
        
        List<Partido> listaPartidos;
        listaPartidos = PartidoDAO.listarPartidosJornada(miJornada);
        
        //ArrayList<Partido> listaPartidos=(ArrayList<Partido>) PartidoDAO.listarPartidosJornada(miJornada);
        //System.out.println(numEquipo+3);
        String nombreA;
        String nombreB;
        for (Partido e : listaPartidos) {
            //List<PartidoJugado> ListaEquipo=PartidoJugadoDAO.listarEquiposporPartido(e.getIdPartido());
                    //EquipoDAO.obtenerNombreEquipo(miJornada);
        }
        return tipoCompeticion;
    }
   
    

}
