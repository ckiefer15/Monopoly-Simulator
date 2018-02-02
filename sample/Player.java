package sample;

import java.util.Observable;

/**
 * Created by ih6413pg on 11/16/17.
 */
public class Player extends Observable {
    private String name;
    private Piece piece;
    private Board board;
    private Die[] dice;
    private int rollTotal;
    private String updateString = "";

    public Player(String name, Die[] dice, Board board)
    {
        this.name = name;
        this.dice = dice;
        this.board = board;
        piece = new Piece(board.getStartSquare());
    }

    public void takeTurn()
    {
        // roll dice
        rollTotal = 0;
        for (int i = 0; i < dice.length; i++)
        {
            dice[i].roll();
            rollTotal += dice[i].getFaceValue();
        }

        Square newLoc = board.getSquare(piece.getLocation(), rollTotal);
        piece.setLocation(newLoc);

        updateLocation();

    }
    public void updateLocation(){
        updateString = (getName() + " moved to " + getLocation().getIndex());
        setChanged();
        notifyObservers(updateString);
    }
    public Square getLocation()
    {
        return piece.getLocation();
    }

    public void setLocation(int i)
    {
        Square newLoc = board.getSquare(i);
        this.piece.setLocation(board.getSquare(newLoc, 0));
    }

    public String getName()
    {
        return name;
    }
    public int getRollTotal(){
        return rollTotal;
    }
}
