package telran.org.de.scotlandyard.exception;

public class NoUniqueUserEmailException extends RuntimeException {

    public NoUniqueUserEmailException(String message) {
        super(message);
    }
}
