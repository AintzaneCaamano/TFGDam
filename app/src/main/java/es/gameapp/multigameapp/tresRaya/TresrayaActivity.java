package es.gameapp.multigameapp.tresRaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import es.gameapp.multigameapp.Common;
import es.gameapp.multigameapp.InfoActivity;
import es.gameapp.multigameapp.R;
import es.gameapp.multigameapp.YourScoreActivity;
import es.gameapp.multigameapp.simon.SimonActivity;

public class TresrayaActivity extends AppCompatActivity implements View.OnClickListener{
    //Views
    private Button btnReturn;
    private Button btnScore;
    private Button btnInfo;
    private Button btnA1;
    private Button btnA2;
    private Button btnA3;
    private Button btnB1;
    private Button btnB2;
    private Button btnB3;
    private Button btnC1;
    private Button btnC2;
    private Button btnC3;
    private TextView txtVScore;

    //General vars
    private boolean continuePlaying;
    private int score=0;
    private int moves=0;
    private final String GAMETITLE = "TRESRAYA";
    private Context context;
    private final String PLAYERA = "X";
    private final String PLAYERB = "O";
    private String[][] matrix = new String[3][3];
    private Button[][] btnList = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tresraya);

        //Save context
        context = this;

        //Initialize views
        btnReturn = findViewById(R.id.btn_tresraya_return);
        btnScore = findViewById(R.id.btn_tresraya_save);
        btnInfo = findViewById(R.id.btn_tresraya_info);
        btnA1 = findViewById(R.id.btn_tresraya_a1);
        btnA2 = findViewById(R.id.btn_tresraya_a2);
        btnA3 = findViewById(R.id.btn_tresraya_a3);
        btnB1 = findViewById(R.id.btn_tresraya_b1);
        btnB2 = findViewById(R.id.btn_tresraya_b2);
        btnB3 = findViewById(R.id.btn_tresraya_b3);
        btnC1 = findViewById(R.id.btn_tresraya_c1);
        btnC2 = findViewById(R.id.btn_tresraya_c2);
        btnC3 = findViewById(R.id.btn_tresraya_c3);
        txtVScore = findViewById(R.id.txtV_tresraya_score);

        //game Buttons OnClickListener
        btnA1.setOnClickListener(this);
        btnA2.setOnClickListener(this);
        btnA3.setOnClickListener(this);
        btnB1.setOnClickListener(this);
        btnB2.setOnClickListener(this);
        btnB3.setOnClickListener(this);
        btnC1.setOnClickListener(this);
        btnC2.setOnClickListener(this);
        btnC3.setOnClickListener(this);

        //init vars
        continuePlaying=true;
        score = 300;
        //Fills matrix array
        initMatrix();
        initBtnList();

        showScore();

        //Onclick for the intents to other activities
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Returns to menu
                setResult(RESULT_OK);
                finish();
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens your score
                Intent intento = new Intent(TresrayaActivity.this, YourScoreActivity.class);
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
                Intent intento = new Intent(TresrayaActivity.this, InfoActivity.class);
                intento.putExtra("game", GAMETITLE);
                startActivity(intento);
            }
        });
        //End of Intents section

    }

    @Override
    public void onClick(View v) {
        //This gets sign to write it in the buttons
        String text = PLAYERA;
        //This checks the button to set the text and saves it in the array
        if (continuePlaying) {
            if (v == btnA1) {
                btnList[0][0].setText(text);
                matrix[0][0] = text;
            } else if (v == btnA2) {
                matrix[1][0] = text;
                btnList[1][0].setText(text);
            } else if (v == btnA3) {
                matrix[2][0] = text;
                btnList[2][0].setText(text);
            } else if (v == btnB1) {
                matrix[0][1] = text;
                btnList[0][1].setText(text);
            } else if (v == btnB2) {
                matrix[1][1] = text;
                btnList[1][1].setText(text);
            } else if (v == btnB3) {
                matrix[2][1] = text;
                btnList[2][1].setText(text);
            } else if (v == btnC1) {
                matrix[0][2] = text;
                btnList[0][2].setText(text);
            } else if (v == btnC2) {
                matrix[1][2] = text;
                btnList[1][2].setText(text);
            } else if (v == btnC3) {
                matrix[2][2] = text;
                btnList[2][2].setText(text);
            }
            //Count number of moves
            moves++;
            if (moves >3){
                score= score -100;
                showScore();
            }
            //This disables the button
            v.setEnabled(false);
            //This checks the button
            checkWinner();
        }
    }

    private void initMatrix(){
        //this fills the matrix array with dots
        for (int i = 0 ; i<matrix.length; i++){
            for (int j = 0 ; j<matrix.length; j++){
                matrix[i][j]=".";
            }
        }
    }
    private void initBtnList(){
        //this fills the btnlist array with buttons
        btnList[0][0] = btnA1;
        btnList[1][0] = btnA2;
        btnList[2][0] = btnA3;
        btnList[0][1] = btnB1;
        btnList[1][1] = btnB2;
        btnList[2][1] = btnB3;
        btnList[0][2] = btnC1;
        btnList[1][2] = btnC2;
        btnList[2][2] = btnC3;
    }

    private void showScore(){
        //Show score in screen
        String showScore = getResources().getString(R.string.txt_yourScore) + " " + String.valueOf(score);
        txtVScore.setText(showScore);
    }

    private void cleanButtons(){
        //Clear text
        btnList[0][0].setText("");
        btnList[1][0].setText("");
        btnList[2][0].setText("");
        btnList[0][1].setText("");
        btnList[1][1].setText("");
        btnList[2][1].setText("");
        btnList[0][2].setText("");
        btnList[1][2].setText("");
        btnList[2][2].setText("");
        //Enable buttons
        btnList[0][0].setEnabled(true);
        btnList[1][0].setEnabled(true);
        btnList[2][0].setEnabled(true);
        btnList[0][1].setEnabled(true);
        btnList[1][1].setEnabled(true);
        btnList[2][1].setEnabled(true);
        btnList[0][2].setEnabled(true);
        btnList[1][2].setEnabled(true);
        btnList[2][2].setEnabled(true);
    }

    private void checkWinner(){
        if(!checkMatrix()){
            play();
        }else{
            Common.showToast(context, "Un jugador ha ganado");
            continuePlaying=false;
        }
    }
    private boolean checkMatrix(){
        //Checks if any player won the game
        for (int i = 0 ; i<matrix.length; i++){
            if(matrix[i][i].equalsIgnoreCase(PLAYERA) || matrix[i][i].equalsIgnoreCase(PLAYERB)){
                //If there is a dot in the central position it doesnt check the line
                if (matrix[i][0]==matrix[i][1] && matrix[i][0]==matrix[i][2]){
                    //this checks the matrix horizontally
                    return true;
                }else if (matrix[0][i]==matrix[1][i] && matrix[0][i]==matrix[2][i]){
                    //This checks the line vertically
                    return true;
                }
            }
        }
        if(matrix[1][1].equalsIgnoreCase(PLAYERA) || matrix[1][1].equalsIgnoreCase(PLAYERB)) {
            //It checks the central box then checks the diagonals
            if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) {
                return true;
            }else if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0]) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }


    private void play(){
        boolean rep= true;
        int delay = 350;
        int num1 = 1;
        int num2 = 1;
        Random random = new Random();

        if(!matrix[1][1].equalsIgnoreCase(("."))){
            //Checks if the center box is occupied
            //Checks if player has two boxes filled
            int[] analize =checkBoard();
            if (analize[0]==0) {
                while (rep) {
                    num1 = random.nextInt(3);
                    num2 = random.nextInt(3);
                    //Adds half a second each time
                    if (matrix[num1][num2].equalsIgnoreCase(".")) {
                        rep = false;
                    }
                }
            }else if (analize[0]==1) {
                num1=analize[1];
                while (rep) {
                    num2 = random.nextInt(3);
                    //Adds half a second each time
                    if (matrix[num1][num2].equalsIgnoreCase(".")) {
                        rep = false;
                    }
                }
            }else if (analize[0]==-1) {
                num2=analize[1];
                while (rep) {
                    num1 = random.nextInt(3);
                    //Adds half a second each time
                    if (matrix[num1][num2].equalsIgnoreCase(".")) {
                        rep = false;
                    }
                }
            }else if(analize[0]==2){
                if (matrix[0][0].equalsIgnoreCase(".")) {
                    num1=0;
                    num2=0;
                }else if (matrix[2][2].equalsIgnoreCase(".")){
                    num1=2;
                    num2=2;
                }
            }else if(analize[0]==-2){
                if (matrix[0][2].equalsIgnoreCase(".")) {
                    num1=0;
                    num2=2;
                }else if (matrix[2][0].equalsIgnoreCase(".")){
                    num1=2;
                    num2=0;
                }
            }
        }
        matrix[num1][num2] = PLAYERB;
        checkMatrix();
        //Initialize handler to set the delay
        Handler handler = new Handler();
        int finalNum = num2;
        int finalNum1 = num1;
        handler.postDelayed(new Runnable() {
            public void run() {
                btnList[finalNum1][finalNum].setText(PLAYERB);
                btnList[finalNum1][finalNum].setEnabled(false);
                changeButton(finalNum1, finalNum);
                //Closes the the delay code
            }
        }, delay); //0.35 seconds delay for each
    }

    private int[] checkBoard(){
        int lineH = 0;
        int lineV = 0;
        int[] ret = new int[2];
        ret[0]=0;
        //Checks if any player won the game
        for (int i = 0 ; i<matrix.length; i++){
            lineH = 0;
            lineV = 0;
                //If there is a dot in the central position it doesnt check the line
                if (matrix[i][0]==matrix[i][1] && matrix[i][0].equalsIgnoreCase(PLAYERA)){
                    //this checks the matrix horizontally
                    lineH ++;
                }else if(matrix[i][0]==matrix[i][2] && matrix[i][0].equalsIgnoreCase(PLAYERA)){
                    //this checks the matrix horizontally
                    lineH ++;
                }else if (matrix[i][1]==matrix[i][2] && matrix[i][1].equalsIgnoreCase(PLAYERA)){
                    //this checks the matrix horizontally
                    lineH ++;
                }else if (matrix[0][i]==matrix[1][i] && matrix[0][i].equalsIgnoreCase(PLAYERA)){
                    //This checks the line vertically
                    lineV++;
                }else if (matrix[0][i]==matrix[2][i] && matrix[0][i].equalsIgnoreCase(PLAYERA)){
                    //This checks the line vertically
                    lineV++;
                }else if (matrix[1][i]==matrix[2][i] && matrix[1][i].equalsIgnoreCase(PLAYERA)){
                    //This checks the line vertically
                    lineV++;
                }
                if(lineH>1){
                    ret[0]=1;
                    ret[1]=i;
                    return ret;
                }else if(lineV>1){
                    ret[0]=-1;
                    ret[1]=i;
                    return ret;
                }
        }
        lineH = 0;
        lineV = 0;
            //It checks the diagonals
            if (matrix[0][0] == matrix[1][1] && matrix[0][0].equalsIgnoreCase(PLAYERA)){
                lineH++;
            }
            if(matrix[0][0] == matrix[2][2] && matrix[0][0].equalsIgnoreCase(PLAYERA)) {
                lineH++;
            }
            if(matrix[1][1] == matrix[2][2] && matrix[1][1].equalsIgnoreCase(PLAYERA)){
                lineH++;
            }
            if (lineH>1){
                ret[0]=2;
                return ret;
            }
            if (matrix[0][2] == matrix[1][1] && matrix[0][2].equalsIgnoreCase(PLAYERA)){
                lineH++;
            }
            if(matrix[0][2] == matrix[2][0] && matrix[0][2].equalsIgnoreCase(PLAYERA)) {
                lineH++;
            }
            if(matrix[1][1] == matrix[2][0] && matrix[1][1].equalsIgnoreCase(PLAYERA)){
                lineH++;
            }
            if (lineH>1){
                ret[0]=-2;
                return ret;
            }

        return ret;
    }

    private void changeButton(int num1, int num2){

    }


}