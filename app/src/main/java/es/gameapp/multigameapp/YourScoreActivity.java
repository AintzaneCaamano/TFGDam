package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.gameapp.multigameapp.simon.SimonActivity;

public class YourScoreActivity extends AppCompatActivity {
    //This activity shows the score you got from a game and allows you to save it in DB
    private Button btnReturn;
    private Button btnRanking;
    private TextView txtVGameName;
    //vars
    private String user;
    private String game;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score);
        //Buttons
        btnReturn = findViewById(R.id.btn_score_return);
        btnRanking = findViewById(R.id.btn_score_save);
        //TxtViews
        txtVGameName = findViewById(R.id.txtV_score_gameName);

        //Get extras from previous activity
        Bundle extras = getIntent().getExtras();
        game = extras.getString("game");
        score = extras.getInt("score");

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
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open your score
                Intent intento = new Intent(YourScoreActivity.this, RankingActivity.class);
                startActivity(intento);
                setResult(RESULT_OK);
                finish();
            }
        });
        //End Intents
    }

}