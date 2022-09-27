package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads kitchen list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Kitchen readKitchen() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseKitchen(jsonObject);
    }

    // EFFECTS: reads grocery list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GroceryList readGroceryList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGroceryList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses kitchen from JSON object and returns it
    private Kitchen parseKitchen(JSONObject jsonObject) {
        Kitchen k = new Kitchen();
        addInventory(k, jsonObject);
        return k;
    }

    // MODIFIES: k
    // EFFECTS: parses item list from JSON object and adds them to kitchen
    private void addInventory(Kitchen k, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("item list");
        for (Object json : jsonArray) {
            JSONObject nextFoodItem = (JSONObject) json;
            addFoodItem(k, nextFoodItem);
        }
    }

    // MODIFIES: k
    // EFFECTS: parses food item from JSON object and adds it to kitchen
    private void addFoodItem(Kitchen k, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String location = jsonObject.getString("location");
        int quantity = jsonObject.getInt("quantity");
        String expiryDate = jsonObject.getString("expiry date");
        k.addFoodItem(name, type, location, quantity, expiryDate);
    }

    // EFFECTS: parses grocery list from JSON object and returns it
    private GroceryList parseGroceryList(JSONObject jsonObject) {
        GroceryList gl = new GroceryList();
        addGroceries(gl, jsonObject);
        return gl;
    }

    // MODIFIES: gl
    // EFFECTS: parses grocery list from JSON object and adds them to grocery list
    private void addGroceries(GroceryList gl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("grocery list");
        for (Object json : jsonArray) {
            JSONObject nextItemToBuy = (JSONObject) json;
            addItemToBuy(gl, nextItemToBuy);
        }
    }

    // MODIFIES: gl
    // EFFECTS: parses item to buy from JSON object and adds it to grocery list
    private void addItemToBuy(GroceryList gl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        gl.addItem(name);
    }
}
