package timeric.viergwinnt.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public final class StartController {

	public JComponent getViewComponent() {
		JPanel viewComponent = new JPanel(new BorderLayout());
		
		JPanel centerPane = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 0, 20, 0);

		centerPane.add(new JButton("Einfaches Spiel"), gbc);

		gbc.gridy = 1;
		centerPane.add(new JButton("Großes Spielfeld"), gbc);

		gbc.gridy = 2;
		centerPane.add(new JButton("3 Spieler (großes Spielfeld)"), gbc);

		gbc.gridy = 3;
		centerPane.add(new JButton("Spielregeln"), gbc);

		viewComponent.add(centerPane, BorderLayout.CENTER);
		return viewComponent;
		
	}

}
