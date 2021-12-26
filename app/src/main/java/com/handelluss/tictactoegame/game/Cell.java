package com.handelluss.tictactoegame.game;

public enum Cell {
    X, O, EMPTY;

    public static String toString(Cell cell) {
        switch (cell){
            case X:
                return "X";
            case O:
                return "O";
            default:
                return " ";
        }
    }
}
