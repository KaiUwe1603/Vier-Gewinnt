package timeric.viergwinnt.ui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class StartController {

	public JComponent getViewComponent() {
		JPanel viewComponent = new JPanel();
		
		JLabel hallo = new JLabel("Hallo Welt!");
		JLabel by = new JLabel("Tschüss!");

		viewComponent.add(hallo);
		viewComponent.add(by);
		return viewComponent;
		
	}

}
