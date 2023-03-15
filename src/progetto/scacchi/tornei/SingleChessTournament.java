package progetto.scacchi.tornei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;

public abstract class SingleChessTournament extends ChessTournament {
	
	private Collection<ChessPlayer> chessPlayers = new ArrayList<>();
	private ChessArbiter chessArbiter;
	private final Integer timeControl;
	private final Integer rounds;
	
	public SingleChessTournament (String name, ChessArbiter chessArbiter, Integer timeControl, Integer rounds) {
		super(name);
		this.chessArbiter= chessArbiter;
		this.timeControl= timeControl;
		this.rounds= rounds;
		if (rounds<5 || rounds >11) {
			throw new IllegalArgumentException("Un torneo deve avere minimo 5 turni di gioco e al massimo 11");
		}
	}
	
	protected Collection<ChessPlayer> getChessPlayers() {
		return chessPlayers;
	}
	
	public ChessArbiter getChessArbiter() {
		return chessArbiter;
	}
	
	public Integer getTimeControl() {
		return timeControl;
	}

	public Integer getRounds() {
		return rounds;
	}

	@Override
	public String toString() {
		return super.toString() + "[timeControl= "  + timeControl + ", chessPlayers= " + chessPlayers + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
        	return false;
        SingleChessTournament other = (SingleChessTournament) obj;
        return timeControl == other.timeControl && Objects.equals(chessPlayers, other.chessPlayers);
	}
	
	public void add(ChessPlayer player) {
		chessPlayers.add(player);
		chessPlayers=orderList(chessPlayers);
	}
	
	public void remove(ChessPlayer player) {
		chessPlayers.remove(player);
	}
	
	protected abstract Collection<ChessPlayer> orderList(Collection<ChessPlayer> chessPlayers);

}
