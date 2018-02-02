package sample;

/**
 * Created by ih6413pg on 12/3/17.
 */
public class DrawCardSquare extends Square {

    public DrawCardSquare(String name, int index)
    {
        super(name, index);
    }

    public void getCard()
    {
        System.out.println("Got a card!");
    }

}
