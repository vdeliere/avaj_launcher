package src.tower;

import src.coordinates.Coordinates;
import src.weather.WeatherProvider;

public class WeatherTower extends Tower{

    public String getWeather(Coordinates p_coordinates){
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }
}