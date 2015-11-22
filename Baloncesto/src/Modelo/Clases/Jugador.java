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
public class Jugador extends Persona{
    private double altura;
    private int dorsal;

    public Jugador(double altura, int dorsal, int idPersona,String nombre, String apellidos, String dni, Date fechaN, String email, String contrasena, String tlf, int rol) {
        //super(nombre, apellidos, dni, fechaN, tlf, email, contrasena, idPersona);
        super(idPersona, nombre, apellidos, dni, fechaN, email, contrasena, tlf, rol);
        this.altura = altura;
        this.dorsal = dorsal;
    }

    public Jugador(double altura, int dorsal) {
        this.altura = altura;
        this.dorsal = dorsal;
    }
    
    public Jugador(){
        
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "Jugador{" + "altura=" + altura + ", dorsal=" + dorsal + '}';
    }
    
    
}
