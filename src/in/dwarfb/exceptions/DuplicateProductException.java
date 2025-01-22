package in.dwarfb.exceptions;

/**
 * Exception thrown when a product with a duplicate ID or details is added to the inventory.
 * This is useful for preventing duplicates in the inventory system.
 */
public class DuplicateProductException extends Exception {
    
    /**
     * Constructs a new DuplicateProductException with the specified detail message.
     * @param message The detail message that provides additional information about the exception.
     */
    public DuplicateProductException(String message) {
        super(message);
    }
}
