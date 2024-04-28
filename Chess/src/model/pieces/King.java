package model.pieces;

import model.Board;

public class King {
	
	// Instance variables
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The row of the king.
     * @param col   The column of the king .
     * @param isBlack   The color of the king.
     */
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    /**
     * Verifies that the move is legal
     * @param board the board that the king is on
     * @param endRow The end row to check
     * @param endCol the end column to check
     * @return returns a boolean true if the move is legal and false if the move is illegal
     */
	public boolean isMoveLegal(Board board, int endRow, int endCol) {
		return board.verifyAdjacent(row, col, endRow, endCol);
    }
}
