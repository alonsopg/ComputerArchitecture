package arquitectura.de.computadoras;


/**
 *Clase que permitira trabajar con palabras de 32 bits
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class Word {

    private String palabra;
    private int intValue;

    //Constructor que inicializara la palabra con valor cero
    public Word() {
        intValue = 0;
        palabra = "0x00000000"; //Formato de sistema Hexadecimal
    }

    //Constructor que crea una palabra en Hexadecimal 0x00000000
    public Word(String pal) throws NumberFormatException, StringIndexOutOfBoundsException {

        String subpal;
        String cadenasin = "";
        if (pal.length() <= 10) {
            //quita los espacios de la palabra
            for (int i = 0; i < pal.length(); i++) {
                if (pal.charAt(i) != ' ') {
                    cadenasin += pal.charAt(i);
                }
            }
            pal = cadenasin;
            intValue = Long.decode(pal).intValue();
            subpal = pal.substring(2, pal.length());
            while (subpal.length() < 8) {
                subpal = "0".concat(subpal);
            }
            palabra = "0x".concat(subpal);
        } else {
            throw new StringIndexOutOfBoundsException();
        }

    }

    //Regresa las palabras que contienen los bits menos significativos
    public String getWordFromBits(int firstBit, int lastBit) {
        String palbit;
        palbit = Integer.toBinaryString(intValue);
        while (palbit.length() < 32) {
            palbit = "0".concat(palbit);
        }
        return palbit.substring(firstBit, lastBit);
    }

    //Crea una palabra a partir de que recibe un entero con signo
    public Word(int value) {
        palabra = Integer.toHexString(value);
        intValue = value;
        while (palabra.length() < 8) {
            palabra = "0".concat(palabra);
        }
        palabra = "0x".concat(palabra);
    }
    //Regresa el valor de la palabra en Hexadecimal

    public String getValueHex() {
        return palabra;
    }

    //Recibe el valor de una cadena en Hexadecimal
    public void setValueHex(String hex) {
        this.intValue = Long.decode(hex).intValue();
        this.palabra = hex;
    }
    //Regresa el valor de la palabra en un entero con signo

    public int getValueInt() {
        return intValue;
    }

    //Imprime el resultado
    public void imprime() {
        System.out.println(palabra.toUpperCase());
        System.out.println(intValue);
    }
}
