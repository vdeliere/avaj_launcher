package src.aircraft;

import src.coordinates.Coordinates;
import src.weather.WeatherProvider;
import src.file.FileHandler;

public class Helicopter extends Aircraft{

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate){
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions(){
        String   weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
        
        int      lon = this.coordinates.getLongitude();
        int      lat = this.coordinates.getLatitude();
        int      height = this.coordinates.getHeight();
        
        switch (weather) {
            case "SUN":
                lon += 10;
                height += 2;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": This is hot.");
                break;
            case "RAIN":
                lon += 5;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": This rain clean my dirty helicopter.");
                break;
            case "FOG":
                lon += 1;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": That fog is too thick");
                break;
            case "SNOW":
                height -= 12;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": My rotor is going to freeze!");
                break;
        }
        if (height > 100)
            height = 100;
        this.coordinates = new Coordinates(lon, lat, height);
        if (this.coordinates.getHeight() < 0)
            landing();
    }
}