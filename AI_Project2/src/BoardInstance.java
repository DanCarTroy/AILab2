import java.util.ArrayList;
import java.util.Scanner;

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
		
		System.out.println("instanceboard Constructor used");
		
		instanceBoard.PlaceTile(p.getRow(), p.getCol());
		
		/*ArrayList<Position> opposingList*/
		possibleMoves = instanceBoard.generatePossibleMoves();
		possibleMoves.trimToSize();
		System.out.println("number Of possible moves for white in this instance: "+ possibleMoves.size());// List of possible moves that White can make
		int heuristic_score = (10 - possibleMoves.size());
		
		instanceScore = heuristic_score;
	
		
		return heuristic_score;
	}

	public ArrayList<Position> getPossibleMoveList()
	{
		return possibleMoves;
	}
	
	

}
