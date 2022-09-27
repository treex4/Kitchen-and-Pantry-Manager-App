package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a list of items in the kitchen and some commands that can be done to the list
public class Kitchen implements Writable {
    private ArrayList<FoodItem> itemList;


    // EFFECTS: initializes a newly created itemList as an empty list
    public Kitchen() {
        itemList = new ArrayList<FoodItem>();
    }

    // EFFECTS: returns number of items in the list
    public int getItemCount() {
        return itemList.size();
    }

    // EFFECTS: if item is found in the list
    //              - returns item with details
    //          otherwise, return message
    public String getFoodItem(String name) {
        String foundItem = "";
        for (FoodItem item : itemList) {
            if (item.getName().equals(name)) {
                foundItem = "Name: " + item.getName() + "\n" + "Type: " + item.getFoodType() + "\n" + "Location: "
                        + item.getLocation() + "\n" + "Quantity: " + item.getQuantity() + "\n" + "Expiry Date: "
                        + item.getExpiryDate() + "\n";
                return foundItem;
            }
        }
        return "Error: Item not found.\n";
    }

    // EFFECTS: returns the list of items
    public ArrayList<FoodItem> getItemList() {
        EventLog.getInstance().logEvent(new Event("Displayed kitchen inventory."));
        return itemList;
    }

    // EFFECTS: lists all the items with their respective location
    public String getAllItems() {
        String items = "";
        for (FoodItem item : itemList) {
            items += item.getName() +  " is located in " + item.getLocation() + "\n";
        }
        return items;
    }

    // MODIFIES: this
    // EFFECTS: if item is not already in the list
    //              - add item to list
    //              - returns true
    //          otherwise, returns false
    public boolean addFoodItem(String name, String type, String location, int quantity, String expiryDate) {
        for (FoodItem item : itemList) {
            if (item.getName().equals(name)) {
                return false;
            }
        }
        FoodItem itemToAdd = new FoodItem(name, type, location, quantity, expiryDate);
        itemList.add(itemToAdd);
        EventLog.getInstance().logEvent(new Event(name + " added to kitchen inventory."));
        return true;
    }

    // MODIFIES: this
    // EFFECTS: if item is in the list
    //              - remove item from list
    //              - returns true
    //          otherwise, returns false
    public boolean removeFoodItem(String name) {
        for (Iterator<FoodItem> it = itemList.iterator(); it.hasNext(); ) {
            while (it.hasNext()) {
                if (it.next().getName().equals(name)) {
                    it.remove();
                    EventLog.getInstance().logEvent(new Event(name + " removed from kitchen inventory."));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("item list", itemListToJson());
        return json;
    }

    // EFFECTS: returns items in this kitchen as a JSON array
    private JSONArray itemListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodItem item : itemList) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}