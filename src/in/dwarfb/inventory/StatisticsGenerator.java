package in.dwarfb.inventory;

import java.time.LocalDate;

/**
 * The StatisticsGenerator class is responsible for generating various statistics
 * related to the inventory and orders in the Inventory Management System.
 * Assuming com.inventory.product already exists with the packages for Product,Good,Cargo and Order
 * com/
 * └── inventory/
    └── statistics/
        ├── StatisticsGenerator.java
        └── Statistic.java
 */
public class StatisticsGenerator {
    private Inventory inventory;
    private Order[] orders;

    /**
     * StatisticsGenerator with the given inventory and order data.
     *
     * @param inventory The array of products currently in the inventory.
     * @param orders    The array of all orders placed in the system.
     */
    public StatisticsGenerator(Inventory inventory) {
        this.inventory = inventory;
        var orders = inventory.getOrders();
        this.orders = new Order[orders.size()];
        for(int i = 0; i < orders.size(); i++){
            this.orders[i] = orders.get(i);
        }
    }

    /**
     * Statistics about the inventory and orders in the form of an array.
     *
     * @return An array of Statistic objects containing various statistics.
     */
    public Statistic[] generateAllStatistics() {
        Statistic[] statistics = new Statistic[9];
        statistics[0] = new Statistic("totalRemainingProducts", getTotalRemainingProducts());
        statistics[1] = new Statistic("remainingGoodsVsCargo", getRemainingGoodsVsCargo());
        statistics[2] = new Statistic("maxItemsOrderedByLocation", getMaxItemsOrderedByLocation());
        statistics[3] = new Statistic("productsOrderedLastMonth", getProductsOrderedOverPeriod(LocalDate.now().minusMonths(1), LocalDate.now()));
        statistics[4] = new Statistic("topSellingProducts", getTopSellingProducts(5));
        statistics[5] = new Statistic("averageOrderValue", getAverageOrderValue());
        statistics[6] = new Statistic("inventoryValue", getInventoryValue());
        statistics[7] = new Statistic("outOfStockProducts", getOutOfStockProducts());
        statistics[8] = new Statistic("productTurnoverRate", getProductTurnoverRate());
        return statistics;
    }

    /**
     * Calculates the total number of remaining products in the inventory.
     *
     * @return The total number of products in stock.
     */
    public int getTotalRemainingProducts() {
        int total = 0;
        for (Product product : inventory.asArrayList()) {
            total += product.getStock();
        }
        return total;
    }

    /**
     * Calculates the number of remaining goods vs cargo products in the inventory.
     *
     * @return A string representation of the count of remaining goods and cargo products.
     */
    public String getRemainingGoodsVsCargo() {
        int goods = 0, cargo = 0;
        for (Product product : inventory.asArrayList()) {
            if (product.getProductType() ==  ProductType.GOOD) {
                goods += product.getStock();
            } else if (product.getProductType() ==  ProductType.CARGO) {
                cargo += product.getStock();
            }
        }
        return "Goods: " + goods + ", Cargo: " + cargo;
    }

    /**
     * Calculates the maximum number of items ordered from each location.
     *
     * @return A string representation of locations and their maximum ordered items.
     */
    public String getMaxItemsOrderedByLocation() {
        StringBuilder result = new StringBuilder();
        String[] locations = getUniqueLocations();
        for (String location : locations) {
            int maxItems = 0;
            for (Order order : orders) {
                if (order.getShippingLocation().equals(location)) {
                    int orderItems = getTotalItemsInOrder(order);
                    if (orderItems > maxItems) {
                        maxItems = orderItems;
                    }
                }
            }
            result.append(location).append(": ").append(maxItems).append(", ");
        }
        return result.toString();
    }

