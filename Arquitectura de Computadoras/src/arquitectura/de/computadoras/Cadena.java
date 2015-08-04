/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitectura.de.computadoras;

import java.io.*;

/**
 * Clase que implementa la recepcion de cadenas para alimentacion del programa
 *
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class Cadena {

    private String fname;
    private BufferedReader buffer;

    public Cadena(String nombre) {
        fname = nombre;
        try {
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Este archivo no se encuentra");
        } catch (IOException ioe) {
            System.out.println("Error de entrada/salida");
        }
    }

    public String getString() {
        String res = new String();
        String aux = new String();
        try {
            while ((aux = buffer.readLine()) != null) {
                res += (" " + aux);
            }
            buffer.close();
        } catch (IOException ioe) {
            System.out.println(" Ha habido un error de lectura, la cadena se regresara vacia");
            return res;
        }
        return res;
    }

    public int espacios(String cad) {
        int temp = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == ' ') {
                temp++;
            }

        }
        return temp;
    }

    public String[] descompone(String cad) {
        String[] subcadenas = new String[this.espacios(cad)];
        String temp = "";
        cad = cad.substring(1);
        cad = cad + " ";
        int j = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == ' ') {

                subcadenas[j] = temp;

                temp = " ";
                j++;
            } else {
                temp = temp + cad.charAt(i);

            }
        }
        return subcadenas;
    }

    public static void main(String[] args) {
//Nota: Se tiene que poner la direccion exacta del txt para que se pueda leer el archivo.
        Cadena fts = new Cadena("Direccion de donde se encuentre el archivo instrucciones.txt");
        String texto = fts.getString();
        System.out.println(texto + "*");
        System.out.println(fts.espacios(texto) + "aqui");
        String[] prueba;
        prueba = fts.descompone(texto);
        System.out.println();
        for (int i = 0; i < prueba.length; i++) {
            System.out.println(prueba[i]);
        }
    }
}