package progetto.scacchi.arbitri;

import java.util.Objects;

public class ChessArbiter {
	
	private final String name;
	private ArbiterCategory category;
	
	public ChessArbiter (String name, ArbiterCategory category) {
		this.name= name;
		this.category= category;
	}

	public String getName() {
		return name;
	}

	public ArbiterCategory getCategory() {
		return category;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [name= " + name + ", category= " + category + "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChessArbiter other = (ChessArbiter) obj;
        return category==other.category && Objects.equals(name, other.name);
	}

}
