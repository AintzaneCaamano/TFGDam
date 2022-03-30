package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.gameapp.multigameapp.media.MediaPController;
import es.gameapp.multigameapp.simon.SimonActivity;

public class YourScoreActivity extends AppCompatActivity {
    //This activity shows the score you got from a game and allows you to save it in DB
    private Button btnReturn;
    private Button btnRanking;
    private TextView txtVGameName;
    private TextView txtVScore;
    private TextView txtVPlayerName;
    //vars
    private String user;
    private String game;
    private int score;

    //Media
    private MediaPController mediaPController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score);
        //Buttons
        btnReturn = findViewById(R.id.btn_score_return);
        btnRanking = findViewById(R.id.btn_score_save);
        //TxtViews
        txtVGameName = findViewById(R.id.txtV_score_gameName);
        txtVScore = findViewById(R.id.txtV_score_score);
        txtVPlayerName = findViewById(R.id.txtV_score_insertName);

        //Get extras from previous activity
        Bundle extras = getIntent().getExtras();
        game = extras.getString("game");
        score = extras.getInt("score");

        txtVGameName.setText(game);
        txtVScore.setText(String.valueOf(score));

        //Media
        //Initialize Uris
        Uri Endmusic = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.grand_final_orchestral);
        Uri music = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.comedy);
        //Initialize mediaPController
        mediaPController = new MediaPController(this);
        //Initialize music
        mediaPController.initMediaPlayer(Endmusic);
        //End Media

        //Intents
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Closes mediaController
                mediaPController.closeMediaPlayer();
                //Return to menu
                setResult(RESULT_OK);
                finish();
            }
        });
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(saveRanking()) {
                   //Closes mediaController
                   mediaPController.closeMediaPlayer();
                   //Open ranking
                   Intent intento = new Intent(YourScoreActivity.this, RankingActivity.class);
                   intento.putExtra("game", game);
                   startActivity(intento);
                   setResult(RESULT_OK);
                   finish();
               }
            }
        });
        //End Intents
    }

    private boolean saveRanking(){
        user = txtVPlayerName.getText().toString();
        if (user.length()>3) {
            return true;
        }else {
            String text = getResources().getString(R.string.toast_UserLength);
            Common.showToast(this, text);
            return false;
        }
    }

}