package in.dwarfb.inventory;

import java.util.ArrayList;
import in.dwarfb.gui.Notification;

public class Inventory {
    private ArrayList<Product> products;
    private int id;
    private Notification notification;
    private final long EMERGENCY_STOCK = 10;
    private final long LOW_STOCK = 20;

    public Inventory(Notification n){
        id = 0;
        products = new ArrayList<>();
        notification = n;
    }
    public Product getItem(String id) throws Exception{
        for(var product : products){
            if(product.getID().equals(id))
                return product;
        }
        throw new Exception("Product not found: " + id);
    }

    public void addProduct(String name, long stock, double price, ProductType type)throws Exception{
        id++;
        for(var product : products){
            if(product.getID().equals(""+id))
                throw new Exception("Product with ID already exists: " + id);
        }
        products.add(new Product(String.valueOf(id), name,price, stock, type));
    }

    public void purchase(String id, long count) throws Exception{
        try{
            Product p = getItem(id);
            if(count > p.getStock())
                throw new Exception(
                    String.format("Item %s(%s), not in stock(%d), %d asked",
                        p.getName(), p.getID(), p.getStock(), count)
                );
            p.setStock(p.getStock() - count);
            long stock = p.getStock();
            if(stock == 0)
                notification.notify(
                    String.format("%s(%s) is FINISHED, please restock!",
                        p.getName(), p.getID())
                );
            else if(stock < EMERGENCY_STOCK)
                notification.notify(
                    String.format("%s(%s) is EMERGENCY LOW stock(%d), please restock!",
                        p.getName(), p.getID(),stock)
                );
            else if(stock < LOW_STOCK)
                notification.notify(
                    String.format("%s(%s) is LOW stock(%d), please restock!",
                        p.getName(), p.getID(),stock)
                );

        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Product> asArrayList(){
        return products;
    }

    public void setProducts(ArrayList<Product> p){
        products = p;
    }

}
