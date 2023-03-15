package progetto.scacchi.tornei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;

public final class StandardChessTournament extends SingleChessTournament {
	
	public StandardChessTournament(String name, ChessArbiter chessArbiter, Integer timeControl, Integer rounds) {
		
		super(name, chessArbiter, timeControl, rounds);
		if (timeControl < 60) {
			throw new IllegalArgumentException("Un torneo Standard deve avere almeno 60 minuti di riflessione");
		}
	}
	
	@Override
	protected Collection<ChessPlayer> orderList(Collection<ChessPlayer> chessPlayers) {
		return chessPlayers.stream().sorted(Comparator.comparing(ChessPlayer::getStandardRating).reversed()).collect(Collectors.toCollection(ArrayList::new));
	}

}
