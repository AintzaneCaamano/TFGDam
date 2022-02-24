package es.gameapp.multigameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class YourScoreActivity extends AppCompatActivity {
    //This activity shows the score you got from a game and allows you to save it in DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score);
    }
}