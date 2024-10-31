package in.dwarfb.user;

public abstract class User {
    protected String username;
    protected String password;

    public User(String uname, String psswd){
        username = uname;
        password = psswd;
    }

    public boolean authenticate(String uname, String psswd){
        return uname.equals(username) && password.equals(psswd);
    }
}
