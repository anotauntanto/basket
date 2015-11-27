/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import Modelo.Clases.Arbitro;
import Modelo.Clases.Equipo;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.ArbitroDAO;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import java.io.IOException;
import java.sql.Array;
import static java.sql.Types.INTEGER;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Clase Generar, incluye metodos para generar la competicion y para avanzar en las siguientes jornadas 
 * @author grupo_baloncesto
 */
public class Generar {
    private int i,j;
    /**
     * Constructor generico de la clase Generar
     * 
     */
    public Generar(){
        
    }
    
    /**
     * Metodo general que llama el controlador que generará la siguiente jornada
     * @return int 0, si genera la jornada o -1 en otro caos 
     */
    
    public int generarSigJornada(){
        int sigJ=sigJornadaN();
        if (sigJ==-1){ //NO hago nada porque no ha finalizado
            return -1;
        }
        //Si ha terminado
        else if (sigJ==0){ 
            primeraJornada();
            
        } else{ //A partir de jornada 1
            generarJornadaN(sigJ);
        }
        return 0;
    }
    
    /**
     * Metodo que genera ronda previa, o primera jornada con mismo numero de equipos con categoria distintas  
     * @return int 1, si ronda previa o 0, si tengo igualdad de categorias
     */
    
    public int primeraJornada(){
        int equiposFuera=numEquiposCopaPrevia();
        int numSacados=0;
        ArrayList arrayEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        ArrayList arrayEquiposPrevia=new ArrayList();
        Collections.shuffle(arrayEquipos);  //Se baraja la lista de los equipos
        Iterator it=arrayEquipos.iterator();
        while (it.hasNext() && numSacados<equiposFuera){
            Equipo elem=(Equipo) it.next();
            numSacados++;
            arrayEquiposPrevia.add(elem);
        }
        if (equiposFuera!=0){
            sigJornada((ArrayList) arrayEquiposPrevia,false);
            return 1;
        }
        else { //Supongo mismo numero de Cat 1 y Cat 2
            sigJornada((ArrayList) arrayEquipos,true);
            return 0;
        }
        
    }
    
    /**
     * Metodo que genera y guarda en la BBDD la siguiente jornada teniendo en cuenta si los equipos se distinguen por categorias. 
     * @param miArray ArrayList de equipos que jugarán la siguiente jornada
     * @param conCategoria Boolean si true, los equipos se emparejan por categorias
     */
    public void sigJornada(ArrayList miArray,boolean conCategoria){
        
       
        List<Integer> arrayUno=new ArrayList<>();
        List<Integer> arrayDos=new ArrayList<>();
        
        for (Iterator it = miArray.iterator(); it.hasNext();) {
            Equipo elem = (Equipo) it.next();
            if (conCategoria){   //Entra si hay mismo numero de equipos con categoria 1 y 2
                if (elem.getCategoria()==1){
        
                    i=(Integer) elem.getIdEquipo();
        
                    arrayUno.add(i);        

                }
                else {
                    
                    arrayDos.add(elem.getIdEquipo());
                   
                }
                
            }
            else {
                
              
                arrayUno.add(elem.getIdEquipo());
                elem=(Equipo) it.next();
                arrayDos.add(elem.getIdEquipo());
          
            }
            
        }
        if (arrayUno.size()!=arrayDos.size()){ //Si distinto tamaño de categoria 1 y 2 sale
                    return;
        }
        int sigJornada=PartidoDAO.obtenerJornadaActual()+1;
        
        //Fija la fecha actual
        Calendar cal = Calendar.getInstance();
        Date fecha = cal.getTime();
    
        //Asignar Arbitros
        
        ArrayList <Arbitro> misArbitros = (ArrayList <Arbitro>) ArbitroDAO.obtenerTodosArbitros();
     
        Partido nuevoPartido=new Partido();
        PartidoJugado nuevoPartidoJugado = new PartidoJugado();
        for (i=0;i<arrayUno.size();i++){  //Para cada emparejamiento genera un partido y su equivalencia en la BBDD
            nuevoPartido.setFecha(fecha);
            nuevoPartido.setLocalizacion("Malaga");
            nuevoPartido.setNumJornada(sigJornada);
            
            //Asiganr Arbitros Azar
            int azar= (int) (Math.random() * misArbitros.size());
            System.out.println("tam: "+misArbitros.size()+ "num"+azar);
            nuevoPartido.setIdArbitro(misArbitros.get(azar).getIdPersona());
           
            nuevoPartido.setResultado("0");
            PartidoDAO.crearPartido(nuevoPartido);
            nuevoPartidoJugado.setHeGanado(2);
            //Primer PartidoJugado
            nuevoPartidoJugado.setIdPartido(PartidoDAO.obtenerIdActual());
            nuevoPartidoJugado.setIdEquipo(arrayUno.get(i));
            PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
            //Segundo PartidoJugado
            nuevoPartidoJugado.setIdEquipo(arrayDos.get(i));
            PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
     
        }
        
             
    }
    
    /**
     * Metodo que retorna el numero de la siguiente jornada o -1 si no ha finalizado la actual
     * @return int jornada actual o -1 si no ha terminado
     */
    
    public int sigJornadaN(){
        int jornada=PartidoDAO.obtenerJornadaActual();
  
        ArrayList arrayPartidos=(ArrayList) PartidoDAO.listarPartidosJornada(jornada);
        for (Iterator it=arrayPartidos.iterator();it.hasNext();){
            Partido elem=(Partido) it.next();
            if (elem.getResultado().equals("0")){
                 return -1; //Sale si la jornada actual no ha finalizado
                 
            }
        }
        
        return jornada; //Si ha terminado me devuelve la jornada actual
    }
    
