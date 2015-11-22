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
    
    
    
    
}
