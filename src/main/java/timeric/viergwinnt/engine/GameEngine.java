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
		GEWONNEN, ALLE_VERLOREN, NAECHSTER_SPIELER
	}


	private final Spieler[] spieler;

	private int aktuellerSpielerNum;

	private final Map<Integer, List<Chip>> spielStandMap;

	private final SpielStandPruefer spielStandPruefer;

	public GameEngine(Spielinfo spielinfo) {
		this.spieler = new Spieler[spielinfo.anzahlSpieler];
		for (int spielerNum = 0; spielerNum < spielinfo.anzahlSpieler; spielerNum++) {
			spieler[spielerNum] = new Spieler("Spieler " + (spielerNum + 1), MOEGLICHE_FARBEN[spielerNum]);
		}
		aktuellerSpielerNum = 0;
		
		this.spielStandMap = createSpielStandMap(spielinfo);
		this.spielStandPruefer = new SpielStandPruefer(spielinfo);
		
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
		SpielStand spielStand = spielStandPruefer.pruefe(spielStandMap, getAktuellerSpieler());

		if (spielStand == SpielStand.NAECHSTER_SPIELER) {
			aktuellerSpielerNum++;
			if (aktuellerSpielerNum >= spieler.length) {
				aktuellerSpielerNum = 0;
			}
		}

		return spielStand;
	}

	static class SpielStandPruefer {

		private static final int GEWINN_TREFFER = 4;
		private final Spielinfo spielinfo;

		public SpielStandPruefer(Spielinfo spielinfo) {
			this.spielinfo = spielinfo;
		}

		public SpielStand pruefe(Map<Integer, List<Chip>> spielStandMap, Spieler spielerZuPruefen) {
			boolean eventuellVerloren = pruefeSpielVerloren(spielStandMap);

			if (pruefeHorizontal(spielStandMap, spielerZuPruefen) || pruefeVertikal(spielStandMap, spielerZuPruefen)
					|| pruefeDiagonal(spielStandMap, spielerZuPruefen)) {

				return SpielStand.GEWONNEN;
			}

			return eventuellVerloren ? SpielStand.ALLE_VERLOREN : SpielStand.NAECHSTER_SPIELER;

		}


		private boolean pruefeSpielVerloren(Map<Integer, List<Chip>> spielStandMap) {
			return !spielStandMap.values().stream()
					.filter(spaltenListe -> spaltenListe.size() < spielinfo.anzahlZeilen).findAny().isPresent();
		}

		private boolean pruefeHorizontal(Map<Integer, List<Chip>> spielStandMap, Spieler spielerZuPruefen) {
			for(int zeile = 0; zeile < spielinfo.anzahlZeilen; zeile++) {
			
				int treffer = 0;
				for(int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {
					List<Chip> spaltenListe = spielStandMap.get(spalte);
					if (spaltenListe.size() <= zeile) {
						break;
					}

					if (spaltenListe.get(zeile).spieler.equals(spielerZuPruefen)) {
						treffer++;
						
					} else {
						treffer = 0;
					}
					
					
					if (treffer == GEWINN_TREFFER) {
						return true;
					}
				
				}
			}
			return false;
		}

		private boolean pruefeVertikal(Map<Integer, List<Chip>> spielStandMap, Spieler spielerZuPruefen) {
			for (int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {

				List<Chip> spaltenListe = spielStandMap.get(spalte);
				int treffer = 0;
				for (int zeile = 0; zeile < spielinfo.anzahlZeilen; zeile++) {
					if (spaltenListe.size() <= zeile) {
						break;
					}

					if (spaltenListe.get(zeile).spieler.equals(spielerZuPruefen)) {
						treffer++;

					} else {
						treffer = 0;
					}

					if (treffer == GEWINN_TREFFER) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean pruefeDiagonal(Map<Integer, List<Chip>> spielStandMap, Spieler spielerZuPruefen) {
			for (int spalte = 0; spalte < spielinfo.anzahlSpalten; spalte++) {

				List<Chip> spaltenListe = spielStandMap.get(spalte);
				for (int zeile = 0; zeile < spielinfo.anzahlZeilen; zeile++) {
					if (spaltenListe.size() <= zeile) {
						break;
					}

					if (pruefeInSpielZelle(spielStandMap, spielerZuPruefen, spalte, zeile, 0, true)
							|| pruefeInSpielZelle(spielStandMap, spielerZuPruefen, spalte, zeile, 0, false)) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean pruefeInSpielZelle(Map<Integer, List<Chip>> spielStandMap, Spieler spielerZuPruefen, int spalte,
				int zeile, int treffer, boolean aufsteigend) {

			List<Chip> spaltenListe = spielStandMap.get(spalte);
			if (spalte >= spielinfo.anzahlSpalten || spalte < 0 || zeile >= spaltenListe.size() || zeile < 0) {
				return false;
			}

			if (spaltenListe.get(zeile).spieler.equals(spielerZuPruefen)) {
				int neuTreffer = treffer + 1;
				if (neuTreffer == GEWINN_TREFFER) {
					return true;
				}

				return pruefeInSpielZelle(spielStandMap, spielerZuPruefen, ++spalte,
						aufsteigend ? ++zeile : --zeile, neuTreffer, aufsteigend);

			}

			return false;
		}

	}

}
