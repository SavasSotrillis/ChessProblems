public class NQueensSolutions 
{
	static int board[][], r, c, solutions=0, sizeBoard;
	static boolean nextRowBool=false, nextColumnBool=true, backtrackBool=false, solutionIncreasedBool=false, continueBool=true;
	
	static void nextColumn()
	{
		nextColumnBool=false;
		c++;
		if(c==sizeBoard)
		{
			solutionIncreasedBool=true;
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
		if(r>=sizeBoard)
		{
			backtrackBool=true;
			return;
		}
		for (int i = 0; i < c; ++i)                    // row test
	   	{
		   if (board[r][i] == 1)
			{
				nextRowBool=true;
				return;
			}
	   	}
		for (int i = 1; r - i >= 0 && c - i >= 0; i++) // up-diagonal test
		{
			if (board[r-i][c-i] == 1)
			{
				nextRowBool=true;
				return;
			}
		}
         
		for (int i = 1; r + i < sizeBoard && c - i >= 0; i++)  // down-diagonal test
		{
			if (board[r+i][c-i] == 1)
			{
				nextRowBool=true;
				return;
			}
		}
         
		board[r][c] = 1;
		nextColumnBool=true;
		return;
	}
	static void backtrack()
	{
		backtrackBool=false;
		c--;
		if(c <0)
		{
			continueBool=false;
			return;
		}
		for (r = 0; board[r][c] != 1; ++r);
		board[r][c]=0;
		nextRowBool=true;
		return;
		
	}
	static void solutionIncreased()
	{
		solutionIncreasedBool=false;
		solutions++;
		backtrackBool=true;
		return;
	}
	public static void main(String args[])
	{
		for(int i=0; i<12; i++)
		{
			nextColumnBool=true;
			solutions=0;
			sizeBoard=i+1;
			continueBool=true;
			board=new int[sizeBoard][sizeBoard];
			c=0;
			r=-1;
			board[0][0]=1;
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
				if (solutionIncreasedBool)
				{
					//System.out.print("print");
					solutionIncreased();
				}
			}
			System.out.println(sizeBoard +" queen problem has " +solutions + " solutions." );
			
		}

	}
}

