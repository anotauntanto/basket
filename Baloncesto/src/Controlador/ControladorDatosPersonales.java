/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Persona;
import Vistas.VistaDatosPersonales;

/**
 *
 * @author inftel08
 */
public class ControladorDatosPersonales {
    //private Persona persona;
    
    public ControladorDatosPersonales(Persona persona, VistaDatosPersonales vista) {
        //this.persona = persona;
       javax.swing.JTextField campo = vista.getCampoNombre();
       campo.setText(persona.getNombre());
       campo = vista.getCampoApellidos();
       campo.setText(persona.getApellidos());
       campo = vista.getCampoDni();
       campo.setText(persona.getDni());
       campo = vista.getCampoTelefono();
       campo.setText(persona.getTlf());
       campo = vista.getCampoEmail();
       campo.setText(persona.getEmail());
       campo = vista.getCampoFechaNac();
       campo.setText(persona.getFechaN().toString());
       
    }
    
    
    
    
}
