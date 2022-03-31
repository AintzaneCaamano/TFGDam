package es.gameapp.multigameapp.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.extras.DataManager;
import es.gameapp.multigameapp.modelo.Pregunta;
import es.gameapp.multigameapp.modelo.Score;
import es.gameapp.multigameapp.sopa.SopaActivity;

public class TriviaActivity extends AppCompatActivity {
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;
    private TextView textViewTitulo, textViewStringScore, textViewScore, textViewStringIntentos, textViewIntentos, textViewPregunta, textViewOpcion1, textViewOpcion2, textViewOpcion3, textViewOpcion4;

    //vars
    private final int LIMITE_FALLOS=3;
    private final String GAMETITLE = "TRIVIA";
    private int score=0, fallos=0, posicionPregunta;
    private ArrayList<Pregunta> arrayPreguntas;
    private Pregunta pregunta;
    private DataManager db;

    {
        db = new DataManager(TriviaActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        //Initialize views
        iniciarViews();
        textViewTitulo.setText(GAMETITLE);

        //Consultar las preguntas en la BBDD para añadir el texto
        arrayPreguntas = db.selectAllPreguntas();

        //Randomizamos la pregunta y las opciones
        generarPregunta();

        //Añadir textos a las views
        refrescarViews();

        //OnClick de los textViews
        textViewOpcion1.setOnClickListener(v -> {
            comprobarRespuesta(1);
        });
        textViewOpcion2.setOnClickListener(v -> {
            comprobarRespuesta(2);
        });
        textViewOpcion3.setOnClickListener(v -> {
            comprobarRespuesta(3);
        });
        textViewOpcion4.setOnClickListener(v -> {
            comprobarRespuesta(4);
        });

        //Intents
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return to menu
                setResult(RESULT_OK);
                finish();
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open your score
                Intent intento = new Intent(TriviaActivity.this, YourScoreActivity.class);
                intento.putExtra("game", GAMETITLE);
                intento.putExtra("score", score);
                startActivity(intento);
                setResult(RESULT_OK);
                finish();
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open your score
                Intent intento = new Intent(TriviaActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End Intents
    }

    private int generarRandom(){
        Random rnd = new Random();
        int ret=rnd.nextInt(arrayPreguntas.size());
        return ret;
    }

    private void randomizarOpciones(Pregunta pregunta){
        String[] arrayOpciones = {pregunta.getOpcion1(), pregunta.getOpcion2(), pregunta.getOpcion3(), pregunta.getOpcion4()};
        Random rnd = new Random();
        String aux;
        for (String ignored : arrayOpciones) {
            //Guardamos la posicion random y el String
            int posicion = rnd.nextInt(arrayOpciones.length);
            int posicionAux = posicion;
            aux = arrayOpciones[posicion];

            //Buscamos otra posicion random y sustituimos la anterior
            posicion = rnd.nextInt(arrayOpciones.length);
            arrayOpciones[posicionAux]=arrayOpciones[posicion];

            //Cojemos el primer String guardado(ahora borrado en el array) y lo guardamos en la nueva posicion
            arrayOpciones[posicion]=aux;

            //insertamos las opciones randomizadas de nuevo
            pregunta.setOpcion1(arrayOpciones[0]);
            pregunta.setOpcion2(arrayOpciones[1]);
            pregunta.setOpcion3(arrayOpciones[2]);
            pregunta.setOpcion4(arrayOpciones[3]);
        }
    }

    private void generarPreguntaYRefrescar(){
        generarPregunta();
        refrescarViews();
    }

    private void generarPregunta(){
        posicionPregunta = generarRandom();
        pregunta = arrayPreguntas.get(posicionPregunta);
        randomizarOpciones(pregunta);
    }

    private boolean comprobarRespuesta(int opcion){
        boolean ret = false;
        switch (opcion){
            case 1:
                if(pregunta.getOpcion1().equalsIgnoreCase(pregunta.getRespuesta())){
                    acertarPregunta();
                    ret=true;
                }else{
                    fallarPregunta();
                }

                return ret;
            case 2:
                if(pregunta.getOpcion2().equalsIgnoreCase(pregunta.getRespuesta())){
                    acertarPregunta();
                    ret=true;
                }else{
                    fallarPregunta();
                }

                return ret;
            case 3:
                if(pregunta.getOpcion3().equalsIgnoreCase(pregunta.getRespuesta())){
                    acertarPregunta();
                    ret=true;
                }else{
                    fallarPregunta();
                }

                return ret;
            case 4:
                if(pregunta.getOpcion4().equalsIgnoreCase(pregunta.getRespuesta())){
                    acertarPregunta();
                    ret=true;
                }else{
                    fallarPregunta();
                }

                return ret;
            default:
                return false;
        }
    }

    private void acertarPregunta(){
        Toast toast1 =Toast.makeText(getApplicationContext(), getText(R.string.stringCorrecto), Toast.LENGTH_SHORT);
        toast1.show();
        score=score+500;
        generarPreguntaYRefrescar();
    }

    private void fallarPregunta(){
        Toast toast1 =Toast.makeText(getApplicationContext(), getText(R.string.stringIncorrecto), Toast.LENGTH_SHORT);
        toast1.show();
        fallos=fallos+1;
        if(comprobarFallos()){
            generarPreguntaYRefrescar();
        }else{
            terminarPartida();
        }
    }

    private boolean comprobarFallos(){
        return fallos<LIMITE_FALLOS;
    }

    private void terminarPartida(){
        //guardarScore();
        mostrarDialogPartidaTerminada();
    }

    private void guardarScore(){
        //Open your score
        Intent intento = new Intent(TriviaActivity.this, YourScoreActivity.class);
        intento.putExtra("game", GAMETITLE);
        intento.putExtra("score", score);
        startActivity(intento);
        setResult(RESULT_OK);
        finish();
    }

    private void mostrarDialogPartidaTerminada(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set the title of the Alert Dialog
        alertDialogBuilder.setTitle(getString(R.string.stringPartidaTerminada));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.stringMensajePartidaTerminada))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.stringReiniciar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                score=0;
                                fallos=0;
                                generarPreguntaYRefrescar();
                            }
                        })
                .setNegativeButton(getString(R.string.stringVolverMenu),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Return to menu
                                setResult(RESULT_OK);
                                finish();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void iniciarViews(){
        btnReturn = findViewById(R.id.btn_trivia_return);
        btnScore = findViewById(R.id.btn_trivia_save);
        btnInfo= findViewById(R.id.btn_trivia_info);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewStringScore = findViewById(R.id.textViewStringScore);
        textViewScore = findViewById(R.id.textViewScore);
        textViewStringIntentos = findViewById(R.id.textViewStringIntentos);
        textViewIntentos = findViewById(R.id.textViewIntentos);
        textViewPregunta = findViewById(R.id.textViewPregunta);
        textViewOpcion1 = findViewById(R.id.textViewOpcion1);
        textViewOpcion2 = findViewById(R.id.textViewOpcion2);
        textViewOpcion3 = findViewById(R.id.textViewOpcion3);
        textViewOpcion4 = findViewById(R.id.textViewOpcion4);
    }

    private void refrescarViews(){
        textViewScore.setText(String.valueOf(score));
        textViewIntentos.setText(String.valueOf(fallos));
        textViewPregunta.setText(pregunta.getTexto());
        textViewOpcion1.setText(pregunta.getOpcion1());
        textViewOpcion2.setText(pregunta.getOpcion2());
        textViewOpcion3.setText(pregunta.getOpcion3());
        textViewOpcion4.setText(pregunta.getOpcion4());
    }
}