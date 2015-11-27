/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inftel06
 */
public class FicherosTipo {

    public static void escribirFichero(int tipo, int numEq) throws IOException {
        File archivo = null;
        FileOutputStream fileout = null;

        try {
            String nombre = "fichero.txt";
            archivo = new File(nombre);
            fileout = new FileOutputStream(archivo.getAbsolutePath());
            System.out.println(archivo.getAbsolutePath());
            //fichero = new FileWriter(fileout);
            //pw = new PrintWriter(fichero);

            if (tipo == 1) {
                String copa = "Liga";
                byte[] bytes = copa.getBytes(StandardCharsets.UTF_8);
                fileout.write(bytes);

                //pw.println("Liga");
            } else if (tipo == 2) {
                String copa = "Copa";

                byte[] bytes = copa.getBytes(StandardCharsets.UTF_8);
                fileout.write(bytes);

                //pw.println("Copa");
            } else if (tipo == 3) {
                //pw.println("Mixto:"+numEq);
                String copa = "Mixto:" + numEq;
                byte[] bytes = copa.getBytes(StandardCharsets.UTF_8);
                fileout.write(bytes);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero de entrada no encontrado");

        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fileout) {
                    fileout.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String leerFichero() {

        File archivo = null;
        //FileReader fr = null;
        //BufferedReader br = null;
        String linea = null;
        FileInputStream filein = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            String nombre = "fichero.txt";
            archivo = new File(nombre);
            filein = new FileInputStream(archivo.getAbsolutePath());

         // Lectura del fichero
            //linea=br.readLine();
            int content;
            char linea_[] = new char [7];
            int contador = 0;
            while ((content = filein.read()) != -1) {
 
                linea_[contador] = (char) content;
                contador++;
            }
            
            char res[] = new char[contador];
            res = linea_;
            linea = String.valueOf(res);
            System.out.println(res);
  
            //while((linea=br.readLine())!=null)
            //   System.out.println(linea);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            try {
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta
                // una excepcion.
                filein.close();
            } catch (IOException ex) {
                Logger.getLogger(FicherosTipo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return linea;
    }
}
