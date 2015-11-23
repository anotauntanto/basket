/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author inftel07
 */
public class PartidoDAO {
    private static Connection con;
    
    
    public static void crearPartido (Partido par) {
        con = Conexion.conectar();
        //persona = per;
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Partido (Id_Partido, Resultado, Jornada, Id_Arbitro, Fecha, Localizacion)"
                    + " values (INC_ID_PARTIDO.NextVal,?,?,?,?,?)");
            //ps.setInt(1, per.getIdPersona());
            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getNumJornada());
            ps.setInt(3, par.getIdArbitro());
            ps.setDate(4, new java.sql.Date(par.getFecha().getTime()));
            ps.setString(5, par.getLocalizacion());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    public static void modificarResultado(Partido par) {
        con = Conexion.conectar();
        
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Partido set Resultado=? where id_Partido = ?");
            
            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getIdPartido());
            ps.executeUpdate();
                
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    
}
