package progetto.scacchi.giocatori;

import java.util.Objects;

public class ChessPlayer {
	
	private final Integer idFIDE;
	private final String name;
	private String category;
	private Integer standardRating;
	private Integer rapidRating;
	private Integer blitzRating;
	
	public ChessPlayer (Integer idFIDE, String name, String category, Integer StandardRating, Integer rapidRating, Integer blitzRating) {
		this.idFIDE=idFIDE;
		this.name=name;
		this.category=category;
		this.standardRating=StandardRating;
		this.rapidRating=rapidRating;
		this.blitzRating=blitzRating;
	}
	
	public Integer getidFIDE() {
		return idFIDE;
	}
	
	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public Integer getStandardRating() {
		return standardRating;
	}

	public Integer getRapidRating() {
		return rapidRating;
	}

	public Integer getBlitzRating() {
		return blitzRating;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [idFIDE= " + idFIDE + ",name= " + name + ", category= " + category +", StandardRating= " + standardRating + 
				", RapidRating= " + rapidRating + ", BlitzRating= " + blitzRating + "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChessPlayer other = (ChessPlayer) obj;
        return idFIDE==other.idFIDE && standardRating==other.standardRating && rapidRating==other.rapidRating && 
        		blitzRating==other.blitzRating && category==other.category && Objects.equals(name, other.name);
	}
	
}
