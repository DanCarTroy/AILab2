
public class Node {

	private OthelloBoard boardState;
	private int score;
	
	public Node(OthelloBoard board){
		boardState = board;
	}
	
	public OthelloBoard getBoardState(){
		return boardState;
	}
	
	public static int findOpponentNumMoves(OthelloBoard boa){
		
		int numOfMoves = boa.getNumPossibleMoves();
		boa.resetNumPossibleMove();
		
		return numOfMoves;
	}
}

//the node needs to know the following:

//the list of possible move
//the player playing
//and a copy of the board.