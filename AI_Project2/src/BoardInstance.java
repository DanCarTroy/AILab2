import java.util.ArrayList;
import java.util.Collections;

public class BoardInstance {
	
	
	private int instanceScore;
	
	private OthelloBoard instanceOfABoard;
	
	private ArrayList<Position> possibleMoves;
	
	
	public BoardInstance() {
		// TODO Auto-generated constructor stub
		
		instanceScore = 0;
		possibleMoves = new ArrayList<Position>();
		instanceOfABoard = new OthelloBoard();
		
	}
	
	public BoardInstance(OthelloBoard board)
	{	
		instanceOfABoard = new OthelloBoard(board);
		instanceScore = 0;
		possibleMoves = instanceOfABoard.generatePossibleMoves();  
		
	}
	
	public BoardInstance(OthelloBoard board, boolean switchTurn)
	{	
		instanceOfABoard = new OthelloBoard(board);
		instanceScore = 0;
		possibleMoves = instanceOfABoard.generatePossibleMoves();  
		
	}
	
	public int calculateInstanceScore(OthelloBoard b, Position p) {
				
		OthelloBoard instanceBoard = new OthelloBoard(b);
		
		//instanceBoard.PrintBoard();
		
		System.out.println("\n~~~ A.I. IMAGINARY TURN ~~~");
		
		instanceBoard.PlaceTile(p.getRow(), p.getCol());
		
		/*ArrayList<Position> opposingList*/
		possibleMoves = instanceBoard.generatePossibleMoves();
		possibleMoves.trimToSize();
		System.out.println("number Of possible moves for white in this instance: "+ possibleMoves.size());// List of possible moves that White can make
		int heuristic_score = (10 - possibleMoves.size());
		
		instanceScore = heuristic_score;
	
		
		return heuristic_score;
	}
	
	public int calculateInstanceScore2(OthelloBoard b, Position p){
		//Creating instance of our own player Instance of the game
		ArrayList<Integer> bunchOfScores = new ArrayList<Integer>();
		OthelloBoard instanceBoard = new OthelloBoard(b);
		OthelloBoard tempBoardBlack = new OthelloBoard(instanceBoard);
		
		System.out.println("\n~~~ A.I. IMAGINARY TURN (BLACK) ~~~ " + p);
		tempBoardBlack.PlaceTile(p.getRow(), p.getCol());
		BoardInstance opponetInstance = new BoardInstance(tempBoardBlack);
		

		
		ArrayList<Position> opposingMoveList = opponetInstance.getPossibleMoveList();
		
		for(Position x: opposingMoveList)
		{
			int blackScore = 0;
			int whiteScore = 0;
			
			// Step 2: for each position of white, place the tile
			OthelloBoard tempBoardwhite = new OthelloBoard(tempBoardBlack);
			//Creating a new instance where we get the other player's next move.
			System.out.println("\n~~~ OPPONENT IMAGINARY TURN (WHITE) ~~~ " + x);
			tempBoardwhite.PlaceTile(x.getRow(), x.getCol());
			
			//Step3: calculate score of each player then make the difference/
			// the board of firstINstance should be here maybe!
			for(int i = 0; i <tempBoardwhite.getBoard().length; i++){
				for(int j = 0; j < tempBoardwhite.getBoard().length; j++){
					if(tempBoardwhite.getBoard()[i][j] == OthelloCell.BLACK)
						blackScore++;
					if(tempBoardwhite.getBoard()[i][j] == OthelloCell.WHITE)
						whiteScore++;
				}
			}
			
			int posScore = blackScore - whiteScore;
			System.out.println("heuristic score: " + posScore);
			bunchOfScores.add(posScore);
			
		}

		if(bunchOfScores.size() == 0){
			return 0;
		}
		bunchOfScores.trimToSize();
		int maxheuristic = Collections.max(bunchOfScores);
		bunchOfScores.clear();
		
		return maxheuristic;

	}

	public ArrayList<Position> getPossibleMoveList()
	{
		return possibleMoves;
	}
	
	

}
