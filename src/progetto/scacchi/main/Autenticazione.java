package progetto.scacchi.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import progetto.scacchi.giocatori.ChessPlayer;

public class Autenticazione {

	private static final String URL= "jdbc:mysql://localhost:3306/scacchi";

	private static final String userName= "root";

	private static final String password= "Borgioli57";

	public ChessPlayer autenticazione() throws SQLException{

		System.out.println("Buongiorno scacchista! Autentificati (ID FIDE)");

		List<Integer> players= selectIdPlayers();

		Scanner scanner= new Scanner(System.in);

		Integer idPlayer=0;
		ChessPlayer player=null;

		do {

			try {
				idPlayer= Integer.valueOf(scanner.nextLine().trim());
			}
			catch (NumberFormatException e) {
				System.out.println("Non hai inserito un numero!");
			}


			if (players.contains(idPlayer)) {
				player= selectPlayer(idPlayer);
				System.out.println("Benvenuto " + player.getName() + "!");
			}

			else {
				System.out.println("ID FIDE non valido, riprovare");
			}

		}while(!players.contains(idPlayer));

		return player;

	}

	private List<Integer> selectIdPlayers() throws SQLException{

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String selectPlayer= "select idFIDE from chessplayer;\n";

		ResultSet resultSet= statement.executeQuery(selectPlayer);

		List<Integer> idList= new ArrayList<>();

		while (resultSet.next()) {
			idList.add(resultSet.getInt("idFIDE"));
		}

		resultSet.close();

		connection.close();

		return idList;

	}

	private ChessPlayer selectPlayer(Integer id) throws SQLException{

		Connection connection= DriverManager.getConnection(URL, userName, password);

		Statement statement= connection.createStatement();

		String selectPlayer= "select * from chessplayer where idFIDE=" + id +";\n";

		ResultSet resultSet= statement.executeQuery(selectPlayer);

		ChessPlayer player=null;

		while (resultSet.next()) {
			player= new ChessPlayer(resultSet.getInt("IdFIDE"), resultSet.getString("nome"), 
					resultSet.getString("categoria"), resultSet.getInt("standardRating"), 
					resultSet.getInt("rapidRating"), resultSet.getInt("blitzRating"));
		}

		resultSet.close();

		connection.close();

		return player;

	}



}
