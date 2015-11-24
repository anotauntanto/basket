/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.PartidoJugado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inftel08
 */
public class PartidoJugadoDAO {
    private static Connection con;
    private static List <PartidoJugado> listaPartidosJugados=null;
    
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
     
     public static List<PartidoJugado> listarPartidosJugadosEquipo (int id_equipo) { //para emparejamientos
        con = Conexion.conectar();
        listaPartidosJugados= new ArrayList<>();
        PartidoJugado partido = null; 
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Equipo_partido where Equipo_partido.Id_equipo = "+id_equipo);
  
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                partido = new PartidoJugado (rs.getInt(1), rs.getInt(2), rs.getInt(3));
                listaPartidosJugados.add(partido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaPartidosJugados;
        
    }
     
    public static List<PartidoJugado> obtenerTodosPartidosJugados(){
         PartidoJugado partidoJug=null;
         con=Conexion.conectar();
         listaPartidosJugados= new ArrayList();
         try{
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("Select * from Equipo_partido");
             while(rs.next()){
                 partidoJug = new PartidoJugado(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                 listaPartidosJugados.add(partidoJug);
             }
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return listaPartidosJugados;
    }
    
    
     public static void modificarResultado(PartidoJugado parJug) {
        con = Conexion.conectar();

        try {
            PreparedStatement ps = con.prepareStatement("update Equipo_Partido set he_ganado=? where id_partido = ? and id_equipo= ?");

           
            /*if (ganado){
                ps.setInt(1, 1);
            }
            else {
                ps.setInt(1, 0);
            }*/
            ps.setInt(1, parJug.getHeGanado());
            ps.setInt(2, parJug.getIdPartido());
            ps.setInt(3, parJug.getIdEquipo());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
}
