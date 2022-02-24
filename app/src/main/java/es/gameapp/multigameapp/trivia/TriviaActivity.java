package es.gameapp.multigameapp.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.sopa.SopaActivity;

public class TriviaActivity extends AppCompatActivity {
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;

    //vars
    private int score=0;
    private final String GAMETITLE = "TRIVIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        //Initialize views
        btnReturn = findViewById(R.id.btn_trivia_return);
        btnScore = findViewById(R.id.btn_trivia_save);
        btnInfo= findViewById(R.id.btn_trivia_info);

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
}