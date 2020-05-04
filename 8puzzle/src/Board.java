import edu.princeton.cs.algs4.Queue;

public class Board {
	
	private final int[][] blocks;

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {

    	this.blocks = blocks;
    	
    	
    }
    
    // board size N
	public int size() {
		return this.blocks[0].length;
	}
	
	// number of blocks out of place
	public int hamming() {
		int n = 0; // blocks out of place
		int p = 1; // board position
		for (int[] row : this.blocks)
			for ( int j : row)
				if ( p++ != j && j!=0 ) n++;
		return n;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int man = 0;
		
		int r = 0; // row counter
		for (int[] row : this.blocks) {
			
			int c = 0; // col counter
			for ( int j : row) {
				if (j == 0) { c++; continue; }
				// tile j is supposed to be in row j/N, col (j-1)%N
				int srow = (j-1)/this.size();
				int scol = (j-1)%this.size();

				man += ( Math.abs(srow -r) + Math.abs(scol -c) );
				c++;
			}
			r++;
		}
		return man;
	}
	
	// is this board the goal board?
	public boolean isGoal()  {
		int p = 1; // board position
		for (int[] row : this.blocks)
			for ( int j : row)
				if ( p++ != j && j!=0 ) 
					return false;
		return true;
	}
	
	// is this board solvable?
	public boolean isSolvable() {
		if (size()%2 == 1) {
			if (countInversions()%2 == 1) return false;
			return true;
		}
		else {
			if ((countInversions()+getBlankRow())%2 == 1)
				return true;
			return false;
		}
	}
	
	/*
	 * returns the row in which the blank tile can be found
	 */
	private int getBlankRow() {
		for (int r = 0; r < size(); r++)
			for (int c = 0; c < size(); c++)
				if (blocks[r][c] == 0) return r;
		return -1;
	}
	
	private int countInversions() {
		int in = 0;
		int[] b = this.flatten();
		for (int i = 0; i < b.length; i++)
			for (int j = i+1; j < b.length; j++)
				if (b[j] < b[i] &&b[j]!=0) in++;
		
		return in;
	}
	
	/* 
	 * flattens a Board
	 */
	private int[] flatten() {
		int[] b = new int[(int) Math.pow(this.size(), 2)];
		
		int i = 0;
		for (int[] r : this.blocks)
			for (int c : r)
				b[i++] = c;
		
		return b;
	}
	
	// does this board equal y?
	public boolean equals(Object y) {		
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        
        if (this.size() != that.size()) return false;
        
        for (int i = 0; i < size(); i++)
        	for (int j = 0; j < size(); j++)
        		if (this.blocks[i][j] != that.blocks[i][j]) return false;
        
        
        return true;
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<>();
		
		int[] z = findZero();
		// indices neighboring the blank tile:
		int[][] ns = { 
				{z[0]+1, z[1]}  , 
				{z[0],   z[1]-1}, 
				{z[0]-1, z[1]}  ,
				{z[0],   z[1]+1} };
		
		for (int[] n : ns) {
			
			if (n[0] < 0 || n[1] < 0) continue;
			if (n[0] >= size() || n[1] >= size()) continue;
			
			int[][] newBlocks = new int[size()][size()];
			for (int i = 0; i < this.size(); i++) {
				for (int j = 0; j < this.size(); j++) {
					if (i == n[0] && j == n[1]) continue; // newBlocks is initialed to zeros
					if (i == z[0] && j == z[1]) newBlocks[i][j] = this.blocks[n[0]][n[1]];
					else newBlocks[i][j] = this.blocks[i][j];
				}
			}
			
			q.enqueue(new Board(newBlocks));
			
		}
		
		return q;
	}
	
	/*
	 * returns position of zero
	 */
	private int[] findZero() {
		
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < this.size(); j++) {;
				if (this.blocks[i][j] == 0) {
					int[] z = {i, j};
					return z;
				}
			}
		}
		
		return null;
	}
	

	
	// string representation of this board (in the output format specified below)
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(size() + "\n");
	    for (int i = 0; i < size(); i++) {
	        for (int j = 0; j < size(); j++) {
	            s.append(String.format("%2d ", blocks[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	
//	// unit tests (not graded)
//	public static void main(String[] args) {
//		
//		int[][] start0 = {{1,2,3},{4,5,6},{7,8,0}};
//		int[][] start1 = {{3,2,1},{4,5,6},{7,0,8}};
//		int[][] start2 = {{1,2,3},{4,6,5},{8,0,7}};
//		
//		Board testBoard = new Board(start2);
//		System.out.println(testBoard);
//		System.out.println(testBoard.hamming());
//		System.out.println(testBoard.manhattan());
//		System.out.println(testBoard.isGoal());
//		System.out.println(testBoard.isSolvable());
//		System.out.println();
//		for (Board b : testBoard.neighbors())
//			System.out.println(b);
//		//System.out.println(testBoard.neighbors());
//	}

	
	public int tileAt(int row, int col) {
		return this.blocks[row][col];
	}

}
