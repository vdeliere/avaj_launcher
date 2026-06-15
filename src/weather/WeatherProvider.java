package src.weather;

import src.coordinates.Coordinates;

public class WeatherProvider{
    private static WeatherProvider  instance;
    private String[]                weather;
    
    private WeatherProvider(){
        weather = new String[]{"RAIN", "FOG", "SUN", "SNOW"};
    }

    public static WeatherProvider getInstance(){
        if (instance == null)
            instance = new WeatherProvider();
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates){
        long random = (p_coordinates.getLatitude() + p_coordinates.getLongitude() + p_coordinates.getHeight() + System.nanoTime()) % weather.length;
        return weather[(int)random];
    }
}