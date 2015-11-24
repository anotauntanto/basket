/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Objects;

/**
 *Esta clase representa la entidad Equipo
 * @author grupo_baloncesto 
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
     * Constructor de la clase Equipo con todos sus atributos
     * @param idEquipo int identificador del equipo
     * @param nombre String nombre del equipo
     * @param provincia String provincia del equipo
     * @param categoria int categoria a la que pertenece el equipo
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
     * @return String nombre, devuelve el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * definimos el parametro nombre del equipo para que pueda ser usado por todos los métodos
     * @param nombre String nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return int categoria, devuelve la categoría del equipo
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * definimos el parametro categoria del equipo para que pueda ser usado por todos los métodos
     * @param categoria int categoria del equipo
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * 
     * @return String provincia, devuelve la provincia de donde es el equipo
     */
    public String getProvincia() {
        return provincia;
    }
    /**
     * definimos el parametro provincia del equipo para que pueda ser usado por todos los métodos
     * @param provincia String provincia
     */

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * 
     * @return int, devuelve el id del equipo
     */

    public int getIdEquipo() {
        return idEquipo;
    }
    /**
     * definimos el parametro id del equipo para que pueda ser usado por todos los métodos
     * @param idEquipo int identificador del equipo
     */

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    /**
     * 
     * @return Equipo
     */

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", categoria=" + categoria + ", provincia=" + provincia + ", idEquipo=" + idEquipo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (this.idEquipo != other.idEquipo) {
            return false;
        }
        return true;
    }
    
             
}
