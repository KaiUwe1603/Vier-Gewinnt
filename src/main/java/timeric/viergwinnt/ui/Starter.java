package timeric.viergwinnt.ui;

import javax.swing.JFrame;

public class Starter {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Vier Gewinnt");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1200, 800);
		mainFrame.setLocationByPlatform(true);
		
		
		mainFrame.setVisible(true);
	}

}
