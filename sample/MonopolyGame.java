package sample;

import java.util.*;

public final class MonopolyGame extends Observable{
    private static MonopolyGame INSTANCE = new MonopolyGame();
    private static final int ROUNDS_TOTAL = 20;
    private static final int PLAYERS_TOTAL = 2;
    private List players = new ArrayList(PLAYERS_TOTAL);
    private Board board = new Board();
    private Die[] dice = { new Die(), new Die() };
    private int roundNum = 1;
    private String updateString = "";
    private MonopolyGame()
    {
        Player p;
        p = new Player("Horse", dice, board);
        players.add(p);
        p = new Player("Car", dice, board);
        players.add(p);
    }

    public static MonopolyGame getInstance()
    {
        return INSTANCE;
    }

    public void resetGame(){
        players = new ArrayList(PLAYERS_TOTAL);
        board = new Board();
        roundNum = 1;
        updateString = "";

        Player p;
        p = new Player("Horse", dice, board);
        players.add(p);
        p = new Player("Car", dice, board);
        players.add(p);

    }

    public void playGame()
    {
        for (int i = 0; i < ROUNDS_TOTAL; i++)
        {
            playRound();
            roundNum++;
        }
    }

    public List getPlayers()
    {
        return players;
    }

    private void playRound()
    {
        for (Iterator iter = players.iterator(); iter.hasNext(); )
        {
            Player player = (Player) iter.next();
            player.takeTurn();
            updatePlayerLocationImage(player);
        }
        updatePlayerLocationText();
    }


    public void createGameFromLoad(List<PlayerPair> playerInfo){
        players = new ArrayList(PLAYERS_TOTAL);
        for (int i = 0; i < playerInfo.size(); i++)
        {
            PlayerPair pair = playerInfo.get(i);
            Player player = new Player(pair.name, dice, board);
            player.setLocation(pair.position);
            updatePlayerLocationImage(player);
            players.add(player);
        }
        notifyLoad();
    }

    private void updatePlayerLocationText(){
        updateString = "--------------------------\n" +
                "Round: " + roundNum + "\n" +
                "--------------------------\n";
        for (Iterator iter = players.iterator(); iter.hasNext(); )
        {
            Player player = (Player) iter.next();
            updateString += (player.getName() + " rolled a "
                    + player.getRollTotal()
                    + " and moved to Square: "
                    + player.getLocation().getIndex() + "\n");
        }
        setChanged();
        notifyObservers(updateString);
    }
    private void updatePlayerLocationImage(Player player){
        setChanged();
        notifyObservers(player);
    }
    private void notifyLoad(){
        setChanged();
        notifyObservers("Loaded Game Complete.\n");
    }
}
