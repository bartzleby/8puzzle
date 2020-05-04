import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private Stack<Board> sol;
	
    private class sBoard implements Comparable<sBoard> {
    	
    	private Board board;
    	private int moves;
    	private int priority;
    	private sBoard prev;
    	
    	@SuppressWarnings("unused")
		public sBoard() {}
    	public sBoard(int m, Board b, sBoard p) {
    		
    		this.board = b;
    		this.moves = m;
        	
    		this.priority = board.manhattan() + this.moves;
    		//this.priority = board.hamming() + this.moves;
    		
    		
    		this.prev = p;
    	
    	}

    	@Override
		public int compareTo(sBoard o) {
			return this.priority - o.priority;
		}
    	
    }

	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	
    	if (!initial.isSolvable()) throw new IllegalArgumentException();
    	if (initial.equals(null)) throw new NullPointerException();
    	
    	sBoard isb = new sBoard(0, initial, null);
    	MinPQ<sBoard> bq = new MinPQ<>();
    	bq.insert(isb);
    	
    	while (true) {
	    	isb = bq.delMin();
	    	if (isb.board.isGoal()) break;
	    	for (Board b : isb.board.neighbors()) {
	    		if (isb.prev != null && b.equals(isb.prev.board)) continue;
	    		sBoard sb = new sBoard(isb.moves+1, b, isb);
	    		bq.insert(sb);
	    	}
    	}
    	
    	sol = new Stack<>();
    	while (isb.prev != null) {
    		sol.push(isb.board);
    		isb = isb.prev;
    	}

    }
    
    // min number of moves to solve initial board
    public int moves() {
    	return sol.size();
    }
    
    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
    	return sol;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
    }
    
//    public static void main(String[] args) {
//    	
//		int[][] start0 = {{1,2,3},{4,5,6},{7,8,0}};
//		int[][] start1 = {{3,2,1},{4,5,6},{7,0,8}};
//		int[][] start2 = {{1,2,3},{4,6,5},{8,0,7}};
//		int[][] start3 = {{1,2,3},{4,0,6},{8,7,5}};
//		
//		Board testBoard = new Board(start3);
//		
//		System.out.println(testBoard);
//		
//    	Solver s = new Solver(testBoard);
//    	System.out.println(s.moves());
//    	for (Board b : s.solution())
//    		System.out.println(b);
//    	
//    	
//    }

}
