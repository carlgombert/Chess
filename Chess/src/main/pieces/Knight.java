package main.pieces;

import main.Board;

public class Knight {
	
	// Instance variables
    private int row;
    private int col;
    private boolean isBlack;
    
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
	
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
