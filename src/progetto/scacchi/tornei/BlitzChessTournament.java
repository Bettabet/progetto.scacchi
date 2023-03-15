package progetto.scacchi.tornei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;

public final class BlitzChessTournament extends SingleChessTournament {
	
	public BlitzChessTournament(String name, ChessArbiter chessArbiter, Integer timeControl, Integer rounds) {
		super(name, chessArbiter, timeControl, rounds);
		if (timeControl > 10) {
			throw new IllegalArgumentException("Un torneo Blitz deve avere al massimo 10 minuti di riflessione");
		}
	}
	
	@Override
	protected Collection<ChessPlayer> orderList(Collection<ChessPlayer> chessPlayers) {
		return chessPlayers.stream().sorted(Comparator.comparing(ChessPlayer::getBlitzRating).reversed()).collect(Collectors.toCollection(ArrayList::new));
	}

}
