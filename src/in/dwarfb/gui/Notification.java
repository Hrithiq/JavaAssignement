/**
 * Manages notifications within the application.
 * This class provides functionality to store, retrieve, and clear
 * notification messages.
 */
package in.dwarfb.gui;

import java.util.ArrayList;

public class Notification {
    /** List to store notification messages */
    ArrayList<String> notifications;

    /**
     * Constructs a new Notification object.
     * Initializes the notifications list with an initial capacity of 10.
     */
    public Notification() {
        notifications = new ArrayList<>(10);
    }

    /**
     * Adds a new notification message to the list.
     * 
     * @param notification the message to be added to notifications
     */
    public void notify(String notification) {
        notifications.add(notification);
    }

    /**
     * Returns all notification messages as an ArrayList.
     * 
     * @return ArrayList containing all notification messages
     */
    public ArrayList<String> asArrayList() {
        return notifications;
    }

    /**
     * Clears all notification messages from the list.
     */
    public void clear() {
        notifications.clear();
    }
}