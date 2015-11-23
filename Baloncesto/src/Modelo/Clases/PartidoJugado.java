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
public class PartidoJugado {
    private int heGanado;
    private int idEquipo;
    private int idPartido;

    public PartidoJugado(int heGanado, int idEquipo, int idPartido) {
        this.heGanado = heGanado;
        this.idEquipo = idEquipo;
        this.idPartido = idPartido;
    }
    
    public PartidoJugado(){
        
    }

    public int getHeGanado() {
        return heGanado;
    }

    public void setHeGanado(int heGanado) {
        this.heGanado = heGanado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
}
