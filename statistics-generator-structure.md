# StatisticsGenerator Structure and Names

## Directory Structure

```
com/
└── inventory/
    └── statistics/
        ├── StatisticsGenerator.java
        └── Statistic.java
```

## StatisticsGenerator Class

### Method Names:
1. generateAllStatistics()
2. getTotalRemainingProducts()
3. getRemainingGoodsVsCargo()
4. getMaxItemsOrderedByLocation()
5. getUniqueLocations()
6. getTotalItemsInOrder()
7. getProductsOrderedOverPeriod()
8. getTopSellingProducts()
9. getTotalSoldForProduct()
10. getAverageOrderValue()
11. getInventoryValue()
12. getOutOfStockProducts()
13. getProductTurnoverRate()

### Variable Names:
1. inventory (Product[])
2. orders (Order[])
3. statistics (Statistic[])
4. total (int)
5. goods (int)
6. cargo (int)
7. result (StringBuilder)
8. locations (String[])
9. maxItems (int)
10. orderItems (int)
11. uniqueCount (int)
12. uniqueLocations (String[])
13. startDate (LocalDate)
14. endDate (LocalDate)
15. topProducts (Product[])
16. topSales (int[])
17. totalSold (int)
18. limit (int)
19. orderProducts (Product[])
20. quantities (int[])
21. averageInventory (int)
22. turnoverRate (double)

## Statistic Class

### Method Names:
1. getName()
2. getValue()
3. toString()

### Variable Names:
1. name (String)
2. value (Object)

## Other Classes Referenced (To be done by others):

### Product Class (expected methods):
1. getName()
2. getQuantityInStock()
3. getPrice()
4. getInitialStock()

### Order Class (expected methods):
1. getShippingLocation()
2. getOrderDate()
3. getProducts()
4. getProductQuantities()
5. getTotalAmount()

### Good Class (used in instanceof check)

### Cargo Class (used in instanceof check)

