package telran.org.de.scotlandyard.exception;

public class FavoriteNotFoundException extends  RuntimeException{
    public FavoriteNotFoundException(String message){
        super(message);
    }
}
