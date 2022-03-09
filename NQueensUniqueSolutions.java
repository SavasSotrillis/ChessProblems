import java.util.ArrayList;

public class NQueensUniqueSolutions 
{
	static int board[][], r, c, solutions=0;
	static boolean nextRowBool=false, nextColumnBool=true, backtrackBool=false, solutionsCounterBool=false, continueBool=true;
	static ArrayList<int[][]> solutionArr=new ArrayList<int[][]>();
	static int sizeBoard;
	
	static void nextColumn()
	{
		nextColumnBool=false;
		c++;
		if(c==sizeBoard)
		{
			solutionsCounterBool=true;
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
	static int [][] rotateArr(int [][] x)
	{
		int temp[][]=new int[sizeBoard][sizeBoard];
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				temp[i][j]=x[j][sizeBoard-i-1];
			}
		}
		return temp;
	}
	static int [][] reflectX(int [][] x)
	{
		int temp[][]=new int[sizeBoard][sizeBoard];
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				temp[i][j]=x[i][sizeBoard-j-1];
			}
		}
		return temp;
	}
	static int [][] reflectY(int [][] x)
	{
		int temp[][]=new int[sizeBoard][sizeBoard];
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				temp[i][j]=x[sizeBoard-i-1][j];
			}
		}
		return temp;
	}
	static int [][] reflectXY(int [][] x)
	{
		int temp[][]=new int[sizeBoard][sizeBoard];
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				temp[i][j]=x[j][i];
			}
		}
		return temp;
	}
	static int [][] reflectYX(int [][] x)
	{
		int temp[][]=new int[sizeBoard][sizeBoard];
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				temp[i][j]=x[sizeBoard-j-1][sizeBoard-i-1];
			}
		}
		return temp;
	}
	static void addSolution()
	{
		int [][] tempboard=new int[sizeBoard][sizeBoard];
	
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
			{
				tempboard[i][j]=board[i][j];
			}
		}
		solutionArr.add(tempboard);
		solutionArr.add(rotateArr(tempboard));
		solutionArr.add(rotateArr(solutionArr.get(solutionArr.size()-1)));
		solutionArr.add(rotateArr(solutionArr.get(solutionArr.size()-1)));
		solutionArr.add(reflectX(tempboard));
		solutionArr.add(reflectY(tempboard));
		solutionArr.add(reflectXY(tempboard));
		solutionArr.add(reflectYX(tempboard));
	}
	static boolean unique()
	{
		for(int i=0; i<solutionArr.size(); i++)
		{
			boolean same=true;
			for(int j=0; j<sizeBoard; j++)
			{
				for(int k=0; k<sizeBoard; k++)
				{
					//System.out.println(i + "" + j +"" +k);
					if(solutionArr.get(i)[j][k]!=board[j][k])
					{
						same=false;
						k=sizeBoard;
						j=sizeBoard;
					}
				}
			}
			if(same)
			{
				return false;
			}
		}
		addSolution();
		return true;
	}
	static void solutionsCounter()
	{
		solutionsCounterBool=false;
		if(unique())
		{
			solutions++;			
		}
		backtrackBool=true;
		return;
		
	}
	public static void main(String args[])
	{
		for(int i=0; i<12; i++)
		{
			solutionArr.clear();
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
					//System.out.solutionsCounter("nextRow");
					nextRow();
				}
				if(nextColumnBool)
				{
					//System.out.solutionsCounter("nextCol");
					nextColumn();
				}
				if(backtrackBool)
				{
					//System.out.solutionsCounter("Backtrack");
					backtrack();
				}
				if (solutionsCounterBool)
				{
					//System.out.solutionsCounter("solutionsCounter");
					solutionsCounter();
				}
			}
			System.out.println(sizeBoard +" queen problem has " +solutions + " unique solutions." );

		}
	}

}
