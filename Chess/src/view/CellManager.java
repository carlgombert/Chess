package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import controller.Game;
import model.Piece;
import util.ImageUtil;

public class CellManager {
	
	private Piece[][] board;
	private int cellSize = 100;
	
	private int[] selectedPoint = new int[2];
	
	private boolean selectedPiece = false;
	
	private boolean whiteTurn = true;
	
	private boolean gameOver = false;
	
	private Rectangle RESTART_BUTTON = new Rectangle(700, 820, 80, 30);
	
	private Font hudFont = new Font("Lucida Grande", Font.PLAIN, 12);
	private Font popupFont = new Font("Lucida Grande", Font.BOLD, 22);
	
	public CellManager(Piece[][] board) {
		this.board = board;
	}
	
	public void render(Graphics g) {
		boolean lightSquare = true;
		
		for(int y = 0; y < 8; y++) {
			lightSquare = !lightSquare;
			for(int x = 0; x < 8; x++) {
				
				if(lightSquare) {
					g.setColor(Color.LIGHT_GRAY);
				}else {
					g.setColor(Color.DARK_GRAY);
				}
				lightSquare = !lightSquare;
				
				
				g.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
				
				if(selectedPiece && x == selectedPoint[0] && y == selectedPoint[1]) {
					g.setColor(Color.RED);					
					g.drawRect(x*cellSize, y*cellSize, cellSize, cellSize);
				}
				if(board[y][x] != null) {
					g.drawImage(ImageUtil.getImage(board[y][x].getCharacter()), x*cellSize, y*cellSize, cellSize, cellSize, null);
				}
			}
		}
		
		g.setColor(Color.DARK_GRAY.darker());
		
		g.fill3DRect(0, 800, 800, 100, true);
		
		g.setColor(Color.RED.darker().darker());
		
		g.fillRect(RESTART_BUTTON.x, RESTART_BUTTON.y, RESTART_BUTTON.width, RESTART_BUTTON.height);
		
		g.setFont(hudFont);
		g.setColor(Color.WHITE);
		g.drawString("RESTART", RESTART_BUTTON.x+12, RESTART_BUTTON.y+20);
		
		if(gameOver) {
			g.setFont(popupFont);
			if(whiteTurn) {
				g.drawString("Black Wins!", 200, 840);
			}
			else {
				g.drawString("White Wins!", 200, 840);
			}
		}
	}
	
	public void clickedBox(int x, int y) {
		if(RESTART_BUTTON.contains(x, y)) {
			gameOver = false;
			selectedPiece = false;
			whiteTurn = true;
			
			Game.newGame();
		}
		else if(!gameOver && x < 800 && y < 800) {
			if(selectedPiece) {
				x = x - (x % cellSize);
				x = x / cellSize;
				
				y = y - (y % cellSize);
				y = y / cellSize;
				
				if(Game.board.movePiece(selectedPoint[1], selectedPoint[0], y, x, !whiteTurn)) {
					System.out.println("bug");
					whiteTurn = !whiteTurn;
					selectedPiece = false;
					if(Game.board.isGameOver()) {
						gameOver = true;
					}
				} 
				else if(x == selectedPoint[0] && y == selectedPoint[1]) {
					selectedPiece = false;
				}
				else if(board[y][x] != null && board[y][x].getIsBlack() != whiteTurn) {
					selectedPoint[0] = x;
					selectedPoint[1] = y;
				}
			}
			else {
				x = x - (x % cellSize);
				x = x / cellSize;
				
				y = y - (y % cellSize);
				y = y / cellSize;
				
				if(board[y][x] != null && board[y][x].getIsBlack() != whiteTurn) {
					selectedPiece = true;
					selectedPoint[0] = x;
					selectedPoint[1] = y;
				}
			}
		}
	}
}
