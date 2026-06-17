package src.simulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import src.validator.Validator;
import src.aircraft.AircraftFactory;
import src.aircraft.Flyable;
import src.tower.WeatherTower;
import src.coordinates.Coordinates;
import src.file.FileHandler;

public class Simulator{
    public static void main(String[] args){
        // Arguments verification
        if (args.length != 1){
            System.err.print("Usage: java Simulator <file_name.txt>");
            System.exit(1);
        }

        String filename = args[0];

        if (!filename.endsWith(".txt")){
            System.err.print("Wrong type of files\nPlease submit a .txt file");
            System.exit(1);
        }

        // Launch of the parsing
        Validator validator = new Validator();
        try {
            validator.parseFile(filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            System.exit(1);
        }

        // Creation of the weatherTower
        WeatherTower weatherTower = new WeatherTower();

        // Recuperation of the parsed aircrafts list
        List<Validator.AircraftInfo> infos = validator.getAircraftInfos();

        // Recuperation of our singleton AircraftFactory
        AircraftFactory factory = AircraftFactory.getInstance();

        // Creation of the Aircrafts
        for (Validator.AircraftInfo info : infos){
            Coordinates coords = new Coordinates(
                info.getLongitude(),
                info.getLatitude(),
                info.getHeight()
            );
            Flyable newAircraft = factory.newAircraft(
                info.getType(),
                info.getName(),
                coords
            );
            // We register the aircraft to the weather tower
            newAircraft.registerTower(weatherTower);
        }
       

        // THE SIMULATION
        for(int i = 0; i < validator.getIterations(); i++){
            weatherTower.conditionChanged();
        }

        // Writing of th simulation file
        try{
            FileHandler.getInstance().writeOutputToFile();
        } catch (IOException e){
            System.err.println("Writing error of simulation.txt");
            System.exit(1);
        }
    }
}