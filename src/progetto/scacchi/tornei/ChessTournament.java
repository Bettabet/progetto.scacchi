package progetto.scacchi.tornei;

import java.util.Objects;

public abstract class ChessTournament {
	
private final String name;
	
	public ChessTournament (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [name= " + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChessTournament other = (ChessTournament) obj;
		return Objects.equals(name, other.name);
	}

	
}
