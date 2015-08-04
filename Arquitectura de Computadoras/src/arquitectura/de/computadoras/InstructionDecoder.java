package arquitectura.de.computadoras;



/**
 *Clase que decodifica una instruccion
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class InstructionDecoder {
   
   
    private int rs,rt,rd;
    private int op;
    private int funct;
    private int shamt;
    private Word target;
    private Word immAdd;
   
//Constructor que inicializa todo en cero
    public InstructionDecoder(){
        this.rs=rs;
        this.rt=rt;
        this.rd=rd;
        this.op=op;
        this.funct=funct;
        this.shamt=shamt;
        this.target=target;
        this.immAdd=immAdd;
    }
   
//Inicializa la instruccion a partir de que recibe como parametro un Word
    public void setInstruction(Word inst){
        this.target = new Word(Integer.parseInt(inst.getWordFromBits(6,32),2));
        this.immAdd = new Word(Integer.parseInt(inst.getWordFromBits(16,32),2));
        this.funct = Integer.parseInt(inst.getWordFromBits(26,32),2);
        this.shamt = Integer.parseInt(inst.getWordFromBits(21,26),2);
        this.rd = Integer.parseInt(inst.getWordFromBits(16,21),2);
        this.rt = Integer.parseInt(inst.getWordFromBits(11,16),2);
        this.rs = Integer.parseInt(inst.getWordFromBits(6,11),2);
        this.op = Integer.parseInt(inst.getWordFromBits(0,6),2);
     }
   
   //Regresa la instruccion Target
    public Word getTarget(){
        return target;
    }
  
    //Regresa la instruccion Rd
    public int getRd(){
        return rd;
    }
    //Regresa la instruccion Rt
    public int getRt(){
        return rt;
    }
  
    //Regresa la instruccion Funct
    public int getFunct(){
        return funct;
    }
    //Regresa la instruccion ImmAdd
    public Word getImmAdd(){
        return immAdd;
    }
 //Regresa la instruccion Op
    public int getOp(){
        return op;
    }
    //Regresa la instruccion Shamt
    public int getShamt(){
        return shamt;
    }
  //Regresa la instruccion Rs
    public int getRs(){
        return rs;
    }
  
   

    //Metodo que imprime cada una de las instrucciones

    public void Cadena(){
        System.out.println("rs=0x".concat(Integer.toHexString(rs)));
        System.out.println("rt=0x".concat(Integer.toHexString(rt)));
        System.out.println("rd=0x".concat(Integer.toHexString(rd)));
        System.out.println("op=0x".concat(Integer.toHexString(op)));
        System.out.println("funct=0x".concat(Integer.toHexString(funct)));
        System.out.println("shamnt=0x".concat(Integer.toHexString(shamt)));
    }
    public static void main(String argv[]){
        InstructionDecoder prue = new InstructionDecoder();
        Word is = new Word("0x0001244");
        prue.setInstruction(is);
        System.out.println(prue.rs);
        System.out.println(prue.rt);
        System.out.println(prue.funct);
        System.out.println(prue.op);
        System.out.println(prue.shamt);
    }
}
