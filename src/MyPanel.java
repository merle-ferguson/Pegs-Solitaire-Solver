import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int [] values = {800, 100, 750, 200, 850, 200, 700, 300, 800, 300, 900, 300, 650, 400, 750, 400, 850, 400, 950, 400, 600, 500, 700, 500, 800, 500, 900, 500, 1000, 500};
	private int holeSize = 5;
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


		Ellipse2D.Double circle = null;
		for(int x = 0; x< values.length; x+=2){
			circle = new Ellipse2D.Double(values[x], values[x+1], holeSize, holeSize);
			g2d.fill(circle);
		}
	}

}
