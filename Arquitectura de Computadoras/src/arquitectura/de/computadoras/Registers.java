/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitectura.de.computadoras;

/**
 *
 * @author User
 */
import java.io.*;

/**
 *Clase que simula registros de un CPU
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */

public class Registers
{
    Word []reg;
    private Word writeData;
    private Word readData1;
    private Word readData2;
    private int readRegister1;
    private int readRegister2;
    private int writeRegister;

//Inicializa todos los registros en cero
    public Registers()
    {
        writeData = new Word(0);
        readData1 = new Word(0);
        readData2 = new Word(0);
        reg = new Word [32];
        for(int i=0; i<reg.length; i++)
        {
            reg[i] = new Word(0);}
        reg[29] = new Word("0x7fffeffc");
    }
    //Metodo que exporta los valores de los 32 registros a un archivo.
public void displayRegisters(String archivo)
    {
        try
        {
            PrintWriter arch = new PrintWriter(
                    new BufferedWriter(new FileWriter(archivo)));
            for(int i=0; i<reg.length; i++)
            {
                arch.println("reg"+i+":"+reg[i].getValueHex());
            }
            arch.close();
        }
        catch(NullPointerException e)
        {}
        catch(FileNotFoundException f)
        {System.out.println("Hubo un error al intentar escribir");}
        catch(IOException  i)
        {}
       
    }
   
    //Metodo que devuelve el valor del registro en readRegister1.
    public Word getReadData1Value()
    {
        return reg[readRegister1];
    }
    //Metodo que devuelve el valor del registro en readRegister1.
    public Word getReadData2Value()
    {
        return reg[readRegister2];
    }
    //Metodo que fija readRegister2 en val y fija readData2
    //al valor del registro readRegister2.
    public void setReadRegister1(int val) throws ArrayIndexOutOfBoundsException
    {
        if(val<0 | val>31)
        {
            throw new ArrayIndexOutOfBoundsException("El valor necesariamente debe de estar en el numero de registros, debe de encontrarse en un rango [0, 31] ");
        }
        else readRegister1 = val;
       
        readData1 = new Word(reg[readRegister1].getValueHex());
    }
      //Metodo que fija readRegister2 en val y fija readData2
      //al valor del registro readRegister2.
    public void setReadRegister2(int val) throws ArrayIndexOutOfBoundsException
    {
        if(val<0 | val>31)
        {
            throw new ArrayIndexOutOfBoundsException("El valor necesariamente debe de estar en el numero de registros, debe de encontrarse en un rango [0, 31]");
        }
        else readRegister2 = val;
      
        readData2=new Word(reg[readRegister2].getValueHex());
    }
  
    //Fija writeRegister en val.
    public void setWriteRegister(int val)throws ArrayIndexOutOfBoundsException
    {
        if(val<0 | val>31)
        {
            throw new ArrayIndexOutOfBoundsException("El valor necesariamente debe de estar en el numero de registros, debe de encontrarse en un rango [0, 31]");
        }
        else writeRegister = val;
    }
    //Guarda el valor de writeData en reg[writeRegister].
    public void assertRegWrite()
    {
        reg[writeRegister] = writeData;
    }


     //Fija writeData en val.
    public void setWriteData(Word val)
    {
        writeData = val;
    }
   
    public String despliega()
    {
        String desp = new String();
        int r8, r16, r24;
        for(int i = 0; i < 8; i++)
        {
            r8 = i + 8;
            r16 = i + 16;
            r24 = i + 24;

            desp = desp + "R"+i+" = "+"0x"+reg[i].getValueHex().substring(2,reg[i].getValueHex().length()).toUpperCase()+"     R"+r8+" = "+"0x"+reg[i+8].getValueHex().substring(2,reg[i].getValueHex().length()).toUpperCase()+
                    "     R"+r16+" = "+"0x"+reg[i+16].getValueHex().substring(2,reg[i].getValueHex().length()).toUpperCase()+"     R"+r24+" = "+"0x"+reg[i+24].getValueHex().substring(2,reg[i].getValueHex().length()).toUpperCase()+"\n";
        }
        return desp;
       
    }
   
   
}