package src.tower;

import java.util.List;
import src.aircraft.Flyable;

public class Tower{
    private List<Flyable> observers;

    public void register(Flyable p_flyable){
        observers.add(p_flyable);
    }

    public void unregister(Flyable p_Flyable){
        observers.remove(p_Flyable);
    }

    protected void conditionChanged(){
        // a coder
    }
}