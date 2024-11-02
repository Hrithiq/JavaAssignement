package in.dwarfb.user;

public abstract class User {
    protected String username;
    protected String password;
    protected UserType uType;

    public User(String uname, String psswd, UserType usertype){
        username = uname;
        password = psswd;
        uType = usertype;
    }

    public boolean authenticate(String uname, String psswd){
        return uname.equals(username) && password.equals(psswd);
    }

    public UserType getUserType(){
        return uType;
    }
}
