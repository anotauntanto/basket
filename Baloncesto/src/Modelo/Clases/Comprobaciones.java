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
public class Comprobaciones {

    private static int dni;
    private static char letra;
    private static char tabla[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
        'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    
    public static boolean esDNIbueno (String sNif) {
        char letraAux;
        String sNumeros = null;
        boolean resultado = true;

        // Elimina la última posición de la cadena
        sNumeros = sNif.substring(0, sNif.length() - 1);
        try {
            dni = Integer.parseInt(sNumeros);
        } catch (NumberFormatException e) {
            resultado = false;
        }

        // Letra obtenidad de la entrada del usuario
        letraAux = Character.toUpperCase(sNif.charAt(sNif.length() - 1));
        // Letra correspondiente al número introducido
        letra = tabla[dni % 23];

        //!Character.isLetter(letraAux) || 
        if (letraAux != letra) {
            resultado = false;
        }

        return resultado;

    }
    
    public static boolean esTlfbueno (String sTlf) {
        return sTlf.length() == 9;
    }
}
