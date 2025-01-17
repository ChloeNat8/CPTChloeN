// how to write to terminal
// do we have to follow cadawas style in methods
// note to self - make coloured pieces 
// notes
// make font bigger
// center?
// 

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class FinalCPTChloeN{
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720);
		
		// Load Images
		// Main Menu
		BufferedImage imgMainMenu = con.loadImage("mainmenu.jpg");
		
		con.drawImage(imgMainMenu, 0, 0);
		
		// Variables
		char chrChoice;
		boolean boolExit;
		
		// Initialize Variables
		boolExit = false;
		
		// Main Menu
		while(!boolExit){
			con.println();
			chrChoice = con.getChar();
			
			// Depending on the choice the user, do the associated task
			if(chrChoice == 'p'){
				playGame(con);
			}else if(chrChoice == 'l'){
			}else if(chrChoice == 'i'){
			}else if(chrChoice == 't'){
			}else if(chrChoice == 'q'){
				con.println("Exiting Game...");
				boolExit = true;
			}
		}	
		
		con.closeConsole();	
	}

// Methods

	// playGame
	public static void playGame(Console con) {
		// Variables
		// Board
		int intBoard[][];
		int intRows = 6;
        int intColumns = 7;
        int intEmpty = 0;
        // Players
        int intPlayer1 = 1;
        int intPlayer2 = 2;
        // Gameplay
        boolean boolIsPlayer1Turn = true;
        boolean boolGameWon = false;
        
        // Create the Board
        intBoard = new int[intRows][intColumns];
        InitializeBoard(intBoard, intRows, intColumns, intEmpty, con);
        
        // Main Game
        while (!boolGameWon && !IsBoardFull(intBoard, intRows, intColumns, intEmpty)) {
            con.setBackgroundColor(new Color(82, 67, 130));
            MakeBoard(intBoard, intRows, intColumns, con);
            if(boolIsPlayer1Turn == true){
				con.println("Player 1 (X), it's your turn");
			}else{
				con.println("Player 2 (O), it's your turn");
			}
            con.print("Enter the column (1-7) to drop your piece: ");
            int column = con.readInt() - 1;
            
            if (column < 0 || column >= intColumns) {
                con.println("Invalid column. Please try again.");
                continue;
            }
            if(boolIsPlayer1Turn = true){
				if(!DropPiece(intBoard, intColumns, intPlayer1, intRows, intEmpty)) {
					con.println("Column is full. Please try a different column.");
					continue;
				}
            }else{
				if(!DropPiece(intBoard, intColumns, intPlayer2, intRows, intEmpty)){
                con.println("Column is full. Please try a different column.");
                continue;
				}
			}
		}
	}
	
	// InitializeBoard
	// This method creates the 2d array and fills it all with 0
	public static void InitializeBoard(int[][] board, int rows, int columns, int empty, Console con){
        int x;
        int y;
        for (x = 0; x < rows; x++) {
            for (y = 0; y < columns; y++) {
                board[x][y] = empty;
            }
        }
    }
    
    // IsBoardFull
    public static boolean IsBoardFull(int[][] board, int rows, int columns, int empty){
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
        int x;
        int y;
        char chrPiece;
        for (y = 1; y <= columns; y++) {
            con.print(y + " ");
        }
        con.println();
        for (x = 0; x < rows; x++) {
            for (y = 0; y < columns; y++) {
				if(board[x][y] == 1){
					chrPiece = 'X';
				}else if(board[x][y] == 2){
					chrPiece = 'O';
				}else{
					chrPiece = '.';
				}
                con.print(chrPiece + " ");
            }
            con.println();
        }

    }
    
    // Drop Piece
    public static boolean DropPiece(int[][] board, int column, int piece, int rows, int empty) {
        int x;
        for (x = rows - 1; x >= 0; x--){
            if (board[x][column] == empty){
                board[x][column] = piece;
                return true;
            }
        }
        return false;
    }
}

// 
