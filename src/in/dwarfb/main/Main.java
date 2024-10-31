package in.dwarfb.main;

import in.dwarfb.inventory.Inventory;

/**
 * Main
 */ 
public class Main {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.addProduct("Apples", 10, 30);
        inv.addProduct("Orange", 20, 5);
    }
}