/******************************************************************************
 *  Name:     D. Bartz
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:       
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 4: Slider Puzzle


/******************************************************************************
 *  Explain briefly how you implemented the Board data type.
 *****************************************************************************/
 
The board data type is very simple, consisting of nothing more than a 2D array
representing the tile layout.



/******************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 *****************************************************************************/
 
Board, moves, priority, previous search node.
It additionally implements comparable on priority.




/******************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method as function of the board size n? Recall that with order-of-growth
 *  notation, you should discard leading coefficients and lower-order terms,
 *  e.g., n log n or n^3.

 *****************************************************************************/
 
Description:

 I implemented two private methods: 
 	countInversions() & getBlankRow() 
 to test conditions as outlined on assignment page.  


Order of growth of running time:

n4?

/******************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt even if xx > yy.
 *****************************************************************************/


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt 	28			0.49		0.03
   puzzle30.txt 	30			1.11		0.03
   puzzle32.txt 	32			OutOfMem	0.24
   puzzle34.txt 	34			tooLong		0.10
   puzzle36.txt 	36						2.07
   puzzle38.txt 	38						1.29
   puzzle40.txt 	40						0.17
   puzzle42.txt 	42						4.85
   




/******************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 *****************************************************************************/

A better priority function since this will converge on the solution quicker,
simultaneously reducing the amount of memory needed and time used.




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/



/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/



/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/



/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/