package telran.org.de.scotlandyard.exception;

public class PaymentAmountNotEnoughException extends RuntimeException{

    public PaymentAmountNotEnoughException(String message) {
        super(message);
    }
}
