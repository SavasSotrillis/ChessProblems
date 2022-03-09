
public class EightQueens2D 
{
	static int board[][], r, c, solutions=0;
	static boolean nextRowBool=false, nextColumnBool=true, backtrackBool=false, printBool=false, continueBool=true;
	
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
	static void print()
	{
		printBool=false;
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

/*
public class EightQueens2D 
{
	static int board[][], r, c, solutions=0;
	

	public static void main(String args[])
	{
		board=new int[8][8];
		c=0;
		r=-1;
		board[0][0]=1;
		
		
		boolean nextColumn, nextRow, backtrack, print;
		nextColumn=true;
		print=false;
		nextRow=false;
		backtrack=false;
		print=false;
		int counter=0;
		while(true)
		{
			//System.out.println(counter++);
			if(nextColumn)
			{
				System.out.println(counter++);
				c++;
				if(c==8)
				{
					print=true;
					nextColumn=false;
					nextRow=false;
					backtrack=false;
					print=false;
					
				}
				else
				{
					r=-1;
					print=false;
					nextColumn=false;
					nextRow=true;
					backtrack=false;
					print=false;
				}
			}
			if(nextRow)
			{
				nextRow=false;
				r++;
				if(r>=8)
				{
					backtrack=true;
					
				}
				else
				{
					for (int i = 0; i < c; ++i)                    // row test
				   	{
					   if (board[r][i] == 1)
						{
						   nextRow=true;
						   
						}
				   	}
					
					for (int i = 1; r - i >= 0 && c - i >= 0 && nextRow==false; i++) // up-diagonal test
					{
						if (board[r-i][c-i] == 1)
						{
							nextRow=true;
							
						}
					}
			         
					for (int i = 1; r + i < 8 && c - i >= 0&& nextRow==false; i++)  // down-diagonal test
					{
						if (board[r+i][c-i] == 1)
						{
							nextRow=true;
							
						}
					}
			        if(nextRow==false)
			        {
						board[r][c] = 1;
						nextColumn=true;
			        }
				}
				
				
			}
			if(backtrack)
			{
				c--;
				if(c <0)
				{
					return;
				}
				for (r = 0; board[r][c] != 1; ++r);
				board[r][c]=0;
				nextRow=true;
				backtrack=false;
			}
			if(print)
			{
				solutions++;
				System.out.println("Solutions number " + solutions);
				
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
				backtrack=true;
				print=false;
			}

		}
	}
}
*/
