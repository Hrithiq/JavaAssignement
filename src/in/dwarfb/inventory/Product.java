/**
 * Represents a product in the inventory management system.
 * This class maintains product details such as ID, name, stock levels,
 * price, and product type.
 */
package in.dwarfb.inventory;

public class Product {
    /** Unique identifier for the product */
    private String ID;
    /** Name of the product */
    private String name;
    /** Current stock level */
    private long stock;
    /** Initial stock level */
    private long originalStock;
    /** Price of the product */
    private double price;
    /** Type/category of the product */
    private ProductType productType;

    /**
     * Constructs a new Product with specified details.
     * 
     * @param id unique identifier for the product
     * @param n name of the product
     * @param price price of the product
     * @param s initial stock level
     * @param type category/type of the product
     */
    public Product(String id, String n, double price, long s, ProductType type) {
        ID = id;
        name = n;
        stock = s;
        originalStock = stock;
        productType = type;
        this.price = price;
    }

    /**
     * Returns the product type/category.
     * 
     * @return ProductType enum representing the product category
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Returns the product's unique identifier.
     * 
     * @return String representing the product ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the product name.
     * 
     * @return String representing the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current stock level.
     * 
     * @return long value representing current stock
     */
    public long getStock() {
        return stock;
    }

    /**
     * Returns the product price.
     * 
     * @return double value representing the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets a new price for the product.
     * 
     * @param price new price to set
     */
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * Updates the stock level and sets it as the new original stock.
     * 
     * @param stock new stock level to set
     */
    public void setStock(long stock) {
        this.stock = stock;
        this.originalStock = stock;
    }

    /**
     * Sets the product type/category.
     * 
     * @param productType new ProductType to set
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    /**
     * Returns the initial stock level when the product was created
     * or last reset.
     * 
     * @return long value representing the original stock level
     */
    public long getInitialStock() {
        return originalStock;
    }

    /**
     * Returns a string representation of the Product.
     * 
     * @return String containing the product name, ID, and current stock
     */
    @Override
    public String toString() {
        return String.format("Name: %s ID:%s Stock: %d", name, ID, stock);
    }
}