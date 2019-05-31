package timeric.viergwinnt.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import timeric.viergwinnt.data.Spielinfo;

public class GameController {

	private final Spielinfo spielinfo;

	private final Map<Integer, JComponent[]> spielFelderMap;
	private final List<JButton> spielControllerButtons;

	public GameController(Spielinfo spielinfo) {
		this.spielinfo = spielinfo;

		this.spielFelderMap = new HashMap<>();
		this.spielControllerButtons = new LinkedList<>();
	}

	public JComponent getViewComponent() {
		JPanel viewComponent = new JPanel(new BorderLayout());


		viewComponent.add(createSpielFeld(), BorderLayout.CENTER);

		return viewComponent;
	}

	private JComponent createSpielFeld() {
		JPanel spielFeldPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 30, 10);
		gbc.gridy = 0;

		setAddCoinButtons(spielFeldPanel, gbc);
		gbc.gridy = gbc.gridy + 1;
		final int currentY = gbc.gridy;
		
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.fill = GridBagConstraints.BOTH;
		for (int i = 0; i < spielinfo.anzahlSpalten; i++) {
			gbc.gridx = i;
			gbc.gridy = currentY;

			for (int j = 0; j < spielinfo.anzahlZeilen; j++) {


				JComponent spielZelle = new JPanel();
				spielZelle.setMinimumSize(new Dimension(50, 50));
				spielZelle.setBorder(BorderFactory.createLineBorder(Color.black));

				spielFeldPanel.add(spielZelle, gbc);

				gbc.gridy = gbc.gridy + 1;
//				spielFelderMap.

			}


		}

		return spielFeldPanel;
	}

	private void setAddCoinButtons(JPanel spielFeldPanel, GridBagConstraints gbc) {
		for (int i = 0; i < spielinfo.anzahlSpalten; i++) {
			gbc.gridx = i;

			JButton spielControllerButton = new JButton(Integer.valueOf(i + 1).toString());
			spielControllerButton.addActionListener(this::addCoinAction);

			spielControllerButtons.add(spielControllerButton);
			spielFeldPanel.add(spielControllerButton, gbc);

		}
	}

	private void addCoinAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
