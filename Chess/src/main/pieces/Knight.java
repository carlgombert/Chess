package main.pieces;

import main.Board;

public class Knight {
	
	// Instance variables
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The row of the knight.
     * @param col   The column of the knight .
     * @param isBlack   The color of the knight.
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    /**
     * Verifies that the move is legal
     * @param board the board that the knight is on
     * @param endRow The end row to check
     * @param endCol the end column to check
     * @return returns a boolean true if the move is legal and false if the move is illegal
     */
	public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(col == endCol-1 || col == endCol+1) {
        	if(row == endRow-2 || row == endRow+2) {
        		return true;
        	}
        }
        else if(col == endCol-2 || col == endCol+2) {
        	if(row == endRow-1 || row == endRow+1) {
        		return true;
        	}
        }
        return false;
    }
}
