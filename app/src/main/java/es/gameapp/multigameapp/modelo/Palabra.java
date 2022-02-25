package es.gameapp.multigameapp.modelo;

public class Palabra {

    private int idPalabra;
    private String stringPalabra;

    public Palabra(){

    }

    public Palabra(int idPalabra, String palabra) {
        this.idPalabra = idPalabra;
        this.stringPalabra = palabra;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getStringPalabra() {
        return stringPalabra;
    }

    public void setStringPalabra(String stringPalabra) {
        this.stringPalabra = stringPalabra;
    }
}
