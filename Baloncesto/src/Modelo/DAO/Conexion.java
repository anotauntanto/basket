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

    private Connection con = null;
    Statement stmt = null; 
    String url = "jdbc:oracle:thin:INFTEL15_3/INFTEL@olimpia.lcc.uma.es:1521:edgar";

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
    
    public Connection conectar() throws InstantiationException, IllegalAccessException {
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
    
    public void desconexion () {
        
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

}
