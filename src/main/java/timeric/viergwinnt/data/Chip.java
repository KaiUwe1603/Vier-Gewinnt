package timeric.viergwinnt.data;

public class Chip {

	public final Spieler spieler;
	public final int spalte;
	public final int zeile;

	public Chip(Spieler spieler, int spalte, int zeile) {
		this.spieler = spieler;
		this.spalte = spalte;
		this.zeile = zeile;
	}

}
