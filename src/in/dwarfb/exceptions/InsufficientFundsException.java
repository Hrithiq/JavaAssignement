package in.dwarfb.exceptions;

/**
 * Exception thrown when a customer attempts to make a purchase without sufficient funds.
 * This is used to notify the system of failed transactions due to lack of funds.
 */
public class InsufficientFundsException extends Exception {
    
    /**
     * Constructs a new InsufficientFundsException with the specified detail message.
     * @param message The detail message that provides additional information about the exception.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
