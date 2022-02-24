package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RankingActivity extends AppCompatActivity {
    //This activity shows you the highest scores in a game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }
}