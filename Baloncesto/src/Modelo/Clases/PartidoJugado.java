/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *Esta clase representa la relación juega que relaciona jugador con partido
 * @author grupo_baloncesto
 */
public class PartidoJugado {
    
    /**
     * Atributos de la clase PartidoJugado
     */
    private int heGanado;
    private int idEquipo;
    private int idPartido;

    /**
     * Contructor de la clase PartidoJugado
     * @param idPartido int identificador de partido
     * @param idEquipo int identificador de equipo
     * @param heGanado int que me devuelve 0 si he perdido y 1 si he ganado
     */
    public PartidoJugado(int idPartido, int idEquipo, int heGanado) {
        this.heGanado = heGanado;
        this.idEquipo = idEquipo;
        this.idPartido = idPartido;
    }
    
    /**
     * 
     */
    
    public PartidoJugado(){
        
    }

    /**
     * 
     * @return int heganado
     */
    public int getHeGanado() {
        return heGanado;
    }
    /**
     * definimos el parametro heganado de partidoJugado para que pueda ser usado por todos los métodos
     * @param heGanado int que me devuelve 1 si gano y cero si pierdo
     */

    public void setHeGanado(int heGanado) {
        this.heGanado = heGanado;
    }
    /**
     * 
     * @return int id, devuelve el identificador del equipo
     */

    public int getIdEquipo() {
        return idEquipo;
    }
    /**
     * 
     * @param idEquipo int identificador del equipo
     */

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    /**
     * 
     * @return int id, devuelve el id del partido
     */

    public int getIdPartido() {
        return idPartido;
    }
    /**
     * 
     * @param idPartido int identificador de partido
     */

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
}
