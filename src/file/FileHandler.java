package src.file;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

public final class FileHandler{
    private static FileHandler instance;
    private List<String> output; 

    private FileHandler(){
        output = new ArrayList<String>();
    }

    public static FileHandler getInstance(){
        if (instance == null)
            instance = new FileHandler();
        return instance;
    }

    public void addToOutput(String str){
        output.add(str);
    }

    public void writeOutputToFile() throws IOException{
        try (FileWriter writer = new FileWriter("simulation.txt")){
            String o = String.join("\n", output) + "\n";
            writer.write(o);
        }
    }
}