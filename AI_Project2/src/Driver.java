import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		OthelloBoard b1 = new OthelloBoard();
		b1.PrintBoard();
		b1.ShowPossibleMoves();

		Scanner kb = new Scanner(System.in);
		while (!b1.GameIsOver()){
			System.out.print("Which position would you like to enter? ");
	    	int row = kb.nextInt();
	    	int col = kb.nextInt();
	  
			b1.PlaceTile(col, row);
			b1.ShowPossibleMoves();
		}
		
	}

	//HELLO DANIEL! THIS IS MY PUSH
	
}
