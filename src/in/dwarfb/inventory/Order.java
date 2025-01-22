/**
 * Represents an order in the inventory management system.
 * This class manages a collection of products and their quantities along with order details
 * such as date and total price.
 */
package in.dwarfb.inventory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    /** The date when the order was placed */
    private LocalDate date;
    /** The total price of the order */
    private float price;
    /** List of products in the order */
    private ArrayList<Product> products;
    /** List of quantities corresponding to each product */
    private ArrayList<Long> quantity;

    /**
     * Constructs a new Order with empty product list and zero price.
     * Initializes the products and quantity ArrayLists with initial capacity of 10.
     */
    public Order() {
        products = new ArrayList<>(10);
        quantity = new ArrayList<>(10);
        price = 0;
    }

    /**
     * Returns the shipping location for the order.
     *
     * @return String representing the shipping location (currently hardcoded to "Manipal")
     */
    public String getShippingLocation() {
        return "Manipal";
    }

    /**
     * Returns the list of products in the order.
     *
     * @return ArrayList containing all products in the order
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Returns the quantities of each product in the order.
     *
     * @return ArrayList containing quantities corresponding to each product
     */
    public ArrayList<Long> getProductQuantities() {
        return quantity;
    }

    /**
     * Returns the total amount of the order.
     *
     * @return float value representing the total price of the order
     */
    public float getTotalAmount() {
        return price;
    }

    /**
     * Adds a product to the order or updates its quantity if it already exists.
     * Updates the total price accordingly.
     *
     * @param p the Product to be added
     * @param q the quantity of the product to be added
     */
    public void addProduct(Product p, long q) {
        for (int i = 0; i < products.size(); i++) {
            if (p.getID().equals(products.get(i).getID())) {
                quantity.set(i, quantity.get(i) + q);
                price += p.getPrice() * q;
                return;
            }
        }
        products.add(p);
        quantity.add(q);
        price += p.getPrice() * q;
    }

    /**
     * Sets the order date to the current date.
     */
    public void setDate() {
        date = LocalDate.now();
    }

    /**
     * Returns the date when the order was placed.
     *
     * @return LocalDate representing the order date
     */
    public LocalDate getOrderDate() {
        return date;
    }
}
