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
import static java.sql.Types.INTEGER;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author inftel06
 */
public class Generar {
    //private ArrayList miArray;  
    //private int[] arrayUno,arrayDos;
    private int i,j;
    public Generar(){
        
    }
    //Entro conCategoria a true si hay mismo numero de equipos con categoria, false si no 
    public void sigJornada(ArrayList miArray,boolean conCategoria){
        //miArray=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        i=0;j=0;
        List<Integer> arrayUno=new ArrayList<>();
        List<Integer> arrayDos=new ArrayList<>();
        //int[] arrayUno=new int[3];
        //int[] arrayDos=null;
        //Collections.shuffle(miArray);
        for (Iterator it = miArray.iterator(); it.hasNext();) {
            Equipo elem = (Equipo) it.next();
            if (conCategoria){
                if (elem.getCategoria()==1){
                    System.out.println(elem.getIdEquipo());
                    i=(Integer) elem.getIdEquipo();
                    //arrayUno.add(elem.getIdEquipo());
                    arrayUno.add(i);
                    
                    //i++;
                }
                else {
                    
                    arrayDos.add(elem.getIdEquipo());
                    //arrayDos[j]=elem.getIdEquipo();
                    //j++;
                }
            }
            else {//Si categoria copio
                
                //arrayUno[i]=elem.getIdEquipo();
                arrayUno.add(elem.getIdEquipo());
                elem=(Equipo) it.next();
                arrayDos.add(elem.getIdEquipo());
                //arrayDos[i]=elem.getIdEquipo();
                //i++;
            }
            j++;
        }
        int sigJornada=PartidoDAO.obtenerJornadaActual()+1;
        //MiFecha
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1985,10,1);
        Date fecha = cal.getTime();
        //Date fecha=null;
        
