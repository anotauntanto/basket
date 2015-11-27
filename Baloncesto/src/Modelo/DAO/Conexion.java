/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Conexion con la Base de Datos
 * @author grupo_baloncesto
 */
public class Conexion {
 
    private static String url = "jdbc:oracle:thin:INFTEL15_3/INFTEL@olimpia.lcc.uma.es:1521:edgar";
 
    /**
     * Constructor de la clase Conexion
     */
    public Conexion() {
      
    }
    
    /**
     * Metodo que conecta con la Base de Datos  
     * @return Connection conexion a devolver 
     */
    public static Connection conectar() {
        Connection con = null;
        
        if (con==null){
            
            try{
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                
                con = DriverManager.getConnection(url,null);
               
            }
            catch (SQLException ex){
                System.out.println("Problema al conectar con la base de datos");
            }
            catch (ClassNotFoundException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return con;
    }
    
    /**
     * Metodo que desconecta con la Base de Datos
     * @param con Connection conexion a cerrar
     */
    public static void desconexion (Connection con) {
        
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

}
