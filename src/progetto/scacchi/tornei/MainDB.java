package progetto.scacchi.tornei;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import progetto.scacchi.giocatori.ChessPlayer;

public class MainDB {
	
    private static final String URL= "jdbc:mysql://localhost:3306/scacchi";
	
	private static final String userName= "root";
	
	private static final String password= "Borgioli57";

	public static void main(String[] args) throws SQLException, IOException{
		
		    String stringaAllPlayers = new String(Files.readAllBytes(Paths.get("allPlayers.txt")), StandardCharsets.UTF_8);
			
			String[] stringhe=stringaAllPlayers.split("[;\n]");
			
			List<String> stringheList= new ArrayList<>();
			
			Collections.addAll(stringheList, stringhe);
			
			List<String> nomiList= new ArrayList<>();
			List<String> idFIDEList= new ArrayList<>();
			List<String> categorieList= new ArrayList<>();
			List<String> standardList= new ArrayList<>();
			List<String> rapidList= new ArrayList<>();
			List<String> blitzList= new ArrayList<>();
			int id=41;
			int cat=35;
			int elostd=42;
			int elorapid=45;
			int eloblitz=48;
			
			for (int i=33; i<stringheList.size(); i=i+33) {
				nomiList.add(stringheList.get(i));
				idFIDEList.add(stringheList.get(id));
				categorieList.add(stringheList.get(cat));
				standardList.add(stringheList.get(elostd));
				rapidList.add(stringheList.get(elorapid));
				blitzList.add(stringheList.get(eloblitz));
				id+=33;
				cat+=33;
				elostd+=33;
				elorapid+=33;
				eloblitz+=33;
			}

			List<Integer> bothOfIdAndCat= new ArrayList<>();
			
			for (int i=0; i<idFIDEList.size(); i++) {
				
				if (!categorieList.get(i).equals("") && !idFIDEList.get(i).equals("")) {
					bothOfIdAndCat.add(i);
				}
			}
			
			List<String> newidList= new ArrayList<>();
			List<String> newnomiList= new ArrayList<>();
			List<String> newcategorieList= new ArrayList<>();
			List<String> newStdEloList= new ArrayList<>();
			List<String> newRapidEloList= new ArrayList<>();
			List<String> newBlitzEloList= new ArrayList<>();
			
			for (int i=0; i<bothOfIdAndCat.size(); i++) {
					newidList.add(idFIDEList.get(bothOfIdAndCat.get(i)));
					newnomiList.add(nomiList.get(bothOfIdAndCat.get(i)));
					newcategorieList.add(categorieList.get(bothOfIdAndCat.get(i)));
					newStdEloList.add(standardList.get(bothOfIdAndCat.get(i)));
					newRapidEloList.add(rapidList.get(bothOfIdAndCat.get(i)));
					newBlitzEloList.add(blitzList.get(bothOfIdAndCat.get(i)));
			}		
			
		
		/*createPlayersTable();
		
		ChessPlayer Bettazzi= new ChessPlayer(886815, "Daniele Bettazzi", PlayerCategory.CM, 2003, 1958, 1879);
		
        ChessPlayer Caprino= new ChessPlayer(841323, "Marco Caprino", PlayerCategory.M, 2064, 2052, 1960);
		
		ChessPlayer DeFilomeno= new ChessPlayer(828688, "Simone De Filomeno", PlayerCategory.IM, 2450, 2469, 2273);
		
		ChessPlayer Gabbani= new ChessPlayer(2842853, "Sara Gabbani", PlayerCategory.N1, 1920, 1818, 1844);
		
		addPlayer(Bettazzi);
		
		addPlayer(Caprino);
		
		addPlayer(DeFilomeno);
		
		addPlayer(Gabbani);
		
		addPlayer(new ChessPlayer(2860851, "Alberto Centamore", PlayerCategory.CM, 2022, 2027, 2006));*/
		
		//ChessPlayer player= new ChessPlayer(111111, "Pippo", "NC", 1200, 1250, 1300);
		
		//ChessPlayer player2= new ChessPlayer(222222, "Pluto", "CM", 2030, 2090, 2040);
		
		//ChessPlayer player3= new ChessPlayer(333333, "Paperino", "FM", 2340, 2290, 2310);
		
		//ChessPlayer player4= new ChessPlayer(444444, "Minnie", "2N", 1650, 1710, 1590);
		
		//Collection<ChessPlayer> collection= new ArrayList<>();
		
		//collection.add(player);
		//collection.add(player2);
		//collection.add(player3);
		//collection.add(player4);
		
		//addPlayersWithBatch(collection);
		
		//addPlayerWithPrepareStatement(player);
		
		Collection<ChessPlayer> players= creaPlayer(newidList, newnomiList, newcategorieList, newStdEloList, newRapidEloList, newBlitzEloList);

		addPlayersWithBatch(players);

	}
	
