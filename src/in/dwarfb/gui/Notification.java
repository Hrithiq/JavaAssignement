package in.dwarfb.gui;

import java.util.ArrayList;

public class Notification {
    ArrayList<String> notifications;
    public Notification(){
        notifications = new ArrayList<>(10);
    }

    public void notify(String notification){
        notifications.add(notification);
    }

    public ArrayList<String> asArrayList(){
        return notifications;
    }

    public void clear(){
        notifications.clear();
    }
}
