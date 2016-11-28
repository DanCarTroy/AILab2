import java.util.ArrayList;

public class Searcher {
	
	private static ArrayList<Integer> scores; 

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
		ArrayList<Position> rootMoveList = firstInstance.getPossibleMoveList();
		
		
		for(Position p: rootMoveList)
		{
			// Step 2: Instantiate a new board with position p.
			BoardInstance childInstance = new BoardInstance();
			
			
		
			//Step3: Get the list of possible moves of the opposing player. 
			//childInstance.getPossibleMoveList();
			int score = childInstance.calculateInstanceScore(root, p);
			scores.add(score);
			
		}
		
		Integer tmp = 0; 
		Integer max = 0;
		Position p = null;
		for(int i = 0; i < rootMoveList.size(); i++)
		{
			tmp = scores.get(i);
			if(tmp > max)
			{
				max = tmp;
				p = new Position(rootMoveList.get(i));
			}
			
		}
		
		
		return p;
		
	}

}
