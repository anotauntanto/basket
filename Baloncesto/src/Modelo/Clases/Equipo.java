/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author inftel06
 */
public class Equipo {
    private String nombre;
    private int categoria;
    private String provincia;
    private int idEquipo;

    public Equipo(String nombre, int categoria, String provincia, int idEquipo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.provincia = provincia;
        this.idEquipo = idEquipo;
    }
    
    public Equipo(){
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", categoria=" + categoria + ", provincia=" + provincia + ", idEquipo=" + idEquipo + '}';
    }
    
             
}
