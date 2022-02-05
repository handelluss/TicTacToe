package com.handelluss.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.handelluss.tictactoegame.game.Controller;
import com.handelluss.tictactoegame.game.GameStatus;

public class TwoPlayersActivity extends AppCompatActivity {

    private Button[][] buttons;
    private TextView scoreFirstPlayer;
    private TextView scoreSecondPlayer;
    private TextView winTextView;
    private Controller controller;
    private int fieldSize;

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
        initListeners();
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
    private void initListeners(){
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                int row = i;
                int col = j;
                buttons[i][j].setOnClickListener(view -> {
                    if(isCellEmpty(row, col))
                        makeMove(row, col);
                });
            }
        }
    }
    
    private void makeMove(int row, int col){
        setStatusTextView();

        buttons[row][col].setText(controller.getSign());
        controller.move(row, col);
        if(controller.getGameStatus() != GameStatus.PLAYING){
            controller.reset();
            resetActivityField();
        }
    }

    private void setStatusTextView(){
        if(!winTextView.getText().equals(getString(R.string.cellEmpty))){
            winTextView.setText(R.string.cellEmpty);
        }
    }

    private void resetActivityField(){
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                buttons[i][j].setText(R.string.cellEmpty);
            }
        }
        winTextView.setText(getWinnerTextView());
        scoreFirstPlayer.setText(Integer.toString(controller.getFirstPlayerScore()));
        scoreSecondPlayer.setText(Integer.toString(controller.getSecondPlayerScore()));
    }

    private int getWinnerTextView(){
        GameStatus gameStatus = controller.getGameStatus();

        if (gameStatus == GameStatus.FIRST_WON)
            return R.string.firstWonText;
        else if (gameStatus == GameStatus.SECOND_WON)
            return R.string.secondWonText;
        else
            return R.string.drawText;
    }

    private boolean isCellEmpty(int row, int col){
        return buttons[row][col].getText().equals(getString(R.string.cellEmpty));
    }
    
}