/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.EquipoDAO;
import Vistas.VistaOrganizacionOK;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author inftel08
 */
public class ControladorCampeonato {
    private VistaOrganizacionOK miVista;
    private Vector<String> num_equipos;
    
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
        
        System.out.println(num_equipos);
        //generar array
        
    }

    public ControladorCampeonato(VistaOrganizacionOK miVista) {
            this.miVista = miVista;
            this.GenerarLista();
            DefaultComboBoxModel model = new DefaultComboBoxModel(num_equipos);
            miVista.getCampoListaNumeros().setModel(model);
    }
    
    
    

}
