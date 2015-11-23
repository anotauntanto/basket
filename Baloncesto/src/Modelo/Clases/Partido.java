/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *
 * @author inftel06
 */
public class Partido {
    private Date fecha;
    private String localizacion;
    private int numJornada;
    private String resultado;
    private int idPartido;
    private int idArbitro;

    public Partido(Date fecha, String localizacion, int numJornada, String resultado, int idPartido, int idArbitro) {
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.numJornada = numJornada;
        this.resultado = resultado;
        this.idPartido = idPartido;
        this.idArbitro = idArbitro;
    }
    
    public Partido(){
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    @Override
    public String toString() {
        return "Partido{" + "fecha=" + fecha + ", localizacion=" + localizacion + ", numJornada=" + numJornada + ", resultado=" + resultado + ", idPartido=" + idPartido + ", idArbitro=" + idArbitro + '}';
    }
    
    
    
}
