import java.util.ArrayList;

public class OthelloBoard {
	
	private OthelloCell[][] board;
	private boolean isBlackTurn;
	private boolean isWhiteTurn;
	
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
	
	public OthelloBoard(OthelloBoard original){
		
		board = new OthelloCell[8][8];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				board[i][j] = getCopyCell(original.GetCell(i, j));
			}
		}
		
			
			//both player will have possible moves at the beginning of the game
			blackHasMoves = original.GetBlackHasMoves();
			whiteHasMoves = original.GetWhiteHaseMoves();
			
			//TODO: MIGHT BE IN WRONG PLACE
			if(original.getIsBlackTurn())
			{                                        
				this.SetTurnBlack();  //This has to be black because we are making a copy of original
			}
			else // if White has the turn in the original board 
			{
				this.SetTurnWhite(); //This has to be white because we are making a copy of original
			}
				
			
	}
	
	public boolean CheckBoardFull(){
		boolean isFull = true;
		//if there is an empty space, the game is not done.
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == OthelloCell.EMPTY)
					return false;
			}
		}
		return isFull;
	}
	
	public void SetBlackHasMoves(boolean bool){
		blackHasMoves = bool;
	}
	
	public void SetWhiteHasMoves(boolean bool){
		whiteHasMoves = bool;
	}
	
	public boolean getIsBlackTurn()
	{
		return isBlackTurn;
	}
	
	public boolean getIsWhiteTurn()
	{
		return isWhiteTurn;
	}
	
	public boolean GetWhiteHaseMoves(){
		return whiteHasMoves;
	}
	
	public boolean GetBlackHasMoves(){
		return blackHasMoves;
	}
	
	public OthelloCell[][] getBoard(){
		return board;
	}
	
	//returns cell of a board position
	public OthelloCell GetCell(int row, int col){
		return board[row][col];
	}
	/*
	//returns cell of a board position
	public OthelloCell GetCellDeep(int row, int col){
		
		OthelloCell copy = board[row][col];
	
		
		OthelloCell[][] copy = new OthelloCell[8][8];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				copy[i][j] = board[]
			}
		}
		
			
		return copy;
	}
	*/
	public void SetTurnWhite(){
		System.out.println("White's turn." + board.hashCode());
		isBlackTurn = false;
		isWhiteTurn = true;
	}
	
	public void SetTurnBlack(){
		System.out.println("Black's turn." + board.hashCode());
		isBlackTurn = true;
		isWhiteTurn = false;
	}
	
	public boolean GameIsOver(){
		boolean gameIsOver = false;
		
		//if there are no possible moves for both player.
		if(!whiteHasMoves && !blackHasMoves){
			return true;
		}		
		
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
	
	public boolean CheckTiles(){
		boolean allSame = false;
		boolean hasWhite = false;
		boolean hasBlack = false;
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == OthelloCell.WHITE)
					hasWhite = true;
				if(board[i][j] == OthelloCell.BLACK)
					hasBlack = true;
				
			}
		}
		
		if(hasBlack == false || hasWhite == false){
			allSame = true;
		}
		

		
		return allSame;
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
	
	public ArrayList<Position> ShowPossibleMoves(){

		ArrayList<Position> possibleMoves = new ArrayList<Position>();
		
		String str = "Possible Moves: ";
		String checkStr = "Possible Moves: ";
		//TODO: make a list of possible moves for other AIs.
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(IsLegalMove(i,j)){
					str += "(" + j + ". " + i + ") ";
					possibleMoves.add(new Position(i, j));
				}
			}
		}
			
		if(str.equals(checkStr)){  //Checks if the Current player does not have possible moves
			System.out.println("No possible move!");
			if(isBlackTurn){
				blackHasMoves = false;
				SetTurnWhite();
				ShowPossibleMoves(); // Note: Gotta review this
			}
			else if(isWhiteTurn){
				whiteHasMoves = false;
				SetTurnBlack();
				ShowPossibleMoves();   // Note: GOtta review this
			}
			checkPlayerHasMoves();
		}else{
			System.out.println(str); //Current player has possible moves
		}
		
		//just to make sure:
		temp_list.clear();
		real_list.clear();
		return possibleMoves;
		
	}
	
	public ArrayList<Position> generatePossibleMoves(){

		ArrayList<Position> possibleMoves = new ArrayList<Position>();
		
		String str = "Possible Moves: ";
		String checkStr = "Possible Moves: ";
		//TODO: make a list of possible moves for other AIs.
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(IsLegalMove(i,j)){
					System.out.println("ADDED!");
					str += "(" + j + ". " + i + ") ";
					possibleMoves.add(new Position(i, j));
				}
			}
		}
			
		if(str.equals(checkStr)){  //Checks if the Current player does not have possible moves
			// If the A.I does not have possible moves it is going to set the board to the other player. 
			if(isBlackTurn){
				blackHasMoves = false;
				isWhiteTurn = true;
				isBlackTurn = false;
				//SetTurnWhite();
				//ShowPossibleMoves(); //This one would print the moves for the player that plays the next turn //Could be used for a level 2,3.. algo
			}
			else if(isWhiteTurn){
				whiteHasMoves = false;
				isWhiteTurn = false;
				isBlackTurn = true;
				//SetTurnBlack();
				//ShowPossibleMoves();
			}
			checkPlayerHasMoves();
		}else{
		  //Current player has possible moves
		}
		
		//just to make sure:
		temp_list.clear();
		real_list.clear();
		possibleMoves.trimToSize();
		return possibleMoves;
		
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
	
	public static OthelloCell getCopyCell(OthelloCell original){
		OthelloCell copy = null;
		
		if(original == OthelloCell.BLACK){
			copy =  OthelloCell.BLACK;
		}
		if(original == OthelloCell.WHITE){
			copy = OthelloCell.WHITE;
		}
		if(original == OthelloCell.EMPTY){
			copy = OthelloCell.EMPTY;
		}
		
		return copy;
	}
}
