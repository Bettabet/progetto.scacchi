package progetto.scacchi.main;

import java.sql.SQLException;

import progetto.scacchi.giocatori.ChessPlayer;

public class Main {

	public static void main(String[] args) throws SQLException{
		
		Autenticazione autenticazione= new Autenticazione();
		
		ChessPlayer player= autenticazione.autenticazione();
		
		IscrizioneTorneo iscrizioneTorneo= new IscrizioneTorneo();
		
		iscrizioneTorneo.iscrizioneTorneo(player);	

	}

}
