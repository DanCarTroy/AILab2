import java.util.ArrayList;
import java.util.Random;

public class Searcher {
	
	private static ArrayList<Integer> scores = new ArrayList<Integer>(); 
	private static ArrayList<Integer> differenceInScores = new ArrayList<Integer>();

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
		
		Position p = heuristicOppositionCount(rootMoveList);
		
		scores.clear();
		
		return p;
		
		
	}
	
	private static Position heuristicOppositionCount(ArrayList<Position> scoreList){
	
		
		Integer tmp = 0; 
		Integer max = 0; //Collections.max(scores);
		Position p = new Position(0, 0);  //Gotta change this. Update: maybe not. 
		
		for(int i = 0; i < scoreList.size(); i++)
		{
			tmp = scores.get(i);
			if(tmp > max)
			{
				max = tmp;
				p = new Position(scoreList.get(i));
				
			}
			
		}
		
		return p;
	
	}
	
	public static Position runInstance2(OthelloBoard  root){
		
		BoardInstance firstInstance = new BoardInstance(root);
		ArrayList<Position> rootMoveList = firstInstance.getPossibleMoveList();
		rootMoveList.trimToSize();
		
		for(Position p: rootMoveList)
		{
			// Step 2: Instantiate a new board with position p.
			BoardInstance childInstance = new BoardInstance();  //Board created here always sets the turn to black at the beginning
			
			//Step3: Get the list of possible moves of the opposing player. 
			//childInstance.getPossibleMoveList();     /// the board of firstINstance should be here maybe!
			int score = childInstance.calculateInstanceScore2(root, p);
			
			differenceInScores.add(score);
			
		}

		differenceInScores.trimToSize();
		
		Position p = heuristicDifferenceScore(rootMoveList);
		
		//differenceInScores.clear();
		
		return p;
		
	}
	
	private static Position heuristicDifferenceScore(ArrayList<Position> rootMoveList){
		//The reason why we have to set max to -9999, is because the first move of white will ALWAYS 
		//have equal number of tiles as black, therefore we have to set max value to super low to no get 0
		//heuristic score.
	
		
		Integer tmp = 0; 
		Integer max = 0; //Collections.max(scores);
		Position p = new Position(0, 0);  //Gotta change this. Update: maybe not. 
		
		for(int i = 0; i < rootMoveList.size(); i++)
		{
			tmp = differenceInScores.get(i);
			if(tmp > max)
			{
				max = tmp;
				p = new Position(rootMoveList.get(i));
				
			}
			else if(tmp == max){
				System.out.println("equals num of tiles");
				Random rand = new Random();

				int  n = rand.nextInt(rootMoveList.size()) + 0;
				p = new Position(rootMoveList.get(n));

			}
			else{
				Random rand = new Random();

				int  n = rand.nextInt(rootMoveList.size()) + 0;
				p = new Position(rootMoveList.get(n));
			}
			
		}
		
		return p;
	
	}

}
