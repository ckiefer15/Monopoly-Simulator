package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVStorage {


    File gameData = new File("/Users/we2423hd/Documents/monopoly/src/GameData.csv");

    public void saveToCSV(String gameName, List<Player> players) throws IOException {
        FileWriter fw = new FileWriter(gameData, true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Player player: players){
            bw.append(String.valueOf(gameName));
            bw.append(",");
            bw.append(player.getName());
            bw.append(",");
            bw.append(String.valueOf(player.getLocation().getIndex()));
            bw.append(System.lineSeparator());
        }
        bw.close();

    }

}
