package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.gameapp.multigameapp.media.MediaPController;

public class RankingActivity extends AppCompatActivity {
    //This activity shows you the highest scores in a game
    private Button btnReturn;
    //Media
    private MediaPController mediaPController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //Media
        //Initialize Uris
        Uri music = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.comedy);
        //Initialize mediaPController
        mediaPController = new MediaPController(this);
        //Initialize music
        mediaPController.initMediaPlayer(music);
        //End Media

        btnReturn = findViewById(R.id.btn_ranking_return);
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

        //End Intents
    }
}