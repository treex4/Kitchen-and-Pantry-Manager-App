package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a grocery list of items to be bought and some commands that can be done to the list
public class GroceryList implements Writable {
    private ArrayList<ItemToBuy> groceryList;

    // EFFECTS: instantiates a newly created groceryList as an empty list
    public GroceryList() {
        groceryList = new ArrayList<ItemToBuy>();
    }

    // EFFECTS: returns number of items in the list
    public int getItemCount() {
        return groceryList.size();
    }

    // EFFECTS: lists all the items
    public String getAllItems() {
        String items = "";
        for (ItemToBuy item : groceryList) {
            items += item.getName() + "\n";
        }
        return items;
    }

    // EFFECTS: returns the grocery list
    public ArrayList<ItemToBuy> getItemList() {
        EventLog.getInstance().logEvent(new Event("Displayed grocery list."));
        return groceryList;
    }

    // MODIFIES: this
    // EFFECTS: adds specified item to grocery list
    public void addItem(String name) {
        ItemToBuy item;
        item = new ItemToBuy(name);
        groceryList.add(item);
        EventLog.getInstance().logEvent(new Event(name + " added to grocery list."));
    }

    // MODIFIES: this
    // EFFECTS: if item is in the list
    //              - remove item from list
    //              - returns true
    //          otherwise, returns false
    public boolean removeItem(String name) {
        for (Iterator<ItemToBuy> it = groceryList.iterator(); it.hasNext(); ) {
            while (it.hasNext()) {
                if (it.next().getName().equals(name)) {
                    it.remove();
                    EventLog.getInstance().logEvent(new Event(name + " removed from grocery list."));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("grocery list", groceryListToJson());
        return json;
    }

    // EFFECTS: returns items in this grocery list as a JSON array
    private JSONArray groceryListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ItemToBuy item : groceryList) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}
