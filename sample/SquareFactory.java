package sample;

/**
 * Created by ih6413pg on 12/3/17.
 */

public class SquareFactory {

    private static final SquareFactory INSTANCE = new SquareFactory();

    private SquareFactory()
    {

    }

    public static SquareFactory getInstance()
    {
        return INSTANCE;
    }

    public Square createSquare(int i)
    {
        // in the future, should get this data form a config file or similar (XML?)
        if (i == 2 || i == 7 || i == 17 || i == 22 || i == 33 || i == 36)
        {
            return new DrawCardSquare("Square " + i, i - 1);
        }
        else
        {
            return new Square("Square " + i, i -1);
        }
    }
}
