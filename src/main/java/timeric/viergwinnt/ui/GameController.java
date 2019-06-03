package timeric.viergwinnt.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeric.viergwinnt.data.Spielinfo;
import timeric.viergwinnt.engine.GameEngine;

public class GameController {

	private final Spielinfo spielinfo;
	private final GameEngine gameEngine;

	private final Map<Integer, JComponent[]> spielZellenMap;

	private final List<JButton> spielControllerButtons;

	private final JPanel spielerAnzeigePanel = new JPanel();
	private final JPanel footerPanel = new JPanel(new BorderLayout());
	private final JLabel anzeigeAktuellerSpielerLabel = new JLabel();
	private final JLabel ergebnisLabel = new JLabel();

	private JPanel viewComponent;


	public GameController(Spielinfo spielinfo) {
		this.spielinfo = spielinfo;
		this.gameEngine = new GameEngine(spielinfo);

		this.spielZellenMap = createSpielZellenMap(spielinfo);

		this.spielControllerButtons = new LinkedList<>();
	}

	private static Map<Integer, JComponent[]> createSpielZellenMap(Spielinfo spielinfo) {
		Map<Integer, JComponent[]> spielZellenMap = new HashMap<>();
		for (int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {
			spielZellenMap.put(Integer.valueOf(spalte), new JComponent[spielinfo.anzahlZeilen]);

		}
		return Collections.unmodifiableMap(spielZellenMap);
	}

	public JComponent getViewComponent() {
		if (viewComponent == null) {
			viewComponent = new JPanel(new BorderLayout());

			spielerAnzeigePanel.setBackground(gameEngine.getAktuellerSpieler().farbe);
			spielerAnzeigePanel.add(new JLabel("Aktueller Spieler: "));
			spielerAnzeigePanel.add(anzeigeAktuellerSpielerLabel);// TODO Auto-generated method
			viewComponent.add(spielerAnzeigePanel, BorderLayout.NORTH);

			anzeigeAktuellerSpielerLabel.setFont(anzeigeAktuellerSpielerLabel.getFont().deriveFont(32f));
			anzeigeAktuellerSpielerLabel.setText(gameEngine.getAktuellerSpieler().name);

			ergebnisLabel.setFont(ergebnisLabel.getFont().deriveFont(32f));

			viewComponent.add(createSpielFeld(), BorderLayout.CENTER);

			JButton openRulesDialogButton = new JButton("Spielregeln");
			openRulesDialogButton.addActionListener(e -> RulesDialog.openRulesDialog());

			JPanel footerButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			footerButtonPanel.add(openRulesDialogButton);


			footerPanel.add(footerButtonPanel, BorderLayout.EAST);
			footerPanel.add(ergebnisLabel, BorderLayout.CENTER);
			footerPanel.addPropertyChangeListener(evt -> {
				if (evt.getPropertyName().equals("background")) {
					footerButtonPanel.setBackground(footerPanel.getBackground());
				}

			});

			viewComponent.add(footerPanel, BorderLayout.SOUTH);
		}
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
		for (int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {
			gbc.gridx = spalte;
			gbc.gridy = currentY;

			for (int zeile = spielinfo.anzahlZeilen - 1; zeile >= 0; zeile--) {// TODO Auto-generated method


				JComponent spielZelle = new JPanel();
				spielZelle.setBorder(BorderFactory.createLineBorder(Color.black));

				spielFeldPanel.add(spielZelle, gbc);

				gbc.gridy = gbc.gridy + 1;
				spielZellenMap.get(Integer.valueOf(spalte))[zeile] = spielZelle;

			}


		}

		spielFeldPanel.setBackground(Starter.GAME_BACKGROUND);
		return spielFeldPanel;
	}


	private void setAddCoinButtons(JPanel spielFeldPanel, GridBagConstraints gbc) {
		for (int i = 0; i < spielinfo.anzahlSpalten; i++) {
			gbc.gridx = i;

			JButton spielControllerButton = new JButton(Integer.valueOf(i + 1).toString());
			spielControllerButton.setPreferredSize(new Dimension(50, 50));
			spielControllerButton.addActionListener(this::addCoinAction);
			spielControllerButton.setBackground(gameEngine.getAktuellerSpieler().farbe);

			spielControllerButtons.add(spielControllerButton);
			spielFeldPanel.add(spielControllerButton, gbc);

		}
	}

	private void addCoinAction(ActionEvent e) {
		int zuSpielendeSpalte = spielControllerButtons.indexOf(e.getSource());
		int chipInZeile = gameEngine.werfeChipEin(zuSpielendeSpalte);

		spielZellenMap.get(zuSpielendeSpalte)[chipInZeile].setBackground(gameEngine.getAktuellerSpieler().farbe);

		if (chipInZeile >= spielinfo.anzahlZeilen - 1) {
			((JButton) e.getSource()).setEnabled(false);
			((JButton) e.getSource()).setBackground(Color.LIGHT_GRAY);
		}

		updateSpielFeld();

	}

	private void updateSpielFeld() {
		switch (gameEngine.pruefeSpielStand()) {
		case GEWONNEN:
			footerPanel.setBackground(gameEngine.getAktuellerSpieler().farbe);
			spielEnde("Gewonnen hat: " + gameEngine.getAktuellerSpieler().name);
				break;
		
		case ALLE_VERLOREN:
			spielEnde("Leider haben alle verloren");
				break;

		case NAECHSTER_SPIELER:
			spielerAnzeigePanel.setBackground(gameEngine.getAktuellerSpieler().farbe);
			anzeigeAktuellerSpielerLabel.setText(gameEngine.getAktuellerSpieler().name);
			spielControllerButtons.stream().filter(button -> button.isEnabled())
					.forEach(button -> button.setBackground(gameEngine.getAktuellerSpieler().farbe));
		
		}
		
	}

	private void spielEnde(String spielErgebnisMsg) {
		ergebnisLabel.setText(spielErgebnisMsg);

		spielControllerButtons.forEach(button -> button.setEnabled(false));
	}

}
