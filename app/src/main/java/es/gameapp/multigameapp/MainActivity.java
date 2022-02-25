package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< Updated upstream

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
=======
import android.content.Context;
import android.os.Bundle;
import es.gameapp.multigameapp.extras.DataManager;
import es.gameapp.multigameapp.extras.InsertData;
>>>>>>> Stashed changes

public class MainActivity extends AppCompatActivity {
    //This activity starts the app
    private DataManager db;

    {
        db = new DataManager(MainActivity.this);
    }

    //Temporal button for intents
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< Updated upstream
        btn = findViewById(R.id.btn_temporal_return);
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
=======
        cargarBaseDatos(MainActivity.this);



    }

    private void cargarBaseDatos(Context context){
        if(db.isEmpty()){
            InsertData insert = new InsertData(context);
            insert.cargarPreguntasSql();
        }
>>>>>>> Stashed changes
    }
}