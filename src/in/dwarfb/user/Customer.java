package in.dwarfb.user;

public class Customer extends User {
    private float money;
    public Customer(String uname, String password, float money){
        super(uname, password,UserType.CUSTOMER);
        this.money = money;
    }

    public boolean affordable(float cost){
        return money > cost;
    }

    public void purchase(float cost) throws Exception {
        if(money < cost)
            throw new Exception(
                String.format("User %s cannot afford purchase of %d with %d amount",
                    username, money, cost)
            );

        money = money - cost;
    }
}
