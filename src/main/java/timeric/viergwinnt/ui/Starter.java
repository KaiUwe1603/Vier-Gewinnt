package timeric.viergwinnt.ui;

import javax.swing.JFrame;

public final class Starter {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Vier Gewinnt");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1200, 800);
		mainFrame.setLocationByPlatform(true);
		
		StartController startController = new StartController();
		mainFrame.setContentPane(startController.getViewComponent());
		
		mainFrame.setVisible(true);
	}

}
