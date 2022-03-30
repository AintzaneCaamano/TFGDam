package es.gameapp.multigameapp.modelo;

public class Palabra {

    private int idPalabra, idSopaFk;
    private String stringPalabra;

    public Palabra(){

    }

    public Palabra(int idPalabra, int idSopaFk, String stringPalabra) {
        this.idPalabra = idPalabra;
        this.idSopaFk = idSopaFk;
        this.stringPalabra = stringPalabra;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public int getIdSopaFk() {
        return idSopaFk;
    }

    public void setIdSopaFk(int idSopaFk) {
        this.idSopaFk = idSopaFk;
    }

    public String getStringPalabra() {
        return stringPalabra;
    }

    public void setStringPalabra(String stringPalabra) {
        this.stringPalabra = stringPalabra;
    }
}
