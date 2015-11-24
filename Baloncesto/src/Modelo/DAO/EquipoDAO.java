/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Equipo;
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
public class EquipoDAO {
    private static Connection con;
    private static List<Equipo> listaEquipos;
    
    public static void insertarEquipo(Equipo eq) {
        con = Conexion.conectar();
        //persona = per;
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Equipo (Id_Equipo, Nombre, Provincia, Categoria)"
                    + " values (INC_ID_EQUIPO.NextVal,?,?,?)");
            //ps.setInt(1, per.getIdPersona());
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getProvincia());
            ps.setInt(3, eq.getCategoria());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    
    public static void modificarEquipo(Equipo eq) {
        con = Conexion.conectar();
        
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Equipo set Nombre=?, Provincia=?, Categoria=?");
            
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getProvincia());
            ps.setInt(3, eq.getCategoria());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    public static List<Equipo> obtenerTodosEquipos(){
        Equipo equipo=null;
        con=Conexion.conectar();
        listaEquipos= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Equipo");
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            
            //hacer un switch cmo el q hizo la profe para distinguir q tipo de datos hay en la bbdd todo esto dentro de un for de number

            while (rs.next()) {
                equipo = new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                listaEquipos.add(equipo);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaEquipos;
    }
    
    public static int obtenerIdEquipo(String nombre){
        int id=0;
        
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select id_equipo from Equipo where nombre=?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            while (rs.next()) {
                
                id=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }
    
    public static String obtenerNombreEquipo(int id){
        String res = null;
        con=Conexion.conectar();
        try{
            
            PreparedStatement ps = con.prepareStatement("select nombre from Equipo where id_equipo=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int number = rsmd.getColumnCount();
            while (rs.next()) {
                
                res=rs.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
     public static List<Integer> listarEquiposGanadoresJornadaN(int num_jornada) { //para emparejamientos
        con = Conexion.conectar();
        //listaIdEquiposGanados = new ArrayList<>();
        List<Integer> listaIdEquiposGanados = new ArrayList<>();
        

        try {
            PreparedStatement ps = con.prepareStatement("select id_equipo from Partido P, Equipo_partido P where P.jornada=? and P.id_partido=E.id_partido and"
                    + " E.he_ganado=1");
            ps.setInt(1, num_jornada);
            ResultSet rs = ps.executeQuery();
            //Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("select * from Partido P, Equipo_Partido E where p.Jornada = " + num_jornada+" and ");

            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                

                listaIdEquiposGanados.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listaIdEquiposGanados;

    }
}
