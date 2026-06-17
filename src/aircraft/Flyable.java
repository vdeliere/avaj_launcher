package src.aircraft;

import src.tower.WeatherTower;

public abstract class Flyable{
    protected WeatherTower weatherTower;

    public Flyable(){}

    public abstract void updateConditions();

    public abstract String getAircraftIdentity();

    public void registerTower(WeatherTower p_tower){
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }
}

