/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.PartidoDAO;

/**
 *
 * @author inftel08
 */
public class ControladorClasificacion {
    
    public void listarClasificacion() {
        
        PartidoDAO.listarPartidosJornada(num_jornada);
        
    }
    
}
