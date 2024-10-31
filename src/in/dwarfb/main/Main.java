package in.dwarfb.main;

import in.dwarfb.gui.Notification;
import in.dwarfb.inventory.Inventory;
import in.dwarfb.inventory.ProductType;
import javafx.application.Application;

/**
 * Main
 */
public class Main{
    public static void main(String[] args) {
        Notification notification = new Notification();
        Inventory inv = new Inventory(notification);
        try {
            inv.addProduct("Apples", 10, 30,ProductType.CARGO);
            inv.addProduct("Orange", 20, 5,ProductType.CARGO);
            inv.purchase("1",5);
            for(var e: inv.asArrayList()){
                System.out.println(e);
            }
            for(var n: notification.asArrayList()){
                System.out.println(n);
            }
        } catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }
}
