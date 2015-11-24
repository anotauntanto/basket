/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.util.Date;

/**
 *Esta clase representa la entidad Partido
 * @author grupo_baloncesto
 */
public class Partido {
    
    /**
     * Atributos de la clase Partido
     */
    private Date fecha;
    private String localizacion;
    private int numJornada;
    private String resultado;
    private int idPartido;
    private int idArbitro;

    
    /**
     * Constructor de la clase Partido
     * @param idPartido int identificador de partido
     * @param resultado String resultado de un partido
     * @param numJornada int numero de jornada del partido
     * @param idArbitro int identificador de arbitro
     * @param fecha Date fecha del partido
     * @param localizacion String localización de partido
     */
    public Partido(int idPartido, String resultado, int numJornada, int idArbitro, Date fecha, String localizacion) {
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.numJornada = numJornada;
        this.resultado = resultado;
        this.idPartido = idPartido;
        this.idArbitro = idArbitro;
    }
    
    /**
     * 
     */
    public Partido(){
        
    }
    /**
     * 
     * @return Date fecha del partido
     */

    public Date getFecha() {
        return fecha;
    }
    /**
     * definimos el parametro fecha del partido para que pueda ser usado por todos los métodos
     * @param fecha Date fecha del partido
     */

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return String localizacion del partido
     */
    public String getLocalizacion() {
        return localizacion;
    }
    /**
     * definimos el parametro localizacion del partido para que pueda ser usado por todos los métodos
     * @param localizacion String localizacion 
     */

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
    /**
     * 
     * @return int numero de jornada
     */

    public int getNumJornada() {
        return numJornada;
    }
    /**
     * definimos el parametro numero de jornada del partido para que pueda ser usado por todos los métodos
     * @param numJornada int numero de jornada
     */

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    /**
     * 
     * @return String resultado del partido
     */
    public String getResultado() {
        return resultado;
    }
    
    /**
     * definimos el parametro resultado del partido para que pueda ser usado por todos los métodos
     * @param resultado String resultado del partido
     */

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * 
     * @return int id del partido
     */
    public int getIdPartido() {
        return idPartido;
    }
    
    /**
     * definimos el parametro id del partido para que pueda ser usado por todos los métodos
     * @param idPartido int identificador del partido
     */

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    /**
     * 
     * @return int id, devuelve el id del arbitro
     */

    public int getIdArbitro() {
        return idArbitro;
    }
    /**
     * definimos el parametro id del arbitro del partido para que pueda ser usado por todos los métodos
     * @param idArbitro int identificador del arbitro
     */

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }
    /**
     * 
     * @return Partido
     */

    @Override
    public String toString() {
        return "Partido{" + "fecha=" + fecha + ", localizacion=" + localizacion + ", numJornada=" + numJornada + ", resultado=" + resultado + ", idPartido=" + idPartido + ", idArbitro=" + idArbitro + '}';
    }
    
    
    
}
