package main;

import java.util.Scanner;

/**
 * Description of method’s functionality
 *
 * @param  myParameter  description of myParameters usage
 * @return         what the method is returning
 */

/**
 * Description of class purpose and functionality
 */


public class Game {
	public static void main(String[] args) {
		boolean running = true;
		
		Board board = new Board();
		
		//initializing board pieces
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        
        //creating boolean to keep track of whose turn it is
        boolean whiteTurn = true;
		
        Scanner input = new Scanner(System.in);
        
		while(running) {
			
			//print board to console
			System.out.println(board.toString());
			System.out.println("\n");
			
			if(whiteTurn) {
				System.out.println("White turn");
			}
			else {
				System.out.println("Black turn");
			}
			
			boolean moving = true;
			
			char[] move;
			
			// loops until player makes successful move with no errors
			while(moving) {
				System.out.println("enter your move (Format: [start row] [start column] [end row] [end column]");
				String inputMove = input.nextLine();
				
				move = inputMove.toCharArray();
				
				if(move.length >= 7) { // make sure input is the proper length
					int row1 = Character.getNumericValue(move[0]);
					int col1 = Character.getNumericValue(move[2]);
					int row2 = Character.getNumericValue(move[4]);
					int col2 = Character.getNumericValue(move[6]);
					
					if(!board.movePiece(row1, col1, row2, col2, !whiteTurn)) {
						System.out.println("invalid move\n");
					}
					else {
						moving = false;
						if(board.isGameOver()) {
							if(whiteTurn) {
								System.out.println("White has won!");
							}
							else {
								System.out.println("Black has won!");
							}
							running = false;
						}
					}
				}
				else {
					System.out.println("invalid move (wrong format)\n");
				}
			}
			
			whiteTurn = !whiteTurn;
		}
	}
}
