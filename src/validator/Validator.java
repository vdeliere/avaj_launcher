package src.validator;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import src.exceptions.InvalidFileInformation;

public class Validator {
    private int                 iterations;
    private List<AircraftInfo>  aircraftInfos;

    public class AircraftInfo{
        private String  type;
        private String  name;
        private int     longitude;
        private int     latitude;
        private int     height;
    }

    public  Validator(){
        iterations = -1;
        aircraftInfos = new ArrayList<AircraftInfo>();
    }

    public void parseFile(String filename) throws FileNotFoundException, InvalidFileInformation{
        System.out.println("Let's go parsing\n");
        File file = new File(filename);
        boolean firstline = true;

        if(!file.exists()){
            System.err.println("Error: this file doesn't exist");
            return;
        }
        if (!file.isFile()){
            System.err.println("Error: the path is a folder not a file");
            return;
        }
        if (!file.canRead()){
            System.err.println("Error: you don't have the read right for this file");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                if (firstline){
                    this.iterations = Integer.parseInt(line.trim());
                    System.out.println("1st line:" + iterations);
                    if (iterations < 1)
                        throw new InvalidFileInformation("Iteration number has to be a positive integer");
                    firstline = false;
                }
                else{
                     System.out.println(line);
                }
            }
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}