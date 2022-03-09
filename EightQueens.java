//import java.util.Scanner;

public class EightQueens 
{
	static int board[];
	static int c;
	static int solutions;
	static int n;
	static boolean valid()
	{
		for (int i = 0; i < c; ++i)
		{
			if (board[i] == board[c] || board[i] - board[c] == c - i || board[c]-board[i] == c - i)
			{
				return false;
			}
		}
		return true;
	}
	
	static void print()
	{
		System.out.println("Solution number: " + ++solutions);
		
		for(int i =0; i<n; i++)
		{
			System.out.print(board[i]);
		}
		System.out.println();
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(i==board[j])
				{
					System.out.print("X");
				}
				else
				{
					System.out.print("O");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public static void main(String[] args)
	{
		//System.out.println("Input the n queen problem");
		//Scanner input=new Scanner(System.in);
		n=8;
		board=new int[n];
		for(int i=0; i<n; i++)
		{
			board[i]=0;
		}
		c=0;
		
		while(c>=0)
		{
			if(c==7)
			{
				print();
				c--;
			}
			else
			{
				c++;
				board[c]=-1;
			}
			while(c>=0)
			{
				board[c]++;
				if(board[c]==n)
				{
					c--;
				}
				else if(valid())
				{
					break;
				}
			}
		}
		//input.close();
		
	}
}
