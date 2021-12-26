package com.handelluss.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.handelluss.tictactoegame.game.Controller;

public class TwoPlayersActivity extends AppCompatActivity {

    Button[][] buttons;
    TextView scoreFirstPlayer;
    TextView scoreSecondPlayer;
    TextView winTextView;
    Controller controller;
    int fieldSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);
        initGame();
    }

    private void initGame(){
        controller = new Controller();
        fieldSize = controller.getFieldSize();
        buttons = new Button[fieldSize][fieldSize];
        initButtons();
        scoreFirstPlayer = findViewById(R.id.scoreFirstPlayer);
        scoreSecondPlayer = findViewById(R.id.scoreSecondPlayer);
        winTextView = findViewById(R.id.winTextView);
    }

    private void initButtons(){
        buttons[0][0] = findViewById(R.id.buttonX0Y0);
        buttons[0][1] = findViewById(R.id.buttonX0Y1);
        buttons[0][2] = findViewById(R.id.buttonX0Y2);
        buttons[1][0] = findViewById(R.id.buttonX1Y0);
        buttons[1][1] = findViewById(R.id.buttonX1Y1);
        buttons[1][2] = findViewById(R.id.buttonX1Y2);
        buttons[2][0] = findViewById(R.id.buttonX2Y0);
        buttons[2][1] = findViewById(R.id.buttonX2Y1);
        buttons[2][2] = findViewById(R.id.buttonX2Y2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                int cellX = i;
                int cellY = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeMove(cellX, cellY);
                    }
                });
            }
        }
    }
    private void makeMove(int cellX, int cellY){
        if(!winTextView.getText().equals(R.string.cellEmpty)){
            winTextView.setText(R.string.cellEmpty);
        }
        if(controller.isCellEmpty(cellX, cellY)){
            controller.move(cellX, cellY);
            buttons[cellX][cellY].setText(controller.getStringLastMoveValue());
            if(!controller.isPlaying()){
                resetActivityField();
            }
        }
    }
    private void resetActivityField(){
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                buttons[i][j].setText(" ");
            }
        }
        winTextView.setText(getWinnerTextView());
        scoreFirstPlayer.setText(Integer.toString(controller.getScoreFirstPlayer()));
        scoreSecondPlayer.setText(Integer.toString(controller.getScoreSecondPlayer()));
    }
    private int getWinnerTextView(){
        if(controller.isWin()){
            switch (controller.getStringLastMoveValue()){
                case "X":
                    return R.string.firstWonText;
                case "O":
                    return R.string.secondWonText;
                default:
                    Log.e("winTextViewError", "default case happened");
                    return -1;
            }
        }
        else{
            return R.string.drawText;
        }

    }
}