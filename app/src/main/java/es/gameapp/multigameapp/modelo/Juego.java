package es.gameapp.multigameapp.modelo;

public class Juego {

    private int idJuego;
    private String nombre;

    public Juego(){

    }

    public Juego(int idJuego, String nombre) {
        this.idJuego = idJuego;
        this.nombre = nombre;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
