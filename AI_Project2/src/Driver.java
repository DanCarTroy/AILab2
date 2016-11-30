import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		
		OthelloBoard b1 = new OthelloBoard();
		System.out.println("Black's turn.");
		b1.PrintBoard();
		
		
		Scanner kb = new Scanner(System.in);
		
		while (!b1.GameIsOver()){
			
			if(b1.CheckTiles()){ //check if all tiles placed are of the same color. A condition to end the game.
				b1.SetBlackHasMoves(false);
				b1.SetWhiteHasMoves(false);
				break;
			}
			if(b1.CheckBoardFull()){ //check if the board is full. Condition to end the game.
				break;
			}
			
			if(b1.GetBlackHasMoves() == false && b1.GetWhiteHaseMoves() == false){ //Check if both players don't have available moves. Ends game.
				break;
			}
			
			if(b1.GetBlackHasMoves() == false || b1.GetWhiteHaseMoves() == false){ // If the current player doesn't have a move skip turn.
				if(b1.GetBlackHasMoves() == false){
					b1.SetBlackHasMoves(true);
					
				} 
				if(b1.GetWhiteHaseMoves() == false){
					b1.SetWhiteHasMoves(true);
				
				}
				continue;
			}
			
			b1.ShowPossibleMoves();
			
			
			if(b1.getIsBlackTurn())
			{
				Position best = Searcher.runInstance2(b1); // Add case there are not possible moves. empty position//ERROR HERE! Switched to white's turn inside here and this will always return position (0,0)
				System.out.println("\n========= A.I making a move! It played " + best + ". =========");
				b1.PlaceTile(best.getRow(), best.getCol());
				System.out.println("White's turn.");
			}
			else
			{
				System.out.print("Which position would you like to enter? ");
				int row = kb.nextInt();
		    	int col = kb.nextInt();
		    	b1.PlaceTile(col, row);
		    	System.out.println("Black's turn.");
			}
			
		}
	
		System.out.println("Game is over!");
		b1.CalculateScore();
		
	
		
		/*
		OthelloBoard b1 = new OthelloBoard();
		b1.PrintBoard();
		
		Scanner kb = new Scanner(System.in);
		
		while (!b1.GameIsOver()){
			
			if(b1.CheckTiles()){ //check if all tiles placed are the same.
				b1.SetBlackHasMoves(false);
				b1.SetWhiteHasMoves(false);
				break;
			}
			if(b1.CheckBoardFull()){ //check if the board is full
				break;
			}
			
			if(b1.GetBlackHasMoves() == false && b1.GetWhiteHaseMoves() == false){ //Check if both players dont have available moves
				break;
			}
			
			if(b1.GetBlackHasMoves() == false || b1.GetWhiteHaseMoves() == false){ //check if one of them have, continue.
				if(b1.GetBlackHasMoves() == false){
					b1.SetBlackHasMoves(true);
					
				} 
				if(b1.GetWhiteHaseMoves() == false){
					b1.SetWhiteHasMoves(true);
				
				}
				continue;
			}
			
			b1.ShowPossibleMoves();
			System.out.print("Which position would you like to enter? ");
	    	int row = kb.nextInt();
	    	int col = kb.nextInt();
	    
			b1.PlaceTile(col, row);
			
		}
	
		System.out.println("Game is over!");
		b1.CalculateScore();
		
		*/
		
	}

	
}
