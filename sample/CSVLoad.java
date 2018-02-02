package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVLoad {

    File gameData = new File("/Users/we2423hd/Documents/monopoly/src/GameData.csv");
    List<PlayerPair> playerInfo = new ArrayList<PlayerPair>();
    List<String> gameNames = new ArrayList<String>();
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";


    private void buildPlayer(String name, int position){
        playerInfo.add(new PlayerPair(name, position));
    }

    public void findPlayer(String gameName) {
            try (BufferedReader br = new BufferedReader(new FileReader(gameData))) {

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] gameData = line.split(cvsSplitBy);

                    if (gameData[0].equals(gameName)) {

                        playerInfo.add(new PlayerPair(gameData[1], Integer.parseInt(gameData[2])));
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public List<PlayerPair> getPlayerInfo(){
        return playerInfo;
    }

    public List<String> getGameNames(){
        String gameName = "";
        try (BufferedReader br = new BufferedReader(new FileReader(gameData))) {

            while ((line = br.readLine()) != null) {
                String[] gameData = line.split(cvsSplitBy);
                gameName = gameData[0];
                if (gameData[0].equals(gameName)) {
                    if(!(gameNames.contains(gameName))){
                        gameNames.add(gameName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameNames;
    }

}
