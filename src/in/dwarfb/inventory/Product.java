package in.dwarfb.inventory;

enum ProductType{
    GOOD,CARGO
}

public class Product {
    private String ID;
    private String name;
    private long stock;
    private double price;
    private ProductType productType;
    
    public ProductType getProductType() {
        return productType;
    }
    public String getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public long getStock() {
        return stock;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }


    public void setStock(long stock) {
        this.stock = stock;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    
    public Product(String id, String n, double price, long s, ProductType type){
        ID = id;
        name = n;
        stock = s;
        productType = type;   
        this.price = price;
    }
}
