package main;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		boolean running = true;
		
		Board board = new Board();
		
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        
        boolean whiteTurn = true;
		
        Scanner input = new Scanner(System.in);
        
		while(running) {
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
			
			while(moving) {
				System.out.println("enter your move (Format: [start row] [start column] [end row] [end column]");
				String inputMove = input.nextLine();
				
				move = inputMove.toCharArray();
				
				if(move.length >= 7) {
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
								running = false;
							}
							else {
								System.out.println("Black has won!");
								running = false;
							}
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
