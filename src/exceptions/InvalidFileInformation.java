package src.exceptions;

public class InvalidFileInformation extends RuntimeException {
    
    public InvalidFileInformation(){
        super("An information in the scenario file is not valid");
    }
    
    public InvalidFileInformation(String message){
        super(message);
    }

    public InvalidFileInformation(Throwable cause){
        super(cause);
    }

    public InvalidFileInformation(String message, Throwable cause){
        super(message, cause);
    }
}

