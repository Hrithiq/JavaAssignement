package in.dwarfb.user;

import in.dwarfb.exceptions.InsufficientFundsException;

/**
 * Represents a customer user in the system.
 * Customers have an associated balance and can make purchases.
 */
public class Customer extends User {
    /** The customer's current balance */
    private float money;

    /**
     * Creates a new customer with specified credentials and initial balance.
     * 
     * @param uname Customer's username
     * @param password Customer's password
     * @param money Initial balance
     */
    public Customer(String uname, String password, float money) {
        super(uname, password, UserType.CUSTOMER);
        this.money = money;
    }

    /**
     * Checks if the customer can afford a purchase.
     * 
     * @param cost The cost to check against customer's balance
     * @return true if customer has sufficient funds, false otherwise
     */
    public boolean affordable(float cost) {
        return money > cost;
    }

    /**
     * Processes a purchase by deducting the cost from customer's balance.
     * 
     * @param cost The amount to deduct
     * @throws InsufficientFundsException if customer's balance is less than the cost
     */
    public void purchase(float cost) throws Exception {
        if(money < cost)
            throw new InsufficientFundsException(
                String.format("User %s cannot afford purchase of %d with %d amount",
                    username, money, cost)
            );
        money = money - cost;
    }
}