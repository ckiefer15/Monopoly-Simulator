package sample;

/**
 * Created by ih6413pg on 11/19/17.
 */
public class Piece {

    private Square location;

    public Piece(Square location)
    {
        this.location = location;
    }

    public Square getLocation()
    {
        return location;
    }

    public void setLocation(Square location)
    {
        this.location = location;
    }
}
