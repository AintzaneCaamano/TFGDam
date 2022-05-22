package es.gameapp.multigameapp.sopa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.extras.DataManager;
import es.gameapp.multigameapp.hanoi.HanoyActivity;
import es.gameapp.multigameapp.modelo.Sopa;
import es.gameapp.multigameapp.trivia.TriviaActivity;


public class SopaActivity extends AppCompatActivity {
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;
    private TextView textViewLinea1, textViewLinea2, textViewLinea3, textViewLinea4, textViewLinea5, textViewLinea6, textViewLinea7, textViewTematica, textViewTitulo, textViewScore, textViewIntentos;
    private CheckBox checkBoxPalabra1, checkBoxPalabra2, checkBoxPalabra3, checkBoxPalabra4, checkBoxPalabra5, checkBoxPalabra6;
    private ArrayList<Sopa> arraySopas;
    private Sopa sopa;
    String[] arrayPalabras;
    private Button botonComprobar;
    private EditText editTextRespuesta;
    private DataManager db;

    {
        db = new DataManager(SopaActivity.this);
    }

    //vars
    private int score=0;
    private int fallos=0;
    private final String GAMETITLE = "SOPA TEMATICA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopa);
        //Initialize views
        btnReturn = findViewById(R.id.btn_sopa_return);
        btnScore = findViewById(R.id.btn_sopa_save);
        btnInfo= findViewById(R.id.btn_sopa_info);
        botonComprobar = findViewById(R.id.buttonComprobarPalabra);
        textViewLinea1 = findViewById(R.id.textViewLinea1);
        textViewLinea2 = findViewById(R.id.textViewLinea2);
        textViewLinea3 = findViewById(R.id.textViewLinea3);
        textViewLinea4 = findViewById(R.id.textViewLinea4);
        textViewLinea5 = findViewById(R.id.textViewLinea5);
        textViewLinea6 = findViewById(R.id.textViewLinea6);
        textViewLinea7 = findViewById(R.id.textViewLinea7);
        textViewTematica = findViewById(R.id.textViewTematica);
        textViewTitulo = findViewById(R.id.textViewTituloSopa);
        textViewTitulo.setText(GAMETITLE);
        textViewScore = findViewById(R.id.textViewScoreSopa);
        textViewIntentos = findViewById(R.id.textViewIntentosSopa);
        editTextRespuesta = findViewById(R.id.editTextPalabra);
        checkBoxPalabra1 = findViewById(R.id.checkBoxPalabra1);
        checkBoxPalabra2 = findViewById(R.id.checkBoxPalabra2);
        checkBoxPalabra3 = findViewById(R.id.checkBoxPalabra3);
        checkBoxPalabra4 = findViewById(R.id.checkBoxPalabra4);
        checkBoxPalabra5 = findViewById(R.id.checkBoxPalabra5);
        checkBoxPalabra6 = findViewById(R.id.checkBoxPalabra6);

        consultarBBDD();
        iniciarSopa();

