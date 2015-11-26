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
import Vistas.VistaNoEntra;
import Vistas.VistaOrganizacionOK;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;


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
    //private I18N bundleName;
    private JLabel error=new JLabel();
    private JButton botonAcceder=new JButton();
    private JButton botonIngles=new JButton();
    private JButton botonEspañol=new JButton();
    private JLabel labelDni=new JLabel();
    private JLabel labelContrasena=new JLabel();
    private JLabel labelSaludo=new JLabel();
    private static final String BUNDLE_NAME=I18N.class.getPackage().getName()+".messages";
    
    
   
    
    public ControladorLogin(){
        error=new JLabel();
        botonAcceder=new JButton();
        botonIngles=new JButton();
        botonEspañol=new JButton();
        labelDni=new JLabel();
        labelContrasena=new JLabel();
        labelSaludo=new JLabel();
        //bundleName=new I18N();
        
    }
    
    public void setIngles(VistaLogin miLogin){
        String loc=I18N.getBUNDLE_NAME();
        Locale locale=new Locale("en","GB");
       // String miSt=getClass().getResourceAsStream("/Users/inftel06/NetBeansProjects/basket/Baloncesto/src/Internacionalizacion").toString();
        ResourceBundle rs=ResourceBundle.getBundle(BUNDLE_NAME,locale);
        botonAcceder=miLogin.getjButton1();
        labelSaludo=miLogin.getjLabel1();
        labelContrasena=miLogin.getjLabel3();
        labelDni=miLogin.getjLabel2();
        labelDni.setText(rs.getString("DNI"));
        labelSaludo.setText(rs.getString("saludo"));
        labelContrasena.setText(rs.getString("contrasena"));
        botonAcceder.setText(rs.getString("entra"));
        
        
    }
    
    public void setEspañol(VistaLogin miLogin){
         Locale locale=new Locale("es","ES");
       // String miSt=getClass().getResourceAsStream("/Users/inftel06/NetBeansProjects/basket/Baloncesto/src/Internacionalizacion").toString();
        ResourceBundle rs=ResourceBundle.getBundle(BUNDLE_NAME,locale);
        botonAcceder=miLogin.getjButton1();
        labelSaludo=miLogin.getjLabel1();
        labelContrasena=miLogin.getjLabel3();
        labelDni=miLogin.getjLabel2();
        labelDni.setText(rs.getString("DNI"));
        labelSaludo.setText(rs.getString("saludo"));
        labelContrasena.setText(rs.getString("contrasena"));
        botonAcceder.setText(rs.getString("entra"));
    }
    
    public void confirmarPassword(String dni, String password, VistaLogin miLogin){
        //miPersonaDAO = new PersonaDAO();
        miPersona=PersonaDAO.obtenerPersonaPorDni(dni);
        
        /*JLabel error=new JLabel();
        JButton botonAcceder=new JButton();
        JButton botonIngles=new JButton();
        JButton botonEspañol=new JButton();
        JLabel labelDni=new JLabel();
        JLabel labelContrasena=new JLabel();
        JLabel labelSaludo=new JLabel();*/
        
        
        
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
                        VistaOrganizacionOK nuevaVist4=new VistaOrganizacionOK();
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
                        //new VistaNoEntra(miLogin,true).setVisible(true);
                        error=miLogin.getjLabel4();
                        error.setVisible(true);
        }
    }
        
}
