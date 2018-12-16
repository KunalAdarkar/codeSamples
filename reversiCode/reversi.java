import java.util.Scanner;

public class reversi {

   
    
    public static String[][] createBoard(int boardSize) // creates the initial board
    {
    	int initialW1 = ((boardSize / 2) - 1) * boardSize + (boardSize / 2) - 1 ;
    	int initialB1 = initialW1 + 1;
    	int initialB2 = initialW1 + boardSize;
    	int initialW2 = initialB2 + 1; 

    	String[][] startingBoard;
    	startingBoard = new String[boardSize][boardSize];
    	for(int i = 0; i < boardSize; i++)
    	{
    		for(int j = 0; j < boardSize; j++)
    		{
    			if((boardSize * i + j) == initialW1)
    			{
    				startingBoard[i][j] = "W|";
    			}
    			else if((boardSize * i + j) == initialB1)
    			{
    				startingBoard[i][j] = "B|";
    			}
    			else if((boardSize * i + j) == initialW2)
    			{
    				startingBoard[i][j] = "W|";
    			}
    			else if((boardSize * i + j) == initialB2)
    			{
    				startingBoard[i][j] = "B|";
    			}
    			else
    			{
    				
    				startingBoard[i][j] = "_|";
    			}
    			
    		}
    		
    	}
    	
    	showBoard(startingBoard, boardSize);
    	
 
    	
    	
    	
    	return startingBoard;
    }
    
    public static void showBoard(String[][] board, int boardSize) // displays the board as a string
    {
    for(int i = 0; i < boardSize; i++)
    {
    	
    	for(int j = 0; j < boardSize; j++)
    	{
    		System.out.print(board[i][j]);
    		
    		
    	}
    	System.out.println(i);
    }
    for(int k = 1; k <= boardSize; k++)
    {
    	System.out.print(k + " ");
    }
    
    System.out.println("");
    	
    	
    }
    
    public static String[][] playerMovement(String[][] board, int boardSize, int player) // moves the player's tile to the defined position
    {
    	
    	String a = "";
    	
    	
    	if(player == 1)
    	{
    		a = "B|";
    	}
    	else if (player == 2)
    	{
    		a = "W|";
    	}
    	
    	if(player == 1)
    	{
    	System.out.println("Black Tile Player turn: Where would you like to move your piece, use the format (row,column)");
    	}
    	else if(player == 2)
    	{
    		System.out.println("White Tile Player turn: Where would you like to move your piece, use the format (row,column)");
    	}
    		Scanner reader = new Scanner(System.in);
    	String input = reader.nextLine();
    	
    	int row = Character.getNumericValue(input.charAt(1));
    	int column = Character.getNumericValue(input.charAt(3)) - 1;
    	int valid = moveValid(board,row, column, boardSize,player);
    	if( valid >= 0)
    	{
    		board[row][column] = a;
    		
    		if(valid == 0)
    		{
    			board[row + 1][column] = a;
    		}
    		else if(valid == 1)
    		{
    			board[row - 1][column] = a;
    			
    		}
    		else if(valid == 2)
    		{
    			board[row][column + 1] = a;
    			
    		}
    		else if(valid == 3)
    		{
    			board[row][column - 1] = a;
    			
    		}
    		else if(valid == 4)
    		{
    			board[row + 1][column - 1] = a;
    			
    		}
    		else if(valid == 5)
    		{
    			board[row - 1][column - 1] = a;
    			
    		}
    	}
    	else
    	{
    		System.out.println("Invalid Move, try again!");
    		showBoard(board,boardSize);
    		playerMovement(board,boardSize,player);
    	}
    	showBoard(board,boardSize);
    	
    	return board;
    	
    }
    
   
    
    public static int moveValid(String[][] board,int row, int column, int boardSize, int player) // finds out if the move is valid and what type of move it is
    {
    	String a = "";
    	String b = "";
    	String c = "_|";
    	
    	if(player == 1)
    	{
    		a = "W|";
    		b = "B|";
    	}
    	else if(player == 2)
    	{
    		a = "B|";
    		b = "W|";
    	}
    	
    	if(row + 1 <= boardSize - 1 & row + 2 <= boardSize - 1)
    	{
    	if( board[row + 1][column] == a & board[row + 2][column] == b & board[row][column] == c)
    	{
    		
    	return 0;
    	}
    	}
    	if(row - 1 >= 0 & row - 2 >= 0 )
    	{
    	if(board[row - 1][column] == a & board[row - 2][column] == b & board[row][column] == c)
    	{
    	
        	return 1;
    	}
    	}
    	if(column + 1 <= boardSize - 1 & column + 2 <= boardSize - 1 )
    	{
    	if(board[row][column + 1] == a & board[row][column + 2] == b & board[row][column] == c)
    	{
    		
        	return 2;
    	}
    	}
    	if(column - 1 >= 0 & column - 2 >= 0 )
    	{
    	if(board[row][column - 1] == a & board[row][column - 2] == b & board[row][column] == c)
    	{
    		
        	return 3;
    	}
    	}
    	if((column - 2 >= 0 & row + 2 <= boardSize - 1 ))
    	{
    	// diagnols
    	if(board[row + 1][column - 1] == a & board[row+2][column - 2] == b & board[row][column] == c)
    	{
    		
        	return 4;
    	}
    	}
    	if((column - 2 >= 0 & row - 2 >= 0))
    	{
    	// diagnols
    	if(board[row - 1][column - 1] == a & board[row-2][column - 2] == b & board[row][column] == c)
    	{
    		
        	return 5;
    	}
    	}
    	
    	
    	
		return -1;
    	
    	
    }
    
