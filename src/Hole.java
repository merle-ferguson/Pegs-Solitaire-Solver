import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

public class Hole extends JButton {

	private static final long serialVersionUID = 1L;
	Dimension size = new Dimension(50,50);
	private boolean filled = true;
	int row,d1,d2 = 0;

	public Hole(int x,int y,int z) {
		super();
		setContentAreaFilled(false);
		row = x;
		d1 = y;
		d2 = z;
	}

	protected void paintComponent(Graphics g) {

	}

	protected void paintBorder(Graphics g) {
		
	}

	public void setFilled(boolean s){
		filled = s;
	}

	public int getRow(){
		return row;

	}
	public int getD1(){
		return d1;
	}
	public int getD2(){
		return d2;


	}
	public boolean isFilled(){
		return filled;
	}
	
	
}