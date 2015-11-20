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
public class Estadisticas {
    private int idJugador;
    private int idPartido;
    private int puntos;
    private int asistencias;
    private int rebotes;

    public Estadisticas(int idJugador, int idPartido, int puntos, int asistencias, int rebotes) {
        this.idJugador = idJugador;
        this.idPartido = idPartido;
        this.puntos = puntos;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
    }
    
    public Estadisticas(){
        
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }
    
}
