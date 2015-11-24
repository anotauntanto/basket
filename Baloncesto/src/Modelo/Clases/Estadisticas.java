/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author grupo_baloncesto, esta clase representa la relación juega, que relaciona jugador con partido
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
     * 
     * @param idJugador Constructor de la clase Estadisticas con todos sus parámetros incluidos los derivados
     * @param idPartido
     * @param puntos
     * @param asistencias
     * @param rebotes 
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
     * @return Me devuelve el id del jugador
     */

    public int getIdJugador() {
        return idJugador;
    }
    /**
     * 
     * @param idJugador definimos el parametro id del jugador para que pueda ser usado por todos los métodos
     */

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    /**
     * 
     * @return devuelve el id del partido
     */

    public int getIdPartido() {
        return idPartido;
    }
    /**
     * 
     * @param idPartido definimos el parametro id del partido para que pueda ser usado por todos los métodos
     */

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    /**
     * 
     * @return devuelve los puntos
     */

    public int getPuntos() {
        return puntos;
    }
    /**
     * 
     * @param puntos definimos el parametro ipuntos para que pueda ser usado por todos los método
     */

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    /**
     * 
     * @return devuelve las asistencias
     */

    public int getAsistencias() {
        return asistencias;
    }
    /**
     * 
     * @param asistencias definimos el parametro asistencias para que pueda ser usado por todos los método
     */

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }
    /**
     * 
     * @return devuelve los rebotes
     */

    public int getRebotes() {
        return rebotes;
    }
    /**
     * 
     * @param rebotes definimos el parametro rebotes para que pueda ser usado por todos los método
     */

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }
    /**
     * 
     * @return 
     */

    @Override
    public String toString() {
        return "Estadisticas{" + "idJugador=" + idJugador + ", idPartido=" + idPartido + ", puntos=" + puntos + ", asistencias=" + asistencias + ", rebotes=" + rebotes + '}';
    }
    
    
}
