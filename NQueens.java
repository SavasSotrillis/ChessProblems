import java.util.Scanner;

public class NQueens 
{
	static int board[][], r, c, solutions=0, sizeBoard;
	static boolean nextRowBool=false, nextColumnBool=true, backtrackBool=false, printBool=false, continueBool=true;
	
	static void nextColumn()
	{
		nextColumnBool=false;
		c++;
		if(c==sizeBoard)
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
	static void print()
	{
		printBool=false;
		solutions++;
		System.out.println("Solution number " + solutions);
		
		for(int i=0; i<sizeBoard; i++)
		{
			for(int j=0; j<sizeBoard; j++)
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
		Scanner input=new Scanner(System.in);
		sizeBoard=input.nextInt();
		System.out.println("Enter Board size");
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
			if (printBool)
			{
				//System.out.print("print");
				print();
			}
		}
		input.close();
	}
}

