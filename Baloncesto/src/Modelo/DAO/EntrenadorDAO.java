/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Entrenador;
import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author inftel07
 */
public class EntrenadorDAO {
    private static List <Entrenador> listaEntrenadores=null;
    static Connection con=null;
    
    public EntrenadorDAO(){
    }
    
    public static void insertarEntrenador (Entrenador ent){
        
        
        PersonaDAO.insertarPersona(ent);
        
        Persona per = PersonaDAO.obtenerPersonaPorDni(ent.getDni());
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Entrenador (id_Persona, id_equipo, nivel)"
                    + " values (?,?,?)");
            
            ps.setInt(1, per.getIdPersona());
            ps.setInt(2, ent.getIdEquipo());
            ps.setInt(3, ent.getNivel());
            
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void modificarEntrenador (Entrenador ent) {
        int id=PersonaDAO.obtenerIdPersona(ent.getDni());
        ent.setIdPersona(id);
        
        //Persona per = new Persona(jug.getIdPersona(), jug.getNombre(), jug.getDNI(), jug.getFecha(), jug.getEmail(), jug.getContrasena(), jug.getRol());
        PersonaDAO.modificarPersona(ent);
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Entrenador set id_equipo=?, Nivel=? where id_Persona=?");
            ps.setInt(1, ent.getIdEquipo());
            ps.setInt(2, ent.getNivel());
            
            ps.setInt(3, ent.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Entrenador obtenerEntrenadorPorDni (String dni) {
        con = Conexion.conectar();
        Persona persona = PersonaDAO.obtenerPersonaPorDni(dni);
        Entrenador entrenador = null;
        
        try{
            PreparedStatement ps = con.prepareStatement("select * from Entrenador ent inner join Persona per on ent.id_Persona = per.id_Persona and ent.id_persona=?");
            ps.setInt(1, persona.getIdPersona());//tb se puede hacer por idPersona cambiamos aqui idPersona y arriba tb per.idPersona=?
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                entrenador = new Entrenador(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return entrenador;
    }
}
