package com.handelluss.tictactoegame.game;

public class Controller {

    private class Player {

        private int score = 0;
        private final String number; // "first" or "second"

        Player(String number){
                this.number = number;
        }

        private int getWinCount(){
            return score;
        }
        private void hasWon(){
            score++;
        }
    }

    private final Player player1;
    private final Player player2;

    private String startMovingPlayer;
    private String currentMovingPlayer;
    private Field.Cell currentSign;

    private final Field field;
    private GameStatus gameStatus;

    public Controller(){
        player1 = new Player("first");
        player2 = new Player("second");
        startMovingPlayer = player1.number;
        currentMovingPlayer = startMovingPlayer;
        currentSign = Field.Cell.X;
        gameStatus = GameStatus.PLAYING;
        field = new Field();
    }

    public int getFieldSize(){
        return field.FIELD_SIZE;
    }
    public String getSign() {
        return Field.Cell.toString(currentSign);
    }
    public int getFirstPlayerScore() {
        return player1.getWinCount();
    }
    public int getSecondPlayerScore() {
        return player2.getWinCount();
    }
    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public void move(int row, int col){
        field.setCell(row, col, currentSign);

        boolean drawFlag = field.isFull();
        boolean winFlag = field.hasFoundLines();

        if(winFlag){
            if(currentMovingPlayer.equals(player1.number)){
                gameStatus = GameStatus.FIRST_WON;
                player1.hasWon();
            }
            else { // player2.number
                gameStatus = GameStatus.SECOND_WON;
                player2.hasWon();
            }
        }
        else if(drawFlag){
            gameStatus = GameStatus.DRAW;
        }
        else{
            swapCurrentMovingPlayer();
            swapCurrentSign();
        }
    }

    public void reset(){
        field.resetField();
        swapStartMovingPlayer();
        setStartCurrentSign();
        setGameStatus();
    }

    private void swapCurrentMovingPlayer(){
        if(currentMovingPlayer.equals(player1.number))
            currentMovingPlayer = player2.number;
        else
            currentMovingPlayer = player1.number;
    }
    private void swapStartMovingPlayer(){
        if (startMovingPlayer.equals(player1.number)) {
            startMovingPlayer = player2.number;
        }
        else {
            startMovingPlayer = player1.number;
        }
        currentMovingPlayer = startMovingPlayer;

    }
    private void swapCurrentSign(){
        if(currentSign == Field.Cell.X)
            currentSign = Field.Cell.O;
        else
            currentSign = Field.Cell.X;
    }
    private void setStartCurrentSign(){
        currentSign = Field.Cell.X;
    }
    private void setGameStatus(){
        gameStatus = GameStatus.PLAYING;
    }
}
