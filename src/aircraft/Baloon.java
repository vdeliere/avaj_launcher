package src.aircraft;

import src.coordinates.Coordinates;
import src.weather.WeatherProvider;
import src.file.FileHandler;

public class Baloon extends Aircraft{

    public Baloon(long p_id, String p_name, Coordinates p_coordinate){
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions(){
        String   weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
        
        int      lon = this.coordinates.getLongitude();
        int      lat = this.coordinates.getLatitude();
        int      height = this.coordinates.getHeight();
        
        switch (weather) {
            case "SUN":
                lon += 2;
                height += 5;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                height -= 5;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                height -= 3;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": I can't see a thing with that fog.");
                break;
            case "SNOW":
                height -= 15;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": It's snowing. We're gonna crash.");
                break;
        }
        if (height > 100)
            height = 100;
        this.coordinates = new Coordinates(lon, lat, height);
        if (this.coordinates.getHeight() < 0)
            landing();
    }
}