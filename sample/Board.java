package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ih6413pg on 11/19/17.
 */
public class Board {
    private static final SquareFactory SQUARE_FACTORY = SquareFactory.getInstance();
    private static final int SIZE = 40;
    private List<Square> squares = new ArrayList<Square>(SIZE);

    public Board()
    {
        buildSquares();
        linkSquares();
    }

    public Square getSquare(Square start, int distance)
    {
        int endIndex = (start.getIndex() + distance) % SIZE;
        return (Square) squares.get(endIndex);
    }

    public Square getStartSquare()
    {
        return (Square) squares.get(0);
    }

    private void buildSquares()
    {
        for (int i = 1; i <= SIZE; i++)
        {
            build(i);
        }
    }

    private void build(int i)
    {
        squares.add(SQUARE_FACTORY.createSquare(i));
    }

    public void linkSquares()
    {
        for (int i = 0; i < (SIZE - 1); i++)
        {
            link(i);
        }

        Square first = (Square) squares.get(0);
        Square last = (Square) squares.get(SIZE - 1);
        last.setNextSquare(first);
    }

    private void link(int i)
    {
        Square current = (Square) squares.get(i);
        Square next = (Square) squares.get(i + 1);
        current.setNextSquare(next);
    }

    public Square getSquare(int i)
    {
        return squares.get(i);
    }
}
