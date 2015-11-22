/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

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
 * @author inftel07
 */
public class JugadorDAO {
    //private static Jugador jugador=null;
    private static List <Jugador> listaJugadores=null;
    static Connection con=null;
    
    public JugadorDAO(){
    }
    
    public static void insertarJugador (Jugador jug){
        
        //Persona per = new Persona(0, jug.getNombre(), jug.getDNI(), jug.getFecha(), jug.getEmail(), jug.getContrasena(), jug.getRol());
        PersonaDAO.insertarPersona(jug);
        
        Persona per = PersonaDAO.obtenerPersonaPorDni(jug.getDni());
        con = Conexion.conectar();
        //jugador = jug;
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Jugador (id_Persona, Dorsal, Altura, id_equipo)"
                    + " values (?,?,?,?)");
            
            ps.setInt(1, per.getIdPersona());
            ps.setInt(2, jug.getDorsal());
            ps.setDouble(3, jug.getAltura());
            ps.setInt(4, jug.getIdEquipo());
            //insertar id_equipo
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }    
 
    public static List<Jugador> obtenerTodosJugadores(){
        con = Conexion.conectar();
        listaJugadores= new ArrayList<>();
        Jugador jugador=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Jugador ju inner join Persona per on ju.id_Persona = per.id_Persona");
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));
                
                listaJugadores.add(jugador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaJugadores;
    }
    

    
    public static void modificarJugador (Jugador jug) {
        
        //Persona per = new Persona(jug.getIdPersona(), jug.getNombre(), jug.getDNI(), jug.getFecha(), jug.getEmail(), jug.getContrasena(), jug.getRol());
        PersonaDAO.modificarPersona(jug);
        con = Conexion.conectar();
        
        //jugador = jug;
        try{
            PreparedStatement ps =  con.prepareStatement("update Jugador set Dorsal=?,Altura=?,id_Equipo=? where id_Persona=?");
            ps.setInt(1, jug.getDorsal());
            ps.setDouble(2, jug.getAltura());
            ps.setInt(3, jug.getIdEquipo());
            ps.setInt(4, jug.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static Jugador obtenerJugadorPorDni (String dni) {
        con = Conexion.conectar();
        Persona persona = PersonaDAO.obtenerPersonaPorDni(dni);
        
        Jugador jugador = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from Jugador jug inner join Persona per on jug.id_Persona = per.id_Persona and jug.id_persona=?");
            ps.setInt(1, persona.getIdPersona());//tb se puede hacer por idPersona cambiamos aqui idPersona y arriba tb per.idPersona=?
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));//el 5 seria el mismo q el 4, pq listamos jugador y persona y tienen una columna igual q es la q las une
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return jugador;
    }
}
