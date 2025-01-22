package in.dwarfb.exceptions;

/**
 * Exception thrown when there is insufficient stock available to fulfill a product order.
 * This is used to notify the system of failed orders due to lack of product stock.
 */
public class InsufficientStockException extends Exception {
    
    /**
     * Constructs a new InsufficientStockException with the specified detail message.
     * @param message The detail message that provides additional information about the exception.
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
