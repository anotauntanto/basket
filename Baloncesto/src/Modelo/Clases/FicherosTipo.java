/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author inftel06
 */
public class FicherosTipo {
    
    public static void escribirFichero(int tipo, int numEq) throws IOException{
         
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("tipoCompeticion.txt");
            pw = new PrintWriter(fichero);
 
            if (tipo==1){
                pw.println("Liga");
            }
            else if(tipo==2){
                pw.println("Copa");
            }
            else if(tipo==3){
                pw.println("Mixto:"+numEq);
            }
 
        } catch(FileNotFoundException e) {
                   System.out.println("Fichero de entrada no encontrado");
            
         
       
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public static String leerFichero(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String linea = null;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("tipoCompeticion.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         linea=br.readLine();
         //while((linea=br.readLine())!=null)
         //   System.out.println(linea);
       
      }
         
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return linea;
    }
}

