import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * Created on 01/09/2017.
 */
public class Practica {
    public static void main(String[] args) {
        //VARIABLES
        File file = null; //
        String total = "";
        //Panel para agarrar el archivo que se va a scannear
        JButton open = new JButton();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("C:\\Users\\Palaf\\Desktop"));
        jFileChooser.setDialogTitle("Alfabeto + Lenguaje + [Cadena | 0 | 1]");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jFileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
        }

        try {
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(file.toString()));
            Lenguaje lenguaje;
            boolean estado = true;
            //CICLOS
            do {
                //Lectura de datosd
                //Alfabeto
                String x1 = bufferedReader.readLine();
                //Lenguaje
                String x2 = bufferedReader.readLine();
                //Se crea un nuevo lenguaje
                lenguaje = new Lenguaje(x1, x2, "");
                //Se checa el lenguaje con el alfabeto

                total += "Alfabeto: {" + lenguaje.getAbc() + "}\n";
                total += "Lenguaje: {" + lenguaje.getLenguajeString() + "}\n";
                if (lenguaje.pertenece()) {

                    total += lenguaje.getError() + "\n";
                    //Si se entra aqui, entonces el lenguaje no concuerda
                    break;
                }
                //System.out.println("W:");
                while (true) {
                    //Se agarran las sentencias
                    String x3 = bufferedReader.readLine();
                    if (x3.equals("1")) {// System.out.println("¿Terminaste? 1(Si)");
                        estado = false;
                        break;
                    } else if (x3.equals("0")) {// System.out.println("¿Nuevo Lenguaje? 0(Si)");
                        total += "\n";
                        break;
                    } else {
                        //Se checa que el lenguaje coincida con W
                        lenguaje.setW(x3);
                        if (lenguaje.checar()) {
                            total += "W:" + lenguaje.getW() + " es acapetada en L*:{" + lenguaje.getLenguajeString() + "}*, con L^" + lenguaje.graphLenguaje.size + "\n";
                        } else {
                            total += "W:" + lenguaje.getW() + " es rechazada en L*:{" + lenguaje.getLenguajeString() + "}*, " + "{" + lenguaje.graphLenguaje.error + "} no pertence a L\n";
                            lenguaje.graphLenguaje.error = "";
                        }
                        lenguaje.graphLenguaje.size = 0;
                    }
                }
            } while (estado);

            //Se crea una ventana con scroll, para displeyar los resultados
            JTextArea textArea = new JTextArea(total);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Resultados", JOptionPane.NO_OPTION);
        } catch (Exception e) {
            //Hubo un error leyendo el archivo
            JOptionPane.showMessageDialog(null, "Archivo incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }


}