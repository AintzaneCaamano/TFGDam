package es.gameapp.multigameapp.extras;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import es.gameapp.multigameapp.modelo.Juego;
import es.gameapp.multigameapp.modelo.Palabra;
import es.gameapp.multigameapp.modelo.Pregunta;
import es.gameapp.multigameapp.modelo.Sopa;

public class InsertData {

    private DataManager db;
    private ArrayList<Pregunta> arrayPreguntas;
    private ArrayList<Palabra> arrayPalabras;
    private ArrayList<Juego> arrayJuegos;
    private ArrayList<Sopa> arraySopas;


    public InsertData(Context context){
        db = new DataManager(context);
        arrayPreguntas = new ArrayList<>();
        arrayPalabras = new ArrayList<>();
        arrayJuegos = new ArrayList<>();
        arraySopas = new ArrayList<>();
    }

    public void cargarPreguntasSql(){
        cargarArrayPreguntas();
        for (Pregunta pregunta: arrayPreguntas) {
            db.insertPregunta(pregunta);
        }
    }

    public void cargarPalabrasSql(){
        cargarArrayPalabras();
        for (Palabra palabra: arrayPalabras) {
            db.insertPalabra(palabra);
        }
    }

    public void cargarJuegosSql(){
        cargarArrayJuegos();
        for (Juego juego: arrayJuegos) {
            db.insertJuego(juego);
        }
    }

    public void cargarSopasSql(){
        cargarArraySopas();
        for (Sopa sopa: arraySopas) {
            db.insertSopa(sopa);
        }
    }

    private void cargarArrayPreguntas(){
        Pregunta pregunta = new Pregunta();

        //Pregunta1
        pregunta.setTexto("¿Quién fue el primer presidente de la democracia española tras el franquismo?");
        pregunta.setOpcion1("Adolfo Suárez");
        pregunta.setOpcion2("Jose Maria Aznar");
        pregunta.setOpcion3("Jesús Gil");
        pregunta.setOpcion4("Pedro Sánchez");
        pregunta.setRespuesta("Adolfo Suárez");
        arrayPreguntas.add(pregunta);

        //Pregunta2
        pregunta = new Pregunta();
        pregunta.setTexto("¿La invasión de qué fortaleza es considerada como el punto de inicio de la Revolución Francesa?");
        pregunta.setOpcion1("La Bastilla");
        pregunta.setOpcion2("Castillo de Versalles");
        pregunta.setOpcion3("Fontainebleau");
        pregunta.setOpcion4("Castillo de Cheverny");
        pregunta.setRespuesta("La Bastilla");
        arrayPreguntas.add(pregunta);

        //Pregunta3
        pregunta = new Pregunta();
        pregunta.setTexto("¿A partir de qué suceso empieza la Edad Media?");
        pregunta.setOpcion1("La Caída del Imperio Romano");
        pregunta.setOpcion2("Descubrimiento de América");
        pregunta.setOpcion3("La Caida del Imperio Mongol");
        pregunta.setOpcion4("La construcción de la Gran Muralla China");
        pregunta.setRespuesta("La Caída del Imperio Romano");
        arrayPreguntas.add(pregunta);

        //Pregunta4
        pregunta = new Pregunta();
        pregunta.setTexto("¿Quién fue el primer presidente de Estados Unidos?");
        pregunta.setOpcion1("George Washington");
        pregunta.setOpcion2("Thomas Jefferson");
        pregunta.setOpcion3("John Adams");
        pregunta.setOpcion4("Andrew Jackson");
        pregunta.setRespuesta("George Washington");
        arrayPreguntas.add(pregunta);

        //Pregunta5
        pregunta = new Pregunta();
        pregunta.setTexto("¿Qué filósofo de la Antigua Grecia creía que el elemento del que están compuestas todas las cosas es el agua?");
        pregunta.setOpcion1("Tales de Mileto");
        pregunta.setOpcion2("Pitágoras");
        pregunta.setOpcion3("Aristóteles");
        pregunta.setOpcion4("Arquímedes");
        pregunta.setRespuesta("Tales de Mileto");
        arrayPreguntas.add(pregunta);
    }

    private void cargarArrayPalabras(){
        Palabra palabra = new Palabra();

        //Palabra1
        palabra.setStringPalabra("BARBA");
        arrayPalabras.add(palabra);

        //Palabra2
        palabra = new Palabra();
        palabra.setStringPalabra("CAMION");
        arrayPalabras.add(palabra);

        //Palabra3
        palabra = new Palabra();
        palabra.setStringPalabra("ESTUFA");
        arrayPalabras.add(palabra);

        //Palabra4
        palabra = new Palabra();
        palabra.setStringPalabra("LETRERO");
        arrayPalabras.add(palabra);

        //Palabra5
        palabra = new Palabra();
        palabra.setStringPalabra("CEPILLO");
        arrayPalabras.add(palabra);

        //Palabra6
        palabra = new Palabra();
        palabra.setStringPalabra("AGUA");
        arrayPalabras.add(palabra);

        //Palabra7
        palabra = new Palabra();
        palabra.setStringPalabra("VASO");
        arrayPalabras.add(palabra);

        //Palabra8
        palabra = new Palabra();
        palabra.setStringPalabra("PATATA");
        arrayPalabras.add(palabra);

        //Palabra9
        palabra = new Palabra();
        palabra.setStringPalabra("ESCOBA");
        arrayPalabras.add(palabra);

        //Palabra10
        palabra = new Palabra();
        palabra.setStringPalabra("SAL");
        arrayPalabras.add(palabra);
    }

    private void cargarArrayJuegos(){
        Juego juego = new Juego();

        //Juego1
        juego.setNombre("Trivia");
        arrayJuegos.add(juego);

        //Juego2
        juego = new Juego();
        juego.setNombre("Sopa");
        arrayJuegos.add(juego);

        //Juego3
        juego = new Juego();
        juego.setNombre("Simon");
        arrayJuegos.add(juego);

        //Juego4
        juego = new Juego();
        juego.setNombre("Hanoi");
        arrayJuegos.add(juego);

        //Juego5
        juego = new Juego();
        juego.setNombre("Sudoku");
        arrayJuegos.add(juego);

        //Juego6
        juego = new Juego();
        juego.setNombre("BlackJack");
        arrayJuegos.add(juego);
    }

    private void cargarArraySopas() {
        Sopa sopa = new Sopa();

        sopa.setTematica("Planetas del Sistema Solar");
        sopa.setStringSopa("NNIUOSO OMETRAM TZRNBTU UEORAUR LTIERRA PEIEUNN SUNEVOO");
        sopa.setStringArrayPalabras("TIERRA;MARTE;VENUS;PLUTON;URANO;SATURNO");
        arraySopas.add(sopa);
    }
}
