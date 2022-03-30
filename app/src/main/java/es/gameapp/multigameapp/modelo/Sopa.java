package es.gameapp.multigameapp.modelo;

import java.util.ArrayList;

public class Sopa {

    private int idSopa;
    private String stringSopaBase64;
    private ArrayList<Palabra> arrayPalabras;

    public Sopa(){

    }

    public Sopa(int idSopa, String stringSopaBase64, ArrayList<Palabra> arrayPalabras) {
        this.idSopa = idSopa;
        this.stringSopaBase64 = stringSopaBase64;
        this.arrayPalabras = arrayPalabras;
    }

    public int getIdSopa() {
        return idSopa;
    }

    public void setIdSopa(int idSopa) {
        this.idSopa = idSopa;
    }

    public String getStringSopaBase64() {
        return stringSopaBase64;
    }

    public void setStringSopaBase64(String stringSopaBase64) {
        this.stringSopaBase64 = stringSopaBase64;
    }

    public ArrayList<Palabra> getArrayPalabras() {
        return arrayPalabras;
    }

    public void setArrayPalabras(ArrayList<Palabra> arrayPalabras) {
        this.arrayPalabras = arrayPalabras;
    }
}
