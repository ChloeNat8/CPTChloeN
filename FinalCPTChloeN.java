/*
------------------------------------------------------------------------------------------------
Name: Chloe Natividad

Game: Connect 4

Purpose: Like the classic game we all know and love, the objective of the game is to get 4 in a row by taking turns placing pieces

Date: January 21, 2025
------------------------------------------------------------------------------------------------
*/

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class FinalCPTChloeN{
		
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720);
		
		MainMenu(con);
		
	}

// Methods

	// MainMenu
	public static void MainMenu(Console con){
		
		// Load Image
		con.clear();
		BufferedImage imgMainMenu = con.loadImage("mainmenu.jpg");
		con.drawImage(imgMainMenu, 0, 0);
		
		// Variables
		char chrChoice;
		boolean boolExit;
		String strMenu;
		
		// Initialize Variables
		boolExit = false;
		// Main Menu
		while(!boolExit){
			con.println();
			chrChoice = con.getChar();
			
			// Depending on the choice the user, do the associated task
			if(chrChoice == 'p'){
				PlayGame(con);
			}else if(chrChoice == 'l'){
				Leaderboard(con);
			}else if(chrChoice == 'h'){
				Help(con);
			}else if(chrChoice == 't'){
				intTheme = ChooseTheme(con);
			}else if(chrChoice == 'm'){
				MainMenu(con);
			}else if(chrChoice == 's'){
				Secret(con);
			}else if(chrChoice == 'q'){
				boolExit = true;
			}
		}	
		
		con.closeConsole();	
	}
 
	public static void PlayGame(Console con){
		boolean boolPlayAgain = true;
		String strPlayer1Name;
        String strPlayer2Name;
        int intPlayer1Wins = 0;
		int intPlayer2Wins = 0;
        
        // Change Background
        con.clear();
        con.setBackgroundColor(new Color(82, 67, 130));
        
        // Get Player's Names
        con.println();
        con.println("  Connect 4");
        con.println("  ---------------------------------");
        con.print("  Enter Player 1's name (X): ");
        strPlayer1Name = con.readLine();
        con.println();
        con.print("  Enter Player 2's name (O): ");
        strPlayer2Name = con.readLine();
        con.println("  ---------------------------------");
        con.sleep(1000);
        
        while(boolPlayAgain){      		
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
			int intPlacement = 0;
			String strPlacement;
			char chrPlayAgain;
			
			// Change Background
			//con.clear();
			//con.setBackgroundColor(new Color(82, 67, 130));
					
			// Create the Board
			intBoard = new int[intRows][intColumns];
			InitializeBoard(intBoard, intRows, intColumns, intEmpty, con);
			
			// Main Game
			while (!boolGameWon && !IsBoardFull(intBoard, intRows, intColumns, intEmpty)) {
				
				// Print Game
				con.clear();
				PrintHeader(strPlayer1Name, strPlayer2Name, intPlayer1Wins, intPlayer2Wins, con);
				MakeBoard(intBoard, intRows, intColumns, con);
				PrintTurn(boolIsPlayer1Turn, strPlayer1Name, strPlayer2Name, con);
				
				// Get Column Input
				con.print("  Enter the column (1-7) to drop your piece: ");
				strPlacement = con.readLine();
				if(strPlacement.equals("1") || strPlacement.equals("2") || strPlacement.equals("3") || strPlacement.equals("4") || strPlacement.equals("5") || strPlacement.equals("6") || strPlacement.equals("7")){
					intPlacement = Integer.parseInt(strPlacement);
					intPlacement = intPlacement - 1;
				}else{
					while(!strPlacement.equals("1") || !strPlacement.equals("2") || !strPlacement.equals("3") || !strPlacement.equals("4") || !strPlacement.equals("5") || !strPlacement.equals("6") || !strPlacement.equals("7")){
						con.println("  Invalid column. Please try again.");
						con.sleep(2000);
						con.clear();
						PrintHeader(strPlayer1Name, strPlayer2Name, intPlayer1Wins, intPlayer2Wins, con);
						MakeBoard(intBoard, intRows, intColumns, con);
						PrintTurn(boolIsPlayer1Turn, strPlayer1Name, strPlayer2Name, con);
						con.print("  Enter the column (1-7) to drop your piece: ");
						strPlacement = con.readLine();
						if(strPlacement.equals("1") || strPlacement.equals("2") || strPlacement.equals("3") || strPlacement.equals("4") || strPlacement.equals("5") || strPlacement.equals("6") || strPlacement.equals("7")){
							intPlacement = Integer.parseInt(strPlacement);
							intPlacement = intPlacement - 1;
							break;
						}
					}
				}
				
				// Drop Piece
				if(boolIsPlayer1Turn){
					if(!DropPiece(intBoard, intPlacement, intPlayer1, intRows, intEmpty)) {
						con.println("  Column is full. Please try a different column.");
						con.sleep(2000);
						boolIsPlayer1Turn = !boolIsPlayer1Turn;
					}
				}else{
					if(!DropPiece(intBoard, intPlacement, intPlayer2, intRows, intEmpty)){
						con.println("  Column is full. Please try a different column.");
						con.sleep(2000);
						boolIsPlayer1Turn = !boolIsPlayer1Turn;
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
					PrintHeader(strPlayer1Name, strPlayer2Name, intPlayer1Wins, intPlayer2Wins, con);
					MakeBoard(intBoard, intRows, intColumns, con);
					if(boolIsPlayer1Turn){
						intPlayer1Wins = intPlayer1Wins + 1;
						con.println("  " + strPlayer1Name + " (X) wins! Congratulations!");
					}else{
						intPlayer2Wins = intPlayer2Wins + 1;
						con.println("  " + strPlayer2Name + " (O) wins! Congratulations!");
					}
				}else{            
					boolIsPlayer1Turn = !boolIsPlayer1Turn;
				}
				
				// If Draw
				if(!boolGameWon){
					con.clear();
					PrintHeader(strPlayer1Name, strPlayer2Name, intPlayer1Wins, intPlayer2Wins, con);
					MakeBoard(intBoard, intRows, intColumns, con);
					con.println("  " + "It's a draw! No one wins.");
				}
			}
			con.println("  Do you want to play again or go back to the main menu (y/n)");
			con.println("  Press any key to play again or (m) to go back to the main menu");
			chrPlayAgain = con.getChar();
			if(chrPlayAgain == 'm'){
				// Write Highscores to highscores.txt
				TextOutputFile highscores = new TextOutputFile("highscores.txt", true);
				highscores.println(strPlayer1Name);
				highscores.println(intPlayer1Wins);
				highscores.println(strPlayer2Name);
				highscores.println(intPlayer2Wins);
				highscores.close();
				
				MainMenu(con);
			}
		}
	}
	
	// InitializeBoard
	// This method creates the 2d array and fills it all with 0
	public static void InitializeBoard(int[][] intBoard, int intRows, int intColumns, int intEmpty, Console con){
        int x;
        int y;
        for (x = 0; x < intRows; x++) {
            for (y = 0; y < intColumns; y++) {
                intBoard[x][y] = intEmpty;
            }
        }
    }
    public static int intTheme = 0;
    
    // IsBoardFull
    public static boolean IsBoardFull(int[][] intBoard, int intRows, int intColumns, int intEmpty){
        int y;
        for (y = 0; y < intColumns; y++) {
            if (intBoard[0][y] == intEmpty) {
                return false;
            }
        }
        return true;
	}
	
	// MakeHeader
	public static void PrintHeader(String strPlayer1, String strPlayer2, int intPlayer1Wins, int intPlayer2Wins, Console con) {
		con.println();
        con.println("  Connect 4");
        con.println("  ---------------------------------");
        con.println("  " + strPlayer1 + " (X)");
        con.println("  Wins: " + intPlayer1Wins);
        con.println();
        con.println("  " + strPlayer2 + " (O)");
        con.println("  Wins: " + intPlayer2Wins);
        con.println("  ---------------------------------");
    }
	
	// MakeBoard
	public static void MakeBoard(int[][] intBoard, int intRows, int intColumns, Console con) {
        
        int x;
        int y;
        char chrPiece;
        
        Color clrBoard;
        Color clrPlayer1;
        Color clrPlayer2;
        
        
        // Colors
		int intRGB[][] = MakeThemeArray(con);
		
        clrBoard = new Color(intRGB[intTheme][0], intRGB[intTheme][1], intRGB[intTheme][2]);
        clrPlayer1 = new Color(intRGB[intTheme][3], intRGB[intTheme][4], intRGB[intTheme][5]);
        clrPlayer2 = new Color(intRGB[intTheme][6], intRGB[intTheme][7], intRGB[intTheme][8]);
		

        con.println();
        con.print("          ");
        for (y = 1; y <= intColumns; y++) {
			con.print(y + " ");
        }
        con.println();
        for (x = 0; x < intRows; x++) {
			con.print("          ");
            for (y = 0; y < intColumns; y++) {
				if(intBoard[x][y] == 1){
					con.setTextColor(new Color(intRGB[intTheme][3], intRGB[intTheme][4], intRGB[intTheme][5]));
					chrPiece = 'X';
				}else if(intBoard[x][y] == 2){
					con.setTextColor(new Color(intRGB[intTheme][6], intRGB[intTheme][7], intRGB[intTheme][8]));
					chrPiece = 'O';
				}else{
					//con.setTextColor(new Color(255, 255, 255));
					chrPiece = '.';
				}
                con.print(chrPiece + " ");  
                //con.setTextColor(new Color(255, 255, 255));              
            }
            con.println();
        }
        con.println();        
        con.setDrawColor(new Color(intRGB[intTheme][0], intRGB[intTheme][1], intRGB[intTheme][2]));
        con.fillRoundRect(108, 230, 180, 190, 30, 30);
    }
    
    // PrintTurn
    public static void PrintTurn(boolean boolIsPlayer1Turn, String strPlayer1Name, String strPlayer2Name, Console con){
		if(boolIsPlayer1Turn){
			con.println("  " + strPlayer1Name + " (X), it's your turn");
		}else{
			con.println("  " + strPlayer2Name + " (O), it's your turn");
		}
	}
    
    // DropPiece
    public static boolean DropPiece(int[][] intBoard, int intColumn, int intPiece, int intRows, int intEmpty){
        int x;
        for (x = intRows - 1; x >= 0; x--){
            if (intBoard[x][intColumn] == intEmpty){
                intBoard[x][intColumn] = intPiece;
                return true;
            }
        }
        return false;
    }
    
    // CheckWin
    public static boolean CheckWin(int[][] intBoard, int intPiece, int intRows, int intColumns){
     int x;
     int y;
     // Check horizontal
        for (x = 0; x < intRows; x++) {
            for (y = 0; y <= intColumns - 4; y++) {
                if(intBoard[x][y] == intPiece && intBoard[x][y + 1] == intPiece && intBoard[x][y + 2] == intPiece && intBoard[x][y + 3] == intPiece){
                    return true;
                }
            }
        }

        // Check vertical
        for (x = 0; x <= intRows - 4; x++) {
            for (y = 0; y < intColumns; y++) {
                if(intBoard[x][y] == intPiece && intBoard[x + 1][y] == intPiece && intBoard[x + 2][y] == intPiece && intBoard[x + 3][y] == intPiece){
                    return true;
                }
            }
        }

        // Check diagonal 
        for (x = 0; x <= intRows - 4; x++) {
            for (y = 0; y <= intColumns - 4; y++) {
                if(intBoard[x][y] == intPiece && intBoard[x + 1][y + 1] == intPiece && intBoard[x + 2][y + 2] == intPiece && intBoard[x + 3][y + 3] == intPiece){
                    return true;
                }
            }
        }

        // Check diagonal other way
        for (x = 3; x < intRows; x++) {
            for (y = 0; y <= intColumns - 4; y++) {
                if(intBoard[x][y] == intPiece && intBoard[x - 1][y + 1] == intPiece && intBoard[x - 2][y + 2] == intPiece && intBoard[x - 3][y + 3] == intPiece){
                    return true;
                }
            }
        }

        return false;
    }

	// Help
	public static void Help(Console con){
		String strUserInput;
		// Load Image
		con.clear();
		BufferedImage imgHowToPlay = con.loadImage("howtoplay.png");
		con.drawImage(imgHowToPlay, 0, 0);
		con.setTextColor(new Color(50, 49, 109));
		strUserInput = con.readLine();
		con.setTextColor(Color.white);
		MainMenu(con);
	}
		
	// Secret Menu
	public static void Secret(Console con){
		BufferedImage imgDrum = con.loadImage("drum.png");
		con.clear();
		con.setBackgroundColor(new Color(82, 67, 130));
		con.println();
		con.println();
		con.println();
		con.println("          Did you hear about what happened to the Italian chef?");
		con.println();
		con.sleep(2000);
		con.println("          He pasta way!");
		con.drawImage(imgDrum, 200, 200);
		con.println();
		con.sleep(3000);
		MainMenu(con);
	}
	
	// Leaderboard
	public static void Leaderboard(Console con){
				
		String strLeaderboard[][];
		
		String strPlayer;
		String strHighscore;
		int intPlayers = 0;
		String strTemp;
		TextInputFile highscores = new TextInputFile("highscores.txt");
		
		while(highscores.eof() == false){
			strPlayer = highscores.readLine();
			strHighscore = highscores.readLine();
			intPlayers = intPlayers + 1;
		}
		highscores.close();
		
		strLeaderboard = new String[intPlayers][2];
		highscores = new TextInputFile("highscores.txt");
		
		int x;
		int y;
		for(x = 0; x < intPlayers; x++){
			strLeaderboard[x][0] = highscores.readLine();
			strLeaderboard[x][1] = highscores.readLine();
		}
		highscores.close();
		
		for (x = 0; x < intPlayers - 1; x++) {
			for (y = 0; y < intPlayers - x - 1; y++) {
				if (Integer.parseInt(strLeaderboard[y][1]) < Integer.parseInt(strLeaderboard[y + 1][1])) {
					String[] temp = strLeaderboard[y];
					strLeaderboard[y] = strLeaderboard[y + 1];
					strLeaderboard[y + 1] = temp;
				}
            }
        }

		// Print leaderboard
		con.clear();
		con.setBackgroundColor(new Color(82, 67, 130));
		con.println();
		con.println("  Leaderboard:");
		for(x = 0; x < intPlayers; x++) {
			con.println("  " + (x + 1) + ". " + strLeaderboard[x][0] + " - " + strLeaderboard[x][1]);
		}
		con.println("  Press (m) to go back to the main menu");
	}
	
	// MakeThemeArray
	public static int[][] MakeThemeArray(Console con){
		String strThemeName[] = new String[3];
		int intRGB[][] = new int[3][9];
		
		TextInputFile themes = new TextInputFile("themes.txt");
	
		for(int theme = 0; theme < 3; theme++) {
			strThemeName[theme] = themes.readLine();
			for (int x = 0; x < 9; x++){
				intRGB[theme][x] = themes.readInt();
			}
		}
		themes.close();
		
		return intRGB;
	}
		
	// ChooseTheme
	public static int ChooseTheme(Console con){
		int intChoice;
			
		con.clear();
		con.setBackgroundColor(new Color(82, 67, 130));
		con.println();
		con.println("  Choose your theme: ");
		con.println("  1. Default");
		con.println("  2. Classic");
		con.println("  3. Christmas");
		con.println("  After inputing your choice, press (m) to go back to the main menu");
		con.print("  Enter which theme you want to use (1-3): ");
		intChoice = con.readInt() - 1;
		if(intChoice >= 0 && intChoice <=2){
			return intChoice;
		}else{
			while(intChoice < 0 || intChoice > 2){
				con.println("  Invalid choice. Please try again.");
				con.sleep(2000);
				con.clear();
				con.setBackgroundColor(new Color(82, 67, 130));
				con.println();
				con.println("  Choose your theme: ");
				con.println("  1. Default");
				con.println("  2. Classic");
				con.println("  3. Christmas");
				con.println("  After inputing your choice, press (m) to go back to the main menu");
				con.print("  Enter which theme you want to use (1-3): ");
				intChoice = con.readInt() - 1;
				if(intChoice >= 0 && intChoice <=2){
					return intChoice;
				}
			}
		}
		return intChoice;
	}
}
