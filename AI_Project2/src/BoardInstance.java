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
		BoardInstance opponetInstance = new BoardInstance(instanceBoard);
		
		System.out.println("\n~~~ A.I. IMAGINARY TURN (BLACK) ~~~");
		instanceBoard.PlaceTile(p.getRow(), p.getCol());

		int blackScore = 0;
		int whiteScore = 0;
		
		ArrayList<Position> opposingMoveList = opponetInstance.getPossibleMoveList();
		
		for(Position x: opposingMoveList)
		{
			// Step 2: Instantiate a new board with position p.
			BoardInstance childInstance = new BoardInstance();  //Board created here always sets the turn to black at the beginning
			
			//Creating a new instance where we get the other player's next move.
			System.out.println("\n~~~ OPPONENT IMAGINARY TURN (WHITE) ~~~");
			instanceBoard.PlaceTile(x.getRow(), x.getCol());
			
			//Step3: Get the list of possible moves of the opposing player. 
			//childInstance.getPossibleMoveList();     /// the board of firstINstance should be here maybe!
			int score = childInstance.calculateInstanceScore2(root, p);
			
			scores.add(score);
			scores.trimToSize();
			
		}
		
		//Creating a new instance where we get the other player's next move.
		System.out.println("\n~~~ OPPONENT IMAGINARY TURN (WHITE) ~~~");
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
