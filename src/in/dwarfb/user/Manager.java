package in.dwarfb.user;

/**
 * Represents a manager user in the system.
 * Managers have special privileges for system administration.
 */
public class Manager extends User {
    /**
     * Creates a new manager with specified credentials.
     * 
     * @param uname Manager's username
     * @param password Manager's password
     */
    public Manager(String uname, String password) {
        super(uname, password, UserType.MANAGER);
    }
}