package main;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
    	board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
    	board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
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

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
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

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
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

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
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

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int xDistance = Math.abs(startRow - endRow);
        int yDistance = Math.abs(startCol - endCol);
        
        if(xDistance < 2 && yDistance < 2) {
	        return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
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

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
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

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
    	int xDistance = Math.abs(startRow - endRow);
        int yDistance = Math.abs(startCol - endCol);
        
    	if(xDistance == yDistance) {
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
