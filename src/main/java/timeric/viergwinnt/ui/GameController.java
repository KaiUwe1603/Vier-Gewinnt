package timeric.viergwinnt.ui;

import javax.swing.JComponent;
import javax.swing.JLabel;

import timeric.viergwinnt.data.Spielinfo;

public class GameController {
	private final Spielinfo spielinfo;

	public GameController(Spielinfo spielinfo) {
		this.spielinfo = spielinfo;
	}

	public JComponent getViewComponent() {
		// TODO Auto-generated method stub
		return new JLabel("Hallo wir spielen: " + spielinfo.toString());
	}

}
