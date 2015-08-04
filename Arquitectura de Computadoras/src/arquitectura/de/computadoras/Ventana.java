/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitectura.de.computadoras;

/**
 *
 * @author User
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//import javax.swing.JPanelV;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea.*;
import javax.swing.JFileChooser;
import javax.swing.border.EtchedBorder;
import java.io.*;

/**
 * Clase que implementa una interfaz grafica, y poder hacer uso de todos los
 * metodos del paquete.
 *
 * @author alonso palomino garibay 307602673
 * @author fernando colín perusquía 304328028
 */
public class Ventana extends JFrame implements ActionListener {

    private int c;
    private int pc = 0;
    private String assInst;
    private Registers re = new Registers();
    private InstructionMemory me = new InstructionMemory();
    private DataMemory data = new DataMemory();
    private int numInstructions = 0;
    private ALU alu = new ALU();
    private String r1 = new String();
    private InstructionDecoder instructionDecoder = new InstructionDecoder();
    String pinta, pinta1, pinta2, pinta3, pinta4;
    private JTextArea registersTextArea, segmentTextArea, instruccionesTexto;

    public Ventana() {
        super("Xspim P&C");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 45, 800, 750);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);




        JPanel panel1 = new JPanel();
        panel1.setSize(450, 100);
        JPanel panel2 = new JPanel();
        panel2.setSize(20, 100);
        JPanel panel3 = new JPanel();
        panel3.setSize(50, 100);
        JPanel panel4 = new JPanel();
        panel4.setSize(50, 100);


        setLayout(new GridLayout(4, 0));

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        panel1.setBorder(new EtchedBorder());
        panel2.setBorder(new EtchedBorder());
        panel3.setBorder(new EtchedBorder());
        panel4.setBorder(new EtchedBorder());

        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.BLACK);
        panel3.setBackground(Color.BLACK);
        panel4.setBackground(Color.BLACK);



        JButton Quitboton = new JButton("Exit");
        Quitboton.setActionCommand("exit");
        Quitboton.addActionListener(this);

        JButton Loadboton = new JButton("Load");
        Loadboton.setActionCommand("load");
        Loadboton.addActionListener(this);

        JButton Reloadboton = new JButton("Reload");
        Reloadboton.setActionCommand("Reload");
        Reloadboton.addActionListener(this);

        JButton SetValueboton = new JButton("Assign value");
        SetValueboton.setActionCommand("assign value");
        SetValueboton.addActionListener(this);

        JButton Runboton = new JButton("Run");
        Runboton.addActionListener(this);

        JButton Clearboton = new JButton("Clean");
        Clearboton.addActionListener(this);

        JButton Stepboton = new JButton("By steps");
        Stepboton.addActionListener(this);




        JMenuBar barra = new JMenuBar();
        JMenu menu1 = new JMenu("Archivo");
        JMenu menu2 = new JMenu("Ayuda");

        JMenuItem item1 = new JMenuItem("Salir");
        JMenuItem item2 = new JMenuItem("Item 2");
        JMenuItem item3 = new JMenuItem("Item 3");
        JMenuItem item4 = new JMenuItem("Ayuda");
        JMenuItem item5 = new JMenuItem("Item 5");
        JMenuItem item6 = new JMenuItem("Open File");
        item5.setActionCommand("Borrar");

        barra.add(menu1);
        barra.add(menu2);

        menu1.add(item1);
        menu1.add(item6);
//        menu1.add(item2);
//        menu1.add(item3);

        menu2.add(item4);
