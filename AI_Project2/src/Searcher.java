import java.util.ArrayList;
import java.util.Collections;

public class Searcher {
	
	private static ArrayList<Integer> scores = new ArrayList<Integer>(); 

	public Searcher() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Position runInstance(OthelloBoard  root)
	{
		
		BoardInstance firstInstance = new BoardInstance(root);
		
		// Step 1: Get possible move list for initial player.
		ArrayList<Position> rootMoveList = firstInstance.getPossibleMoveList();  //Shallow copy confirmed in debugger. 
		rootMoveList.trimToSize();
		
		for(Position p: rootMoveList)
		{
			// Step 2: Instantiate a new board with position p.
			BoardInstance childInstance = new BoardInstance();  //Board created here always sets the turn to black at the beginning
			
			//Step3: Get the list of possible moves of the opposing player. 
			//childInstance.getPossibleMoveList();
			int score = childInstance.calculateInstanceScore(root, p);
			
			scores.add(score);
			scores.trimToSize();
			
			
		}
		
		Integer tmp = 0; 
		Integer max = 0; //Collections.max(scores);
		Position p = new Position(0, 0);  //Gotta change this. Update: maybe not. 
		
		System.out.println(rootMoveList.size());
		for(int i = 0; i < rootMoveList.size(); i++)
		{
			System.out.println("HERE!");
			tmp = scores.get(i);
			if(tmp > max)
			{
				max = tmp;
				p = new Position(rootMoveList.get(i));
				
			}
			
		}
		
		scores.clear();
		
		return p;
		
	}

}