    private String[] getUniqueLocations() {
        String[] locations = new String[orders.length];
        int uniqueCount = 0;
        for (Order order : orders) {
            String location = order.getShippingLocation();
            boolean isUnique = true;
            for (int i = 0; i < uniqueCount; i++) {
                if (locations[i].equals(location)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                locations[uniqueCount++] = location;
            }
        }
        String[] uniqueLocations = new String[uniqueCount];
        System.arraycopy(locations, 0, uniqueLocations, 0, uniqueCount);
        return uniqueLocations;
    }

    private int getTotalItemsInOrder(Order order) {
        int total = 0;
        for (long quantity : order.getProductQuantities()) {
            total += quantity;
        }
        return total;
    }

    /**
     * Calculates the number of products ordered over a specified period.
     *
     * @param startDate The start date of the period.
     * @param endDate   The end date of the period.
     * @return The total number of products ordered within the specified period.
     */
    public int getProductsOrderedOverPeriod(LocalDate startDate, LocalDate endDate) {
        int total = 0;
        for (Order order : orders) {
            if (order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate)) {
                total += getTotalItemsInOrder(order);
            }
        }
        return total;
    }

    /**
     * Returns the top selling products based on the quantity sold.
     *
     * @param limit The number of top selling products to return.
     * @return A string representation of the top selling products.
     */
    public String getTopSellingProducts(int limit) {
        Product[] topProducts = new Product[limit];
        int[] topSales = new int[limit];

        for (Product product : inventory.asArrayList()) {
            int totalSold = getTotalSoldForProduct(product);
            for (int i = 0; i < limit; i++) {
                if (topProducts[i] == null || totalSold > topSales[i]) {
                    // Shift the array to insert the new product
                    for (int j = limit - 1; j > i; j--) {
                        topProducts[j] = topProducts[j - 1];
                        topSales[j] = topSales[j - 1];
                    }
                    topProducts[i] = product;
                    topSales[i] = totalSold;
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < limit && topProducts[i] != null; i++) {
            result.append(topProducts[i].getName()).append(": ").append(topSales[i]).append(", ");
        }
        return result.toString();
    }

    private int getTotalSoldForProduct(Product product) {
        int total = 0;
        for (Order order : orders) {
            var orderProducts = order.getProducts();
            var quantities = order.getProductQuantities();
            for (int i = 0; i < orderProducts.size(); i++) {
                if (orderProducts.get(i).getID().equals(product.getID())) {
                    total += quantities.get(i);
                }
            }
        }
        return total;
    }

    /**
     * Calculates the average order value.
     *
     * @return The average value of all orders.
     */
    public double getAverageOrderValue() {
        if (orders.length == 0) return 0;
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalAmount();
        }
        return total / orders.length;
    }

    /**
     * Calculates the total value of the current inventory.
     *
     * @return The total value of all products in the inventory.
     */
    public double getInventoryValue() {
        double total = 0;
        for (Product product : inventory.asArrayList()) {
            total += product.getPrice() * product.getStock();
        }
        return total;
    }

    /**
     * Identifies products that are currently out of stock.
     *
     * @return A string representation of products with zero quantity in stock.
     */
    public String getOutOfStockProducts() {
        StringBuilder result = new StringBuilder();
        for (Product product : inventory.asArrayList()) {
            if (product.getStock() == 0) {
                result.append(product.getName()).append(", ");
            }
        }
        return result.toString();
    }

    /**
     * Calculates the turnover rate for each product.
     * The turnover rate is defined as (Total Quantity Sold) / (Average Inventory)
     *
     * @return A string representation of products and their turnover rates.
     */
    public String getProductTurnoverRate() {
        StringBuilder result = new StringBuilder();
        for (Product product : inventory.asArrayList()) {
            long quantitySold = getTotalSoldForProduct(product);
            long averageInventory = (product.getStock() + product.getInitialStock()) / 2;
            double turnoverRate = averageInventory > 0 ? (double) quantitySold / averageInventory : 0;
            result.append(product.getName()).append(": ").append(String.format("%.2f", turnoverRate)).append(", ");
        }
        return result.toString();
    }
}
