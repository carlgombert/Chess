package main;

public class Board {

    // Instance variables
    private Piece[][] board;

	/**
	 * Constructor making the board variable
	 */
	public Board() {
    	board = new Piece[8][8];
    }

    // Accessor Methods

	/**
	 * returns a piece at given row and column
	 * 
	 * @param  row the row of the board to look in
	 * @param  col the column of the board to look in
	 * @return Piece at given row and column
	 */
	public Piece getPiece(int row, int col) {
        return board[row][col];
    }

	/**
	 * changes the piece at given row and column
	 * 
	 * @param  row  row on the board to which will be changed
	 * @param col column on the board to be changed
	 * @param piece the new piece to be set
	 */
	public void setPiece(int row, int col, Piece piece) {
    	board[row][col] = piece;
    }

    // Game functionality methods

	/**
	 * Moves a piece after verifying it is a legal move
	 * 
	 * @param  startRow
	 * @param  startCol
	 * @param  endRow
	 * @param  endCol
	 * @param  isBlack
	 * @return boolean if the piece is moved return true if it is un able to return false
	 */
	public boolean movePiece(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
    	
    	if(verifySourceAndDestination(startRow, startCol, endRow, endCol, isBlack)) {
    		if(board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
    			board[endRow][endCol] = board[startRow][startCol];
    			board[startRow][startCol] = null;
    			board[endRow][endCol].setPosition(endRow, endCol);
    			return true;
    		}
    	}
        return false;
    }

	/**
	 * determines if the game is won
	 * 
	 * @return true if game is won false if game is still valid
	 */
	public boolean isGameOver() {
    	int kingCount = 0;
        for(int i = 0; i < board.length; i++) {
        	for(int n = 0; n < board[i].length; n++) {
        		if(board[i][n] != null) {
	        		if(board[i][n].getCharacter() == '\u265a' || board[i][n].getCharacter() == '\u2654') {
	        			kingCount++;
	        		}
        		}
        	}
        }
        if(kingCount == 2) {
        	return false;
        }
        return true;
    }

    /** 
     * Constructs a String that represents the Board object's 2D array.
     * */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
	public void clear() {
    	for(int i = 0; i < board.length; i++) {
    		for(int n = 0; n < board[i].length; n++) {
    			board[i][n] = null;
    		}
    	}
    }

    // Movement helper functions

	/**
	 * Ensures that the start and destination are available to be moved and moved to.
	 * 
	 * @param startRow The starting row to check
	 * @param startCol The starting column to check
	 * @param endRow The end row to check
	 * @param endCol the end column to check
	 * @param isBlack piece color
	 * @return if the source and destination are available, return true, else return false
	 */
	public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        boolean startBounds = (startRow >= 0 && startRow <= 7) && (startCol >= 0 && startCol <= 7);
        boolean endBounds = (endRow >= 0 && endRow <= 7) && (endCol >= 0 && endCol <= 7);
        
        if(startBounds && endBounds) {
        	if(board[startRow][startCol] != null) {
        		if(board[startRow][startCol].getIsBlack() == isBlack) {
        			if(board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack) {
            			return true;
            		}
        		}
        	}
        }
        return false;
    }

    /**
     * Verifies if the adjacent squares are free from pieces
     * 
     * @param startRow The starting row to check
     * @param startCol The starting column to check
     * @param endRow The end row to check
     * @param endCol the end column to check
	 * @return Returns true if available else returns false
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int xDistance = Math.abs(startRow - endRow);
        int yDistance = Math.abs(startCol - endCol);
        
        if(xDistance < 2 && yDistance < 2) {
	        return true;
        }
        return false;
    }

	/**
	 * Verifies that horizontal spaces are free
	 * 
	 * @param startRow The starting row to check
	 * @param startCol The starting column to check
	 * @param endRow The end row to check
	 * @param endCol the end column to check
	 * @return returns true if they are free returns false if they are not
	 */
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
    	if(startRow - endRow == 0) {
    		if(startCol > endCol) {
    			for(int i = endCol+1; i < startCol; i++) {
    				if(board[startRow][i] != null) {
    					return false;
    				}
    			}
    			return true;
    		}
    		else if(startCol < endCol) {
    			for(int i = startCol+1; i < endCol; i++) {
    				if(board[startRow][i] != null) {
    					return false;
    				}
    			}
    			return true;
    		}
    		else {
				return true;
			}
        }
        return false;
    }

	/**
	 * Ensures vertical squares are available for movement
	 * 
	 * @param startRow The starting row to check
	 * @param startCol The starting column to check
	 * @param endRow The end row to check
	 * @param endCol the end column to check
	 * @return Returns true if vertical squares are available and false if not
	 */
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
    	if(startCol - endCol == 0) {
    		if(startRow > endRow) {
    			for(int i = endRow+1; i < startRow; i++) {
    				if(board[i][startCol] != null) {
    					return false;
    				}
    			}
    			return true;
    		}
    		else if(startRow < endRow) {
    			for(int i = startRow+1; i < endRow; i++) {
    				if(board[i][startCol] != null) {
    					return false;
    				}
    			}
    			return true;
    		}
    		else {
				return true;
			}
        }
        return false;
    }

	/**
	 * Verifies if the diagnols are available to move through
	 * 
	 * @param startRow The starting row to check
	 * @param startCol The starting column to check
	 * @param endRow The end row to check
	 * @param endCol the end column to check
	 * @return Returns true if available to move. Returns false if not
	 */
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
    	int xDistance = Math.abs(startRow - endRow);
        int yDistance = Math.abs(startCol - endCol);
        
    	if(xDistance == yDistance) { // make sure move is in an even diagonal line
    		
    		// test each of five cases, down left, down right, up left, up right, and no move
    		// for each case move in a diagonal line down the path and make sure each
    		// space along the way is null
    		
    		if(startRow > endRow) {
    			if(startCol > endCol) {
    				for(int i = 1; i < startRow-endRow; i++) {
    					if(board[startRow-i][startCol-i] != null) {
        					return false;
        				}
    				}
    				return true;
    			}
    			else if(startCol < endCol) {
    				for(int i = 1; i < startRow-endRow; i++) {
    					if(board[startRow-i][startCol+i] != null) {
        					return false;
        				}
    				}
    				return true;
    			}
    			else {
    				return true;
    			}
    		}
    		else if(startRow < endRow) {
    			if(startCol > endCol) {
    				for(int i = 1; i < endRow-startRow; i++) {
    					if(board[startRow+i][startCol-i] != null) {
        					return false;
        				}
    				}
    				return true;
    			}
    			else if(startCol < endCol) {
    				for(int i = 1; i < endRow-startRow; i++) {
    					if(board[startRow+i][startCol+i] != null) {
        					return false;
        				}
    				}
    				return true;
    			}
    			else {
    				return true;
    			}
    		}
    		else if(startCol == endCol) {
    			return true;
    		}
        }
        return false;
    }
}