    public static boolean endPlayerGame(String [][] board, int boardSize) // determines if the game is over
    {
    	
    	boolean a = true;
    	
    	for(int i = 0; i < boardSize; i++)
    	{
    		for(int j = 0; j < boardSize; j++)
    		{
    		
    			if(board[i][j] == "_|")
    			{
    				int checker1 =	moveValid(board,i,j,boardSize,1);
    				if(checker1 >= 0 )
    				{
    					a = false;
    				}
    				
    				checker1 = moveValid(board,i,j,boardSize,2);
    				if(checker1 >= 0 )
    				{
    					a = false;
    				}
    				
    			}
    		
    		}
    	}
    	
    	
    	
    
    	
    	return a;
    }
    
    public static boolean findWinner(String [][] board, int boardSize) // finds the winner of the game
    {
    	int playerB = 0;
    	int playerW = 0;
    	for(int i = 0; i < boardSize; i++)
    	{
    		for(int j = 0; j < boardSize; j++)
    		{
    			if(board[i][j] == "B|")
    			{
    				playerB++;
    			}
    			if(board[i][j] == "W|")
    			{
    				playerW++;
    			}
    		}
    	}
    	
    	if(playerB > playerW)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    
    public static String [][] computerMovement(String [][] board, int boardSize) // moves the computer's tile across the board
    {
    	
    	
    	for(int i = 0; i < boardSize; i++)
    	{
    		for(int j = 0; j < boardSize; j++)
    		{
    		if(board[i][j] == "_|")
    		{
    			int computerMove = moveValid(board, i, j, boardSize, 2);
    			
    			if(computerMove >= 0)
    			{
    			
    			System.out.println("The computer moves!");
    			board[i][j] = "W|";
    			if(computerMove == 0)
        		{
        			board[i + 1][j] = "W|";
        		}
        		else if(computerMove == 1)
        		{
        			board[i - 1][j] = "W|";
        			
        		}
        		else if(computerMove == 2)
        		{
        			board[i][j + 1] = "W|" ;
        			
        		}
        		else if(computerMove == 3)
        		{
        			board[i][j - 1] = "W|";
        			
        		}
        		else if(computerMove == 4)
        		{
        			board[i + 1][j - 1] = "W|";
        			
        		}
        		else if(computerMove == 5)
        		{
        			board[i - 1][j - 1] = "W|";
        			
        		}
        		
        
    			showBoard(board, boardSize);
    			return board;
    			}
    		
    			
    			
    			}
    	
    		
    				
    		}
    		
    	}
    	System.out.println("The game is over!");
    	 if(findWinner(board,boardSize))
		   {
			   System.out.println("Black tile player won!");
		   }
		   else
		   {
			   System.out.println("White tile player won!");
		   }
    	System.exit(0);
    	return board;
    }

    
    
 public static void main(String[] args) {
    
	int boardSize = 4;
	String[][] board;
	board = new String[boardSize][boardSize];
    board = createBoard(boardSize);
   System.out.println("Choose 1 for player vs player.\nChoose 2 for player vs computer");
    Scanner reader = new Scanner(System.in);
	int input = reader.nextInt();
	
	switch(input) {
	
	case 1: //player vs player
		   for(int i = 0; i < 1000; i++)
		   {
			   if(endPlayerGame(board, boardSize))
			   {
				   break;
			   }
		   board = playerMovement(board,boardSize,1);
		   if(endPlayerGame(board, boardSize))
		   {
			   break;
		   }
		   board = playerMovement(board,boardSize,2);
		  
		   }
		   
		   System.out.println("Game over!");
		 
		   if(findWinner(board,boardSize))
		   {
			   System.out.println("Black tile player won!");
			   System.exit(0);
		   }
		   else
		   {
			   System.out.println("White tile player won!");
			   System.exit(0);
		   }
		   
	case 2: //player vs computer
		
		for(int i = 0; i < 1000; i++)
	   {
		   
	   board = playerMovement(board,boardSize,1);
	   if(endPlayerGame(board, boardSize))
	   {
		   break;
	   }
	   board = computerMovement(board,boardSize);
	   if(endPlayerGame(board, boardSize))
	   {
		   break;
	   }
	   }
		  
	
	}
	
	

 }

}