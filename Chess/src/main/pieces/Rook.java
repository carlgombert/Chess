package main.pieces;

import main.Board;

public class Rook {
	
	// Instance variables
    private int row;
    private int col;
    private boolean isBlack;
    
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

	public boolean isMoveLegal(Board board, int endRow, int endCol) {
		boolean legal = board.verifyVertical(row, col, endRow, endCol) || board.verifyHorizontal(row, col, endRow, endCol);
		return legal;
    }
}
