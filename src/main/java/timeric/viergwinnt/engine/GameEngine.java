package timeric.viergwinnt.engine;

import java.awt.Color;

import timeric.viergwinnt.data.Spieler;
import timeric.viergwinnt.data.Spielinfo;

public class GameEngine {

	// Kompromiss - TODO hier eine schlauere Lösung sich überlegen
	private static final Color[] MOEGLICHE_FARBEN = { Color.YELLOW, Color.RED, Color.GREEN };

	public enum SpielStand {
		GEWONNEN, ALLE_VERLOHREN, NAECHSTER_SPIELER
	}


	private final Spieler[] spieler;

	private int aktuellerSpielerNum;

	public GameEngine(Spielinfo spielinfo) {
		this.spieler = new Spieler[spielinfo.anzahlSpieler];
		for (int spielerNum = 0; spielerNum < spielinfo.anzahlSpieler; spielerNum++) {
			spieler[spielerNum] = new Spieler("Spieler " + (spielerNum + 1), MOEGLICHE_FARBEN[spielerNum]);
		}
		aktuellerSpielerNum = 0;
		
		
	}

	public Spieler getAktuellerSpieler() {
		return spieler[aktuellerSpielerNum];
	}

	public int werfeChipEin(int zuSpielendeSpalte) {
		// TODO Auto-generated method stub
		return 0;
	}

	public SpielStand pruefeSpielStand() {
		// TODO Auto-generated method stub

		aktuellerSpielerNum++;
		if (aktuellerSpielerNum >= spieler.length) {
			aktuellerSpielerNum = 0;
		}

		return SpielStand.NAECHSTER_SPIELER;
	}

}
