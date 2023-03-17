package progetto.scacchi.main;

import java.sql.SQLException;
import java.util.Scanner;

import progetto.scacchi.giocatori.ChessPlayer;

public class GestoreGiocatori {

	public static void main(String[] args) throws SQLException{

		Autenticazione autenticazione= new Autenticazione();

		ChessPlayer player= autenticazione.autenticazione();

		Scanner scanner= new Scanner(System.in);
		String risposta= "";

		do {
			System.out.println("Cosa vuoi fare? Vuoi iscriverti o cancellarti da un torneo?(Scrivi ISCR per iscriverti,"
					+ " CANC per cancellarti)");
			do {
				risposta=scanner.nextLine();
				if (risposta.trim().equalsIgnoreCase("iscr")) {

					IscrizioneTorneo iscrizioneTorneo= new IscrizioneTorneo();

					iscrizioneTorneo.iscrizioneTorneo(player);

				}
				else if (risposta.trim().equalsIgnoreCase("canc")) {

					CancellazioneTorneo cancellazioneTorneo= new CancellazioneTorneo();

					cancellazioneTorneo.cancellazioneTorneo(player);

				}
				else {
					System.out.println("Riprova, cosa vuoi fare? (ISCR per iscriverti, CANC per cancellarti)");
				}	
			}while(!risposta.trim().equalsIgnoreCase("iscr") && !risposta.trim().equalsIgnoreCase("canc"));

			System.out.println("Vuoi fare altro? (SI/NO)");

			do {

				risposta=scanner.nextLine();

				if (risposta.trim().equalsIgnoreCase("no")) {
					break;
				}
				else if (!risposta.trim().equalsIgnoreCase("no") && !risposta.trim().equalsIgnoreCase("si")) {
					System.out.println("Riscrivi la risposta, vuoi fare altro? (SI/NO)");
				}

			}while(!risposta.trim().equalsIgnoreCase("no") && !risposta.trim().equalsIgnoreCase("si"));

		}while(risposta.trim().equalsIgnoreCase("si"));

		System.out.println("Grazie e arrivederci!");

		scanner.close();

	}

}
