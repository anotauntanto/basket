/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Excepciones.EquipoException;
import Modelo.Clases.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Clase EquipoDAO
 * @author grupo_baloncesto
 */
public class EquipoDAO {
    private static Connection con;
    private static List<Equipo> listaEquipos;
    
    /**
     * Metodo para insertar Equipo en la Base de Datos
     * @param eq Equipo a insertar
     */
    public static void insertarEquipo(Equipo eq) {
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Equipo (Id_Equipo, Nombre, Provincia, Categoria)"
                    + " values (INC_ID_EQUIPO.NextVal,?,?,?)");        
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getProvincia());
            ps.setInt(3, eq.getCategoria());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
    }
    
    /**
     * Metodo para modificar un Equipo existente
     * @param eq Equipo a modificar
     */
    public static void modificarEquipo(Equipo eq) {
        con = Conexion.conectar();
        
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Equipo set Nombre=?, Provincia=?, Categoria=? where id_equipo = ?");
        
            
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getProvincia());
            ps.setInt(3, eq.getCategoria());
            ps.setInt(4, eq.getIdEquipo());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
    
    }
    
    /**
     * Metodo para obtener todos los equipos
     * @return List<Equipo> 
     */
    public static List<Equipo> obtenerTodosEquipos(){
        Equipo equipo=null;
        con=Conexion.conectar();
        listaEquipos= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Equipo");

            while (rs.next()) {
                equipo = new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                listaEquipos.add(equipo);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return listaEquipos;
    }
    
    /**
     * Metodo para contar el numero de Equipos
     * @return int, numero de equipos en la Base de Datos
     */
    public static int contarEquipos(){
        int contador = 0;
        con=Conexion.conectar();

        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from Equipo");

            while (rs.next()) {
                contador = rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        return contador;
    }
    
    /**
     * Metodo para obtener el id de un Equipo
     * @param nombre String Nombre del equipo a buscar  
     * @return int, id del Equipo buscado
     */
    public static int obtenerIdEquipo(String nombre){
        int id=0;
        
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select id_equipo from Equipo where nombre=?");
            ps.setString(1, nombre);
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
    
    /**
     * Metodo para comprobar si existe un Equipo por su nombre
     * @param nombre String, Equipo a buscar
     * @throws EquipoException Se lanza si el Equipo no existe
     */
    public static void comprobarEquipo(String nombre) throws EquipoException{
        int cont = 0;
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select count(id_equipo) from Equipo where nombre=?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                cont=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        if (cont == 0) { //el equipo no existe
            throw new EquipoException();
        }
        
       
    }
    
    /**
     * Metodo para comprobar si un Equipo no existe 
     * @param nombre String, Equipo a buscar
     * @throws EquipoException Se lanza si el Equipo existe
     */
    public static void comprobarEquipov2(String nombre) throws EquipoException{
        int cont = 0;
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select count(id_equipo) from Equipo where nombre=?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
          
            while (rs.next()) {
                
                cont=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        
        if (cont != 0) { //el equipo si existe
            throw new EquipoException();
        }
        
       
    }
    
    /**
     * Metodo para obtener Nombre del Equipo por id
     * @param id, id del Equipo a buscar 
     * @return String, nombre del Equipo buscado
     */
    public static String obtenerNombreEquipo(int id){
        String res = null;
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select nombre from Equipo where id_equipo=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                res=rs.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }
        return res;
    }
    
    /**
     * Metodo para listar los Equipos ganadores en una jornada
     * @param num_jornada int, jornada a buscar
     * @return List<Equipo>, lista de Equipos buscados
     */
    public static List<Equipo> listarEquiposGanadoresJornadaN(int num_jornada) { 
        con = Conexion.conectar();
        listaEquipos = new ArrayList<>();
        
        Equipo equipo=null;

        try {
            PreparedStatement ps = con.prepareStatement("select * from Partido P, Equipo_partido E where P.jornada=? and P.id_partido=E.id_partido and"
                    + " E.he_ganado=1");
            ps.setInt(1, num_jornada);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                System.out.println("Entero"+rs.getInt(8));
                equipo = new Equipo(rs.getInt(8), "", "", 1);  //Insertamos solo el id_equipo porque no necesitamos nada mas

                listaEquipos.add(equipo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }

        return listaEquipos;

    }
     
    /**
     * Metodo para listar los Equipos perdedores en una jornada
     * @param num_jornada int, jornada a buscar
     * @return List<Equipo>, lista de Equipos buscados 
     */
    public static List<Equipo> listarEquiposPerdidosJornadaN(int num_jornada) { 
        con = Conexion.conectar();
        listaEquipos = new ArrayList<>();
        
        Equipo equipo=null;

        try {
            PreparedStatement ps = con.prepareStatement("select * from Partido P, Equipo_partido E, Equipo EQ where P.jornada=? and P.id_partido=E.id_partido "
                    + "and E.he_ganado=0 and EQ.id_equipo=E.id_equipo");
            ps.setInt(1, num_jornada);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                
                equipo = new Equipo(rs.getInt(8),rs.getString(11), rs.getString(12), rs.getInt(13)); 

                listaEquipos.add(equipo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            Conexion.desconexion(con);
        }

        return listaEquipos;

    }
    
    
}