//        menu2.add(item5);

        item1.addActionListener(this);
        item1.setActionCommand("quit");
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item4.setActionCommand("ayuda");
        item5.addActionListener(this);
        item6.addActionListener(this);
        item6.setActionCommand("Open File");

        setJMenuBar(barra);

        // panel2.add(Quitboton);
        //panel2.add(Loadboton);
        panel2.add(Reloadboton);
        panel2.add(Runboton);
        panel2.add(Clearboton);
        panel2.add(SetValueboton);
        panel2.add(Stepboton);


        //AREAS DE TEXTO
        registersTextArea = new JTextArea("Registros\n" + re.despliega());
        registersTextArea.setEditable(false);
        segmentTextArea = new JTextArea(me.desplayMemory());
        segmentTextArea.setBackground(Color.BLACK);
        segmentTextArea.setForeground(Color.BLUE);
        segmentTextArea.setEditable(false);
        registersTextArea.setBackground(Color.BLACK);
        registersTextArea.setForeground(Color.BLUE);


        instruccionesTexto = new JTextArea("Instrucciones\n");
        instruccionesTexto.setLineWrap(true);
        instruccionesTexto.setBackground(Color.BLACK);
        instruccionesTexto.setForeground(Color.BLUE);
        JScrollPane areaPanel = new JScrollPane(instruccionesTexto);
        areaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaPanel.setPreferredSize(new Dimension(600, 150));

        JScrollPane areaPanel1 = new JScrollPane(segmentTextArea);
        areaPanel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaPanel1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaPanel1.setPreferredSize(new Dimension(600, 200));

        panel1.add(registersTextArea);
        panel3.add(areaPanel1);
        panel4.add(areaPanel);



        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        AbstractButton boton = (AbstractButton) e.getSource();
        System.out.println(boton.getActionCommand());


        if (boton.getActionCommand() == "exit") {
            int salir = JOptionPane.showConfirmDialog(this, " Deseas salir");
            if (salir == 0) {
                System.exit(0);
            }

        }

        if (boton.getActionCommand() == "quit") {
            int salir = JOptionPane.showConfirmDialog(this, " Deseas salir");
            if (salir == 0) {
                System.exit(0);
            }

        }

        if (boton.getActionCommand() == "Open File") {


            try {
                JFileChooser selecciona = new JFileChooser();
                int respuesta = selecciona.showOpenDialog(this);
                if (respuesta == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "La accion ha sido cancelada");
                } else {
                    DataMemory data = new DataMemory();
                    JOptionPane.showMessageDialog(null, "Se acaba de selecccionar" + selecciona.getSelectedFile());
                    File fil = selecciona.getSelectedFile();
                    r1 = fil.getAbsolutePath();

                    if (checaFile(r1) == null) {
                        numInstructions = me.inicializaMemoria(r1);
                        segmentTextArea.setText(me.desplayMemory());
                        c = pc = 0;
                        assInst = "";
                    } else {
                        JOptionPane.showMessageDialog(null, checaFile(r1));
                    }

                }
            } catch (StringIndexOutOfBoundsException ind) {
                JOptionPane.showMessageDialog(null, "Hubo un error al estar ejecutando la instruccion " + numInstructions);
            } catch (NumberFormatException num) {
                JOptionPane.showMessageDialog(null, "Se detecto un error al estar ejecutando la instruccion " + numInstructions);
            } catch (FileNotFoundException esta) {
                JOptionPane.showMessageDialog(null, "El archivo no existe, intenta con otro");
            } catch (IOException a) {
                JOptionPane.showMessageDialog(null, "No se puede leer el archivo");
            }
        }


        if (boton.getActionCommand() == "Reload") {
            try {
                int rel = JOptionPane.showConfirmDialog(this, "Se cargaran los ultimos datos desde : " + r1);
                if (rel == 0) {
                    if (checaFile(r1) == null) {
                        numInstructions = me.inicializaMemoria(r1);
                        segmentTextArea.setText(me.desplayMemory());
                        c = pc = 0;
                        assInst = "";
                    } else {
                        JOptionPane.showMessageDialog(null, checaFile(r1));
                    }
                }
            } catch (StringIndexOutOfBoundsException trew) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion " + numInstructions);
            } catch (NumberFormatException nrew) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion " + numInstructions);
            } catch (FileNotFoundException eer) {
                JOptionPane.showMessageDialog(null, "No existe el archivo");
            } catch (IOException a) {
                JOptionPane.showMessageDialog(null, "No se puede leer el archivo");
            }
        }



        if (boton.getActionCommand() == "Clean") {
            int salir = JOptionPane.showConfirmDialog(this, "Si haces esto borraras toda tu informacion");
            if (salir == 0) {
                re = new Registers();
                me = new InstructionMemory();
                data = new DataMemory();
                segmentTextArea.setText(me.desplayMemory());
                registersTextArea.setText("Regsitros\n" + re.despliega());
                instruccionesTexto.setText("Instrucciones\n");
                c = pc = 0;
                assInst = "";
                numInstructions = 0;
            }
        }



        if (boton.getActionCommand() == "assign value") {
            String registro, valor;
            try {

                registro = JOptionPane.showInputDialog("Numero de Registro: [0-31]");
                int pos = Integer.parseInt(registro);

                if (pos != 29 && pos != 0) {
                    re.setWriteRegister(Integer.parseInt(registro));
                    valor = JOptionPane.showInputDialog("Valor del Registro en Hex.");
                    re.setWriteData(new Word(valor));
                    re.assertRegWrite();
                    registersTextArea.setText("Registros\n" + re.despliega());
                } else {
                    JOptionPane.showMessageDialog(null, "El valor del registro " + pos + " no puede ser alterado");
                }

            } catch (ArrayIndexOutOfBoundsException ar) {
                JOptionPane.showMessageDialog(null, "Tu registro debe estar entre 0 y 31");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Formato invalido");
            } catch (StringIndexOutOfBoundsException ind) {
                JOptionPane.showMessageDialog(null, "Hexadecimal fuera de rango ");
            }

        }


        if (boton.getActionCommand() == "Run") {
            int rs, rt, rd, funcion, error = 0;
            try {
                for (int i = 0; i <= numInstructions - 1; i++) {
                    error = i;
                    System.out.println(numInstructions);
                    System.out.println();
                    instructionDecoder.setInstruction(me.instructionMem[i]);
                    re.setReadRegister1(rs = instructionDecoder.getRs());
                    re.setReadRegister2(rt = instructionDecoder.getRt());
                    rd = instructionDecoder.getRd();
                    alu.setALULeftInput(re.getReadData1Value());
                    alu.setALURightInput(re.getReadData2Value());
                    funcion = instructionDecoder.getFunct();
                    switch (funcion) {
                        case 32:
                            alu.setALUOperation(2);
                            assInst = assInst + "\n" + c + " add $" + rd + " $" + rs + " $" + rt + "   " + "Suma del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta);
                            break;
                        case 34:
                            alu.setALUOperation(6);
                            assInst = assInst + "\n" + c + " sub $" + rd + " $" + rs + " $" + rt + "   " + "Resta del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta1 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta1);
                            break;
                        case 36:
                            alu.setALUOperation(0);
                            assInst = assInst + "\n" + c + " and $" + rd + " $" + rs + " $" + rt + "   " + "And del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta2 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta2);
                            break;
                        case 37:
                            alu.setALUOperation(1);
                            assInst = assInst + "\n" + c + " or $" + rd + " $" + rs + " $" + rt + "   " + "Or del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + " " + rd;
                            pinta3 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta3);
                            break;
                        case 42:
                            alu.setALUOperation(7);
                            assInst = assInst + "\n" + c + " slt $" + rd + " $" + rs + " $" + rt + "   " + "Set on less than del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + " " + rd;
                            pinta4 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta4);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Instruccion no implementada");
                    }
                    int f = funcion;
                    if (f == 32 || f == 33 || f == 34 || f == 35 || f == 36 || f == 37 || f == 42 || f == 43) {
                        re.setWriteRegister(rd);
                        re.setWriteData(alu.getALUResult());
                        re.assertRegWrite();
                        re.reg[29] = new Word("0x7fffeffc");
                        registersTextArea.setText("Registros\n" + re.despliega());
                    }
                    pc += 4;
                    c++;
                }
            } catch (StringIndexOutOfBoundsException ind) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion ");
            } catch (NumberFormatException num) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion ");
            }
        }
        if (boton.getActionCommand() == "ayuda") {


            JOptionPane.showMessageDialog(null, "Hecho por alonso palomino garibay\n"
                    + "                 &\n" + "fernando colín perusquía");
        }
        if (boton.getActionCommand() == "By steps") {
            int rs, rt, rd, funcion, error = 0;
            try {
                for (int i = 0; i <= numInstructions - 1; i++) {


                    System.out.println(numInstructions);
                    System.out.println();
                    instructionDecoder.setInstruction(me.instructionMem[i]);
                    re.setReadRegister1(rs = instructionDecoder.getRs());
                    re.setReadRegister2(rt = instructionDecoder.getRt());
                    rd = instructionDecoder.getRd();
                    alu.setALULeftInput(re.getReadData1Value());
                    alu.setALURightInput(re.getReadData2Value());
                    funcion = instructionDecoder.getFunct();
                    switch (funcion) {
                        case 32:
                            alu.setALUOperation(2);
                            assInst = assInst + "\n" + c + " add $" + rd + " $" + rs + " $" + rt + "   " + "Suma del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta);
                            break;
                        case 34:
                            alu.setALUOperation(6);
                            assInst = assInst + "\n" + c + " sub $" + rd + " $" + rs + " $" + rt + "   " + "Resta del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta1 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta1);
                            break;
                        case 36:
                            alu.setALUOperation(0);
                            assInst = assInst + "\n" + c + " and $" + rd + " $" + rs + " $" + rt + "   " + "And del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + rd;
                            pinta2 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta2);
                            break;
                        case 37:
                            alu.setALUOperation(1);
                            assInst = assInst + "\n" + c + " or $" + rd + " $" + rs + " $" + rt + "   " + "Or del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + " " + rd;
                            pinta3 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta3);
                            break;
                        case 42:
                            alu.setALUOperation(7);
                            assInst = assInst + "\n" + c + " slt $" + rd + " $" + rs + " $" + rt + "   " + "Set on less than del registro:" + rs + " " + "con el registro:" + " " + rt + " " + "Y se almacena en el registro:" + " " + rd;
                            pinta4 = assInst;
                            instruccionesTexto.setText("Instrucciones\n" + pinta4);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Instruccion no implementada");
                    }
                    int f = funcion;
                    if (f == 32 || f == 33 || f == 34 || f == 35 || f == 36 || f == 37 || f == 42 || f == 43) {
                        int salir = JOptionPane.showConfirmDialog(this, "Continuar");
                        if (salir == 0) {
                            re.setWriteRegister(rd);
                            re.setWriteData(alu.getALUResult());
                            re.assertRegWrite();
                            re.reg[29] = new Word("0x7fffeffc");
                            registersTextArea.setText("Registros\n" + re.despliega());
                        } else {
                            i = numInstructions + 1;
                        }
                    }
                    pc += 4;
                    c++;


                }
            } catch (StringIndexOutOfBoundsException ind) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion ");
            } catch (NumberFormatException num) {
                JOptionPane.showMessageDialog(null, "Error al procesar la instruccion ");
            }
        }
    }

    static String checaFile(String file) {
        String correcto = null;
        String s;
        Word[] este = new Word[100];
        int i = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while (!(s = bf.readLine()).equals(null) && i < 100) {
                este[i] = new Word(s);
                i++;
            }
        } catch (NullPointerException nul) {
            System.out.println("\nEl archivo a leer tiene menos datos de los solicitados\n");
        } catch (FileNotFoundException fre) {
            System.out.println("\nNo se encontro el archivo\n\n");
        } catch (IOException a) {
            System.out.println("\nNo se puede leer el archivo\n\n");
        } catch (StringIndexOutOfBoundsException yrt) {
            i += 1;
            correcto = "Error en la instruccion: " + i;
            return correcto;
        } catch (NumberFormatException oiu) {
            i += 1;
            correcto = "Error en la instruccion: " + i;
            return correcto;
        }


        return correcto;

    }

    public static void main(String[] args) {
        Ventana a = new Ventana();
    }
}
