package es.gameapp.multigameapp.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.sopa.SopaActivity;
import es.gameapp.multigameapp.trivia.TriviaActivity;

public class SudokuActivity extends AppCompatActivity {
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;

    //vars
    private int score=0;
    private final String GAMETITLE = "SUDOKU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        //Initialize views
        btnReturn = findViewById(R.id.btn_sudoku_return);
        btnScore = findViewById(R.id.btn_sudoku_save);
        btnInfo= findViewById(R.id.btn_sudoku_info);

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
                Intent intento = new Intent(SudokuActivity.this, YourScoreActivity.class);
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
                Intent intento = new Intent(SudokuActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End Intents
    }
}