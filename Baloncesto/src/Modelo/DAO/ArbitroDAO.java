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
 *
 * @author inftel08
 */
public class ArbitroDAO {
    private static Connection con = null;
    private static List <Arbitro> listaArbitros=null;
    
    public static void insertarArbitro (Arbitro ar){
   
        PersonaDAO.insertarPersona(ar);
        
        Persona per = PersonaDAO.obtenerPersonaPorDni(ar.getDni());
        con = Conexion.conectar();

        try{
            PreparedStatement ps =  con.prepareStatement("insert into Arbitro (id_Persona, Provincia)"
                    + " values (?,?)");
            
            ps.setInt(1, per.getIdPersona());
            ps.setString(2, ar.getProvincia());
            //insertar id_equipo
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    } 
    
    public static List<Arbitro> obtenerTodosArbitros(){
        con = Conexion.conectar();
        listaArbitros= new ArrayList<>();
        Arbitro arbitro=null;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Arbitro ju inner join Persona per where ju.id_Persona = per.id_Persona");
            //ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                arbitro = new Arbitro(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                
                listaArbitros.add(arbitro);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        return listaArbitros;
    }
    
    public static void modificarArbitro (Arbitro ar) {
        
        //Persona per = new Persona(jug.getIdPersona(), jug.getNombre(), jug.getDNI(), jug.getFecha(), jug.getEmail(), jug.getContrasena(), jug.getRol());
        PersonaDAO.modificarPersona(ar);
        con = Conexion.conectar();
        
        //jugador = jug;
        try{
            PreparedStatement ps =  con.prepareStatement("update Arbitro set Numcolegiado=?,Provincia=? where id_Persona=?");
            ps.setString(1, ar.getProvincia());
            ps.setInt(2, ar.getIdPersona());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static Arbitro obtenerArbitroPorDni (String dni) {
        con = Conexion.conectar();
        Persona p =  PersonaDAO.obtenerPersonaPorDni(dni);
        Arbitro arbitro = null;
        
        
        try{
            PreparedStatement ps = con.prepareStatement("select * from Arbitro jug inner join Persona per where jug.id_Persona = per.id_Persona and jug.id_persona=?");
            ps.setInt(1, p.getIdPersona());//tb se puede hacer por idPersona cambiamos aqui idPersona y arriba tb per.idPersona=?
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                arbitro = new Arbitro(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return arbitro;
    }
}
