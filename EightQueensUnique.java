import java.util.ArrayList;

public class EightQueensUnique 
{
	static int board[][], r, c, solutions=0;
	static boolean nextRowBool=false, nextColumnBool=true, backtrackBool=false, printBool=false, continueBool=true;
	static ArrayList<int[][]> solutionArr=new ArrayList<int[][]>();
	static void nextColumn()
	{
		nextColumnBool=false;
		c++;
		if(c==8)
		{
			printBool=true;
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
		if(r>=8)
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
         
		for (int i = 1; r + i < 8 && c - i >= 0; i++)  // down-diagonal test
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
		int temp[][]=new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=x[j][8-i-1];
			}
		}
		return temp;
	}
	static int [][] reflectX(int [][] x)
	{
		int temp[][]=new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=x[i][8-j-1];
			}
		}
		return temp;
	}
	static int [][] reflectY(int [][] x)
	{
		int temp[][]=new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=x[8-i-1][j];
			}
		}
		return temp;
	}
	static int [][] reflectXY(int [][] x)
	{
		int temp[][]=new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=x[j][i];
			}
		}
		return temp;
	}
	static int [][] reflectYX(int [][] x)
	{
		int temp[][]=new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=x[8-j-1][8-i-1];
			}
		}
		return temp;
	}
	static void addSolution()
	{
		int [][] tempboard=new int[8][8];
	
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
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
			for(int j=0; j<8; j++)
			{
				for(int k=0; k<8; k++)
				{
					if(solutionArr.get(i)[j][k]!=board[j][k])
					{
						same=false;
						k=8;
						j=8;
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
	static void print()
	{
		printBool=false;
		if(unique())
		{
			solutions++;
			System.out.println("Solution number " + solutions);
			
			for(int i=0; i<8; i++)
			{
				for(int j=0; j<8; j++)
				{
					System.out.print(board[i][j]);
				}
				System.out.println();
				
			}
			System.out.println();
			System.out.println();
			
		}
		backtrackBool=true;
		return;
		
	}
	public static void main(String args[])
	{
		board=new int[8][8];
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
			if (printBool)
			{
				//System.out.print("print");
				print();
			}
		}
	}

}
