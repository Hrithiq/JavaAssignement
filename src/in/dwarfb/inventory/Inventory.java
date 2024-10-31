package in.dwarfb.inventory;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private int id;

    public Inventory(){
        id = 1;
        products = new ArrayList<>();
    }
    public Product getItem(String id) throws Exception{
        for(var product : products){
            if(product.getID().equals(id))
                return product;
        }
        throw new Exception("Product not found: " + id);
    }

    public void addProduct(String name, long stock, double price)throws Exception{
        id++;
        for(var product : products){
            if(product.getID().equals(""+id))
                throw new Exception("Product with ID already exists: " + id);
        }
    }

    public ArrayList<Product> asArrayList(){
        return products;
    }

}
