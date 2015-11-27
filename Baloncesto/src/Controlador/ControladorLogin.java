/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Internacionalizacion.I18N;
import Modelo.Clases.Persona;
import Vistas.VistaLogin;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PersonaDAO;
import Vistas.VistaArbitro;
import Vistas.VistaEntrenador;
import Vistas.VistaJugador;
import Vistas.VistaOrganizacion;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;


/**
 * Clase ControladorLogin
 * @author grupo_baloncesto
 */

public class ControladorLogin {
    private String contrasena;
    private String dni;
    private Persona miPersona;
    private VistaLogin miVista;
    private JugadorDAO miJugador;
    private JLabel error=new JLabel();
    private JButton botonAcceder=new JButton();
    private JButton botonIngles=new JButton();
    private JButton botonEspañol=new JButton();
    private JLabel labelDni=new JLabel();
    private JLabel labelContrasena=new JLabel();
    private JLabel labelSaludo=new JLabel();
    
    
    
   
    /**
     * Constructor por defecto
     */
    public ControladorLogin(){
        error=new JLabel();
        botonAcceder=new JButton();
        botonIngles=new JButton();
        botonEspañol=new JButton();
        labelDni=new JLabel();
        labelContrasena=new JLabel();
        labelSaludo=new JLabel();
       
        
    }
    /**
     * Metodo para Internacionalización, fija Idioma Ingles
     * @param miLogin VistaLogin
     */
    public void setIngles(VistaLogin miLogin){
        String loc=I18N.getBUNDLE_NAME();
        Locale locale=new Locale("en","GB");
        ResourceBundle rs=ResourceBundle.getBundle(loc,locale);
        botonAcceder=miLogin.getjButton1();
        labelSaludo=miLogin.getjLabel1();
        labelContrasena=miLogin.getjLabel3();
        labelDni=miLogin.getjLabel2();
        labelDni.setText(rs.getString("DNI"));
        labelSaludo.setText(rs.getString("saludo"));
        labelContrasena.setText(rs.getString("contrasena"));
        botonAcceder.setText(rs.getString("entra"));
        error=miLogin.getjLabel4();
        error.setText(rs.getString("noEntra"));
        
        
    }
    
    /**
     * Metodo para Internacionalizacion, fija Idioma Español
     * @param miLogin VistaLogin
     */
    public void setEspañol(VistaLogin miLogin){
        String loc=I18N.getBUNDLE_NAME();
        Locale locale=new Locale("es","ES");
        ResourceBundle rs=ResourceBundle.getBundle(loc,locale);
        botonAcceder=miLogin.getjButton1();
        labelSaludo=miLogin.getjLabel1();
        labelContrasena=miLogin.getjLabel3();
        labelDni=miLogin.getjLabel2();
        labelDni.setText(rs.getString("DNI"));
        labelSaludo.setText(rs.getString("saludo"));
        labelContrasena.setText(rs.getString("contrasena"));
        botonAcceder.setText(rs.getString("entra"));
        error=miLogin.getjLabel4();
        error.setText(rs.getString("noEntra"));
        
    }
    
    /**
     * Metodo para asegurar que el Uuario existe en la Base de Datos
     * @param dni String
     * @param password String
     * @param miLogin VistaLogin
     */
    public void confirmarPassword(String dni, String password, VistaLogin miLogin){
    
        miPersona=PersonaDAO.obtenerPersonaPorDni(dni);
    
        
        
        if (miPersona!=null){
            
            if (miPersona.getContrasena().equals(password)){
                
                switch (miPersona.getRol()){
                    case 1:
                        VistaJugador nuevaVista=new VistaJugador(miPersona);
                        miLogin.setVisible(false);
                        nuevaVista.setVisible(true);
                        
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
                        VistaOrganizacion nuevaVist4=new VistaOrganizacion();
                        miLogin.setVisible(false);
                        nuevaVist4.setVisible(true);    
                        break;
                }
              
            }
            
            
        }
        else {

                        error.setVisible(true);
        }
    }
        
}
