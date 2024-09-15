package telran.org.de.scotlandyard.exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String message) {
        super(message);
    }
}