    //Retorna el numero de equipos a sacar
    /**
     * Metodo que calcula en numero de equipos que deben jugar la ronda previa de una copa
     * @return int con numero de equipos que jugaran la ronda previa
     */
    public int numEquiposCopaPrevia(){
        ArrayList arrayEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        int tamano=arrayEquipos.size();

       
        int num;
        int n=0;
        while (Math.pow(2,n)<=tamano){
            n++;
        }
        num=tamano-(int) Math.pow(2,n-1);
        num=2*num;
        return num;
        
    }
    
    /**
     * Metodo a llamar para generar la jornada N-esima
     * @param jornada_ant int, indica el numero de la jornada anterior
     * @return int 0, si se ha de generar la siguiente jornada o -1, si la liga ya ha finalizado
     */
   
    public int generarJornadaN(int jornada_ant){
        
        ArrayList idEquiposGanados=(ArrayList) EquipoDAO.listarEquiposGanadoresJornadaN(jornada_ant);
        
       
        if (jornada_ant==1 && numEquiposCopaPrevia()!=0){ //Se comprueba si la ronda anterior era ronda previa
            ArrayList idEquiposPerdidos=(ArrayList) EquipoDAO.listarEquiposPerdidosJornadaN(jornada_ant);
            ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
            todosEquipos.removeAll(idEquiposPerdidos);
           //Crea una lista con los campeones de la ronda previa y los que no jugaron
            Collections.shuffle(todosEquipos);
            sigJornada(todosEquipos,false);
            
        } else{
             if (idEquiposGanados.size()!=1){  //Si hay mas de un ganador sigo generando
                 sigJornada(idEquiposGanados,false);
             }
             else {  //Liga finalizada
                 return -1;
             }
       
        }
        
        return 0;
    }
    

    
    /**
     * Metodo General para generar una competicion tipo Liga
     * @return 0 si genera la liga, -1 en caso contrario
     */
    
    public int generarLiga(){
        int sigJ=sigJornadaN();
        
        if (sigJ==-1){ //No ha finalizado
            return -1;    
        
        }
            generarLigaTotal();
        
        return 0;
    }
    
    /**
     * Metodo que genera todos los partidos que se han de jugar en la liga
     */
    
    public void generarLigaTotal(){
        ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        int tamano=todosEquipos.size();
       
        Partido nuevoPartido=new Partido();
        PartidoJugado nuevoPartidoJugado = new PartidoJugado();
        Calendar cal = Calendar.getInstance();
        Date fecha = cal.getTime();

        
        j=0;
        ArrayList <Arbitro> misArbitros = (ArrayList <Arbitro>) ArbitroDAO.obtenerTodosArbitros();
        for (i=0;i<tamano-1;i++){
            for (j=i+1;j<tamano;j++){  //Creo la combinacion de todos los partidos
                nuevoPartido.setFecha(fecha);
                nuevoPartido.setLocalizacion("Malaga");
                nuevoPartido.setNumJornada(0); //Fijo solo una jornada para la liga num=0
                //Asiganr Arbitros Azar
                int azar= (int) (Math.random() * misArbitros.size());
                
                nuevoPartido.setIdArbitro(misArbitros.get(azar).getIdPersona());

                //Fin arbitros
                
                nuevoPartido.setResultado("0");
                //nuevoPartido.setIdPartido(1);
                PartidoDAO.crearPartido(nuevoPartido);
                nuevoPartidoJugado.setHeGanado(2);
                nuevoPartidoJugado.setIdPartido(PartidoDAO.obtenerIdActual());
                //Primer PartidoJugado
                Equipo miEquipo=(Equipo) todosEquipos.get(i);
                nuevoPartidoJugado.setIdEquipo(miEquipo.getIdEquipo());
                PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
                //Segundo PartidoJugado
                miEquipo=(Equipo) todosEquipos.get(j);
                nuevoPartidoJugado.setIdEquipo(miEquipo.getIdEquipo());
                PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
            }
        }
        
    }
    
    /**
     * Metodo que genera los playoff de un numero de equipos
     * @param numEquiposCopa int, numero de equipos que jugarán los playoff
     */
    
    public void generarPlayoff(int numEquiposCopa){
        ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        Collections.sort(todosEquipos);
       
        //Se queda con la lista de los primeros clasificados
        ArrayList<Equipo> equiposClasificados= new ArrayList<>(todosEquipos.subList(0, numEquiposCopa));
        
        //Se genera un cuadro de playoff con cabezas de serie
        Iterator iter=equiposClasificados.iterator();
        i=0;
        while (iter.hasNext()){
            Equipo elem=(Equipo) iter.next();
            
            if (i<numEquiposCopa/2){
                elem.setCategoria(1);
            }
            else {
                elem.setCategoria(2);
            }
            EquipoDAO.modificarEquipo(elem);
            i++;
        }
        //genera siguiente jornada con estructura de copa
       
        sigJornada(equiposClasificados,true);
        
    }
       
    /**
     * Metodo para generar la jornada N-esima en un playoff
     * @param jornada_ant int, numero de la jornada anterior
     * @return 0 si se ha generado la jornada, -1 en otro caso
     */
       //Metodo Genera Copa Playoff
    public int generarJornadaPlayoffN(int jornada_ant){
        
        ArrayList idEquiposGanados=(ArrayList) EquipoDAO.listarEquiposGanadoresJornadaN(jornada_ant);
        
            if (idEquiposGanados.size()!=1){
                 sigJornada(idEquiposGanados,false);
             }
             else {
                 return -1;
             }
 
            return 0;
    }
    
    

}

 
