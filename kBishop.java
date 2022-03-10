import java.util.Scanner;

public class kBishop 
{
	static int boardSize, bishops;
	
	static boolean validInput()
	{
		if(boardSize<0 || bishops<0 ||(boardSize==1 &&bishops>1) || bishops>boardSize*2-2)
		{
			return false;
		}
		return true;
	}
	static int board[][], r, c, solutions=0, placedBishops=0;
	static boolean nextRowBool=true, nextColumnBool=false, backtrackBool=false, printBool=false, continueBool=true;
	
	static void nextColumn()
	{
		nextColumnBool=false;
		c++;
		if(c==boardSize)
		{
			backtrackBool=true;
			return;
		}

		r=-1;
		nextRowBool=true;
		return;
	}
	static void nextRow()
	{
		nextRowBool=false;
		r++;
		if(r>=boardSize && c>=boardSize)
		{
			backtrackBool=true;
			return;
		}
		if(r>=boardSize)
		{
			nextColumnBool=true;
			return;
		}
		for (int i = 1; r - i >= 0 && c - i >= 0; i++) // up-diagonal test
		{
			if (board[r-i][c-i] == 1)
			{
				nextRowBool=true;
				return;
			}
		}
         
		for (int i = 1; r + i < boardSize && c - i >= 0; i++)  // down-diagonal test
		{
			if (board[r+i][c-i] == 1)
			{
				nextRowBool=true;
				return;
			}
		}
         
		board[r][c] = 1;
		placedBishops++;
		if(placedBishops==bishops)
		{
			printBool=true;
			return;
		}
		nextRowBool=true;
		
		return;
	}
	static void backtrack()
	{
		backtrackBool=false;
		if(c==boardSize)
		{
			c--;
			for (r = boardSize-1; r>=0; r--)
			{
				if(board[r][c]==1)
				{
					board[r][c]=0;
					placedBishops--;
					nextRowBool=true;
					return;
					
				}
			}
		}


		boolean noBishop=true;
		for (r = boardSize-1; r>=0; r--)
		{
			if(board[r][c]==1)
			{
				board[r][c]=0;
				placedBishops--;
				noBishop=false;
				nextRowBool=true;
				return;
				
			}
		}
		//
		if(noBishop)
		{
			c--;
			if(c <0)
			{
				continueBool=false;
				return;
			}
			backtrackBool=true;
			return;
		}
		System.exit(0);
		
	}
	static void print()
	{
		printBool=false;
		solutions++;
		System.out.println("Solution number " + solutions);
		
		for(int i=0; i<boardSize; i++)
		{
			for(int j=0; j<boardSize; j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
			
		}
		System.out.println();
		System.out.println();
		nextRowBool=true;
		placedBishops--;
		board[r][c]=0;
		
		return;
	}
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the size of the square board and the amount of bishops, respectively");
		boardSize=input.nextInt();
		bishops=input.nextInt();
		input.close();
		board=new int[boardSize][boardSize];
		c=0;
		r=0;
		board[0][0]=1;
		placedBishops++;
		//size=1, we can only have 1 bishop
		//if size=2 we can only have 2 bishops
		//if size=3 we can only have 4
		// size=4 we can only have 6 
		//size =5 we can only have 8
		//size 8 we can only have 14
		//size=1 is the only odd one out do to only have 1 box
		
		if(validInput())
		{
			while(continueBool)
			{
				if(nextRowBool)
				{
					//System.out.print("nextRow");
					nextRow();
				}
				if(nextColumnBool)
				{
					//System.out.print("nextCol");
					nextColumn();
				}
				if(backtrackBool)
				{
					//System.out.print("Backtrack");
					backtrack();
				}
				if (printBool)
				{
					//System.out.print("print");
					print();
				}
			}
		}
		else
		{
			System.out.println("Not a valid input");
		}
	}
}
