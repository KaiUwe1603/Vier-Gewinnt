package timeric.viergwinnt.data;

import java.awt.Color;
import java.util.Objects;

public class Spieler {

	public final String name;
	public final Color farbe;

	public Spieler(String name, Color farbe) {
		this.name = name;
		this.farbe = farbe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(farbe, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Spieler)) {
			return false;
		}
		Spieler other = (Spieler) obj;
		return Objects.equals(farbe, other.farbe) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spieler [name=");
		builder.append(name);
		builder.append(", farbe=");
		builder.append(farbe);
		builder.append("]");
		return builder.toString();
	}

}
