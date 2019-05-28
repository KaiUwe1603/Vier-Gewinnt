package timeric.viergwinnt.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
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

		centerPane.add(createButton("Einfaches Spiel", null), gbc);

		gbc.gridy = 1;
		centerPane.add(createButton("Großes Spielfeld", null), gbc);

		gbc.gridy = 2;
		centerPane.add(createButton("3 Spieler (großes Spielfeld)", null), gbc);

		gbc.gridy = 3;
		centerPane.add(createButton("Spielregeln", e -> openRuleDialog()), gbc);

		viewComponent.add(centerPane, BorderLayout.CENTER);
		return viewComponent;
		
	}

	private JButton createButton(String label, ActionListener action) {
		JButton button = new JButton(label);
		button.addActionListener(action);
		return button;
	}

	private void openRuleDialog() {
		JDialog rulesDialog = new JDialog();
		rulesDialog.setTitle("Spielregeln");
		rulesDialog.setSize(1000, 700);
		rulesDialog.setLocationByPlatform(true);

		rulesDialog.setVisible(true);
	}

}
