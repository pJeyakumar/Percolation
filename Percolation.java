/**********
 * NAME: Piranavan Jeyakumar
 * DATE: 13.05.2020
 * DESCRIPTION: A program that creates an N x N grid and checks whether or not the system percolates. This program does not randomly generate open cells, it simply
 * 				fills in opened cells connected to the top row and determines whether or not the system percolates. This program uses Quick Union to unite open adjacent cells.
 * COURSE: Princeton University Algorithms course.
 *********/
public class Percolation 
{
	// Variable Declaration
	private Boolean[][] blnFull;
	private Boolean[][] blnOpen;
	private int[][] cells;
	private int openSites = 0;
	private QuickU uf;
	
	// Creates an n by n grid where all sites are initially blocked (not open)
	public Percolation(int n) 
	{
		// Declaring sizes of the new arrays
		blnFull = new Boolean[n + 1][n + 1];
		blnOpen = new Boolean[n + 1][n + 1];
		cells = new int[n + 1][n + 1];
		
		// Creating Quick Union system
		uf = new QuickU(n * n);
		
		// Going thorough every index and setting it to FALSE
		for(int i = 1; i <= n; i++) 
		{
			for(int j = 1; j <= n; j++) 
			{
				blnFull[i][j] = false;
				blnOpen[i][j] = false;
				cells[i][j] = i + j*n;
			}
		}
	}
	
	// Opens the site at the specified row and col position.
	public void open(int row, int col) 
	{
		if(blnOpen[row][col] == false)
		{
			blnOpen[row][col] = true;
			openSites++;
			
			// If any cell in the first row is opened, it becomes filled
			if(row == 1) 
			{
				blnFull[row][col] = true;
			}
		}
		
		// Unites opened cell with all other adjacent open cells
		if(row < blnOpen.length - 2 && blnOpen[row + 1][col]) 
		{
			uf.union(cells[row][col], cells[row + 1][col]);
		}
		if(row > 1 && blnOpen[row - 1][col]) 
		{
			uf.union(cells[row][col], cells[row - 1][col]);
		}
		if(col < blnOpen.length - 2 && blnOpen[row][col + 1]) 
		{
			uf.union(cells[row][col], cells[row][col + 1]);
		}
		if(col > 1 && blnOpen[row][col - 1]) 
		{
			uf.union(cells[row][col], cells[row][col - 1]);
		}
		
		for(int i = 1; i < blnOpen.length - 1; i++)
		{
			// Checks if the newly opened cell is connected to any cell at the top row
			if(uf.connected(cells[row][col], cells[1][i])) 
			{
				// If it is, it becomes full and all adjacent open cells also become full.
				blnFull[row][col] = true;
				if(row < blnOpen.length - 2 && blnOpen[row + 1][col]) 
				{
					blnFull[row + 1][col] = true;
				}
				if(row > 1 && blnOpen[row - 1][col]) 
				{
					blnFull[row - 1][col] = true;
				}
				if(col < blnOpen.length - 2 && blnOpen[row][col + 1]) 
				{
					blnFull[row][col + 1] = true;
				}
				if(col > 1 && blnOpen[row][col - 1]) 
				{
					blnFull[row][col - 1] = true;
				}
			}
		}
	}
	
	// Checks if the specified row and col position is open
	public boolean isOpen(int row, int col) 
	{
		if(row > 0 && row < blnOpen.length && col > 0 && col < blnOpen.length)
			return blnOpen[row][col];
		return false;
	}
	
	// Checks if the specified row and col position is full
	public boolean isFull(int row, int col) 
	{
		if(row > 0 && row < blnOpen.length && col > 0 && col < blnOpen.length)
			return blnFull[row][col];
		return false;
	}
	
	// Checks how many open sites there are
	public int numberOfOpenSites() 
	{
		return openSites;
	}
	
	// Checks if the system percolates
	public boolean percolates() 
	{
		// Goes through all of the bottom cells, if any of them are full, the system must percolate
		for(int i = 1; i < blnFull.length; i++)
		{
			if(blnFull[blnFull.length - 1][i] == true)
				return true;
		}
		return false;
	}
	
	//	Testing grounds [this is where you would randomly open cells, your code should check whether or not the system percolates, if not, randomly open another cell, loop
	//					until the system percolates, and you can check how many open sites the system has.]
	public static void main(String[] args)
	{

	}

}
