package progetto.scacchi.tornei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;

public final class RapidChessTournament extends SingleChessTournament {
	
	public RapidChessTournament(String name, ChessArbiter chessArbiter, Integer timeControl, Integer rounds) {
		super(name, chessArbiter, timeControl, rounds);
		if (timeControl <= 10 || timeControl >= 60) {
			throw new IllegalArgumentException("Un torneo Rapid deve avere almeno 11 minuti di riflessione ma al massimo 59");
		}
	}
	
	@Override
	protected Collection<ChessPlayer> orderList(Collection<ChessPlayer> chessPlayers) {
		return chessPlayers.stream().sorted(Comparator.comparing(ChessPlayer::getRapidRating).reversed()).collect(Collectors.toCollection(ArrayList::new));
	}

}
