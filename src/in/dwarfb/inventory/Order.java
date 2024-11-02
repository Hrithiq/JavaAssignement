package in.dwarfb.inventory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order{
    private LocalDate date;
    private float price;
    private ArrayList<Product> products;
    private ArrayList<Long> quantity;

    public Order(){
        products = new ArrayList<>(10);
        quantity = new ArrayList<>(10);
        price = 0;
    }

    public String getShippingLocation(){
        return "Manipal";
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public ArrayList<Long> getProductQuantities(){
        return quantity;
    }

    public float getTotalAmount(){
        return price;
    }

    public void addProduct(Product p, long q){
        products.add(p);
        quantity.add(q);
        price+= p.getPrice() * q;
    }

    public void setDate(){
        date = LocalDate.now();
    }

    public LocalDate getOrderDate(){
        return date;
    }

}
