package progetto.scacchi.tornei;

import progetto.scacchi.arbitri.ArbiterCategory;
import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;

public class Main {

	public static void main(String[] args) {
		
		ChessArbiter Bartolini= new ChessArbiter("Leonardo Bartolini", ArbiterCategory.AI);
	
		SingleChessTournament tournament1= new StandardChessTournament("Florence Move", Bartolini, 90, 11);
		
		SingleChessTournament tournament2= new BlitzChessTournament("Lampoween", Bartolini, 5, 9);
		
		ChessPlayer Bettazzi= new ChessPlayer(886815, "Daniele Bettazzi", "CM", 2003, 1958, 1879);
		
		ChessPlayer Caprino= new ChessPlayer(841323, "Marco Caprino", "M", 2064, 2052, 1960);
		
		ChessPlayer DeFilomeno= new ChessPlayer(828688, "Simone De Filomeno", "IM", 2450, 2469, 2273);
		
		ChessPlayer Gabbani= new ChessPlayer(2842853, "Sara Gabbani", "1N", 1920, 1818, 1844);
		
		tournament1.add(Bettazzi);
		tournament1.add(Caprino);
		tournament1.add(DeFilomeno);
		tournament1.add(Gabbani);
		
		tournament2.add(Bettazzi);
		tournament2.add(Caprino);
		tournament2.add(DeFilomeno);
		tournament2.add(Gabbani);
		
		System.out.println(tournament1.toString());
		
		tournament1.remove(Caprino);
		
		System.out.println(tournament1.toString());
		
        System.out.println(tournament2.toString());
		
		tournament2.remove(Caprino);
		
		System.out.println(tournament2.toString());
		
		System.out.println(Bettazzi.getCategory());
		
		
	}

}