        botonComprobar.setOnClickListener(v -> {
            String respuesta = editTextRespuesta.getText().toString();
            for (int i=0; i<arrayPalabras.length; i++){
                if(arrayPalabras[i].equalsIgnoreCase(respuesta)){
                    respuestaCorrecta(i);
                    break;
                }

                if(i==5){
                    respuestaIncorrecta();
                }
            }
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
                Intent intento = new Intent(SopaActivity.this, YourScoreActivity.class);
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
                Intent intento = new Intent(SopaActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End Intents
    }

    private void iniciarSopa() {
        seleccionarSopa();
        rellenarSopa();
        ocultarCheckBoxes();
        rellenarCheckboxes();
        refrescarScoreViews();
        refrescarViews();
        textViewTematica.setText(sopa.getTematica());
    }


    private void rellenarSopa(){
        String sopaLetras = sopa.getStringSopa();
        String[] arraySopa = sopaLetras.split(" ");
        textViewLinea1.setText(arraySopa[0]);
        textViewLinea2.setText(arraySopa[1]);
        textViewLinea3.setText(arraySopa[2]);
        textViewLinea4.setText(arraySopa[3]);
        textViewLinea5.setText(arraySopa[4]);
        textViewLinea6.setText(arraySopa[5]);
        textViewLinea7.setText(arraySopa[6]);
    }

    private void ocultarCheckBoxes(){
        checkBoxPalabra1.setVisibility(View.GONE);
        checkBoxPalabra2.setVisibility(View.GONE);
        checkBoxPalabra3.setVisibility(View.GONE);
        checkBoxPalabra4.setVisibility(View.GONE);
        checkBoxPalabra5.setVisibility(View.GONE);
        checkBoxPalabra6.setVisibility(View.GONE);
    }

    private void mostrarCheckBoxes(){
        checkBoxPalabra1.setVisibility(View.VISIBLE);
        checkBoxPalabra2.setVisibility(View.VISIBLE);
        checkBoxPalabra3.setVisibility(View.VISIBLE);
        checkBoxPalabra4.setVisibility(View.VISIBLE);
        checkBoxPalabra5.setVisibility(View.VISIBLE);
        checkBoxPalabra6.setVisibility(View.VISIBLE);
    }

    private void uncheckCheckBoxes(){
        checkBoxPalabra1.setChecked(false);
        checkBoxPalabra2.setChecked(false);
        checkBoxPalabra3.setChecked(false);
        checkBoxPalabra4.setChecked(false);
        checkBoxPalabra5.setChecked(false);
        checkBoxPalabra6.setChecked(false);
    }

    private void respuestaCorrecta(int posicion){
        switch (posicion) {
            case 0:
                checkBoxPalabra1.setChecked(true);
                checkBoxPalabra1.setVisibility(View.VISIBLE);
                break;

            case 1:
                checkBoxPalabra2.setChecked(true);
                checkBoxPalabra2.setVisibility(View.VISIBLE);
                break;

            case 2:
                checkBoxPalabra3.setChecked(true);
                checkBoxPalabra3.setVisibility(View.VISIBLE);
                break;

            case 3:
                checkBoxPalabra4.setChecked(true);
                checkBoxPalabra4.setVisibility(View.VISIBLE);
                break;

            case 4:
                checkBoxPalabra5.setChecked(true);
                checkBoxPalabra5.setVisibility(View.VISIBLE);
                break;

            case 5:
                checkBoxPalabra6.setChecked(true);
                checkBoxPalabra6.setVisibility(View.VISIBLE);
                break;
        }
        score=score+500;
        refrescarScoreViews();
        comprobarVictoria();
    }

    private void respuestaIncorrecta(){
        Toast toast1 =Toast.makeText(getApplicationContext(), getText(R.string.stringIncorrecto), Toast.LENGTH_SHORT);
        toast1.show();
        fallos=fallos+1;
        refrescarScoreViews();
        if(fallos==3){
            mostrarCheckBoxes();
            mostrarDialogPartidaTerminada();
        }
    }

    private void rellenarCheckboxes(){
        arrayPalabras = sopa.getStringArrayPalabras().split(";");
        checkBoxPalabra1.setText(arrayPalabras[0]);
        checkBoxPalabra2.setText(arrayPalabras[1]);
        checkBoxPalabra3.setText(arrayPalabras[2]);
        checkBoxPalabra4.setText(arrayPalabras[3]);
        checkBoxPalabra5.setText(arrayPalabras[4]);
        checkBoxPalabra6.setText(arrayPalabras[5]);
    }

    private void consultarBBDD(){
        arraySopas = db.selectAllSopas();
    }

    private int generarRandom(){
        Random rnd = new Random();
        int ret=rnd.nextInt(arraySopas.size());
        return ret;
    }

    private void seleccionarSopa(){
        sopa = arraySopas.get(generarRandom());
    }

    private void comprobarVictoria(){
        if(checkBoxPalabra1.isChecked()&&checkBoxPalabra2.isChecked()&&checkBoxPalabra3.isChecked()&&checkBoxPalabra4.isChecked()&&checkBoxPalabra5.isChecked()&&checkBoxPalabra6.isChecked()){
            mostrarDialogPartidaTerminada();
        }
    }

    private void refrescarScoreViews(){
        textViewScore.setText(String.valueOf(score));
        textViewIntentos.setText(String.valueOf(fallos));
    }

    private void refrescarViews(){
        rellenarSopa();
        rellenarCheckboxes();
    }

    private void mostrarDialogPartidaTerminada(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set the title of the Alert Dialog
        alertDialogBuilder.setTitle(getString(R.string.stringPartidaTerminada));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.stringPartidaGanada))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.stringReiniciar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                score=0;
                                fallos=0;
                                uncheckCheckBoxes();
                                iniciarSopa();
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
}