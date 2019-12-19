import java.util.ArrayList;

public class Node {
	private int [][] pegboard;
	Node parent;
	Node(int [][]m, Node p){
		pegboard = m;	
		parent = p;
	}

	public int [][] getPegBoard(){
		return pegboard;
	}

	public ArrayList<Move> legalMoves(){
		ArrayList<Move> temp = new ArrayList<Move>();

		for(int y = 0; y < pegboard.length; y++){
			for(int x = 0; x < pegboard[y].length; x++){
				if(pegboard[y][x] == 1){
					if(inBounds(x+2, y) && pegboard[y][x+2] == 0 && pegboard[y][x+1] == 1){
						temp.add(new Move(x,y, x+2, y, x+1,y ));

					}
					if(inBounds(x+2, y+2) && pegboard[y+2][x+2] == 0 && pegboard[y+1][x+1] == 1){
						temp.add(new Move(x,y, x+2, y+2, x+ 1, y+1));

					}
					if(inBounds(x, y+2) && pegboard[y+2][x] == 0 && pegboard[y+1][x] == 1){
						temp.add(new Move(x,y, x, y+2, x, y+1));

					}
					if(inBounds(x-2, y) && pegboard[y][x-2] == 0 && pegboard[y][x-1] == 1){
						temp.add(new Move(x,y, x-2, y, x-1, y));

					}
					if(inBounds(x-2, y-2) && pegboard[y-2][x-2] == 0 && pegboard[y-1][x-1] == 1){
						temp.add(new Move(x,y, x-2, y-2, x-1, y-1));
						
					}
					if(inBounds(x, y-2) && pegboard[y-2][x] == 0 && pegboard[y-1][x] == 1){
						temp.add(new Move(x,y, x, y-2, x, y-1));
						
					}
				}
			}
		}
		return temp;

	}
	private boolean inBounds(int x, int y){
		if(x>=0 && y >= 0 && x <= 4 && y <=4 && x <= y){
			return true;
		}
		return false;
	}
	
	public int countPegs(){
		int count = 0;
		for(int x = 0; x< pegboard.length; x++){
			for(int y = 0; y< pegboard[x].length; y++){
				if(pegboard[x][y] == 1){
					count ++;
				}
			}
		}
		return count;
	}
	
	public void printboard(){
		for(int y = 0; y< pegboard.length; y++){
			for(int x = 0; x<pegboard[y].length; x++){
				System.out.print(pegboard[y][x]);
			}
			System.out.println();
		}
	}
	
	public Node getParent(){
		return parent;
	}
}
