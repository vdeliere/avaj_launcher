package src.aircraft;

import src.coordinates.Coordinates;
import src.exceptions.InvalidFileInformation;

// Singleton
public class AircraftFactory{
    private static AircraftFactory  instance;

    private AircraftFactory(){}

    public static AircraftFactory getInstance(){
        if (instance == null)
            instance = new AircraftFactory();
        return instance;
    }
    
    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates){
        switch (p_type) {
            case "JetPlane":
                return new JetPlane(0, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(0, p_name, p_coordinates);
            case "Baloon":
                return new Baloon(0, p_name, p_coordinates);
            default:
                throw new InvalidFileInformation("The following aircraft type is not sell by the factory:" + p_type);
        }
    }
}