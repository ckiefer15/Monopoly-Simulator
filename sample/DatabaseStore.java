package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStore {

    List<Player> players = new ArrayList<Player>();
    String url = "jdbc:sqlite:/Users/we2423hd/Downloads/GameData.db";
    Connection conn = DriverManager.getConnection(url,"","");

    public DatabaseStore() throws SQLException {
    }

    public int getLastID(Connection conn)
            throws SQLException
    {
        String query = "SELECT * FROM Games WHERE GameID = (SELECT MAX(GAMEID) FROM Games);";
        try
        {
            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);

            // process the results
            ResultSet rs = ps.executeQuery();
            String id = rs.getString("GameID");
            rs.close();
            ps.close();
            return Integer.parseInt(id);
        }
        catch (SQLException se)
        {
            // log exception;
            throw se;
        }
    }

    public void insertPlayer(Connection conn, String playerName, int location, int gameID)
            throws SQLException
    {
        String query = "INSERT INTO PlayerData (PlayerName, PlayerLocation, PlayerID, GameID) VALUES (?, ?, NULL, ?);";
        try
        {
            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, playerName);
            ps.setInt(2, location);
            ps.setInt(3, gameID);

            // process the results
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException se)
        {
            // log exception;
            throw se;
        }
    }

    public void insertGame(Connection conn, String gameName)
            throws SQLException
    {
        String query = "INSERT INTO Games(GameID, GameName) VALUES (NULL, ?);";
        try
        {
            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, gameName);


            // process the results
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException se)
        {
            // log exception;
            throw se;
        }
    }

    public void setPlayerList(List<Player> players){
        this.players = players;
    }

    public void insertPlayers(String gameName) throws SQLException {
        insertGame(conn, gameName);
        for (Player player:players) {
            insertPlayer(conn, player.getName(),player.getLocation().getIndex(), getLastID(conn));
        }

    }
}
