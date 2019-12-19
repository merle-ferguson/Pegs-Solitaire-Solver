import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		Screen s = new Screen();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setExtendedState(JFrame.MAXIMIZED_BOTH);
		s.setVisible(true);

	}
}
