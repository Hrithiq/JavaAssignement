package in.dwarfb.inventory;

public class Product {
    private String ID;
    private String name;
    private long stock;
    private double price;
    private ProductType productType;

    public Product(String id, String n, double price, long s, ProductType type){
        ID = id;
        name = n;
        stock = s;
        productType = type;
        this.price = price;
    }
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

    @Override
    public String toString(){
        return String.format("Name: %s ID:%s Stock: %d", name, ID, stock);
    }
}
