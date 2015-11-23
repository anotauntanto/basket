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
            PreparedStatement ps =  con.prepareStatement("insert into Jugador_partido (id_persona, id_Partido, rebotes, puntos, asistencias)"
                    + " values (?,?,?,?,?)");
            //ps.setInt(1, es.);
            ps.setInt(2, es.getIdPartido());
            ps.setInt(3, es.getIdPartido());
            ps.setInt(4, es.getRebotes());
            ps.setInt(5, es.getPuntos());
            ps.setInt(6, es.getAsistencias());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void modificarEstadisticasJugador (Estadisticas es) {
        int id=es.getIdJugador();
        //int id=PersonaDAO.modificarPersona(es.getIdJugador());
        //es.setIdPersona(id);
        
        
        //PersonaDAO.modificarPersona();
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Estadisticas set id_partido=?, rebotes=?, puntos=?, asistencias=? where id_Persona=?");
            ps.setInt(1, es.getIdPartido());
            ps.setInt(2, es.getRebotes());
            ps.setInt(3, es.getPuntos());
            ps.setInt(4, es.getAsistencias());
            ps.setInt(5, id);
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static Estadisticas obtenerEstadisticasPorId ( int id) {
        con = Conexion.conectar();
        //Persona persona = PersonaDAO.obtenerPersonaPorDni(dni);
        Estadisticas estadisticas = null;
        
        try{
            Statement st = con.createStatement();
            ResultSet rs  = st.executeQuery("select * from Estadisticas es where es.id_Persona =" + id);
            
            
            while (rs.next()){
                estadisticas = new Estadisticas(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return estadisticas;
    }
    
    
    public static List<Estadisticas> obtenerTodasEstadisticasJugador(){
        con = Conexion.conectar();
        listaEstadisticas= new ArrayList<>();
        Estadisticas estadisticas=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Estadisticas es inner join Persona per on es.id_Persona = per.id_Persona");
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                estadisticas = new Estadisticas(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                
                listaEstadisticas.add(estadisticas);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaEstadisticas;
    }
}
