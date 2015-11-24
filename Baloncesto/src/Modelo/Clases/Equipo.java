/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author grupo_baloncesto, esta clase representa la entidad Equipo
 */
public class Equipo {
    /**
     * Atributos de Equipo
     */
    private String nombre;
    private int categoria;
    private String provincia;
    private int idEquipo;
    
    /**
     * 
     * @param idEquipo Constructor de la clase Equipo con todos sus atributos
     * @param nombre
     * @param provincia
     * @param categoria 
     */

    public Equipo(int idEquipo, String nombre, String provincia, int categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.provincia = provincia;
        this.idEquipo = idEquipo;
    }
    
    /**
     * 
     */
    public Equipo(){
        
    }
    /**
     * 
     * @return devuelve el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre definimos el parametro nombre del equipo para que pueda ser usado por todos los métodos
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return devuelve la categoría del equipo
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * 
     * @param categoria definimos el parametro categoria del equipo para que pueda ser usado por todos los métodos
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * 
     * @return devuelve la provincia de donde es el equipo
     */
    public String getProvincia() {
        return provincia;
    }
    /**
     * 
     * @param provincia definimos el parametro provincia del equipo para que pueda ser usado por todos los métodos
     */

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * 
     * @return devuelve el id del equipo
     */

    public int getIdEquipo() {
        return idEquipo;
    }
    /**
     * 
     * @param idEquipo definimos el parametro id del equipo para que pueda ser usado por todos los métodos
     */

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    /**
     * 
     * @return 
     */

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", categoria=" + categoria + ", provincia=" + provincia + ", idEquipo=" + idEquipo + '}';
    }
    
             
}
