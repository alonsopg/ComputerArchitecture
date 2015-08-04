/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitectura.de.computadoras;

/**
 *
 * @author User
 */
/**
 * Clase que realiza las operaciones de la ALU
 *
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class ALU {

    private Word ALURightInput;
    private Word ALULeftInput;
    private Word ALUResult;
    private int ALUOperation;

//Constructor que se encarga de inicializar a la ALU
    public ALU() {
        ALURightInput = new Word();
        ALULeftInput = new Word();
        ALUResult = new Word();
        ALUOperation = 0;
    }

//Metodo que fija el valor de la entrada izquierda de la ALU
    public void setALULeftInput(Word val) {
        ALULeftInput = val;
    }
    //Metodo que fija el valor de la entrada derecha de la ALU

    public void setALURightInput(Word val) {
        ALURightInput = val;
    }

//Metodo que fija la operacion que se le va asignar a la ALU y ejacuta dicha operacion
    public void setALUOperation(int val) {
        switch (val) {
            case (0):
                int der = ALURightInput.getValueInt();
                int izq = ALULeftInput.getValueInt();
                int r = izq & der;
                ALUResult = new Word(r);
                ALUOperation = 0;
                break;

            case (1):
                int dere = ALURightInput.getValueInt();
                int izqu = ALULeftInput.getValueInt();
                int re = izqu | dere;
                ALUResult = new Word(re);
                ALUOperation = 1;
                break;

            case (2):
                int derec = ALURightInput.getValueInt();
                int izqui = ALULeftInput.getValueInt();
                int res = izqui + derec;
                ALUResult = new Word(res);
                ALUOperation = 2;
                break;

            case (6):
                int derech = ALURightInput.getValueInt();
                int izquie = ALULeftInput.getValueInt();
                int resu = izquie - derech;
                ALUResult = new Word(resu);
                ALUOperation = 6;
                break;

            case (7):
                int derecha = ALURightInput.getValueInt();
                int izquierda = ALULeftInput.getValueInt();
                if (izquierda < derecha) {
                    ALUResult = new Word(izquierda);
                }
                ALUOperation = 7;
                break;

            default:
                System.out.println("esta es una operacion invalida");
                break;
        }


    }
//Metodo que regresa el valor de la variable ALUResult

    public Word getALUResult() {
        return ALUResult;
    }

//Metodo que le asigna un valor a la variable ALUResult utilizando getValueInt
    public boolean get_ALUZero() {
        return ALUResult.getValueInt() == 0;
    }
}
