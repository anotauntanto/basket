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
 *
 * @author inftel05
 */
public class EstadisticasDAO {
     private static List <Estadisticas> listaEstadisticas=null;
    private static Connection con;
    
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
    
    public static void modificarEstadisticasJugador (Estadisticas es) {
        //int id=es.getIdJugador();
        //int id=PersonaDAO.modificarPersona(es.getIdJugador());
        //es.setIdPersona(id);
        
        
        //PersonaDAO.modificarPersona();
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
    
    
    
    public static List<Estadisticas> obtenerTodasEstadisticasJugador(int id){
        con = Conexion.conectar();
        listaEstadisticas= new ArrayList<>();
        Estadisticas estadisticas=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs  = st.executeQuery("select * from jugador_partido es where es.id_jugador =" + id);
            //ResultSetMetaData rsmd = rs.getMetaData();
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
    
    public static Estadisticas obtenerTodasEstadisticasJugadorPartido(int id, int id_partido){
        con = Conexion.conectar();
        Estadisticas estadisticas=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from jugador_partido where id_jugador=? and id_partido=?");
            ps.setInt(1, id);
            ps.setInt(2, id_partido);
            ResultSet rs = ps.executeQuery();
            
            //ResultSetMetaData rsmd = rs.getMetaData();
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
    //si tenemos tiempo hacer un obtener estadisticas de un equipo.
    
}
