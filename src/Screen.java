import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JTextField;
public class Screen extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Peg [] pegboard = new Peg [15];
	private Hole[] holeArray = new Hole[15];
	private int [] values = {800, 100, 750, 200, 850, 200, 700, 300, 800, 300, 900, 300, 650, 400, 750, 400, 850, 400, 950, 400, 600, 500, 700, 500, 800, 500, 900, 500, 1000, 500};
	private int [][] values2 = {{0,0,0},{1,0,1},{1,1,0},{2,0,2},{2,1,1},{2,2,0},{3,0,3},{3,1,2},{3,2,1},{3,3,0},{4,0,4},{4,1,3},{4,2,2},{4,3,1},{4,4,0}};
	private JTextField text;

	MyPanel mainPanel;
	private Peg curSelected = null;
	State currentState = null;
	JButton nextMove = null;
	JButton restart = null;

	public Screen(){
		super("banana");
		setLayout(new GridLayout());

		mainPanel = new MyPanel();
		mainPanel.setLayout(null);

		add(mainPanel);
		mainPanel.setBackground(Color.white);
		makeButtons();
		currentState = State.BEGINNING;

	}

	public void makeButtons(){
		int counter = 0;
		for(int x = 0; x< values.length; x+=2){

			int a = values2[counter][0];
			int b = values2[counter][1];
			int c = values2[counter][2];

			pegboard[counter] = new Peg(a,b,c);
			pegboard[counter].addActionListener(this);
			mainPanel.add(pegboard[counter]);
			pegboard[counter].setBounds(values[x]-23,values[x+1]-23, 50, 50);

			mainPanel.setComponentZOrder(pegboard[counter], 0);

			holeArray[counter] = new Hole(a,b,c);
			holeArray[counter].addActionListener(this);
			mainPanel.add(holeArray[counter]);
			holeArray[counter].setBounds(values[x]-23,values[x+1]-23, 50, 50);

			mainPanel.setComponentZOrder(holeArray[counter], 1);

			counter++;
		}

		nextMove = new JButton("Help");
		nextMove.setBounds(750,0,100,50);
		nextMove.addActionListener(this);
		mainPanel.add(nextMove);
		nextMove.setVisible(false);
		
		restart = new JButton("Restart");
		restart.setBounds(850,0,100,50);
		restart.addActionListener(this);
		mainPanel.add(restart);
		restart.setVisible(false);
		
		text = new JTextField("There exists a solution");
		text.setBounds(750,700,300,50);
		text.setVisible(false);
		text.setBorder(null);
		mainPanel.add(text);

	}

	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();

		if(currentState == State.BEGINNING){
			if(src instanceof Peg){
				Peg p = ((Peg) src);
				Hole h = getHole(p.getRow(), p.getD1(), p.getD2());
				h.setFilled(false);
				mainPanel.remove(p);
				pegboard[getIndex(((Peg)src))] = null;
				changeStateMiddle();
			}
			else if(src instanceof Hole){
				System.out.println("FUCK");
			}
		}
		else if(currentState == State.MIDDLE){
			if(src instanceof Peg){
				changeSelected((Peg)src);
				
			}
			else if (src instanceof Hole){

				Hole h = (Hole)src;
				if(curSelected!=null&&isLegalMove(curSelected,h)){
					doMove(curSelected,h);
				}
			}
			else if(src == nextMove){
				computerMakeNextMove();
			}
			else if(src == restart){
				restartGame();
			}

		}


	}

	public void changeSelected(Peg p){
		if(curSelected!=null){
			curSelected.changeSelected();
		}
		p.changeSelected();
		curSelected = p;
	}

	public void restartGame(){
		setCurSelectedFalse();

		mainPanel.removeAll();
		
		pegboard = new Peg [15];
		holeArray = new Hole[15];
		text = null;
		curSelected = null;
		currentState = null;
		nextMove = null;
		restart = null;

		makeButtons();
		currentState = State.BEGINNING;
	
	}
	
	public void setCurSelectedFalse(){
		for(int x = 0; x< pegboard.length;x++){
			Peg p = pegboard[x];
			if(p!=null){
				p.setCurSelected(false);
				
			}
		}
		curSelected = null;
	}

	public void changeStateMiddle(){
		currentState = State.MIDDLE;
		nextMove.setVisible(true);
		text.setVisible(true);
		restart.setVisible(true);
	}

	public boolean isLegalMove(Peg a, Hole b){

		int dif1 = Math.abs(a.getRow() - b.getRow());
		int dif2 = Math.abs(a.getD1() - b.getD1());
		int dif3 = Math.abs(a.getD2() - b.getD2());

		if((dif1 == 0 && dif2 == 2 && dif3 == 2)||(dif1 == 2 && dif2 == 0 && dif3 == 2) || (dif1 == 2 && dif2 == 2 && dif3 == 0))
		{
			Hole middle = getHole((a.getRow()+b.getRow())/2,(a.getD1()+b.getD1())/2, (a.getD2()+b.getD2())/2 );
			if(middle.isFilled())
				return true;

		}
		return false;
	}

	public void doMove(Peg a, Hole b){
		Peg jump = getPeg((a.getRow()+b.getRow())/2, (a.getD1()+b.getD1())/2, (a.getD2()+b.getD2())/2);

		Hole i = getHole(a.getRow(),a.getD1(),a.getD2());
		i.setFilled(false);

		a.setLocation(b.getLocation());
		a.setLoc(b.getRow(), b.getD1(), b.getD2());
		mainPanel.setComponentZOrder(a, 0);
		
		Hole j = getHole(b.getRow(), b.getD1(),b.getD2());
		j.setFilled(true);

		Hole h = getHole(jump.getRow(),jump.getD1(), jump.getD2());
		h.setFilled(false);
		
		pegboard[getIndex(jump)] = null;
		mainPanel.remove(jump);
		
		setCurSelectedFalse();

		int [][] myboard = {{0},{1,2},{3,4,5},{6,7,8,9},{10,11,12,13,14}};
		int counter = 0;
		for(int y = 0 ; y<myboard.length; y++){
			for(int x = 0 ; x<myboard[y].length; x++){
				if(holeArray[counter].isFilled()){
					myboard[y][x] = 1;

				}
				else{
					myboard[y][x] = 0;
				}
				counter++;
			}
		}

		SolutionCalculator sc = new SolutionCalculator();
		int [] answer = sc.calculateSolution(myboard);

		for(int x=0; x<9;x++){
			System.out.print(answer[x]);
		}
		System.out.println();
		for(int x = 0; x< 9; x++)
		{
			if(answer[x]==-1){
				text.setText("There is no solution");
				text.repaint();
				break;
			}
		}
	}

	public Peg getPeg(int row, int d1, int d2){
		for(int x = 0; x< pegboard.length; x++){

			Peg p = pegboard[x];
			if(p!=null&&p.getRow()==row && p.getD1()==d1&&p.getD2()==d2)
				return p;
		}
		return null;
	}

	public Hole getHole(int row, int d1, int d2){
		for(int x = 0; x< holeArray.length; x++){
			Hole p = holeArray[x];
			if(p!=null&&p.getRow()==row && p.getD1()==d1&&p.getD2()==d2)
				return p;
		}
		return null;
	}

	public int getIndex(Peg p){
		for(int x = 0; x< pegboard.length; x++){
			if(pegboard[x] == p){
				return x;
			}
		}
		return -1;
	}

	public void computerMakeNextMove(){
		int [][] myboard = {{0},{1,2},{3,4,5},{6,7,8,9},{10,11,12,13,14}};
		int counter = 0;
		for(int y = 0 ; y<myboard.length; y++){
			for(int x = 0 ; x<myboard[y].length; x++){
				if(holeArray[counter].isFilled()){
					myboard[y][x] = 1;

				}
				else{
					myboard[y][x] = 0;
				}
				counter++;
			}
		}

		SolutionCalculator sc = new SolutionCalculator();
		int [] answer = sc.calculateSolution(myboard);

		Hole a = getHole(answer[0],answer[1],answer[2]);
		Peg b = getPeg(answer[3],answer[4],answer[5]);
		Peg c = getPeg(answer[6],answer[7],answer[8]);

		if(b!=null&&isLegalMove(b,a)){
			doMove(b,a);
		}
		else if(c!=null&&isLegalMove(c,a)){
			doMove(c,a);
		}
		else{
			text.setText("There is no solution");
			text.repaint();
		}

	}

	public enum State{
		BEGINNING,
		MIDDLE,
		END
	}
}
