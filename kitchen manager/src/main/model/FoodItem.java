package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food item having a name, type, location, quantity, and expiry date
public class FoodItem implements Writable {
    private String foodName;
    private String foodType;
    private String location;
    private int quantity;
    private String expiryDate;

    public FoodItem(String name, String type, String location, int quantity, String expiryDate) {
        this.foodName = name;
        this.foodType = type;
        this.location = location;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    // EFFECTS: returns name of the item
    public String getName() {
        return foodName;
    }

    // EFFECTS: returns type of the item
    public String getFoodType() {
        return foodType;
    }

    // EFFECTS: returns location of the item
    public String getLocation() {
        return location;
    }

    //EFFECTS: returns item quantity
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns expiration date of the item
    public String getExpiryDate() {
        return expiryDate;
    }

    // MODIFIES: this
    // EFFECTS: changes name of the item
    public void setFoodName(String name) {
        this.foodName = name;
    }

    // MODIFIES: this
    // EFFECTS: changes type of the item
    public void setFoodType(String type) {
        this.foodType = type;
    }

    // MODIFIES: this
    // EFFECTS: changes location of the item
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES: this
    // EFFECTS: changes item quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // MODIFIES: this
    // EFFECTS: changes the expiration date of the item
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", foodName);
        json.put("type", foodType);
        json.put("location", location);
        json.put("quantity", quantity);
        json.put("expiry date", expiryDate);
        return json;
    }
}

