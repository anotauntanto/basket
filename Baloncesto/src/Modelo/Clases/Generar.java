/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author inftel06
 */
public class Generar {
    private ArrayList miArray;  
    private int[] arrayUno,arrayDos;
    private int i,j;
    public Generar(){
        
    }
    public void sigJornada(){
        miArray=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        i=0;j=0;
        Collections.shuffle(miArray);
        for (Iterator it = miArray.iterator(); it.hasNext();) {
            Equipo elem = (Equipo) it.next();
            if (elem.getCategoria()==1){
                arrayUno[i]=elem.getIdEquipo();
                i++;
            }
            else {
                arrayDos[j]=elem.getIdEquipo();
                j++;
            }
        }
        
        
        
             
    }
    
    public int sigJornadaN(){
        int jornada=PartidoDAO.obtenerJornadaActual();
        //ArrayList arrayPartidos=(ArrayList) PartidoJugadoDAO.obtenerTodosPartidosJugados();
        ArrayList arrayPartidos=(ArrayList) PartidoDAO.listarPartidosJornada(jornada);
        for (Iterator it=arrayPartidos.iterator();it.hasNext();){
            Partido elem=(Partido) it.next();
            if (elem.getResultado().equals("0")){
                 return 1; //Sale si la jornada actual no ha finalizado
                 
            }
        }
        
        return 0;
    }
    
}

 