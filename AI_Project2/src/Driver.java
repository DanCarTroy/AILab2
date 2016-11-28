import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		
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
