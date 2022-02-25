package es.gameapp.multigameapp.modelo;

import java.util.ArrayList;

public class Sopa {

    private ArrayList<String> arraySopa;
    private ArrayList<Palabra> arrayPalabras;

    public Sopa(){

    }

    public Sopa(ArrayList<Palabra> arrayPalabras){

    }

    public ArrayList<String> getArraySopa() {
        return arraySopa;
    }

    public void setArraySopa(ArrayList<String> arraySopa) {
        this.arraySopa = arraySopa;
    }

    public ArrayList<Palabra> getArrayPalabras() {
        return arrayPalabras;
    }

    public void setArrayPalabras(ArrayList<Palabra> arrayPalabras) {
        this.arrayPalabras = arrayPalabras;
    }
}
