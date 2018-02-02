package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DatabaseAdapter implements Storage{

    DatabaseStore databaseStorage = new DatabaseStore();
    DatabaseLoad databaseLoader = new DatabaseLoad();

    public DatabaseAdapter() throws SQLException {
    }

    public void store(String gameName, List<Player> players) throws IOException, SQLException {
        databaseStorage.setPlayerList(players);
        databaseStorage.insertPlayers(gameName);
    }


    public List<PlayerPair> load(String gameName) throws SQLException {
        return databaseLoader.loadGame(gameName);
    }


    public List<String> getGameNames(String type) throws SQLException {
        return databaseLoader.getGameNames();
    }
}
