package src.aircraft;

import src.coordinates.Coordinates;
import src.weather.WeatherProvider;
import src.file.FileHandler;

public class JetPlane extends Aircraft{

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate){
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions(){
        String   weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
        
        int      lon = this.coordinates.getLongitude();
        int      lat = this.coordinates.getLatitude();
        int      height = this.coordinates.getHeight();
        
        switch (weather) {
            case "SUN":
                lat += 10;
                height += 2;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": Sunny skies here I come !");
                break;
            case "RAIN":
                lat += 5;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                lat += 1;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": I'm totaly lost in that fog !");
                break;
            case "SNOW":
                height -= 7;
                FileHandler.getInstance().addToOutput(this.getAircraftIdentity() + ": OMG! Winter is coming!");
                break;
        }
        if (height > 100)
            height = 100;
        this.coordinates = new Coordinates(lon, lat, height);
        if (this.coordinates.getHeight() < 0)
            landing();
    }
}