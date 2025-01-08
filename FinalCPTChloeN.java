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

	// playGame
	public static void playGame(Console con) {
		// Variables
		// Board
		int intBoard[][];
		int intRows = 6;
        int intColumns = 5;
        int intEmpty = 0;
        // Players
        int intPlayer1 = 1;
        int intPlayer2 = 2;
        // Gameplay
        boolean boolIsPlayer1Turn = true;
        boolean boolGameWon = false;
        
        // Create the Board
        intBoard = new int[intRows][intColumns];
        InitializeBoard(intBoard, intRows, intColumns, intEmpty);
        
        // Main Game
        while (!gameWon && !IsBoardFull(intBoard, intRows, intColumns, intEmpty)) {
            MakeBoard(intBoard, intRows, intColumns, con);
            con.println("Player " + (isPlayer1Turn ? "1 (X)" : "2 (O)") + ", it's your turn.");
            con.print("Enter the column (1-7) to drop your piece: ");
            int column = con.readInt() - 1;
        
	}
	
	// InitializeBoard
	public static void InitializeBoard(int[][] board, int rows, int columns, int empty) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                board[x][y] = empty;
            }
        }
    }
    
    // IsBoardFull
    public static boolean IsBoardFull(int[][] board, int rows, int columns, int empty) {
        for (int y = 0; y < columns; y++) {
            if (board[0][y] == empty) {
                return false;
            }
        }
        return true;
	}
	
	// MakeBoard
	public static void MakeBoard(int[][] board, int rows, int columns, Console con) {
        con.println();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                char displayChar = (board[x][y] == 1) ? 'X' : (board[i][j] == 2) ? 'O' : '.';
                con.print(displayChar + " ");
            }
            con.println();
        }
        for (int j = 1; j <= columns; j++) {
            con.print(j + " ");
        }
        con.println();
    }
}
// 
