/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.PartidoJugado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author inftel08
 */
public class PartidoJugadoDAO {
    private static Connection con;
    
     public static void crearPartidoJugado (PartidoJugado par) {
        con = Conexion.conectar();

        try{
            PreparedStatement ps =  con.prepareStatement("insert into Equipo_partido (Id_Partido, Id_equipo, He_ganado)"
                    + " values (?,?,?)");
            //ps.setInt(1, per.getIdPersona());
            ps.setInt(1, par.getIdPartido());
            ps.setInt(2, par.getIdEquipo());
            ps.setInt(3, par.getHeGanado());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    
    
}
