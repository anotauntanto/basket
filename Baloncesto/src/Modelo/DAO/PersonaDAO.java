package Modelo.DAO;

import Modelo.Clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static Persona persona=null;
    static Connection con= null;
    private static List <Persona> listaPersonas=null;
    
    public PersonaDAO() {
    }
    
    public static void insertarPersona(Persona per) {
        con = Conexion.conectar();
        persona = per;
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Persona (Nombre,DNI,Fecha,Email,Contrasena,Rol)"
                    + " values (?,?,?,?,?,?)");
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getDNI());
            ps.setDate(3, new java.sql.Date(per.getFecha().getTime()));
            ps.setString(4,per.getEmail());
            ps.setString(5,per.getContrasena());
            ps.setInt(6, per.getRol());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static List<Persona> ObtenerTodasPersonas(){
        
        con=Conexion.conectar();
        listaPersonas= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from persona");
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();

            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));
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
        persona=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from persona where DNI=?");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            while (rs.next()) {
                persona = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return persona;
    }
    

     public static void modificarPersona(Persona per) {
        con = Conexion.conectar();
        persona = per;
        try{
            PreparedStatement ps =  con.prepareStatement("update Persona set Nombre=?,DNI=?,Fecha=?,Email=?,Contrasena=?,Rol=? where idPersona=?");
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getDNI());
            ps.setDate(3, new java.sql.Date(per.getFecha().getTime()));
            ps.setString(4,per.getEmail());
            ps.setString(5,per.getContrasena());
            ps.setInt(6, per.getRol());
            ps.setInt(7, per.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}