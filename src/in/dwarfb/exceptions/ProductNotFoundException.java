package in.dwarfb.exceptions;

/**
 * Exception thrown when a requested product is not found in the inventory.
 * This is used to notify the system of invalid product requests.
 */
public class ProductNotFoundException extends Exception {
    
    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     * @param message The detail message that provides additional information about the exception.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
