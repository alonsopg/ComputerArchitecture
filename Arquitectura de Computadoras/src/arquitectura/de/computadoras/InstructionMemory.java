package arquitectura.de.computadoras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author User
 */


public class InstructionMemory {

    private int InstructionMemorySize = 100;
    Word instructionMem[];
    private int readAddress;
    private Word Instruction;

//Inicializa todas las variables a cero
    public InstructionMemory() {
        instructionMem = new Word[InstructionMemorySize];
        Instruction = new Word();
        instructionMem = new Word[100];
        for (int i = 0; i < instructionMem.length; i++) {
            instructionMem[i] = new Word(0);
        }
    }

    public void dumpInstructionMemory(String the_file, int startAddress, int endAddress) {
        if (startAddress >= 0 && endAddress <= 399) {
            Word[] arr = new Word[100];
            try {
                PrintWriter ps = new PrintWriter(
                        new BufferedWriter(new FileWriter(the_file)));
                for (int i = startAddress; i <= endAddress; i++) {
                    arr[i] = instructionMem[i];
                    ps.println(arr[i].getValueHex());
                }
                ps.close();
            } catch (NullPointerException e) {
            } catch (FileNotFoundException f) {
                System.out.println("Se detecto un error al tratar de escribir");
            } catch (IOException i) {
            }
        }
    }

//Metodo que lee las instrucciones de MIPS que se encuentren en el archivo
    public boolean initializeInstructionMemory(String the_file, int numInst) {
        boolean r = false;
        String[] arr;

        numInst = numInst - 1;
        if (1 <= numInst && numInst <= 100) {
            try {
                Cadena file = new Cadena(the_file);
                String cad = file.getString();
                arr = file.descompone(cad);
                for (int i = 0; i <= numInst; i++) {
                    instructionMem[i] = new Word(arr[i]);
                }
                r = true;

            } catch (NullPointerException nul) {
                System.out.println("\nEl archivo no cuenta con los datos suficientes\n");
            }
        } else {
            System.out.println("Las palabras se ha pasado del rango, intentalo con words de menos tamaño");
        }
        return r;


    }
    //Regresa el valor de la instruccion

    public Word getInstruction() {
        return Instruction;
    }
//Metodo que fija el valor de la direccion

    public void setAddress(int val) {
        readAddress = val;
        instructionMem[val] = Instruction;
    }

    public String desplayMemory() {
        String s = new String();
        int i10, i20, i30, i40, i50, i60, i70, i80, i90;
        for (int i = 0; i < 10; i++) {
            i10 = i + 10;
            i20 = i + 20;
            i30 = i + 30;
            i40 = i + 40;
            i50 = i + 50;
            i60 = i + 60;
            i70 = i + 70;
            i80 = i + 80;
            i90 = i + 90;
            s = s + i + "[" + instructionMem[i].getValueHex() + "]    "
                    + i10 + "[" + instructionMem[i10].getValueHex() + "]    "
                    + i20 + "[" + instructionMem[i20].getValueHex() + "]    "
                    + i30 + "[" + instructionMem[i30].getValueHex() + "]    "
                    + i40 + "[" + instructionMem[i40].getValueHex() + "]"
                    + i50 + "[" + instructionMem[i50].getValueHex() + "]    "
                    + i60 + "[" + instructionMem[i60].getValueHex() + "]    "
                    + i70 + "[" + instructionMem[i70].getValueHex() + "]    "
                    + i80 + "[" + instructionMem[i80].getValueHex() + "]    "
                    + i90 + "[" + instructionMem[i90].getValueHex() + "]    " + "\n";
        }
        return s;
    }

    public int inicializaMemoria(String the_file) throws FileNotFoundException, IOException {
        int i = 0;
        String s;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(the_file));
            while (!(s = bf.readLine()).equals(null) && i < 100) {

                instructionMem[i] = new Word(s);
                i++;
            }
        } catch (NullPointerException nul) {
            System.out.println("\nEl archivo no cuenta con los datos suficientes\n");
        }

        return i;
    }
}
