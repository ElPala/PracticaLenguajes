/**
 * Created on 01/09/2017.
 */
public class Lenguaje {

    private String lenguajeString; //Lenguaje en forma de String
    private String abc; //alfabeto
    private String w; //W a analizar
    private String error;//En caso de que se genere un error, se guarda
    GraphLenguaje graphLenguaje;


    //Constructor de la clase:
    public Lenguaje(String abc, String lenguajeString, String W) {
        //Se asignan el Lenguaje
        setLenguajeString(lenguajeString);
        //Se asigna el alfabeto
        setAbc(abc);
        //Se asigna el W
        setW(W);
        //Se inicializa el error sin nada
        setError("");
        graphLenguaje = new GraphLenguaje();
        setGraphLenguaje();
        //Se asigna el HashMap con la funcion hashMap(String)
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLenguajeString() {
        return lenguajeString;
    }

    public void setLenguajeString(String lenguajeString) {
        this.lenguajeString = lenguajeString;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public void setGraphLenguaje() {
        int p1 = 0;
        int p2 = getLenguajeString().length();
        for (int x = 0; x < getLenguajeString().length(); x++) {
            if (x == getLenguajeString().length() - 1) {
                graphLenguaje.insert(getLenguajeString().substring(p1, p2));
            } else if (getLenguajeString().charAt(x) == ',') {
                graphLenguaje.insert(getLenguajeString().substring(p1, x));
                p1 = x + 1;
            }
        }
    }

    //Checa si el Lenguaje pertenece al alfabeto
    public boolean pertenece() {
        for (int x = 0; x < getLenguajeString().length(); x++) { //Se itera el string y se compara con el lenguaje
            for (int y = 0; y <= getAbc().length(); y = y + 2) { //Se va iterando de dos en dos, puesto que el lenguaje esta dado a,b,c...
                if (getLenguajeString().charAt(x) == ',' || getLenguajeString().charAt(x) == getAbc().charAt(y)) {
                    //Se ingnoran las "," y si se encuentra W[x] en alfabeto[y], se deja de buscar.
                    break;
                } else if (y == getAbc().length() - 1) { //Si se llega al TOPE del alfabeto, entonces W[x]  no existe en el alfabeto.

                    setError("El alfabeto no concuerda con L, " + getLenguajeString().charAt(x) + " no pertence al alfabeto:{" + getAbc() + "}");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checar() {
        return graphLenguaje.valido(getW());
    }

}