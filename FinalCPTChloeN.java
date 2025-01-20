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
		
		// Font
		
		
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
        String strPlayer1Name;
        String strPlayer2Name;
        // Gameplay
        boolean boolIsPlayer1Turn = true;
        boolean boolGameWon = false;
        int intPlacement;
        
        // Change Background
        con.clear();
        con.setBackgroundColor(new Color(82, 67, 130));
        
        // Get Player's Names
        con.println("Enter Player 1's name (X): ");
        strPlayer1Name = con.readLine();
        con.println("Enter Player 2's name (O): ");
        strPlayer2Name = con.readLine();
        
        // Create the Board
        intBoard = new int[intRows][intColumns];
        InitializeBoard(intBoard, intRows, intColumns, intEmpty, con);
        
        // Main Game
        while (!boolGameWon && !IsBoardFull(intBoard, intRows, intColumns, intEmpty)) {
            
            // Print Game
            con.clear();
            PrintHeader(strPlayer1Name, strPlayer2Name, con);
            MakeBoard(intBoard, intRows, intColumns, con);
            
            // Display Turn
            PrintTurn(boolIsPlayer1Turn, strPlayer1Name, strPlayer2Name, con);
			
			// Get Column Input
            
            con.print("Enter the column (1-7) to drop your piece: ");
            intPlacement = con.readInt() - 1;
            
            while(intPlacement < 0 || intPlacement >= intColumns){
				con.println("Invalid column. Please try again.");
				con.sleep(2000);
				con.clear();
				PrintHeader(strPlayer1Name, strPlayer2Name, con);
				MakeBoard(intBoard, intRows, intColumns, con);
				PrintTurn(boolIsPlayer1Turn, strPlayer1Name, strPlayer2Name, con);
				con.print("Enter the column (1-7) to drop your piece: ");
				intPlacement = con.readInt() - 1;
            }
            
            // Drop Piece
            if(boolIsPlayer1Turn){
				if(!DropPiece(intBoard, intPlacement, intPlayer1, intRows, intEmpty)) {
					con.println("Column is full. Please try a different column.");
				}
            }else{
				if(!DropPiece(intBoard, intPlacement, intPlayer2, intRows, intEmpty)){
					con.println("Column is full. Please try a different column.");
				}
			}
			
			// Check for Win
			if(boolIsPlayer1Turn){
				boolGameWon = CheckWin(intBoard, intPlayer1, intRows, intColumns);
			}else{
				boolGameWon = CheckWin(intBoard, intPlayer2, intRows, intColumns);
			}

			// Handle Win or Switch Turns
			if(boolGameWon){
				con.clear();
				PrintHeader(strPlayer1Name, strPlayer2Name, con);
                MakeBoard(intBoard, intRows, intColumns, con);
                if(boolIsPlayer1Turn){
					con.println(strPlayer1Name + " (X) wins! Congratulations!");
				}else{
					con.println(strPlayer2Name + " (O) wins! Congratulations!");
				}
			}else{            
				boolIsPlayer1Turn = !boolIsPlayer1Turn;
			}
       		
       		// If Draw
       		if(!boolGameWon){
				con.clear();
				PrintHeader(strPlayer1Name, strPlayer2Name, con);
				MakeBoard(intBoard, intRows, intColumns, con);
				con.println("It's a draw! No one wins.");
			}
			
			// Ask User to Replay
			
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
	
	// MakeHeader
	public static void PrintHeader(String strPlayer1, String strPlayer2, Console con) {
        con.println("Connect 4");
        con.println("---------------------------------");
        con.println(strPlayer1 + " (X)");
        con.println();
        con.println(strPlayer2 + " (O)");
        con.println("---------------------------------");
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
    
    // PrintTurn
    public static void PrintTurn(boolean IsPlayer1Turn, String Player1Name, String Player2Name, Console con){
		if(IsPlayer1Turn){
			con.println(Player1Name + " (X), it's your turn");
		}else{
			con.println(Player2Name + " (O), it's your turn");
		}
	}
    
    // DropPiece
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
    
    // CheckWin
    public static boolean CheckWin(int[][] board, int piece, int rows, int columns) {
     int x;
     int y;
     // Check horizontal
        for (x = 0; x < rows; x++) {
            for (y = 0; y <= columns - 4; y++) {
                if(board[x][y] == piece && board[x][y + 1] == piece && board[x][y + 2] == piece && board[x][y + 3] == piece){
                    return true;
                }
            }
        }

        // Check vertical
        for (x = 0; x <= rows - 4; x++) {
            for (y = 0; y < columns; y++) {
                if(board[x][y] == piece && board[x + 1][y] == piece && board[x + 2][y] == piece && board[x + 3][y] == piece){
                    return true;
                }
            }
        }

        // Check diagonal 
        for (x = 0; x <= rows - 4; x++) {
            for (y = 0; y <= columns - 4; y++) {
                if(board[x][y] == piece && board[x + 1][y + 1] == piece && board[x + 2][y + 2] == piece && board[x + 3][y + 3] == piece){
                    return true;
                }
            }
        }

        // Check diagonal other way
        for (x = 3; x < rows; x++) {
            for (y = 0; y <= columns - 4; y++) {
                if(board[x][y] == piece && board[x - 1][y + 1] == piece && board[x - 2][y + 2] == piece && board[x - 3][y + 3] == piece){
                    return true;
                }
            }
        }

        return false;
    }
    
}

// 
