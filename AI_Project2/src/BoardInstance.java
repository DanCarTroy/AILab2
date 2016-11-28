import java.util.ArrayList;
import java.util.Scanner;

public class BoardInstance {
	
	
	private int instanceScore;
	
	private ArrayList<Position> possibleMoves;
	

	public BoardInstance() {
		// TODO Auto-generated constructor stub
		
		instanceScore = 0;
		possibleMoves = new ArrayList<Position>();
		
	}
	
	public BoardInstance(OthelloBoard board)
	{
		possibleMoves = new ArrayList<Position>();
		
		OthelloBoard b1 = new OthelloBoard(board);
		b1.PrintBoard();
		
		Scanner kb = new Scanner(System.in);
		
		while (!b1.GameIsOver()){
			
			if(b1.CheckTiles()){ //check if all tiles placed are the same.
				b1.SetBlackHasMoves(false);
				b1.SetWhiteHasMoves(false);
				break;
			}
			if(b1.CheckBoardFull()){ //check if the board is full
				break;
			}
			
			if(b1.GetBlackHasMoves() == false && b1.GetWhiteHaseMoves() == false){ //Check if both players dont have available moves
				break;
			}
			
			if(b1.GetBlackHasMoves() == false || b1.GetWhiteHaseMoves() == false){ //check if one of them have, continue.
				if(b1.GetBlackHasMoves() == false){
					b1.SetBlackHasMoves(true);
					
				} 
				if(b1.GetWhiteHaseMoves() == false){
					b1.SetWhiteHasMoves(true);
				
				}
				continue;
			}
			
			possibleMoves = b1.generatePossibleMoves();
			System.out.print("Which position would you like to enter? ");
	    	//int row = kb.nextInt();
	    	//int col = kb.nextInt();
	    
			//b1.PlaceTile(colChosen, rowChosen);
			
		}
	
		System.out.println("Game is over!");
		b1.CalculateScore();
		
	}
	
	public int calculateInstanceScore(OthelloBoard b, Position p) {
				
		OthelloBoard instanceBoard = new OthelloBoard(b);
		
		instanceBoard.PrintBoard();
		
		System.out.println("instanceboard Constructor used");
		
		instanceBoard.PlaceTile(p.getCol(), p.getRow());
		
		/*ArrayList<Position> opposingList*/
		possibleMoves = instanceBoard.generatePossibleMoves(); // List of possible moves that White can make
		int heuristic_score = (10 - possibleMoves.size());
		
		instanceScore = heuristic_score;
	
		
		return heuristic_score;
	}

	public ArrayList<Position> getPossibleMoveList()
	{
		return possibleMoves;
	}
	
	

}
