package in.dwarfb.user;

import java.util.ArrayList;

/**
 * Manages all users in the system.
 * Handles user creation, authentication, and storage.
 */
public class UserHandler {
    /** List of all users in the system */
    ArrayList<User> users;

    /**
     * Creates a new UserHandler with initial capacity of 10 users.
     */
    public UserHandler() {
        users = new ArrayList<>(10);
    }

    /**
     * Adds a new customer to the system.
     * 
     * @param username Customer's username
     * @param password Customer's password
     * @param money Customer's initial balance
     */
    public void addCustomer(String username, String password, float money) {
        users.add(new Customer(username, password, money));
    }

    /**
     * Adds a new manager to the system.
     * 
     * @param username Manager's username
     * @param password Manager's password
     */
    public void addManager(String username, String password) {
        users.add(new Manager(username, password));
    }

    /**
     * Initializes the system with sample user data.
     */
    public void setDummyData() {
        addCustomer("binary", "1234", 1000);
        addManager("manager", "1234");
    }

    /**
     * Authenticates a user based on provided credentials.
     * 
     * @param uname Username to authenticate
     * @param psswd Password to authenticate
     * @return The authenticated User object, or null if authentication fails
     */
    public User authenticateUser(String uname, String psswd) {
        for(var u : users) {
            if(u.authenticate(uname, psswd))
                return u;
        }
        return null;
    }
}
