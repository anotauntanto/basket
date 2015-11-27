/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Excepciones.DorsalException;
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
 * Clase JugadorDAO
 * @author grupo_baloncesto
 */
public class JugadorDAO {
    
    private static List <Jugador> listaJugadores=null;
    static Connection con=null;
    
    /**
     * Constructor por defecto
     */
    public JugadorDAO(){
    }
    
    /**
     * Metodo para insertar Jugador
     * @param jug Jugador a insertar
     */
    public static void insertarJugador (Jugador jug){
        
        
        PersonaDAO.insertarPersona(jug);
        
        Persona per = PersonaDAO.obtenerPersonaPorDni(jug.getDni());
        con = Conexion.conectar();
        
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
        }finally {
            Conexion.desconexion(con);
        }
    }    
    
    /**
     * Metodo para obtener todos los jugadores
     * @return List<Jugador>, lista de los jugadores obtenidos
     */
    public static List<Jugador> obtenerTodosJugadores(){
        con = Conexion.conectar();
        listaJugadores= new ArrayList<>();
        Jugador jugador=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Jugador ju inner join Persona per on ju.id_Persona = per.id_Persona");
            
            while (rs.next()) {
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));
                
                listaJugadores.add(jugador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaJugadores;
    }
    
    /**
     * Metodo para obtener la lista de los jugadores por Equipo
     * @param id_equipo int, id del Equipo a buscar
     * @return List<Jugador> lista de los jugadores buscados
     */
    public static List<Jugador> obtenerTodosJugadoresEquipo(int id_equipo){
        
        con = Conexion.conectar();
        listaJugadores= new ArrayList<>();
        Jugador jugador=null;
        try{
            
            PreparedStatement ps = con.prepareStatement("select * from Jugador where id_equipo = ?");
            ps.setInt(1, id_equipo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4));
                listaJugadores.add(jugador);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaJugadores;
    }
    

    /**
     * Metodo para modificar un Jugador ya existente
     * @param jug Jugador a modificar
     */
    public static void modificarJugador (Jugador jug) {
        int id=PersonaDAO.obtenerIdPersona(jug.getDni());
        jug.setIdPersona(id);         
        
        PersonaDAO.modificarPersona(jug);
        con = Conexion.conectar();

        try{
            PreparedStatement ps =  con.prepareStatement("update Jugador set Dorsal=?,Altura=?,id_Equipo=? where id_Persona=?");
            ps.setInt(1, jug.getDorsal());
            ps.setDouble(2, jug.getAltura());
            ps.setInt(3, jug.getIdEquipo());
            ps.setInt(4, jug.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    }
    
    /**
     * Metodo para obtener un Jugador por Dni
     * @param dni String, dni a buscar
     * @return Jugador buscado
     */
    public static Jugador obtenerJugadorPorDni (String dni) {
        con = Conexion.conectar();
        Persona persona = PersonaDAO.obtenerPersonaPorDni(dni);
       
        
        Jugador jugador = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from Jugador jug inner join Persona per on jug.id_Persona = per.id_Persona and jug.id_persona=?");
            ps.setInt(1, persona.getIdPersona());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));//el 5 seria el mismo q el 4, pq listamos jugador y persona y tienen una columna igual q es la q las une
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return jugador;
    }
    
    /**
     * Metodo para obtener un Jugador por Dorsal y Equipo
     * @param dorsal int, dorsal del jugador a buscar
     * @param id_equipo int, id del equipo a buscar
     * @return Jugador buscado
     */
    public static Jugador obtenerJugadorPorDorsal (int dorsal, int id_equipo) {
        con = Conexion.conectar();
        
        
        Jugador jugador = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from Jugador jug inner join Persona per on jug.id_Persona = per.id_Persona and jug.dorsal=? and jug.id_equipo=?");
            ps.setInt(1, dorsal);
            ps.setInt(2, id_equipo);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                jugador = new Jugador(rs.getDouble(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));//el 5 seria el mismo q el 4, pq listamos jugador y persona y tienen una columna igual q es la q las une
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return jugador;
    }
    
    
    /**
     * Metodo para obtener Jugador por Id
     * @param persona, Persona a buscar
     * @return Jugador buscado
     */
    public static Jugador obtenerJugadorPorID (Persona persona) {
        con = Conexion.conectar();
      
        Jugador jugador = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from Jugador jug inner join Persona per on jug.id_Persona = per.id_Persona and jug.id_persona=?");
            ps.setInt(1, persona.getIdPersona());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                jugador = new Jugador(persona, rs.getDouble(2), rs.getInt(3), rs.getInt(4));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return jugador;
    }
    
    
    /**
     * Metodo para comprobar si Existe un dorsal en un Equipo
     * @param dorsal int, dorsal del jugador a buscar
     * @param id_equipo int, id Equipo a buscar
     * @throws DorsalException si el dorsal ya existe en el Equipo
     */
    public static void comprobarDorsal (int dorsal, int id_equipo) throws DorsalException {
        con = Conexion.conectar();
      
        int cont = 0;
      
        
        try{
            PreparedStatement ps = con.prepareStatement("select count(dorsal) from Jugador where dorsal=? and id_equipo = ?");
            ps.setInt(1, dorsal);
            ps.setInt(2, id_equipo);
                    
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                cont = rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        
        if (cont != 0) {
            throw new DorsalException();
        }
        
    }
}
