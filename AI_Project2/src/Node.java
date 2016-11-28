
public class Node {

	private OthelloBoard boardState;
	private int score;
	
	public Node(OthelloBoard board){
		boardState = board;
	}
	
	public OthelloBoard getBoardState(){
		return boardState;
	}
	
	
}
