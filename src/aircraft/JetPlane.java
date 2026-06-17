package src.aircraft;

import src.coordinates.Coordinates;
import src.weather.WeatherProvider;

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
                break;
            case "RAIN":
                lat += 5;
                break;
            case "FOG":
                lat += 1;
                break;
            case "SNOW":
                height -= 7;
                break;
        }
        if (height > 100)
            height = 100;
        this.coordinates = new Coordinates(lon, lat, height);
        if (this.coordinates.getHeight() < 0){
            this.weatherTower.unregister(this);
        }
    }
}