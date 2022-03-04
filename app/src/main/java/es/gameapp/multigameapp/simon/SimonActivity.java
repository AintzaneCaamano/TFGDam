package es.gameapp.multigameapp.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.MenuActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.blackjack.BlackActivity;
import es.gameapp.multigameapp.media.MediaPController;
import es.gameapp.multigameapp.trivia.TriviaActivity;

public class SimonActivity extends AppCompatActivity  implements View.OnClickListener{
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;
    private Button btnDo;
    private Button btnRe;
    private Button btnMi;
    private Button btnFa;
    private TextView txtVScore;

    //General vars
    private int score=0;
    private final String GAMETITLE = "SIMON";
    private Context context;

    //Game functionality vars
    private boolean continuePlaying=true;
    private int sequenceLength=3;
    private int userControl=1;
    private ArrayList<Integer> gameSequence = new ArrayList<Integer>();
    private ArrayList<Integer> userSequence = new ArrayList<Integer>();
    private SimonSequenceManager sequenceManager = new SimonSequenceManager();
    //Media
    private MediaPController mediaPController;
    //Media resources
    private  Uri SOUND_DO;
    private  Uri SOUND_RE;
    private  Uri SOUND_MI;
    private  Uri SOUND_FA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        //Save context
        context = this;

        //Initialize views
        btnReturn = findViewById(R.id.btn_simon_return);
        btnScore = findViewById(R.id.btn_simon_save);
        btnInfo= findViewById(R.id.btn_simon_info);
        btnDo = findViewById(R.id.btn_simon_do);
        btnRe = findViewById(R.id.btn_simon_re);
        btnMi = findViewById(R.id.btn_simon_mi);
        btnFa = findViewById(R.id.btn_simon_fa);
        txtVScore = findViewById(R.id.txtV_simon_score);

        //Simon Buttons OnClickListener
        btnDo.setOnClickListener(this);
        btnRe.setOnClickListener(this);
        btnMi.setOnClickListener(this);
        btnFa.setOnClickListener(this);

        //Uris
        SOUND_DO = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.always);
        SOUND_RE = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.re);
        SOUND_MI = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mi);
        SOUND_FA = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fa);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.always);
        txtVScore.setText(uri.toString());

        //Initialize mediaPController
        mediaPController = new MediaPController(this);

        //Intents to other activities
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Closes mediaController
                mediaPController.closeMediaPlayer();
                //Returns to menu
                setResult(RESULT_OK);
                finish();
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Closes mediaController
                mediaPController.closeMediaPlayer();
                //Opens your score
                Intent intento = new Intent(SimonActivity.this, YourScoreActivity.class);
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
                //Opens info
                Intent intento = new Intent(SimonActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End Intents
    }

    @Override
    public void onClick(View v) {
        //Controls on click of the game methods
        if (v==btnDo){
            mediaPController.initMediaPlayer(SOUND_DO);
            userSequence.add(0);
        }else if (v==btnRe){
            mediaPController.initMediaPlayer(SOUND_RE);
            userSequence.add(1);
        }else if (v==btnMi){
            mediaPController.initMediaPlayer(SOUND_MI);
            userSequence.add(2);
        }else if (v==btnFa){
            mediaPController.initMediaPlayer(SOUND_FA);
            userSequence.add(3);
        }
    }

    private void play(int number){
        if(continuePlaying) {
            //Checks if player hasn´t loose
            if (userControl < sequenceLength) {
                //Counts user moves and saves them
                userControl++;
                userSequence.add(number);
            } else {
                //If there are no moves left, it checks the sequence
                userControl = 1;
                userSequence.add(number);
                if(!sequenceManager.checkSequence(userSequence)){
                    //If gamer looses the game ends
                    continuePlaying=false;
                }else{
                    //If player wins game keeps going
                    score=score+10;
                    sequenceManager.addValue(1);
                    gameSequence=sequenceManager.getSequence();
                    showSequence();
                }
            }
        }
    }

    private void showSequence() {
        //It shows the secuence to the player
    }
}