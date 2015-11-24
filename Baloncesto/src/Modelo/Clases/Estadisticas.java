/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *Esta clase representa la relación juega, que relaciona jugador con partido
 * @author grupo_baloncesto
 */
public class Estadisticas {
    /**
     * Atributos de la entidad Estadisticas
     */
    private int idJugador;
    private int idPartido;
    private int puntos;
    private int asistencias;
    private int rebotes;
    
    /**
     * Constructor de la clase Estadisticas con todos sus parámetros incluidos los derivados
     * @param idJugador int identificador de jugador
     * @param idPartido int identificador de partido
     * @param puntos int puntos que anota un jugador por partido
     * @param asistencias int asistencias de un jugador en un partido
     * @param rebotes int rebotes de un jugador en un partido
     */

    public Estadisticas(int idJugador, int idPartido, int puntos, int asistencias, int rebotes) {
        this.idJugador = idJugador;
        this.idPartido = idPartido;
        this.puntos = puntos;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
    }
    
    /**
     * 
     */
    public Estadisticas(){
        
    }
    /**
     * 
     * @return int id, me devuelve el id del jugador
     */

    public int getIdJugador() {
        return idJugador;
    }
    /**
     * definimos el parametro id del jugador para que pueda ser usado por todos los métodos
     * @param idJugador int identificador de jugador
     */

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    /**
     * 
     * @return int id, devuelve el id del partido
     */

    public int getIdPartido() {
        return idPartido;
    }
    /**
     * definimos el parametro id del partido para que pueda ser usado por todos los métodos
     * @param idPartido int identificador de partido
     */

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    /**
     * 
     * @return int puntos, devuelve los puntos
     */

    public int getPuntos() {
        return puntos;
    }
    /**
     * definimos el parametro ipuntos para que pueda ser usado por todos los método
     * @param puntos int puntos anotados por un jugador
     */

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    /**
     * 
     * @return int asistencias de un jugador
     */

    public int getAsistencias() {
        return asistencias;
    }
    /**
     * definimos el parametro asistencias para que pueda ser usado por todos los método
     * @param asistencias int asistencias de un jugador
     */

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }
    /**
     * 
     * @return int rebotes de un jugador
     */

    public int getRebotes() {
        return rebotes;
    }
    /**
     * definimos el parametro rebotes para que pueda ser usado por todos los método
     * @param rebotes int rebotes de un jugador
     */

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }
    /**
     * 
     * @return Estadisticas de un jugador
     */

    @Override
    public String toString() {
        return "Estadisticas{" + "idJugador=" + idJugador + ", idPartido=" + idPartido + ", puntos=" + puntos + ", asistencias=" + asistencias + ", rebotes=" + rebotes + '}';
    }
    
    
}
