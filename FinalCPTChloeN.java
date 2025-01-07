import arc.*;
import java.awt.*;
import java.awt.image.*;


public class FinalCPTChloeN{
	public static void main(String[] args){
		Console con = new Console();
		
		int intChoice;	
		
		// Main Menu
		con.println("--- Connect 4 Main Menu ---");
		con.println("1. Play Game");
		con.println("2. Instructions");
		con.println("3. Leaderboard");
		con.println("4. Exit");
		con.println("Enter your choice: ");
		intChoice = con.readInt();
        
        if(intChoice == 1){
			playGame(con);
		}
		
	
	}

// Methods
	public static void playGame(Console con) {
		int intRows = 6;
        int intColumns = 7;
        int intEmpty = 0;
        int intPlayer1 = 1;
        int intPlayer2 = 2;
        int intBoard[][];
        intBoard = new int[intRows][intColumns];
        initializeBoard(intBoard, intRows, intColumns, intEmpty);
        
	}
	public static void initializeBoard(int[][] board, int rows, int columns, int empty) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = empty;
            }
        }
    }
	
}

// 
