import java.util.ArrayList;

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
		OthelloBoard instanceBoard = new OthelloBoard(b);
		System.out.println("\n~~~ A.I. IMAGINARY TURN ~~~");
		instanceBoard.PlaceTile(p.getRow(), p.getCol());

		int blackScore = 0;
		int whiteScore = 0;
		
		//Creating a new instance where we get the other player's next move.
		instanceBoard.PlaceTile(p.getRow(), p.getCol());
		
		for(int i = 0; i <b.getBoard().length; i++){
			for(int j = 0; j < b.getBoard().length; j++){
				if(b.getBoard()[i][j] == OthelloCell.BLACK)
					blackScore++;
			}
		}
		
		for(int i = 0; i <b.getBoard().length; i++){
			for(int j = 0; j < b.getBoard().length; j++){
				if(b.getBoard()[i][j] == OthelloCell.WHITE)
					whiteScore++;
			}
		}
		
		int heuristic_score = blackScore - whiteScore;
		
		return heuristic_score;

	}

	public ArrayList<Position> getPossibleMoveList()
	{
		return possibleMoves;
	}
	
	

}
