import java.util.ArrayList;

public class OthelloBoard {
	
	public OthelloCell[][] board;
	public static boolean isBlackTurn;
	public static boolean isWhiteTurn;
	
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
			SetTurnBlack();
	}
	
	//returns cell of a board position
	public OthelloCell GetCell(int row, int col){
		return board[row][col];
	}
	
	public void SetTurnWhite(){
		isBlackTurn = false;
		isWhiteTurn = true;
	}
	
	public void SetTurnBlack(){
		isBlackTurn = true;
		isWhiteTurn = false;
	}
	
	//return true if the game is over (all spots are occupied)
	public boolean GameIsOver(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == OthelloCell.EMPTY)
					return false;
			}
		}
		return true;
	}
	
	public void PlaceTile(int row, int col){
	
		if(IsLegalMove(row, col) && isBlackTurn){
			board[row][col] = OthelloCell.BLACK;
			SetTurnWhite();
			
		}else if (IsLegalMove(row, col) && isWhiteTurn){
			board[row][col] = OthelloCell.WHITE;
			SetTurnBlack();
		}else{
			System.out.println("Not a legal move!");
		}
		PrintBoard();
		
	}
	
	public boolean IsLegalMove(int row, int col){
		boolean isValidMove = false;
		//Cell is not empty, return false
		if(GetCell(row, col) != OthelloCell.EMPTY){
			return false;
		}
		
		//doesn't have adjacent tile of the right color, return false
		if(!HasAdjacent(row, col)){
			return false;
		}
		
		//move is valid at this point.
		//if (HasAdjacent(row, col)){
			if(FindFlank(row, col)){
				isValidMove = true;
			}
	//	}
		
		return isValidMove;
	}
	
	public void ShowPossibleMoves(){
		String str = "Possible Moves: ";
		
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board.length; j++){
					if(IsLegalMove(i,j)){
						str += "(" + j + ". " + i + ") ";
					}
				}
			}
		
		System.out.println(str);
	}
	
	public boolean FindFlank(int row, int col){
		boolean foundFlank = false;
		boolean foundOpposite = false;
		int rowCheck = row;
		int colCheck = col;
		
		//check to the left (in the same row) for same color tile
		while (colCheck != 0){
			colCheck -= 1;
			
			//BLACK's turn.
			if(isBlackTurn){
				if(GetCell(rowCheck, colCheck) == OthelloCell.WHITE){
					foundOpposite = true;
					//TODO: add to temp list all values of positions that have opposite tiles.
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && GetCell(rowCheck, colCheck) == OthelloCell.BLACK){
					//TODO: if foundFlank, copy temp list to a real list.
					//TODO: at the end of checking if it is a legal move, for all position in the list, flip tiles.
					return true;
				}
			}
			
			//WHITE's turn.
			if(isWhiteTurn){
				if(GetCell(rowCheck, colCheck) == OthelloCell.BLACK){
					foundOpposite = true;
					Position pos = new Position(rowCheck, colCheck);
					temp_list.add(pos);
					continue;
				}
				if(foundOpposite && GetCell(rowCheck, colCheck) == OthelloCell.WHITE){
					return true;
				}
			}
			
		}
		colCheck = col;
	
		return foundFlank;
	}
	
	@SuppressWarnings("finally")
	public boolean HasAdjacent(int row, int col){
		boolean hasAdj = false;
		try{
			if(isBlackTurn && ((GetCell(row-1, col-1) == OthelloCell.WHITE || (GetCell(row, col-1) == OthelloCell.WHITE) || 
					(GetCell(row+1, col-1) == OthelloCell.WHITE)||
					(GetCell(row-1, col) == OthelloCell.WHITE) || 
					(GetCell(row+1, col) == OthelloCell.WHITE) || 
					(GetCell(row-1, col+1) == OthelloCell.WHITE) || 
					(GetCell(row, col+1) == OthelloCell.WHITE) ||
					(GetCell(row+1, col+1) == OthelloCell.WHITE)))){
				hasAdj = true;
		}
			else if(isWhiteTurn && ((GetCell(row-1, col-1) == OthelloCell.BLACK || (GetCell(row, col-1) == OthelloCell.BLACK) || 
					(GetCell(row+1, col-1) == OthelloCell.BLACK)||
					(GetCell(row-1, col) == OthelloCell.BLACK) || 
					(GetCell(row+1, col) == OthelloCell.BLACK) || 
					(GetCell(row-1, col+1) == OthelloCell.BLACK)|| 
					(GetCell(row, col+1) == OthelloCell.BLACK) ||
					(GetCell(row+1, col+1) == OthelloCell.BLACK)))){
				hasAdj=true;
			}
		} catch(Exception e){
			
		}finally{
			return hasAdj;
		}
		
	}
	
	public void FlipTiles(int row, int col){
		if(board[row][col] == OthelloCell.BLACK){
			board[row][col] = OthelloCell.WHITE;
		}
		if(board[row][col] == OthelloCell.WHITE){
			board[row][col] = OthelloCell.BLACK;
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
		if(isBlackTurn){
			System.out.println("Black's turn." );
		} else{
			System.out.println("White's turn.");
		}
	}
	
}