	public static void createPlayersTable() throws SQLException{
		
        Connection connection= DriverManager.getConnection(URL, userName, password);
		
		Statement statement= connection.createStatement();
		
		String createTable= "create table if not exists chessPlayer(\n" + 
				"idFIDE int not null primary key,\n" + 
				"name varchar(100) not null,\n" + 
				"category varchar(3) not null,\n" + 
				"standardRating int,\n" + 
				"rapidRating int,\n" + 
				"blitzRating int\n" + 
				")engine=innodb;\n";
		
		statement.executeUpdate(createTable);
		
		connection.close();
		
		System.out.println("Tabella chessPlayer creata correttamente");
		
	}
	
	public static void addPlayer(ChessPlayer chessPlayer) throws SQLException{
		
        Connection connection= DriverManager.getConnection(URL, userName, password);
		
		Statement statement= connection.createStatement();
		
		String addPlayer= "insert into chessPlayer values\n" +
				"(" + chessPlayer.getidFIDE() + ", " +
				"\"" + chessPlayer.getName() + "\", " +
				"\"" + chessPlayer.getCategory() +"\", " +
				chessPlayer. getStandardRating() +", " +
				chessPlayer.getRapidRating() +", " +
				chessPlayer.getBlitzRating() +")";
 		
		statement.executeUpdate(addPlayer);
		
		connection.close();
		
		System.out.println("Giocatore " + chessPlayer.getName() + " inserito correttamente");
				
	}
	
	public static void addPlayerWithPrepareStatement(ChessPlayer player) throws SQLException {
		
        Connection connection= DriverManager.getConnection(URL, userName, password);
		
		PreparedStatement pStatement= connection.prepareStatement("insert into chessPlayer(idFIDE,nome,categoria,"
				+ "standardRating, rapidRating, blitzRating) values(?,?,?,?,?,?)");
		
		pStatement.setInt(1, player.getidFIDE());
		pStatement.setString(2, player.getName());
		pStatement.setString(3, player.getCategory());
		pStatement.setInt(4, player.getStandardRating());
		pStatement.setInt(5, player.getRapidRating());
		pStatement.setInt(6, player.getBlitzRating());
		
		pStatement.execute();
		
		connection.close();
		
		System.out.println("Giocatore " + player.getName() + " inserito correttamente");
		
	}
	
	public static void addPlayersWithBatch(Collection<ChessPlayer> listPlayers) throws SQLException{
		
		Connection connection= DriverManager.getConnection(URL, userName, password);
		
		PreparedStatement pStatement= connection.prepareStatement("insert into chessPlayer(idFIDE,nome,categoria,"
				+ "standardRating, rapidRating, blitzRating) values(?,?,?,?,?,?)");
		
		Iterator<ChessPlayer> iterator= listPlayers.iterator();
		
		while(iterator.hasNext()){
			
			ChessPlayer player= iterator.next();
			
			pStatement.setInt(1, player.getidFIDE());
			pStatement.setString(2, player.getName());
			pStatement.setString(3, player.getCategory());
			pStatement.setInt(4, player.getStandardRating());
			pStatement.setInt(5, player.getRapidRating());
			pStatement.setInt(6, player.getBlitzRating());
			pStatement.addBatch();
		}
		
		pStatement.executeBatch();
		
		connection.close();
		
		System.out.println("Giocatori inseriti correttamente");
		
	}
	
	public static List<ChessPlayer> creaPlayer (List<String> newidList, List<String> newnomiList, List<String> newcategorieList, 
			List<String> newStdEloList, List<String> newRapidEloList, List<String> newBlitzEloList) {
		
		List<ChessPlayer> players= new ArrayList<>();
		
		for (int i=0; i<newidList.size(); i++) {
			ChessPlayer player= new ChessPlayer(Integer.parseInt(newidList.get(i).trim()), newnomiList.get(i).toUpperCase(), 
					newcategorieList.get(i), Integer.parseInt(newStdEloList.get(i)),
					Integer.parseInt(newRapidEloList.get(i)), Integer.parseInt(newBlitzEloList.get(i)));
			
			players.add(player);
			
		}
		System.out.println("ciao");
		
		System.out.println("ciao");
		
		return players;
		
	}

}
