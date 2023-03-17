package progetto.scacchi.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import progetto.scacchi.arbitri.ArbiterCategory;
import progetto.scacchi.arbitri.ChessArbiter;
import progetto.scacchi.giocatori.ChessPlayer;
import progetto.scacchi.tornei.BlitzChessTournament;
import progetto.scacchi.tornei.RapidChessTournament;
import progetto.scacchi.tornei.SingleChessTournament;
import progetto.scacchi.tornei.StandardChessTournament;

public class IscrizioneTorneo {

	private static final String URL= "jdbc:mysql://localhost:3306/scacchi";

	private static final String userName= "root";

	private static final String password= "Borgioli57";

	public void iscrizioneTorneo(ChessPlayer player) throws SQLException{

		System.out.println("A quale torneo vuoi iscriverti? (Indicare il nome) Lista tornei disponibili:");

		List<SingleChessTournament> tournamentsList= selectTorneo();

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
				if (checkPlayer(player, tournament)) {
					System.out.println("Sei gia' iscritto a questo torneo!");
				}
				else {
					iscrizione(player, tournament);
					System.out.println("Sei stato iscritto correttamente!");
				}
			}
			else {
				System.out.println("Torneo non presente, riprova");
			}
		}while(!flag);

	}

	protected static List<SingleChessTournament> selectTorneo() throws SQLException {

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String selectTournament= "select * from torneosingolo t join arbitro a on t.idArbitro=a.idArbitro;\n";

		ResultSet resultSet= statement.executeQuery(selectTournament);

		List<SingleChessTournament> tournamentsList= new ArrayList<>();
		SingleChessTournament tournament= null;
		ChessArbiter arbiter=null;

		while (resultSet.next()) {
			arbiter=new ChessArbiter(resultSet.getString("nome"), ArbiterCategory.valueOf(resultSet.getString("categoria")));
			if (resultSet.getInt("tempo")<=5) {
				tournament= new BlitzChessTournament(resultSet.getString("nomeTorneo"), arbiter, 
						resultSet.getInt("tempo"), resultSet.getInt("turni"));
			}
			else if(resultSet.getInt("tempo")>5 && resultSet.getInt("tempo")<60) {
				tournament= new RapidChessTournament(resultSet.getString("nomeTorneo"), arbiter, 
						resultSet.getInt("tempo"), resultSet.getInt("turni"));
			}
			else if(resultSet.getInt("tempo")>=60) {
				tournament= new StandardChessTournament(resultSet.getString("nomeTorneo"), arbiter, 
						resultSet.getInt("tempo"), resultSet.getInt("turni"));
			}
			tournamentsList.add(tournament);
		}

		resultSet.close();

		connection.close();

		return tournamentsList;

	}

	protected static boolean checkPlayer(ChessPlayer player, SingleChessTournament tournament) throws SQLException {

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String players= "select ts.nomeTorneo , cp.idFIDE , cp.nome from torneosingolo ts \r\n"
				+ "join listeGiocatori lg on ts.idTorneoSingolo = lg.idTorneoSingolo \r\n"
				+ "join chessPlayer cp on lg.idFIDE = cp.idFIDE where nomeTorneo=\"" + tournament.getName() +"\";\n";

		ResultSet resultSet= statement.executeQuery(players);
		boolean flag=false;

		while (resultSet.next()) {

			if(resultSet.getInt("idFIDE")==player.getidFIDE()) {
				flag=true;
				break;
			}

		}

		resultSet.close();

		connection.close();

		return flag;

	}

	private void iscrizione(ChessPlayer player, SingleChessTournament tournament) throws SQLException {

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String number= "select idTorneoSingolo from torneosingolo where nomeTorneo=\"" + tournament.getName() +"\";\n";

		ResultSet resultSet= statement.executeQuery(number);
		Integer numberOfTournament=0;

		while (resultSet.next()) {
			numberOfTournament=resultSet.getInt("idTorneoSingolo");
		}

		String iscrizione= "insert into listegiocatori values(" + numberOfTournament + ", " + player.getidFIDE() +");\n";

		statement.executeUpdate(iscrizione);

		resultSet.close();

		connection.close();

	}


}
