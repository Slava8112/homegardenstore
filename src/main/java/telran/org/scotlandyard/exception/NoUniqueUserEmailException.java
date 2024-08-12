package telran.org.scotlandyard.exception;

public class NoUniqueUserEmailException extends RuntimeException {

    public NoUniqueUserEmailException(String message) {
        super(message);
    }
}
