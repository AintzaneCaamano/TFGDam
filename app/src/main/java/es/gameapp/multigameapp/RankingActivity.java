package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import es.gameapp.multigameapp.adapters.ScoreAdapter;
import es.gameapp.multigameapp.database.DBManager;
import es.gameapp.multigameapp.database.Score;
import es.gameapp.multigameapp.media.MediaPController;

public class RankingActivity extends AppCompatActivity {
    //This activity shows you the highest scores in a game
    private Button btnReturn;

    //Vars
    String game;

    //Adapter
    private ScoreAdapter scoreAdapter= null;

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

        //Get extras from previous activity
        Bundle extras = getIntent().getExtras();
        game = extras.getString("game");

        //DB Manager
        DBManager dbManager = new DBManager(this);
        dbManager.getWritableDatabase();
        ArrayList<Score> scores = (ArrayList <Score>) dbManager.selectScoreByGame(game);

        scoreAdapter= new ScoreAdapter(RankingActivity.this, R.layout.act_list_adapter, scores);
        ((ListView) findViewById( R.id.activityListView )).setAdapter(scoreAdapter);


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