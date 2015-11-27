/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;


import Modelo.Clases.Estadisticas;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase EstadisticasDAO
 * @author grupo_baloncesto
 */
public class EstadisticasDAO {
     private static List <Estadisticas> listaEstadisticas=null;
    private static Connection con;
    
    /**
     * Metodo para insertar Estadisticas de un Jugador
     * @param es Estadisticas a insertar
     */
    public static void insertarEstadisticasJugador (Estadisticas es) {
        
       
       con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Jugador_partido (id_jugador, id_Partido, rebotes, puntos, asistencias)"
                    + " values (?,?,?,?,?)");
            
            ps.setInt(1, es.getIdJugador());
            ps.setInt(2, es.getIdPartido());
            ps.setInt(3, es.getRebotes());
            ps.setInt(4, es.getPuntos());
            ps.setInt(5, es.getAsistencias());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    }
    
    /**
     * Metodo para modificar estadisticas ya existente de un jugador
     * @param es Estadisticas a modificar
     */
    public static void modificarEstadisticasJugador (Estadisticas es) {
      
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("update jugador_partido set rebotes=?, puntos=?, asistencias=? where id_jugador=? and id_partido=?");
            
            ps.setInt(1, es.getRebotes());
            ps.setInt(2, es.getPuntos());
            ps.setInt(3, es.getAsistencias());
            ps.setInt(4, es.getIdJugador());
            ps.setInt(5, es.getIdPartido());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    }
    
    
    /**
     * Metodo para obtener todas las estadisticas de un jugador
     * @param id int, id del Jugador a buscar   
     * @return List<Estadisticas>, lista de las estadisticas buscadas
     */
    public static List<Estadisticas> obtenerTodasEstadisticasJugador(int id){
        con = Conexion.conectar();
        listaEstadisticas= new ArrayList<>();
        Estadisticas estadisticas=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs  = st.executeQuery("select * from jugador_partido es where es.id_jugador =" + id);
      
            while (rs.next()) {
                estadisticas = new Estadisticas(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                
                listaEstadisticas.add(estadisticas);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaEstadisticas;
    }
    
    /**
     * Metodo para obtener las estadisticas de un jugador en un partido
     * @param id int, id jugador a buscar
     * @param id_partido int, id partido a buscar
     * @return Estadisticas buscadas
     */
    public static Estadisticas obtenerTodasEstadisticasJugadorPartido(int id, int id_partido){
        con = Conexion.conectar();
        Estadisticas estadisticas=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from jugador_partido where id_jugador=? and id_partido=?");
            ps.setInt(1, id);
            ps.setInt(2, id_partido);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                estadisticas = new Estadisticas(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return estadisticas;
    }

    
}
