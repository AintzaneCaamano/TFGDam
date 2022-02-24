package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    //This activity explains how to play a game
    //Views
    private Button btnReturn;
    private TextView txtVGameName;
    //vars
    private  String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnReturn = findViewById(R.id.btn_info_return);
        txtVGameName = findViewById(R.id.txtV_info_gameName);

        //Get extras from previous activity
        Bundle extras = getIntent().getExtras();
        game = extras.getString("game");

        //Show game name
        txtVGameName.setText(game);

        //Intents
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return to menu
                setResult(RESULT_OK);
                finish();
            }
        });
        //End Intents
    }
}