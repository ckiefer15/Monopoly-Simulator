package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLoad {
    String url = "jdbc:sqlite:/Users/we2423hd/Downloads/GameData.db";
    Connection conn = DriverManager.getConnection(url,"","");

    public DatabaseLoad() throws SQLException {
    }

    public List<PlayerPair> loadGame(String searchCriteria) throws SQLException {
        List<PlayerPair> playas = new ArrayList<PlayerPair>();
        String query = "SELECT PlayerData.PlayerName, PlayerData.PlayerLocation FROM Games JOIN PlayerData ON Games.GameID=PlayerData.GameID WHERE Games.GameName =  ?";
        try
        {

            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, searchCriteria);

            // process the results
            ResultSet rs = ps.executeQuery();
            while ( rs.next() )
            {
                String name =  ( rs.getString("PlayerName") );
                int setLocal = ( rs.getInt("PlayerLocation") );
                playas.add(new PlayerPair(name, setLocal));
            }
            rs.close();
            ps.close();
            return playas;
        }
        catch (SQLException se)
        {
            // log exception;
            throw se;
        }
    }

    public List<String> getGameNames() throws SQLException {
        List<String> games = new ArrayList<String>();
        String query = "SELECT GameName FROM Games;";
        try
        {

            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);

            // process the results
            ResultSet rs = ps.executeQuery();
            while ( rs.next() )
            {
                String name =  ( rs.getString("GameName") );
                games.add(name);
            }
            rs.close();
            ps.close();
            return games;
        }
        catch (SQLException se)
        {
            // log exception;
            throw se;
        }

    }



}


