/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Clases.Partido;
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
public class PartidoDAO {
    private static List <Partido> listaPartidos=null;
    static Connection con=null;
    
    
    public static void crearPartido (Partido par) {
        con = Conexion.conectar();
        //persona = per;
        
        try{
            PreparedStatement ps =  con.prepareStatement("insert into Partido (Id_Partido, Resultado, Jornada, Id_Arbitro, Fecha, Localizacion)"
                    + " values (INC_ID_PARTIDO.NextVal,?,?,?,?,?)");
            //ps.setInt(1, per.getIdPersona());
            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getNumJornada());
            ps.setInt(3, par.getIdArbitro());
            ps.setDate(4, new java.sql.Date(par.getFecha().getTime()));
            ps.setString(5, par.getLocalizacion());
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    public static List<Partido> listarPartidosJornada (int num_jornada) { //para emparejamientos
        con = Conexion.conectar();
        listaPartidos= new ArrayList<>();
        Partido partido = null; 
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Partido par where par.Jornada = "+num_jornada);
  
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                partido = new Partido (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4) ,rs.getDate(5), rs.getString(6));
                
                listaPartidos.add(partido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return listaPartidos;
        
    }
    
    public static Partido obtenerDatosPartido (int id_partido) { 
        con = Conexion.conectar();
        Partido partido = null; 
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Partido par where par.Id_Partido = "+id_partido);
  
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                partido = new Partido (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4) ,rs.getDate(5), rs.getString(6));

            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return partido;
        
    }
    
 
    public static void modificarResultado(Partido par) {
        con = Conexion.conectar();
        
        try{
            PreparedStatement ps =  con.prepareStatement("update Partido set Resultado=? where id_Partido = ?");
            
            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getIdPartido());
            ps.executeUpdate();
                
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Conexion.desconexion();
    }
    
    
}
