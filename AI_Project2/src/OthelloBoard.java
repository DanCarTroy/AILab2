import java.util.ArrayList;

public class OthelloBoard {
	
	public OthelloCell[][] board;
	public static boolean isBlackTurn;
	public static boolean isWhiteTurn;
	
	private boolean blackHasMoves;
	private boolean whiteHasMoves;
	private ArrayList<Position> temp_list = new ArrayList<Position>();
	private ArrayList<Position> real_list = new ArrayList<Position>();
	
	public OthelloBoard(){	
		board = new OthelloCell[8][8];
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board.length; j++){
					board[i][j] = OthelloCell.EMPTY;
				}
			}
			
			board[3][3] = OthelloCell.WHITE;
			board[4][4] = OthelloCell.WHITE;
			board[3][4] = OthelloCell.BLACK;
			board[4][3] = OthelloCell.BLACK;
			
			//both player will have possible moves at the beginning of the game
			blackHasMoves = true;
			whiteHasMoves = true;
			
			SetTurnBlack();
	}
	
	//returns cell of a board position
	public OthelloCell GetCell(int row, int col){
		return board[row][col];
	}
	
	public void SetTurnWhite(){
		System.out.println("White's turn.");
		isBlackTurn = false;
		isWhiteTurn = true;
	}
	
	public void SetTurnBlack(){
		System.out.println("Black's turn." );
		isBlackTurn = true;
		isWhiteTurn = false;
	}
	
	public boolean GameIsOver(){
		boolean gameIsOver = false;
		
		//if there are no possible moves for both player.
		if(!whiteHasMoves && !blackHasMoves){
			return true;
		}
		/*	
		//if there is an empty space, the game is not done.
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == OthelloCell.EMPTY)
					return false;
			}
		}
		*/
		
		return gameIsOver;
	}
	
	public void CalculateScore(){
		int whiteScore = 0;
		int blackScore = 0;
		
		for(int i = 0; i <board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == OthelloCell.WHITE)
					whiteScore++;
				if(board[i][j] == OthelloCell.BLACK)
					blackScore++;
			}
		}
		
		System.out.println("Score: \nBlack: " + blackScore + "\nWhite: " + whiteScore);
	}
	
	public void PlaceTile(int row, int col){
		
		if(isBlackTurn){
			if(IsLegalMove(row, col)){
				board[row][col] = OthelloCell.BLACK;
				for(Position p : real_list){
					board[p.getRow()][p.getCol()] = OthelloCell.BLACK;
				}
				real_list.clear();
				SetTurnWhite();
			}
			else{
				System.out.println("Not a legal move!");
			}
		}
		else if(isWhiteTurn){
			if (IsLegalMove(row, col)){
				board[row][col] = OthelloCell.WHITE;
				for(Position p : real_list){
					board[p.getRow()][p.getCol()] = OthelloCell.WHITE;
				}
				real_list.clear();
				SetTurnBlack();

			}
			else{
				System.out.println("Not a legal move!");
			}
		}

		PrintBoard();
		
	}
	
	public boolean IsLegalMove(int row, int col){
		boolean isValidMove = false;
		//Cell is not empty, return false
		if(GetCell(row, col) != OthelloCell.EMPTY){
			return false;
		}
		else if(FindFlank(row, col)){
			isValidMove = true;
		}
	
		return isValidMove;
	}
	
	public void ShowPossibleMoves(){
		String str = "Possible Moves: ";
		String checkStr = "Possible Moves: ";
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(IsLegalMove(i,j)){
					str += "(" + j + ". " + i + ") ";
				}
			}
		}
			
		if(str.equals(checkStr)){
			System.out.println("No possible move!");
			if(isBlackTurn){
				System.out.println("HIIII");
				blackHasMoves = false;
				SetTurnWhite();
			}
			else if(isWhiteTurn){
				whiteHasMoves = false;
				SetTurnBlack();
			}
			checkPlayerHasMoves();
		}else{
			System.out.println(str);
		}
		
		//just to make sure:
		temp_list.clear();
		real_list.clear();
	}
	
	public boolean FindFlank(int row, int col){
		boolean foundFlank = false;
		boolean foundOpposite = false;
		int rowCheck = row;
		int colCheck = col;
		
		if(isBlackTurn){
			//check to the left
			while(colCheck > 0){
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			colCheck = col;
			foundOpposite = false;
			
			//check up direction
			while(rowCheck > 0){
				rowCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			foundOpposite = false;
		
			//check to the right
			while(colCheck < 7){
				colCheck += 1;
		
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			colCheck = col;
			foundOpposite = false;
			
			//check down direction
			while(rowCheck < 7){
				rowCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further down
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			foundOpposite = false;
			
			//check diagonal up-left
			while(rowCheck > 0 && colCheck > 0){
				rowCheck -= 1;
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal up-right
			while(rowCheck > 0 && colCheck < 7){
				rowCheck -= 1;
				colCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal down-right
			while(rowCheck < 7 && colCheck < 7){
				rowCheck += 1;
				colCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal down-left
			while(rowCheck < 7 && colCheck > 0){
				rowCheck += 1;
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.WHITE){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.BLACK){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
		
		}
		
		else if(isWhiteTurn){
			//check to the left
			while(colCheck > 0){
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}
			}
			//reset values after looking at left direction
			temp_list.clear();
			colCheck = col;
			foundOpposite = false;
			
			//check up direction
			while(rowCheck > 0){
				rowCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}
			}
			//reset values after looking
			temp_list.clear();
			rowCheck = row;
			foundOpposite = false;
			
			//check to the right
			while(colCheck < 7){
				colCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}
			}
			//reset values after looking at left direction
			temp_list.clear();
			colCheck = col;
			foundOpposite = false;
			
			//check down direction
			while(rowCheck < 7){
				rowCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}
			}
			//reset values after looking
			temp_list.clear();
			rowCheck = row;
			foundOpposite = false;
			
			//check diagonal up-left
			while(rowCheck > 0 && colCheck > 0){
				rowCheck -= 1;
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal up-right
			while(rowCheck > 0 && colCheck < 7){
				rowCheck -= 1;
				colCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal down-right
			while(rowCheck < 7 && colCheck < 7){
				rowCheck += 1;
				colCheck += 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
			
			//check diagonal down-left
			while(rowCheck < 7 && colCheck > 0){
				rowCheck += 1;
				colCheck -= 1;
				
				if(board[rowCheck][colCheck] == OthelloCell.EMPTY){
					//stop looking further left
					break;
				}
				if(!foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//stop looking further
					break;
				}
				if(board[rowCheck][colCheck] == OthelloCell.BLACK){
					//opposite is found, so keep going and add into temp list
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && board[rowCheck][colCheck] == OthelloCell.WHITE){
					//flank found, stop looking further and copy the temp list into real list.
					foundFlank = true;
					for(int i = 0; i < temp_list.size(); i++){
						Position pos = new Position(temp_list.get(i).getRow(), temp_list.get(i).getCol());
						real_list.add(pos);
					}
					break;
				}				
			}
			//reset values after looking at left direction
			temp_list.clear();
			rowCheck = row;
			colCheck = col;
			foundOpposite = false;
		}
		
		return foundFlank;
	}

	public void FlipTiles(Position pos){
		Position p = new Position(pos.getRow(), pos.getCol());
		if(board[p.getRow()][p.getCol()] == OthelloCell.BLACK){
			board[p.getRow()][p.getCol()] = OthelloCell.WHITE;
		}
		if(board[p.getRow()][p.getCol()] == OthelloCell.WHITE){
			board[p.getRow()][p.getCol()] = OthelloCell.BLACK;
		}
	}
		
	public void PrintBoard(){
		int numY = 0;
		System.out.println("------CURRENT BOARD------");
		System.out.println("    0  1  2  3  4  5  6  7");
		for(int i = 0; i < board.length; i++){
			System.out.print(numY++ + "   ");
			for(int j = 0; j < board.length; j++){
				char t = 0;
				if(board[i][j] == OthelloCell.EMPTY)
					t = 'O';
				if(board[i][j] == OthelloCell.WHITE)
					t = 'W';
				if(board[i][j] == OthelloCell.BLACK)
					t = 'B';
				System.out.print(t + "  ");
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	
	public void checkPlayerHasMoves(){
		if(!blackHasMoves && !whiteHasMoves){
			GameIsOver();
		}
	}
}
