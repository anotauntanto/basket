/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inftel08
 */
public class Conexion {

    private static Connection con = null;
    private static Statement stmt = null; 
    private static String url = "jdbc:oracle:thin:INFTEL15_3/INFTEL@olimpia.lcc.uma.es:1521:edgar";
    //private static String url = "jdbc:oracle:thin:INFTEL15_2/INFTEL@olimpia.lcc.uma.es:1521:edgar";

    public Conexion() {
        /*try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:INFTEL15_3/INFTEL@olimpia.lcc.uma.es:1521:edgar", null);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public Connection getConexion() { 
        return this.con;
    }
    
    public static Connection conectar() {
        if (con==null){
            
            try{
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                
                con = DriverManager.getConnection(url,null);
                if (con!=null)
                    System.out.println("Conexión con la base de datos extablecida");
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
    
    public static void desconexion () {
        
        try {
            con.close();
            //System.out.println("Desconexión de la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

}
