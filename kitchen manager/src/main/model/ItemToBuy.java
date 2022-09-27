package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food item to be bought having a name
public class ItemToBuy implements Writable {
    private String name;
    private int quantity;

    // EFFECTS: instantiates a newly created item with name, and initial quantity of 1
    public ItemToBuy(String name) {
        this.name = name;
        this.quantity = 1;
    }

    // EFFECTS: returns name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: returns item quantity
    public int getQuantity() {
        return quantity;
    }

    // MODIFIES: this
    // EFFECTS: changes item name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: changes item quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        return json;
    }
}
