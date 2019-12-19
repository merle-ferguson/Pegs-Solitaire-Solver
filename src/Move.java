
public class Move {
	private int startx,starty,endx,endy,jumpx,jumpy;

	Move(int sx,int sy, int ex, int ey, int jx, int jy){
		startx= sx;
		starty = sy;
		endx=ex;
		endy=ey;
		jumpx = jx;
		jumpy = jy;
	}

	public int getSX(){
		return startx;
	}
	public int getSY(){
		return starty;
	}
	public int getEX(){
		return endx;
	}
	public int getEY(){
		return endy;
	}	
	public int getJX(){
		return jumpx;
	}
	public int getJY(){
		return jumpy;
	}
}
