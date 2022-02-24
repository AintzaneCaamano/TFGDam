package es.gameapp.multigameapp.hanoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.blackjack.BlackActivity;
import es.gameapp.multigameapp.simon.SimonActivity;

public class HanoyActivity extends AppCompatActivity {
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;

    //vars
    private int score=0;
    private final String GAMETITLE = "HANOY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanoy);
        //Initialize views
        btnReturn = findViewById(R.id.btn_hanoy_return);
        btnScore = findViewById(R.id.btn_hanoy_save);
        btnInfo= findViewById(R.id.btn_hanoy_info);

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
                Intent intento = new Intent(HanoyActivity.this, YourScoreActivity.class);
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
                Intent intento = new Intent(HanoyActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End Intents
    }
}