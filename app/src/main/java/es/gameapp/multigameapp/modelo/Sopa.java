package es.gameapp.multigameapp.modelo;

import java.util.ArrayList;

public class Sopa {

    private int idSopa;
    private String tematica, stringSopa, stringArrayPalabras;

    public Sopa(){

    }

    public Sopa(int idSopa, String tematica, String stringSopa, String stringArrayPalabras) {
        this.idSopa = idSopa;
        this.tematica = tematica;
        this.stringSopa = stringSopa;
        this.stringArrayPalabras = stringArrayPalabras;
    }

    public int getIdSopa() {
        return idSopa;
    }

    public void setIdSopa(int idSopa) {
        this.idSopa = idSopa;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getStringSopa() {
        return stringSopa;
    }

    public void setStringSopa(String stringSopa) {
        this.stringSopa = stringSopa;
    }

    public String getStringArrayPalabras() {
        return stringArrayPalabras;
    }

    public void setStringArrayPalabras(String stringArrayPalabras) {
        this.stringArrayPalabras = stringArrayPalabras;
    }
}
