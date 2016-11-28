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
		OthelloBoard b1 = new OthelloBoard(board);
		
		possibleMoves = b1.generatePossibleMoves();
		
	}
	
	public int calculateInstanceScore(OthelloBoard b, Position p) {
				
		OthelloBoard instanceBoard = new OthelloBoard(b);
		
		//instanceBoard.PrintBoard();
		
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
