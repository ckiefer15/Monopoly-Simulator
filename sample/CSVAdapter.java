package sample;

import sample.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CSVAdapter implements Storage {

    CSVStorage csvStorage = new CSVStorage();
    CSVLoad csvLoader = new CSVLoad();

    public void store(String gameName, List<Player> players) throws IOException, SQLException {
        csvStorage.saveToCSV(gameName, players);
    }


    public List<PlayerPair> load(String gameName) throws SQLException {
        csvLoader.findPlayer(gameName);
        return csvLoader.getPlayerInfo();
    }


    public List<String> getGameNames(String type) throws SQLException {
        return csvLoader.getGameNames();
    }
}
