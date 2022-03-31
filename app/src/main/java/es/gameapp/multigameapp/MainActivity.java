package es.gameapp.multigameapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.gameapp.multigameapp.extras.DataManager;
import es.gameapp.multigameapp.extras.InsertData;
import es.gameapp.multigameapp.trivia.TriviaActivity;

public class MainActivity extends AppCompatActivity {
    //This activity starts the app
    private DataManager db;

    {
        db = new DataManager(MainActivity.this);
    }

    //Temporal button for intents
    private Button btn, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //carga los arrays con objetos y los inserta en la base de datos
        cargarBaseDatos(MainActivity.this);

        btn = findViewById(R.id.btn_temporal_return);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, TriviaActivity.class);
            startActivity(intento);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    protected void openActivity(){
        //method to open new activities with intents
        Intent intento = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intento);
    }

    private void cargarBaseDatos(Context context){
        if(db.isEmpty()){
            InsertData insert = new InsertData(context);

            insert.cargarJuegosSql();
            insert.cargarPreguntasSql();
            //insert.cargarPalabrasSql();

        }
    }
}