package in.dwarfb.user;

import java.util.ArrayList;

public class UserHandler{
    ArrayList<User> users;
    public UserHandler(){
        users = new ArrayList<>(10);
    }
    public void addCustomer(String username, String password, float money){
        users.add(new Customer(username, password,money));
    }

    public void addManager(String username, String password){
        users.add(new Manager(username, password));
    }

    public void setDummyData(){
        addCustomer("binary","1234", 1000);
        addManager("manager","1234");
    }

    public User authenticateUser(String uname, String psswd){
        for(var u : users){
            if(u.authenticate(uname, psswd))
                return u;
        }
        return null;
    }
}
