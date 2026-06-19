package src.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import src.exceptions.InvalidFileInformation;
import java.util.Scanner;

public class Validator {
    private int                 iterations;
    private List<AircraftInfo>  aircraftInfos;

    public class AircraftInfo{
        private String  type;
        private String  name;
        private int     longitude;
        private int     latitude;
        private int     height;

        public AircraftInfo(String p_type, String p_name, int p_longitude, int p_latitude, int p_height){
            this.type = p_type;
            this.name = p_name;
            this.longitude = p_longitude;
            this.latitude = p_latitude;
            this.height = p_height;
        }

        public String  getType(){ return type; }
        public String  getName(){ return name; }
        public int     getLongitude(){ return longitude; }
        public int     getLatitude(){ return latitude; }
        public int     getHeight(){ return height; }
    }

    public  Validator(){
        iterations = -1;
        aircraftInfos = new ArrayList<AircraftInfo>();
    }

    public List<AircraftInfo> getAircraftInfos(){
        return this.aircraftInfos;
    }

    public int getIterations(){
        return this.iterations;
    }

    public void parseFile(String filename) throws FileNotFoundException, InvalidFileInformation{
        File file = new File(filename);

        if (!file.exists())
            throw new FileNotFoundException("Error: this file doesn't exist");
        if (!file.isFile())
            throw new InvalidFileInformation("Error: the path is a folder not a file");
        if (!file.canRead())
            throw new InvalidFileInformation("Error: you don't have the read rights for this file");

        try (Scanner scanner = new Scanner(file)){
            // Handling of the first line
            if (scanner.hasNextLine()){
                String firstLine = scanner.nextLine().trim();
                try {
                    int val = Integer.parseInt(firstLine);
                    if (val < 1)
                        throw new InvalidFileInformation("Iteration number has to be a positive integer");
                    this.iterations = val;
                } catch (NumberFormatException e) {
                    throw new InvalidFileInformation("The first line is not a valid integer: '"+ firstLine + "'", e);
                }
            } else
                throw new InvalidFileInformation("The file is empty!");

            // Main read loop for aircrafts
            while (scanner.hasNext()) {
                try {
                    String  type = scanner.next();
                    String  name = scanner.next();
                    int     longitude = scanner.nextInt();
                    int     latitude = scanner.nextInt();
                    int     height = scanner.nextInt();

                    // Validation of the coordinates
                    if (longitude < 0 || latitude < 0 || height < 0)
                        throw new InvalidFileInformation("Coordinates and height must be positive for aircraft: " + name);
                    else if (height > 100)
                        throw new InvalidFileInformation("Height coordinate can't be over 100: " + name);

                    // Add a new entry in AircraftInfo
                    AircraftInfo aircraft = new AircraftInfo(type, name, longitude, latitude, height);
                    this.aircraftInfos.add(aircraft);

                } catch (InputMismatchException e){
                    throw new InvalidFileInformation("Format error in file: expected an integer but found text.", e);
                } catch (NoSuchElementException e){
                    throw new InvalidFileInformation("File ended unexpectedly. An aircraft definition is incomplete.", e);
                }
            }
        } 
    }
}