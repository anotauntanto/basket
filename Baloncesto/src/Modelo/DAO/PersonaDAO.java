/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase PersonaDAO
 * @author grupo_baloncesto
 */


public class PersonaDAO {
    
    private static Connection con= null;
    private static List <Persona> listaPersonas=null;
    
    /**
     * Constructor por defecto
     */
    public PersonaDAO() {
    }
    
    /**
     * Metodo para insertar Persona
     * @param per Persona a insertar
     */
    public static void insertarPersona(Persona per) {
        con = Conexion.conectar();

        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Persona (Id_Persona,Nombre,Apellidos,DNI,Fecha_Nac,Email,Contrasena,Telefono,Rol)"
                    + " values (INC_ID_PERSONA.NextVal,?,?,?,?,?,?,?,?)");
        
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellidos());
            ps.setString(3, per.getDni());
            ps.setDate(4, new java.sql.Date(per.getFechaN().getTime()));
            ps.setString(5,per.getEmail());
            ps.setString(6,per.getContrasena());
            ps.setString(7, per.getTlf());
            ps.setInt(8, per.getRol());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
   
    }
    
    /**
     * Metodo para obtener todas las Personas
     * @return List Persona lista de todas las personas
     */
    public static List<Persona> obtenerTodasPersonas(){
        Persona persona=null;
        con=Conexion.conectar();
        listaPersonas= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from persona");

            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), 
                        rs.getString(3), rs.getString(4), rs.getDate(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getInt(9));
                listaPersonas.add(persona);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }
        
        return listaPersonas;

    }
    
    /**
     * Metodo para obtener una Persona por Dni
     * @param dni String, dni de la Persona a buscar
     * @return Persona buscada
     */
    public static Persona obtenerPersonaPorDni(String dni) {
        con=Conexion.conectar();
     
        Persona persona=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from persona where DNI=?");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));               
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return persona;
    }
    
    /**
     * Metodo para modificar Persona
     * @param per Persona a modificar
     */
    public static void modificarPersona(Persona per) {
    
         con = Conexion.conectar();
    
        try{
            PreparedStatement ps =  con.prepareStatement("update Persona set Nombre=?,Apellidos=?,DNI=?,Fecha_Nac=?,Email=?,Contrasena=?,Telefono=?,Rol=? where id_Persona=?");
            System.out.println("Id persona" + per.getIdPersona());
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellidos());
            ps.setString(3, per.getDni());
            ps.setDate(4, new java.sql.Date(per.getFechaN().getTime()));
            ps.setString(5,per.getEmail());
            ps.setString(6,per.getContrasena());
            ps.setString(7, per.getTlf());
            ps.setInt(8, per.getRol());
            ps.setInt(9, per.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    }
    
    /**
     * Metodo para obtener Id Persona por Dni
     * @param dni String, dni a buscar
     * @return int, id de la Persona buscada
     */
    public static int obtenerIdPersona(String dni){
        int id=0;
        
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select id_persona from Persona where dni=?");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                id=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return id;
    }
}
