package timeric.viergwinnt.data;

public final class Spielinfo {
	public final int anzahlSpieler;
	public final int anzahlZeilen;
	public final int anzahlSpalten;

	public Spielinfo(int anzahlSpieler, int anzahlZeilen, int anzahlSpalten) {
		this.anzahlSpieler = anzahlSpieler;
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spielinfo [anzahlSpieler=");
		builder.append(anzahlSpieler);
		builder.append(", anzahlZeilen=");
		builder.append(anzahlZeilen);
		builder.append(", anzahlSpalten=");
		builder.append(anzahlSpalten);
		builder.append("]");
		return builder.toString();
	}

}

