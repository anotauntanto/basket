/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Persona;
import Vistas.VistaLogin;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PersonaDAO;
import Vistas.VistaArbitro;
import Vistas.VistaEntrenador;
import Vistas.VistaJugador;
import Vistas.VistaNoEntra;
import Vistas.VistaOrganizacion;

/**
 *
 * @author inftel06
 */

public class ControladorLogin {
    private String contrasena;
    private String dni;
    //private PersonaDAO miPersonaDAO;
    private Persona miPersona;
    private VistaLogin miVista;
    private JugadorDAO miJugador;
    
   
    
    public ControladorLogin(){
        
    }
    
    public void confirmarPassword(String dni, String password, VistaLogin miLogin){
        //miPersonaDAO = new PersonaDAO();
        miPersona=PersonaDAO.obtenerPersonaPorDni(dni);
        
        if (miPersona!=null){
            
            if (miPersona.getContrasena().equals(password)){
                
                switch (miPersona.getRol()){
                    case 1:
                        VistaJugador nuevaVista=new VistaJugador(miPersona);
                        miLogin.setVisible(false);
                        nuevaVista.setVisible(true);
                        //System.out.println("holii: "+miPersona.getIdPersona());
                        break;
                    case 2:
                        VistaEntrenador nuevaVista2=new VistaEntrenador(miPersona);
                        miLogin.setVisible(false);
                        nuevaVista2.setVisible(true);
                        break;
                    case 3:
                        VistaArbitro nuevaVist3=new VistaArbitro(miPersona);
                        miLogin.setVisible(false);
                        nuevaVist3.setVisible(true);
                        break;
                    case 0:
                        System.out.println("HOliiiiii");
                        VistaOrganizacion nuevaVist4=new VistaOrganizacion();
                        miLogin.setVisible(false);
                        nuevaVist4.setVisible(true);    
                        break;
                }
                //System.out.println(miPersona.getRol());
            }
            
            
        }
        else {
            //final VistaNoEntra nuevaVista=new VistaNoEntra();
                        //this.setVisible(false);
                        //nuevaVista.setVisible(true);
                        new VistaNoEntra(miLogin,true).setVisible(true);
        }
    }
        
}
