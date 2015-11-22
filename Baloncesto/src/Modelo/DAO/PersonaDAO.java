package Modelo.DAO;

import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author inftel07
 */
public class PersonaDAO {
    //private static Persona persona=null;
    static Connection con= null;
    private static List <Persona> listaPersonas=null;
    
    public PersonaDAO() {
    }
    
    public static void insertarPersona(Persona per) {
        con = Conexion.conectar();
        //persona = per;
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Persona (Id_Persona,Nombre,Apellidos,DNI,Fecha_Nac,Email,Contrasena,Telefono,Rol)"
                    + " values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, per.getIdPersona());
            ps.setString(2, per.getNombre());
            ps.setString(3, per.getApellidos());
            ps.setString(4, per.getDni());
            ps.setDate(5, new java.sql.Date(per.getFechaN().getTime()));
            ps.setString(6,per.getEmail());
            ps.setString(7,per.getContrasena());
            ps.setString(8, per.getTlf());
            ps.setInt(9, per.getRol());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static List<Persona> ObtenerTodasPersonas(){
        Persona persona=null;
        con=Conexion.conectar();
        listaPersonas= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from persona");
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            
            //hacer un switch cmo el q hizo la profe para distinguir q tipo de datos hay en la bbdd todo esto dentro de un for de number

            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                listaPersonas.add(persona);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaPersonas;

    }
    
    public static Persona ObtenerPersonaPorDNI(String dni) {
        con=Conexion.conectar();
        listaPersonas= new ArrayList<>();
        Persona persona=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from persona where DNI=?");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));               
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return persona;
    }
    

     public static void modificarPersona(Persona per) {
        con = Conexion.conectar();
        //Persona persona = per;
        try{
            PreparedStatement ps =  con.prepareStatement("update Persona set Nombre=?,Apellidos=?,DNI=?,Fecha_Nac=?,Email=?,Contrasena=?,Telefono=?,Rol=? where id_Persona=?");

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
        }
    }
}