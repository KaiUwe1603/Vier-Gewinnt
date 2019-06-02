package timeric.viergwinnt.engine;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import timeric.viergwinnt.data.Chip;
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

	private final Map<Integer, List<Chip>> spielStandMap;

	public GameEngine(Spielinfo spielinfo) {
		this.spieler = new Spieler[spielinfo.anzahlSpieler];
		for (int spielerNum = 0; spielerNum < spielinfo.anzahlSpieler; spielerNum++) {
			spieler[spielerNum] = new Spieler("Spieler " + (spielerNum + 1), MOEGLICHE_FARBEN[spielerNum]);
		}
		aktuellerSpielerNum = 0;
		
		this.spielStandMap = createSpielStandMap(spielinfo);
		
	}

	private static Map<Integer, List<Chip>> createSpielStandMap(Spielinfo spielinfo) {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();

		for (int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {
			spielStandMap.put(Integer.valueOf(spalte), new LinkedList<Chip>());
		}

		return Collections.unmodifiableMap(spielStandMap);
	}

	public Spieler getAktuellerSpieler() {
		return spieler[aktuellerSpielerNum];
	}

	public int werfeChipEin(int zuSpielendeSpalte) {
		List<Chip> spalteMitChips = spielStandMap.get(Integer.valueOf(zuSpielendeSpalte));

		int zeile = spalteMitChips.size();
		spalteMitChips.add(new Chip(getAktuellerSpieler(), zuSpielendeSpalte, zeile));

		return zeile;
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
