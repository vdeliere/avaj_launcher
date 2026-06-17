package src.tower;

import java.util.ArrayList;
import java.util.List;
import src.aircraft.Flyable;
import src.file.FileHandler;

public class Tower{
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable p_flyable){
        observers.add(p_flyable);
        FileHandler.getInstance().addToOutput("Tower says: " + p_flyable.getAircraftIdentity() + "registered to weather tower.");
    }

    public void unregister(Flyable p_flyable){
        observers.remove(p_flyable);
        FileHandler.getInstance().addToOutput("Tower says: " + p_flyable.getAircraftIdentity() + "unregistered from weather tower.");
    }

    protected void conditionChanged(){
        List<Flyable> copy = new ArrayList<>(observers);
        for(Flyable observer : copy)
            observer.updateConditions();
    }
}