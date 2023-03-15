package progetto.scacchi.tornei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class FestivalChessTournament extends ChessTournament {
	
	
    private Collection<ChessTournament> chessTournaments = new ArrayList<>();
    
    public FestivalChessTournament(String name) {
    	super(name);
    }

    protected Collection<ChessTournament> getChessTournaments(){
    	return chessTournaments;
    }
    
	@Override
	public String toString() {
		return super.toString() + " [chessTournaments= " + chessTournaments + "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FestivalChessTournament other = (FestivalChessTournament) obj;
        return Objects.equals(chessTournaments, other.chessTournaments);
    }

	public void add(ChessTournament tournament) {
        chessTournaments.add(tournament);
        chessTournaments= chessTournaments.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    public void remove(ChessTournament tournament) {
        chessTournaments.remove(tournament);
    }

}
