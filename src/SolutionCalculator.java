import java.util.ArrayList;

public class SolutionCalculator {
	static int [][] nextBoard;


	public int [] calculateSolution(int [][] myboard){

		//int [][] myboard = {{1},{1,1},{1,1,1},{1,0,1,1},{1,1,1,1,1}};
		Node root = new Node(myboard, null);
		recursion(root);
		
		int moveToRow = -1;
		int moveToD1 = -1;
		int moveToD2 = -1;
		int moveFromRow= -1;
		int moveFromD1= -1;
		int moveFromD2= -1;
		int moveFromRow1= -1;
		int moveFromD11= -1;
		int moveFromD21= -1;

		if(nextBoard!=null)
		{

			for(int y = 0; y< nextBoard.length; y++){
				for(int x = 0; x<nextBoard[y].length; x++){

					if(nextBoard[y][x]!=myboard[y][x]){
						if(myboard[y][x] == 0){
							moveToRow = y;
							moveToD1 = x;
							moveToD2 = (myboard[y].length-1) - x;
						}
						if(myboard[y][x] == 1&&moveFromRow == -1){
							moveFromRow = y;
							moveFromD1 = x;
							moveFromD2 = (myboard[y].length-1) - x;

						}
						else if(myboard[y][x] == 1){
							moveFromRow1 = y;
							moveFromD11 = x;
							moveFromD21 = (myboard[y].length-1) - x;

						}
					}

				}

			}
		}
		nextBoard = null;
		int [] solutions = {moveToRow ,moveToD1,moveToD2,moveFromRow,moveFromD1,moveFromD2,moveFromRow1,moveFromD11,moveFromD21};


		return solutions;

	}


	public void recursion(Node n){


		ArrayList<Move> moves = n.legalMoves();
		int [][] pegboard = n.getPegBoard();

		if(moves.size() == 0 && n.countPegs() == 1){
			while(n.getParent().getParent() != null)
			{
				n = n.getParent();

			}
			nextBoard = n.getPegBoard();
		}
		else if (moves.size() == 0){
			
		}
		else{
			for(Move m: moves){
				int [][]tempboard = {{0},{0,0},{0,0,0},{0,0,0,0},{0,0,0,0,0}};

				for(int y = 0 ; y<pegboard.length; y++){
					for(int x = 0 ; x<pegboard[y].length; x++){
						if(pegboard[y][x] == 1){
							tempboard[y][x] = 1;
						}
					}
				}


				tempboard[m.getSY()][m.getSX()] = 0;
				tempboard[m.getEY()][m.getEX()] = 1;
				tempboard[m.getJY()][m.getJX()] = 0;
				recursion(new Node(tempboard,n));
			}
		}
	}
}
