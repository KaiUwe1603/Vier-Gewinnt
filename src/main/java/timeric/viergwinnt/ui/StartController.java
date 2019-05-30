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

import timeric.viergwinnt.data.Spielinfo;

public final class StartController {

	public JComponent getViewComponent() {
		JPanel viewComponent = new JPanel(new BorderLayout());
		
		JPanel centerPane = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 0, 20, 0);

		centerPane.add(createButton("Einfaches Spiel", e -> openGameDialog(new Spielinfo(2, 5, 6), "Einfaches Spiel")),
				gbc);

		gbc.gridy = 1;
		centerPane.add(
				createButton("Großes Spielfeld", e -> openGameDialog(new Spielinfo(2, 8, 9), "Großes Spielfeld")), gbc);

		gbc.gridy = 2;
		centerPane.add(
				createButton("3 Spieler (großes Spielfeld)", e -> openGameDialog(new Spielinfo(3, 8, 9), "3 Spieler")),
				gbc);

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

	private void openGameDialog(Spielinfo spielinfo, String title) {
		JDialog gameDialog = new JDialog();
		gameDialog.setModal(true);
		gameDialog.setTitle(title);
		gameDialog.setSize(1000, 700);
		gameDialog.setLocationByPlatform(true);

		GameController gameController = new GameController(spielinfo);
		gameDialog.setContentPane(gameController.getViewComponent());

		gameDialog.setVisible(true);
	}

	private void openRuleDialog() {
		JDialog rulesDialog = new JDialog();
		rulesDialog.setTitle("Spielregeln");
		rulesDialog.setSize(1000, 700);
		rulesDialog.setLocationByPlatform(true);

		rulesDialog.setVisible(true);
	}

}