        Partido nuevoPartido=new Partido();
        PartidoJugado nuevoPartidoJugado = new PartidoJugado();
        for (i=0;i<arrayUno.size();i++){
            nuevoPartido.setFecha(fecha);
            nuevoPartido.setLocalizacion("Malaga");
            nuevoPartido.setNumJornada(sigJornada);
            nuevoPartido.setIdArbitro(33);
            nuevoPartido.setResultado("0");
            //nuevoPartido.setIdPartido(1);
            PartidoDAO.crearPartido(nuevoPartido);
            nuevoPartidoJugado.setHeGanado(2);
            nuevoPartidoJugado.setIdPartido(PartidoDAO.obtenerIdActual());
            nuevoPartidoJugado.setIdEquipo(arrayUno.get(i));
            PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
            //Segundo PartidoJugado
            nuevoPartidoJugado.setIdEquipo(arrayDos.get(i));
            PartidoJugadoDAO.crearPartidoJugado(nuevoPartidoJugado);
     
        }
        
        
        
        
             
    }
    //Delvuelve la jornada a crear o cero si no ha terminado
    public int sigJornadaN(){
        int jornada=PartidoDAO.obtenerJornadaActual();
        System.out.println("JorActu: "+jornada);
        //ArrayList arrayPartidos=(ArrayList) PartidoJugadoDAO.obtenerTodosPartidosJugados();
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
    //Para saber si necesito una ronda previa
    public int primeraJornada(){
        int equiposFuera=numEquiposCopaPrevia();
        int numSacados=0;
        ArrayList arrayEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        ArrayList arrayEquiposPrevia=new ArrayList();
        Collections.shuffle(arrayEquipos);
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
        else { //Supongo Mismo numero de Cat 1 y Cat 2
            sigJornada((ArrayList) arrayEquipos,true);
            return 0;
        }
        
        //return 0;
        
    }
    
    //Genera la jornada N-esima
    public int generarJornadaN(int jornada_ant){
        
        ArrayList idEquiposGanados=(ArrayList) EquipoDAO.listarEquiposGanadoresJornadaN(jornada_ant);
        
       
        if (jornada_ant==1 && numEquiposCopaPrevia()!=0){
            ArrayList idEquiposPerdidos=(ArrayList) EquipoDAO.listarEquiposPerdidosJornadaN(jornada_ant);
            ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
            todosEquipos.removeAll(idEquiposPerdidos);
            
             for (Iterator it=todosEquipos.iterator();it.hasNext();){
                Equipo elem=(Equipo) it.next();
                System.out.println("Mi elem: "+elem);
             }
            sigJornada(todosEquipos,false);
            
        } else{
             if (idEquiposGanados.size()!=1){
                 sigJornada(idEquiposGanados,false);
             }
             else {
                 return -1;
             }
            //System.out.println("IDssss "+idEquiposGanados.get(1));
        }
        
       
        
        return 0;
    }
    
    //Metodo a llamar por el controlador, boton GRANDE, GORDO
    public int generarSigJornada(){
        int sigJ=sigJornadaN();
        if (sigJ==-1){ //NO hago nada porque no ha finalizado
            return -1;
        }
        //Si ha terminado
        else if (sigJ==0){ 
            return primeraJornada();
            
        } else{ //A partir de jornada 1
            generarJornadaN(sigJ);
        }
        
        
        return 0;
    }
    //CUIDADODODODODODO
    public int generarLiga(){
        int sigJ=sigJornadaN();
        if (sigJ==-1){ //NO hago nada porque no ha finalizado
            return -1;
            
        }
        //ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        //int tamano=todosEquipos.size();
        //if (todosEquipos.size()%2==0){
            generarLigaTotal();
        //}
        //else {
        //    generarLigaImpar();
        //}    
        return 0;
    }
    
    public void generarLigaTotal(){//ArrayList miArray, int tamano){
        ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        int tamano=todosEquipos.size();
        if (tamano%2!=0){ //Creo equipo VAcio si puedo distinguie jornadas
            
        }
        Partido nuevoPartido=new Partido();
        PartidoJugado nuevoPartidoJugado = new PartidoJugado();
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1985,10,1);
        Date fecha = cal.getTime();
        //i=0;
        j=0;
        for (i=0;i<tamano-1;i++){
            for (j=i+1;j<tamano;j++){  //Creo la combinacion de todos los partidos
                nuevoPartido.setFecha(fecha);
                nuevoPartido.setLocalizacion("Malaga");
                nuevoPartido.setNumJornada(1); //Fijo solo una jornada para la liga
                nuevoPartido.setIdArbitro(33);
                nuevoPartido.setResultado("0");
                //nuevoPartido.setIdPartido(1);
                PartidoDAO.crearPartido(nuevoPartido);
                nuevoPartidoJugado.setHeGanado(2);
                nuevoPartidoJugado.setIdPartido(PartidoDAO.obtenerIdActual());
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
    
    public void clasificacionLiga(int numEquiposCopa){
        ArrayList todosEquipos=(ArrayList) EquipoDAO.obtenerTodosEquipos();
        Collections.sort(todosEquipos);
        //PAra ver
        Iterator it=todosEquipos.iterator();
        while (it.hasNext()){
            Equipo elem=(Equipo) it.next();
            System.out.println("Equipos "+elem);
        }
        for (i=numEquiposCopa-1;i<todosEquipos.size();i++){
            
        }
        
        //List equiposClasificados=(List) todosEquipos.subList(0, numEquiposCopa);
        ArrayList<Equipo> equiposClasificados= new ArrayList<>(todosEquipos.subList(0, numEquiposCopa));
        Iterator iter=equiposClasificados.iterator();
        while (iter.hasNext()){
            Equipo elem=(Equipo) iter.next();
            System.out.println("Equipos Clasif "+elem);
        }
        //Añadir categorias
        //sigJornada(equiposClasificados);
    }
    
    //FIN CUIDADIDDODODDODO
    //Darme una lista con los equipos que han ganado la jornadaN
    
    public static void main(String[] args) {
        // TODO code application logic here
        Generar miGen=new Generar();
        //int sigJornada=PartidoDAO.obtenerJornadaActual()+1;
        int sigJornada=miGen.sigJornadaN();
        //int n=miGen.numEquiposCopaPrevia();
        System.out.println("Salida: "+sigJornada);
        //miGen.primeraJornada();
       // miGen.generarSigJornada();
        //miGen.generarLiga();
        //miGen.clasificacionLiga(3);
        
        String micad="100-20";
        int idpartido=0;
        String nombre1;
        String nombre2;
        String[] parts = micad.split("-");
        int num1=Integer.parseInt(parts[0]);
        int num2=Integer.parseInt(parts[1]);
        if (num1<num2){
        System.out.println("fa");//cambio el heganado
        }
        
        /*Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1985,10,1);
        Date fecha = cal.getTime();
        
        Partido nuevoPartido=new Partido();
        
        nuevoPartido.setFecha(fecha);
            nuevoPartido.setLocalizacion("Malaga");
            nuevoPartido.setNumJornada(sigJornada);
            nuevoPartido.setIdArbitro(33);
            nuevoPartido.setResultado("0");
            nuevoPartido.setIdPartido(1);
            PartidoDAO.crearPartido(nuevoPartido);
        
        int i=0;
        int j=0;
        int tamano=7;
        for (i=0;i<tamano-1;i++){
            for (j=i+1;j<tamano;j++){
                System.out.println("Orden :"+i+" : "+j);
            }
        }*/
    }
}

 
