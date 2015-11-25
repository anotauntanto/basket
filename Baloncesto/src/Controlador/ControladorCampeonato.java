/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.EquipoDAO;
import Vistas.VistaOrganizacionOK;

/**
 *
 * @author inftel08
 */
public class ControladorCampeonato {
    private VistaOrganizacionOK miVista;
    
    public ControladorCampeonato () {
        
        //contar equipos de la bbdd
        int contador_equipos = EquipoDAO.contarEquipos();
        
        //generar array
        
    }
}
