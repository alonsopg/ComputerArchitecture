/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitectura.de.computadoras;

import java.io.*;

/**
 * Clase que simula una memoria
 *
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class DataMemory {

    private int DATAMEMORYSIZE = 100;
    private Word dataMem[];
    private int Address;
    private Word WriteData;
    private Word ReadData;

//Constructor que inicializa las varibles a cero
    public DataMemory() {
        WriteData = new Word();
        ReadData = new Word();
        dataMem = new Word[100];
        for (int i = 0; i < dataMem.length; i++) {
            dataMem[i] = new Word(0);
        }
    }
    //Metodo que se encarga de inizializar la memoria

    public boolean initializeDataMemory(String the_file, int numWords) {
        boolean r = false;
        if (1 <= numWords && numWords <= 100) {
            try {
                BufferedReader bf = new BufferedReader(new FileReader(the_file));
                for (int i = 0; i < numWords; i++) {
                    dataMem[i] = new Word(bf.readLine());
                }
                r = true;
            } catch (FileNotFoundException e) {
                System.out.println("\nEl archivo no se encuentra\n");
            } catch (IOException a) {
                System.out.println("\nEl archivo es incorrecto y no puede ser leido\n");
            } catch (NullPointerException nul) {
                System.out.println("\nEl archivo no cuenta con los datos suficientes\n");
            }
        } else {
            System.out.println("El word se ha excedido del rango");
        }
        return r;
    }

//Devuelve el valor de ReadData
    public Word getReadData() {
        return ReadData;
    }

//Metodo que fija la direccion
    public void setAddress(int val) {
        Address = val;
        dataMem[Address / 4] = ReadData;
    }
    //Metodo que fija WriteData a val

    public void setWriteData(Word val) {
        WriteData = val;
    }

    //Metodo que escribe el valor de WriteData a dataMem[Direccion+4]
    public void assertMemWrite() {
        dataMem[Address / 4] = WriteData;
    }

    public String displayMem() {
        String salida = new String();
        int m10, m20, m30, m40, m50;
        for (int i = 0; i < 9; i++) {
            m10 = i + 9;
            m20 = i + 18;
            m30 = i + 27;
            m40 = i + 36;
            m50 = i + 45;
            salida = salida + i + "[" + dataMem[i].getValueHex() + "]    "
                    + m10 + "[" + dataMem[i + 10].getValueHex() + "]    "
                    + m20 + "[" + dataMem[i + 20].getValueHex() + "]    "
                    + m30 + "[" + dataMem[i + 30].getValueHex() + "]    "
                    + m40 + "[" + dataMem[i + 40].getValueHex() + "]    "
                    + m50 + "[" + dataMem[i + 50].getValueHex() + "]\n";
        }
        return salida;
    }

    public void dumpDataMemory(String the_file, int startAddress, int endAddress) {
        if (startAddress >= 0 && endAddress <= 399) {
            Word[] arr = new Word[endAddress - startAddress];
            try {
                PrintWriter ps = new PrintWriter(
                        new BufferedWriter(new FileWriter(the_file)));
                for (int i = startAddress; i < endAddress; i++) {
                    arr[i] = dataMem[i];
                    ps.println(arr[i].getValueHex());
                }
                ps.close();
            } catch (NullPointerException e) {
            } catch (FileNotFoundException f) {
                System.out.println("No se pudo escribir");
            } catch (IOException i) {
            }
        }
    }

    public static void main(String[] argv) {

        DataMemory dat = new DataMemory();
        dat.initializeDataMemory("/home/irving/Proyecto/instrucciones.txt", 9);
        for (int i = 0; i < dat.dataMem.length; i++) {
            System.out.println(dat.dataMem[i].getValueHex());
        }
        System.out.println();
        dat.dumpDataMemory("/home/irving/Proyecto/instrucciones1.txt", 0, 20);
        dat.displayMem();
    }
}
