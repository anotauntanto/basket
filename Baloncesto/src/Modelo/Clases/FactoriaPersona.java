/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

/**
 *
 * @author inftel08
 */
public class FactoriaPersona {
    
    public Persona makePersona(String rol) {
        switch (rol) {
        case "entrenador":
            return new Entrenador();
        case "arbitro":
            return new Arbitro();
        case "jugador":
            return new Jugador();
        case "organizacion":
            return new Organizacion();
        default:
            return null;
        }
    }
}
