package com.handelluss.tictactoegame.game;

public class Controller {

    /*
        First player is X
        Second player is O
    */

    private int scoreFirstPlayer;
    private int scoreSecondPlayer;
    private int moveCounter;
    private boolean isPlaying;
    private boolean isWin;
    private Cell lastMoveValue;
    private Field field;

    public Controller(){
        field = new Field();
        scoreFirstPlayer = 0;
        scoreSecondPlayer = 0;
        moveCounter = 0;
        lastMoveValue = null;
        isPlaying = true;
    }

    public int getFieldSize(){
        return field.FIELD_SIZE;
    }
    public String getStringLastMoveValue() {
        return Cell.toString(lastMoveValue);
    }
    public int getScoreFirstPlayer() {
        return scoreFirstPlayer;
    }
    public int getScoreSecondPlayer() {
        return scoreSecondPlayer;
    }
    public boolean isCellEmpty(int cellX, int cellY){
        return field.isCellEmpty(cellX, cellY);
    }
    public boolean isPlaying() {
        return isPlaying;
    }
    public boolean isWin() {
        return isWin;
    }

    public void move(int cellX, int cellY){
        Cell moveValue = getMoveValue();
        field.setCell(cellX, cellY, moveValue);
        moveCounter++;
        isWin = field.hasFoundLines();
        if(moveCounter == 9 || isWin){
            isPlaying = false;
            moveCounter = 0;
            if(isWin){
                changeScore();
            }
            field.resetField();
        }
        else{
            isWin = false;
            isPlaying = true;
        }
    }
    private Cell getMoveValue(){
        if(lastMoveValue == Cell.O || lastMoveValue == null){
            lastMoveValue = Cell.X;
            return Cell.X;
        }
        else{
            lastMoveValue = Cell.O;
            return Cell.O;
        }
    }
    private void changeScore(){
        switch(lastMoveValue){
            case X:
                scoreFirstPlayer += 1;
                break;
            case O:
                scoreSecondPlayer += 1;
                break;
            default:
                break;
        }
    }
}
