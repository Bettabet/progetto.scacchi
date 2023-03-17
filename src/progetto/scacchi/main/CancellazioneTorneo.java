package progetto.scacchi.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import progetto.scacchi.giocatori.ChessPlayer;
import progetto.scacchi.tornei.SingleChessTournament;

public class CancellazioneTorneo {

	private static final String URL= "jdbc:mysql://localhost:3306/scacchi";

	private static final String userName= "root";

	private static final String password= "Borgioli57";

	public void cancellazioneTorneo(ChessPlayer player) throws SQLException{

		System.out.println("Da quale torneo vuoi cancellarti? (Indicare il nome) Lista tornei disponibili:");

		List<SingleChessTournament> tournamentsList= IscrizioneTorneo.selectTorneo();

		tournamentsList.forEach(tournament-> System.out.println(tournament.getClass().getSimpleName() + " " + tournament.getName()
		+ ", arbitro:" + tournament.getChessArbiter().getName()+ ", tempo di riflessione:" + 
		tournament.getTimeControl() + ", numero turni:" + tournament.getRounds()));
		
		
		Scanner scanner= new Scanner(System.in);
		boolean flag=false;

		do {
			String nomeTorneo= scanner.nextLine().trim();
			Optional<SingleChessTournament> possibleTournament= tournamentsList.stream()
					.filter(tournament-> nomeTorneo.trim().equalsIgnoreCase(tournament.getName())).findFirst();
			if (possibleTournament.isPresent()){
				flag=true;
				SingleChessTournament tournament= possibleTournament.get();
				if (!IscrizioneTorneo.checkPlayer(player, tournament)) {
					System.out.println("Non sei iscritto a questo torneo!");
				}
				else {
					cancellazione(player, tournament);
					System.out.println("Sei stato cancellato correttamente!");
				}
			}
			else {
				System.out.println("Torneo non presente, riprova");
			}
		}while(!flag);
	}

	private void cancellazione(ChessPlayer player, SingleChessTournament tournament) throws SQLException {

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String number= "select idTorneoSingolo from torneosingolo where nomeTorneo=\"" + tournament.getName() +"\";\n";

		ResultSet resultSet= statement.executeQuery(number);
		Integer idTournament=0;

		while (resultSet.next()) {
			idTournament=resultSet.getInt("idTorneoSingolo");
		}

		String cancellazione= "delete from listegiocatori where idTorneoSingolo=" + idTournament + " and idFIDE=" + player.getidFIDE() +";\n";

		statement.executeUpdate(cancellazione);

		resultSet.close();

		connection.close();
	}

}
