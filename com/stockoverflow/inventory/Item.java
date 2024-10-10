package com.stockoverflow.inventory;

public enum Location { GOOD ,CARGO };
public class Item {
    private long id;
    private String name;
    private int quantity;
    private Location location;

}
