package in.dwarfb.user;

/**
 * Abstract base class for all users in the system.
 * Provides basic user authentication and type functionality.
 */
public abstract class User {
    /** The user's username */
    protected String username;
    /** The user's password */
    protected String password;
    /** The user's type (CUSTOMER or MANAGER) */
    protected UserType uType;

    /**
     * Creates a new user with specified credentials and type.
     * 
     * @param uname Username for the new user
     * @param psswd Password for the new user
     * @param usertype Type of user (CUSTOMER or MANAGER)
     */
    public User(String uname, String psswd, UserType usertype) {
        username = uname;
        password = psswd;
        uType = usertype;
    }

    /**
     * Authenticates user credentials.
     * 
     * @param uname Username to verify
     * @param psswd Password to verify
     * @return true if credentials match, false otherwise
     */
    public boolean authenticate(String uname, String psswd) {
        return uname.equals(username) && password.equals(psswd);
    }

    /**
     * Gets the user's type.
     * @return The user's type (CUSTOMER or MANAGER)
     */
    public UserType getUserType() {
        return uType;
    }
}