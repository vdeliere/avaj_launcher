package src.simulator;

import java.io.FileNotFoundException;

import src.validator.Validator;

public class Simulator{
    public static void main(String[] args){
        if (args.length != 1){
            System.err.print("Usage: java Simulator <file_name.txt>");
            System.exit(1);
        }

        String filename = args[0];

        if (!filename.endsWith(".txt")){
            System.err.print("Wrong type of files\nPlease submit a .txt file");
            System.exit(1);
        }

        Validator validator = new Validator();
        try {
            validator.parseFile(filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            System.exit(1);
        }
    }
}