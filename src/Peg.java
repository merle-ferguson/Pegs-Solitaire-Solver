import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;

public class Peg extends JButton {

	private static final long serialVersionUID = 1L;
	Dimension size = new Dimension(50,50);
	private Color color;
	private boolean selected = false;
	int row,d1,d2 = 0;


	public Peg(int x,int y,int z) {
		super();

		setContentAreaFilled(false);
		Random rand = new Random();
		float r = rand.nextFloat();
		//float g = rand.nextFloat();
		float b = rand.nextFloat();
		color = new Color(r,0,b);

		row = x;
		d1 = y;
		d2 = z;

	}

	protected void paintComponent(Graphics g) {
			g.setColor(color);

			g.fillOval(0, 0, size.width, 
					size.height);
	}

	protected void paintBorder(Graphics g) {
		if(selected){
			g.setColor(color.GREEN);
			for(int x = 0; x< 4; x++){
				g.drawOval(0,0,size.width+x,size.height+x);
			}
		}
	}

	public void setCurSelected(boolean b){
		selected = b;
	}
	
	
	public void changeSelected(){
		selected = !selected;
		repaint();
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

	public void setLoc(int r, int a, int b){
		row = r;
		d1 = a;
		d2 = b;
		
	}
	
	
}