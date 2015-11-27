/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Arbitro;
import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ArbitroDAO 
 * @author grupo_baloncesto
 */
public class ArbitroDAO {
    
    private static Connection con = null;
    private static List <Arbitro> listaArbitros=null;
    
    /**
     * Metodo para insertar un Arbitro en la Base de Datos 
     * @param ar Arbitro a insertar
     */
    public static void insertarArbitro (Arbitro ar){
   
        PersonaDAO.insertarPersona(ar);
        
        Persona per = PersonaDAO.obtenerPersonaPorDni(ar.getDni());
        con = Conexion.conectar();

        try{
            PreparedStatement ps =  con.prepareStatement("insert into Arbitro (id_Persona, Provincia)"
                    + " values (?,?)");
            
            ps.setInt(1, per.getIdPersona());
            ps.setString(2, ar.getProvincia());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    } 
    /**
     * Metodo para obtener todos los arbitros
     * @return List Arbitros 
     */
    public static List<Arbitro> obtenerTodosArbitros(){
        con = Conexion.conectar();
        listaArbitros= new ArrayList<>();
        Arbitro arbitro=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Arbitro ar inner join Persona per on ar.id_Persona = per.id_Persona");
           
            while (rs.next()) {
                arbitro = new Arbitro(rs.getString(2), rs.getInt(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6),rs.getDate(7), rs.getString(8), 
                        rs.getString(9), rs.getString(10), rs.getInt(11));
                
                listaArbitros.add(arbitro);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }
        return listaArbitros;
    }
    /**
     * Metodo para modificar un arbitro ya existente
     * @param ar Arbitro a modificar
     */
    public static void modificarArbitro (Arbitro ar) {
        int id=PersonaDAO.obtenerIdPersona(ar.getDni());
        ar.setIdPersona(id);        
        
        PersonaDAO.modificarPersona(ar);
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Arbitro set Provincia=? where id_Persona=?");
            ps.setString(1, ar.getProvincia());
            ps.setInt(2, ar.getIdPersona());
            
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    }
    
    /** 
     * Metodo paara obtener un arbitro por su Dni
     * @param dni String, dni del arbitro a buscar
     * @return Arbitro, objeto Arbitro encontrado
     */
    public static Arbitro obtenerArbitroPorDni (String dni) {
        con = Conexion.conectar();
        Persona p =  PersonaDAO.obtenerPersonaPorDni(dni);
        Arbitro arbitro = null;
       
        
        try{
            PreparedStatement ps = con.prepareStatement("select * from Arbitro ar inner join Persona per on ar.id_Persona = per.id_Persona and ar.id_persona=?");
            ps.setInt(1, p.getIdPersona());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                arbitro = new Arbitro(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return arbitro;
    }
    
    /**
     * Metodo para recuperar un arbitro a partir de una persona
     * @param persona que se ha de buscar
     * @return Arbitro que corresponde a la persona introducida
     */
    public static Arbitro obtenerArbitroPorID (Persona persona) {
        con = Conexion.conectar();
      
        Arbitro arbitro = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from Arbitro jug inner join Persona per on jug.id_Persona = per.id_Persona and jug.id_persona=?");
            ps.setInt(1, persona.getIdPersona());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                arbitro = new Arbitro(persona, rs.getString(2));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return arbitro;
    }
}
