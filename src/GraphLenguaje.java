import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Palaf on 17/09/2017.
 */
public class GraphLenguaje {

    GNodo root; //GUIA PARA MOVERSER EN LOS GRAFOS
    //EN CASO DE CHEKEO
    ArrayList<Integer> cantidad;
    ArrayList<Integer> posicion;
    String error; //POSIBLES ERRORES
    int size; //TAMAÑO DE L^n

    public GraphLenguaje() {
        cantidad = new ArrayList<>();
        posicion = new ArrayList<>();
        root = new GNodo(null, false);
        size = 0;
        error = "";
    }


    void insert(String s) { //SE VA INSERTANDO LAS CADES
        GNodo aux = root;
        for (int x = 0; x < s.length(); x++) {
            Character c = s.charAt(x);
            aux.insert(c); //SE INSERTA SI ES QUE NO EXISTE
            aux = aux.getNode(c); //SE MUEVE
            if (x == s.length() - 1) {
                aux.ok = true; //SE ASIGNA VERDADERO PARA EL ULTIMO NODO
            }
        }
    }

    boolean valido(String w) {
        GNodo aux = root;
        for (int x = 0; x < w.length(); x++) {
            Character c = w.charAt(x);
            if (aux.ok == true && (!aux.map.containsKey(c) || aux.map.isEmpty())) {
                size++;
                aux = root; //SER REINICIA EN CASO DE NO ENCONTRAR MAS NODOS
            }
            if (x == w.length() - 1 && !aux.map.containsKey(c)) {
                error = w.substring(x, w.length()); //SI ES EL ULTIMO CARACTER Y NO ESTA CONTENIDO SE REGRESA FALSO
                return false;
            }
            if (aux.ok == true && aux.map.size() > 1) {
                cantidad.add(size + 1); //
                posicion.add(x);
            }
            if (aux.map.containsKey(c)) {
                aux = aux.getNode(c);
            } else if (!cantidad.isEmpty()) {
                if (error.equals("")) {
                    error = w.substring(x, w.length());
                }
                size = cantidad.remove(cantidad.size() - 1);
                x = posicion.remove(posicion.size() - 1);
                aux = root;
            } else {
                if (error.equals("")) {
                    error = w.substring(x, w.length());
                }
                return false;
                //jsda
            }
        }
        size++;
        return true;
    }


    private class GNodo {
        HashMap<Character, GNodo> map;
        Character c;
        boolean ok;

        public GNodo(Character c, boolean ok) {
            this.map = new HashMap<>();
            this.c = c;
            this.ok = ok;
        }

        void insert(Character key) {
            if (!map.containsKey(key)) {
                map.put(key, new GNodo(key, false));
            }
        }

        GNodo getNode(Character key) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return null;
        }
    }
}
