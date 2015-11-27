/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Equipo;
import Modelo.Clases.PartidoJugado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase PartidoJugadoDAO
 * @author grupo_baloncesto
 */
public class PartidoJugadoDAO {
    private static Connection con;
    private static List <PartidoJugado> listaPartidosJugados=null;
    
    /**
     * Metodo para crear PartidoJugado
     * @param par Partido a crear
     */
    public static void crearPartidoJugado (PartidoJugado par) {
        con = Conexion.conectar();

        try{
            PreparedStatement ps =  con.prepareStatement("insert into Equipo_partido (Id_Partido, Id_equipo, He_ganado)"
                    + " values (?,?,?)");
           
            ps.setInt(1, par.getIdPartido());
            ps.setInt(2, par.getIdEquipo());
            ps.setInt(3, par.getHeGanado());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
      
    }
    /**
     * Metodo para listar los Partidos Jugados por Equipo
     * @param id_equipo int, id del Equipo a buscar
     * @return List PartidoJugado, lista de los Partidos Buscados 
     */ 
    public static List<PartidoJugado> listarPartidosJugadosEquipo (int id_equipo) { 
        con = Conexion.conectar();
        listaPartidosJugados= new ArrayList<>();
        PartidoJugado partido = null; 
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Equipo_partido where Equipo_partido.Id_equipo = "+id_equipo);
  
            
            while (rs.next()) {
                partido = new PartidoJugado (rs.getInt(1), rs.getInt(2), rs.getInt(3));
                listaPartidosJugados.add(partido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaPartidosJugados;
        
    }
     
    /**
     * Metodo para listar los Equipos por Partido
     * @param id_partido int, id del Partido a buscar
     * @return List PartidoJugado, lista de los Partidos Jugados buscados
     */
    public static List<PartidoJugado> listarEquiposporPartido (int id_partido) {
        con = Conexion.conectar();
        listaPartidosJugados= new ArrayList<>();
        PartidoJugado partido = null; 
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Equipo_partido where Id_partido = "+id_partido);
  
            while (rs.next()) {
                partido = new PartidoJugado (rs.getInt(1), rs.getInt(2), rs.getInt(3));
                listaPartidosJugados.add(partido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaPartidosJugados;
        
    }
    
    /**
     * Metodo para obtener todos los Partidos Jugados
     * @return List PartidoJugado, lista de çpartidos Jugados buscados
     */
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
         }finally {
            Conexion.desconexion(con);
        }
         return listaPartidosJugados;
    }
    
    /**
     * Metodo para modificar un resultado para un PartidoJugado
     * @param parJug PartidoJugado a buscar
     */
    public static void modificarResultado(PartidoJugado parJug) {
        con = Conexion.conectar();

        try {
            PreparedStatement ps = con.prepareStatement("update Equipo_Partido set he_ganado=? where id_partido = ? and id_equipo= ?");

            ps.setInt(1, parJug.getHeGanado());
            ps.setInt(2, parJug.getIdPartido());
            ps.setInt(3, parJug.getIdEquipo());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
     
    }
     
    //Numero de partidos ganados
    /**
     * Metodo para calcular el numero de partidos ganados por un Equipo
     * @param equipo Equipo a buscar
     * @return int, numero de victorias del Equipo buscado
     */
     public static int numeroPartidosGanados(Equipo equipo){
         con = Conexion.conectar();
         int numVictorias=0;

        try {
            PreparedStatement ps = con.prepareStatement("select count(*) from Equipo_Partido where id_equipo= ? and he_ganado=1");

            ps.setInt(1, equipo.getIdEquipo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                numVictorias=rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
         return numVictorias;
     }
}
