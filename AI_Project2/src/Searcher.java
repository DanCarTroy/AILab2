import java.util.ArrayList;
import java.util.Random;

public class Searcher {
	
	private static ArrayList<Integer> scores = new ArrayList<Integer>(); 
	private static ArrayList<Integer> differenceInScores = new ArrayList<Integer>();
	
	//For the FIRST Heuristic, we run instance of the board (make nodes)
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
			
			//Step3: Get the list of possible moves of the opposing player, And calculate The score of the child state
			int score = childInstance.calculateInstanceScore(root, p);
			
			//Step4: add scores into an array
			scores.add(score);
			scores.trimToSize();
			
			
		}
		//Step5: ***
		Position p = heuristicOppositionCount(rootMoveList);
		
		scores.clear();
		
		return p;
		
		
	}
	//Step5: Take scores of the array, and find the maximum of it, then make return the position that is best.
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
	
	//For the SECOND Heuristic, we run instance of the board (make nodes)
	public static Position runInstance2(OthelloBoard  root){
		
		//Step1: make a copy of the root board (state).
		BoardInstance firstInstance = new BoardInstance(root);
		ArrayList<Position> rootMoveList = firstInstance.getPossibleMoveList();
		rootMoveList.trimToSize();
		
		// Step 2: For each possible move of black, calculate the score
		for(Position p: rootMoveList)
		{
			
			BoardInstance childInstance = new BoardInstance();  //Board created here always sets the turn to black at the beginning
			
			//Step3: Get the list of possible moves of the opposing player. 
			int score = childInstance.calculateInstanceScore2(root, p);
			
			differenceInScores.add(score);
			
		}

		differenceInScores.trimToSize();
		
		//compare the array of score
		Position p = heuristicDifferenceScore(rootMoveList);
		
		//differenceInScores.clear();
		
		return p;
		
	}
	
	private static Position heuristicDifferenceScore(ArrayList<Position> rootMoveList){
		//The first move of white will ALWAYS 
		//have equal number of tiles as black
	
		Integer tmp = 0; 
		Integer max = 0; //Collections.max(scores);
		Position p = new Position(0, 0);  //Gotta change this. Update: maybe not. 
		
		//Choose best position based on their score from the index.
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
