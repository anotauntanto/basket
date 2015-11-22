package Modelo.DAO;

import Modelo.Clases.FactoriaPersona;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author inftel06
 */
public class PersonaDAO {

    Conexion c;
    Statement stmt = null;
    
   public boolean iniciarSesion (String dni, String contrasena) {
        
        c = new Conexion ();  
        
        try { 
            stmt = c.getConexion().createStatement();
            String sentencia = "SELECT rol FROM PERSONA WHERE DNI = "+dni+"and contrasena=" + contrasena;
            ResultSet resultado = stmt.executeQuery(sentencia);
            
            if (resultado.next()) { //si esto es verdadero es que existe una coincidencia, es decir, está en la base de datos.
                String rol = resultado.getString("rol");
                FactoriaPersona factoria = new FactoriaPersona();
                Persona p = factoria.makePersona(rol);
 
                //recuperar rol
                
                
            } else {
                //mostrar mensaje por pantalla de inicion de sesión erróneo
                
                //te desconectas de la base de datos
                c.desconexion();
            }
           
           
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
   }

   
    
    
    
    
}
