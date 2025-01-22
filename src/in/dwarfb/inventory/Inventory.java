package in.dwarfb.inventory;

import java.util.ArrayList;
import in.dwarfb.exceptions.DuplicateProductException;
import in.dwarfb.exceptions.InsufficientStockException;
import in.dwarfb.exceptions.ProductNotFoundException;
import in.dwarfb.gui.Notification;

/**
 * Manages the inventory system for products and orders.
 * This class handles product storage, retrieval, and purchase operations
 * while maintaining stock levels and notifications for low inventory.
 */
public class Inventory {
    /** List of products in the inventory */
    private ArrayList<Product> products;

    /** List of orders placed */
    private ArrayList<Order> orders;

    /** Auto-incrementing ID for products */
    private int id;

    /** Notification system for inventory alerts */
    private Notification notification;

    /** Threshold for emergency stock level */
    private final long EMERGENCY_STOCK = 10;

    /** Threshold for low stock level */
    private final long LOW_STOCK = 20;

    /**
     * Creates a new Inventory instance with notification capability.
     *
     * @param n The notification system to use for inventory alerts
     */
    public Inventory(Notification n) {
        id = 0;
        products = new ArrayList<>(10);
        orders = new ArrayList<>(10);
        notification = n;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The product ID to search for
     * @return The product with the specified ID
     * @throws ProductNotFoundException if no product exists with the given ID
     */
    public Product getItem(String id) throws Exception {
        for(var product : products) {
            if(product.getID().equals(id))
                return product;
        }
        throw new ProductNotFoundException("Product not found: " + id);
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name The product name to search for
     * @return The product with the specified name
     * @throws ProductNotFoundException if no product exists with the given name
     */
    public Product getItemByName(String name) throws Exception {
        for(var product : products) {
            if(product.getName().equals(name))
                return product;
        }
        throw new ProductNotFoundException("Product not found: " + name);
    }

    /**
     * Adds a new product to the inventory.
     *
     * @param name The name of the product
     * @param stock Initial stock quantity
     * @param price Product price
     * @param type Product type (CARGO or GOOD)
     * @throws DuplicateProductException if a product with the generated ID already exists
     */
    public void addProduct(String name, long stock, double price, ProductType type) throws Exception {
        id++;
        for(var product : products) {
            if(product.getID().equals(""+id))
                throw new DuplicateProductException("Product with ID already exists: " + id);
        }
        products.add(new Product(String.valueOf(id), name, price, stock, type));
    }

    /**
     * Adds an order to the order history.
     * @param order The order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Returns the list of all orders.
     * @return ArrayList of all orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Processes a purchase request for a product.
     * This method is synchronized to prevent concurrent stock modifications.
     * It also handles stock level notifications.
     *
     * @param id Product ID to purchase
     * @param count Quantity to purchase
     * @throws ProductNotFoundException if the product doesn't exist
     * @throws InsufficientStockException if requested quantity exceeds available stock
     */
    public synchronized void purchase(String id, long count) throws Exception {
        try {
            Product p = getItem(id);
            System.out.println("Purchasing: " + id);
            if(count > p.getStock())
                throw new InsufficientStockException(
                    String.format("Item %s(%s), not in stock(%d), %d asked",
                        p.getName(), p.getID(), p.getStock(), count)
                );
            p.setStock(p.getStock() - count);
            long stock = p.getStock();
            if(stock == 0)
                notification.notify(
                    String.format("%s(%s) is FINISHED, please restock!",
                        p.getName(), p.getID())
                );
            else if(stock < EMERGENCY_STOCK)
                notification.notify(
                    String.format("%s(%s) is EMERGENCY LOW stock(%d), please restock!",
                        p.getName(), p.getID(), stock)
                );
            else if(stock < LOW_STOCK)
                notification.notify(
                    String.format("%s(%s) is LOW stock(%d), please restock!",
                        p.getName(), p.getID(), stock)
                );
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Initializes the inventory with sample products.
     * This method is typically used for testing or demonstration purposes.
     */
    public void addDummyData() {
        try {
            // Add sample products
            addProduct("Apple", 100, 20.0, ProductType.CARGO);
            addProduct("Orange", 150, 15.0, ProductType.CARGO);
            addProduct("Banana", 200, 12.5, ProductType.CARGO);
            addProduct("Mango", 80, 25.0, ProductType.CARGO);
            addProduct("Water", 1000, 1.99, ProductType.CARGO);
            addProduct("Coco-Cola", 800, 2.49, ProductType.GOOD);
            addProduct("Sprite", 400, 2.49, ProductType.GOOD);
            addProduct("Mountain Dew", 1000, 1.49, ProductType.GOOD);
            addProduct("Basmati Rice", 500, 45.0, ProductType.CARGO);
            addProduct("Wheat", 600, 30.0, ProductType.CARGO);
            addProduct("Flour", 200, 50.0, ProductType.CARGO);
            addProduct("Pen", 100, 1.0, ProductType.GOOD);
            addProduct("Notebook", 500, 5.0, ProductType.GOOD);
            addProduct("Wine", 100, 89.99, ProductType.GOOD);
            addProduct("Textbook", 150, 79.99, ProductType.GOOD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all products in the inventory.
     * @return ArrayList of all products
     */
    public ArrayList<Product> asArrayList() {
        return products;
    }

    /**
     * Sets the product list to a new list.
     * Useful for bulk updates or inventory resets.
     * @param p New list of products to set
     */
    public void setProducts(ArrayList<Product> p) {
        products = p;
    }
}
