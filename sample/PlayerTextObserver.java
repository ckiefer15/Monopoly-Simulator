package sample;

import java.util.Observable;
import java.util.Observer;

public class PlayerTextObserver implements Observer {
    private Observable publisher;

    private PlayerTextObserver(){

    }
    public PlayerTextObserver(Observable o){
        publisher = o;
        publisher.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        if( o == publisher){
            System.out.println(arg);
        }
    }
}
