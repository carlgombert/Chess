package model;

import java.util.Scanner;

import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;

public class Piece {
    // Create Instance Variables
	private char character;
	private int row;
	private int col;
	private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
    	this.row = row;
        this.col = col;
        /*if(this.character == '\u265f' && this.isBlack && row == 7){
            this.row = row;
            this.col = col;
            this.promotePawn(row, true);
        } else if(this.character == '\u2659' && !this.isBlack && row == 0){
            this.row = row;
            this.col = col;
            this.promotePawn(row, false);
        } else{
            this.row = row;
            this.col = col;
        }*/

    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
    	return isBlack;
    }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    /*public void promotePawn(int row, boolean isBlack) {
    	Scanner pieceCheck = new Scanner(System.in);
        System.out.println("Enter the type you want to promote to (Knight, Bishop, Rook, or Queen");
        System.out.println("Enter 1,2,3,4 respectively");
        boolean checking = true;
        int input = 0;
        while(checking) {
            try {
                input = pieceCheck.nextInt();
                if(input == 1 || input == 2 || input == 3 || input == 4) {
                    checking = false;
                } else{
                    System.out.println("Enter either 1, 2, 3, or 4");
                }
            } catch (Exception e) {
                System.out.println("Enter either 1, 2, 3, or 4");
                checking = true;
            }
        }
        if(!isBlack){
            if(input == 1){
                this.character = '\u2658';
            } else if(input == 2) {
                this.character = '\u2657';
            } else if(input == 3){
                this.character = '\u2656';
            } else if (input == 4){
                this.character = '\u2655';
            }
        } else if(isBlack){
            if(input == 1){
                this.character = '\u265e';
            } else if(input == 2) {
                this.character = '\u265d';
            } else if(input == 3){
                this.character = '\u265c';
            } else if (input == 4){
                this.character = '\u265b';
            }
        }

    }*/

    /**
     * Returns the character
     * @return         this.character
     */
    public char getCharacter() {
		return character;
	}
    /**
     * Sets a new character for a piece
     * @param  character character to be set
     */
	public void setCharacter(char character) {
		this.character = character;
	}

	/**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
    	return ""+character;
    }


}
