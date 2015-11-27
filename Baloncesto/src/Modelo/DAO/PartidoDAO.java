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
 * Clase PartidoDAO
 * @author grupo_baloncesto
 */
public class PartidoDAO {

    private static List<Partido> listaPartidos = null;
    static Connection con = null;
    
    /**
     * Metodo para crear un Partido
     * @param par Partido a crear
     */
    public static void crearPartido(Partido par) {
        con = Conexion.conectar();

        try {
            PreparedStatement ps = con.prepareStatement("insert into Partido (Id_Partido, Resultado, Jornada, Id_Arbitro, Fecha, Localizacion)"
                    + " values (INC_ID_PARTIDO.NextVal,?,?,?,?,?)");
            
            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getNumJornada());
            ps.setInt(3, par.getIdArbitro());
            ps.setDate(4, new java.sql.Date(par.getFecha().getTime()));
            ps.setString(5, par.getLocalizacion());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }
        
    }
    
    /**
     * Metodo para listar Partidos por jornada
     * @param num_jornada int, numero de jornada a buscar
     * @return List<Partido>, lista de Partidos buscados
     */
    public static List<Partido> listarPartidosJornada(int num_jornada) { 
        con = Conexion.conectar();
        listaPartidos = new ArrayList<>();
        Partido partido = null;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Partido par where par.Jornada = " + num_jornada);
            
            while (rs.next()) {
                partido = new Partido(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6));

                listaPartidos.add(partido);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }

        return listaPartidos;

    }
    
    /**
     * Metodo para obtener los datos de un Partido
     * @param id_partido int, id del Partido a buscar
     * @return Partido buscado
     */
    public static Partido obtenerDatosPartido(int id_partido) {
        con = Conexion.conectar();
        Partido partido = null;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Partido where Id_Partido = " + id_partido);

            while (rs.next()) {
                partido = new Partido(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }

        return partido;

    }
    
    /**
     * Metodo para obtener Jornada Actual
     * @return int, Jornada Actual
     */
    public static int obtenerJornadaActual() {
        con = Conexion.conectar();
      
        int jornadaAct = 0;
        try {
            PreparedStatement ps = con.prepareStatement("select max(jornada) from partido");

            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                jornadaAct = rs.getInt(1);
            }
        } catch (SQLException ex) {
  
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }

        return jornadaAct;
    }
    
    /**
     * Metodo para modificar un resultado
     * @param par Partido que va a modificar su resultado
     */
    public static void modificarResultado(Partido par) {
        con = Conexion.conectar();

        try {
            PreparedStatement ps = con.prepareStatement("update Partido set Resultado=? where id_Partido = ?");

            ps.setString(1, par.getResultado());
            ps.setInt(2, par.getIdPartido());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }
     
    }
    
    /**
     * Metodo para obtener el Id Actual
     * @return int, retorna el maximo valor del Id de Partido
     */
    public static int obtenerIdActual() {
        con = Conexion.conectar();
      
        int IdAct = 0;
        try {
            PreparedStatement ps = con.prepareStatement("select max(id_partido) from partido");

            ResultSet rs = ps.executeQuery();
         
            while (rs.next()) {
                IdAct = rs.getInt(1);
            }
        } catch (SQLException ex) {
        
            System.out.println(ex.getMessage());
        } finally {
            Conexion.desconexion(con);
        }

        return IdAct;
    }

}
