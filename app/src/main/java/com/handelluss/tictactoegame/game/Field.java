package com.handelluss.tictactoegame.game;

public class Field {

    protected final int FIELD_SIZE = 3;
    private Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];

    Field(){
        resetField();
    }

    protected void resetField() {
        for(int i = 0; i < FIELD_SIZE; i++){
            for(int j = 0; j < FIELD_SIZE; j++){
                field[i][j] = Cell.EMPTY;
            }
        }
    }
    protected boolean isCellEmpty(int cellX, int cellY){
        return field[cellX][cellY] == Cell.EMPTY;
    }
    protected void setCell(int cellX, int cellY, Cell cellValue){
        field[cellX][cellY] = cellValue;
    }
    protected boolean hasFoundLines(){
        if(hasFoundDiagonalLines() || hasFoundHorizontalLines() || hasFoundVerticalLines()){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean hasFoundVerticalLines(){
        for(int i = 0; i < FIELD_SIZE; i++){
            if(!isCellEmpty(0, i) && field[0][i] == field[1][i] && field[1][i] == field[2][i]){
                return true;
            }
        }
        return false;
    }
    private boolean hasFoundHorizontalLines(){
        for(int i = 0; i < FIELD_SIZE; i++){
            if(!isCellEmpty(i, 0) && field[i][0] == field[i][1] && field[i][1] == field[i][2]){
                return true;
            }
        }
        return false;
    }
    private boolean hasFoundDiagonalLines(){
        if(!isCellEmpty(0, 0) && field[0][0] == field[1][1] && field[1][1] == field[2][2]){
            return true;
        }
        if(!isCellEmpty(2, 0) && field[2][0] == field[1][1] && field[1][1] == field[0][2]){
            return true;
        }
        return false;
    }
}
