package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Storage {

    /*
    store a game simulation
     */
    void store(String gameName, List<Player> players) throws IOException, SQLException;

    /*
    load a previously stored game
     */
    List<PlayerPair> load(String gameName) throws SQLException;

    /*
    Loads game names from both database and csv to populate the view in gui
     */

    List<String> getGameNames(String type) throws SQLException;
}
