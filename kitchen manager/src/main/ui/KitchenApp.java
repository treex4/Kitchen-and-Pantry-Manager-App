package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Kitchen application inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class KitchenApp {
    private static final String JSON_STORE_K = "./data/kitchen.json";
    private static final String JSON_STORE_GL = "./data/grocerylist.json";
    private GroceryList grocery;
    private Kitchen kitchen;
    private Scanner input;
    private JsonWriter jsonWriterK;
    private JsonReader jsonReaderK;
    private JsonWriter jsonWriterGL;
    private JsonReader jsonReaderGL;

    // EFFECTS: runs the application
    public KitchenApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriterK = new JsonWriter(JSON_STORE_K);
        jsonReaderK = new JsonReader(JSON_STORE_K);
        jsonWriterGL = new JsonWriter(JSON_STORE_GL);
        jsonReaderGL = new JsonReader(JSON_STORE_GL);

        runKitchen();
    }

    //MODIFIES: this
    // EFFECTS: runs the teller application
    private void runKitchen() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.println("\nHave a good day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command in main menu
    private void processMainCommand(String command) {
        if (command.equals("g")) {
            displayGroceryMenu();
            String nextCommand = input.next();
            nextCommand = nextCommand.toLowerCase();
            processGroceryCommand(nextCommand);
        } else if (command.equals("k")) {
            displayKitchenMenu();
            String nextCommand = input.next();
            nextCommand = nextCommand.toLowerCase();
            processKitchenCommand(nextCommand);
        } else {
            System.out.println("Error: Selection not valid. Try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in grocery menu
    private void processGroceryCommand(String command) {
        if (command.equals("list")) {
            viewGroceryList();
        } else if (command.equals("add")) {
            addGroceryItem();
        } else if (command.equals("remove")) {
            removeGroceryItem();
        } else if (command.equals("save")) {
            saveGroceryList();
        } else if (command.equals("load")) {
            loadGroceryList();
        } else if (command.equals("back")) {
            displayMainMenu();
            processMainCommand(command);
        } else {
            System.out.println("Error: Selection not valid. Try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in kitchen menu
    private void processKitchenCommand(String command) {
        if (command.equals("list")) {
            viewKitchenItems();
        } else if (command.equals("add")) {
            addKitchenItem();
        } else if (command.equals("remove")) {
            removeKitchenItem();
        } else if (command.equals("find")) {
            findKitchenItem();
        } else if (command.equals("save")) {
            saveKitchen();
        } else if (command.equals("load")) {
            loadKitchen();
        } else if (command.equals("back")) {
            displayMainMenu();
            processMainCommand(command);
        } else {
            System.out.println("Error: Selection not valid. Try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes kitchen list and grocery list
    private void init() {
        grocery = new GroceryList();
        kitchen = new Kitchen();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nWelcome! Please choose from the following options:");
        System.out.println("\tg -> My Grocery List");
        System.out.println("\tk -> My Kitchen");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays grocery menu of options to user
    private void displayGroceryMenu() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("\tlist -> Get All Items");
        System.out.println("\tadd -> Add Item");
        System.out.println("\tremove -> Remove Item");
        System.out.println("\tsave -> Save Grocery List to File");
        System.out.println("\tload -> Load Grocery List from File");
        System.out.println("\tback -> To Main Menu");
    }

    // EFFECTS: displays kitchen menu of options to user
    private void displayKitchenMenu() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("\tlist -> Get All Items");
        System.out.println("\tadd -> Add Item");
        System.out.println("\tremove -> Remove Item");
        System.out.println("\tfind -> Search for an Item");
        System.out.println("\tsave -> Save Kitchen to File");
        System.out.println("\tload -> Load Kitchen from File");
        System.out.println("\tback -> To Main Menu");
    }

    // EFFECTS: conducts viewing the grocery list
    private void viewGroceryList() {
        System.out.printf(this.grocery.getAllItems());
    }

    // MODIFIES: this
    // EFFECTS: creates a new item by asking for user inputs
    //          then adds it to the grocery list
    private void addGroceryItem() {
        String name;
        int quantity;

        System.out.println("What do you wish to add?\nItem Name:");
        name = input.next();
        name = name.toLowerCase();

        this.grocery.addItem(name);
    }

    // MODIFIES: this
    // EFFECTS: conducts removing an item from the grocery list
    private void removeGroceryItem() {
        String removeName;

        System.out.println("What do you wish to remove?\nItem Name:");
        removeName = input.next();
        removeName = removeName.toLowerCase();

        this.grocery.removeItem(removeName);
    }

    // EFFECTS: conducts viewing the kitchen list
    private void viewKitchenItems() {
        System.out.printf(this.kitchen.getAllItems());
    }

    // MODIFIES: this
    // EFFECTS: conducts adding an item to the kitchen
    private void addKitchenItem() {
        String itemName;
        String type;
        String location;
        int quantity;
        String expiryDate;

        System.out.println("Fill in the following details:\n Item Name:");
        itemName = input.next();
        itemName = itemName.toLowerCase();
        System.out.println("Item type:");
        type = input.next();
        type.toLowerCase();
        System.out.println("Location To Be Stored:");
        location = input.next();
        location = location.toLowerCase();
        System.out.println("Quantity:");
        quantity = input.nextInt();
        System.out.println("Expiry Date (yyyy-mm-dd):");
        expiryDate = input.next();

        kitchen.addFoodItem(itemName, type, location, quantity, expiryDate);
    }

    // MODIFIES: this
    // EFFECTS: conducts removing an item from the kitchen
    private void removeKitchenItem() {
        String itemToRemove;

        System.out.println("What do you wish to remove?\nItem Name:");
        itemToRemove = input.next();
        itemToRemove = itemToRemove.toLowerCase();

        kitchen.removeFoodItem(itemToRemove);
    }

    // EFFECTS: conducts finding an item in the grocery list
    private void findKitchenItem() {
        String findItem;

        System.out.println("What do you wish to find?\nItem Name:");
        findItem = input.next();
        // findItem = findItem.toLowerCase();

        System.out.printf(kitchen.getFoodItem(findItem));
    }

    // EFFECTS: saves the kitchen to file
    private void saveKitchen() {
        try {
            jsonWriterK.open();
            jsonWriterK.write(kitchen);
            jsonWriterK.close();
            System.out.println("Saved your kitchen to " + JSON_STORE_K);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_K);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads kitchen from file
    private void loadKitchen() {
        try {
            kitchen = jsonReaderK.readKitchen();
            System.out.println("Loaded your kitchen from " + JSON_STORE_K);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_K);
        }
    }

    // EFFECTS: saves the grocery list to file
    private void saveGroceryList() {
        try {
            jsonWriterGL.open();
            jsonWriterGL.write(grocery);
            jsonWriterGL.close();
            System.out.println("Saved your grocery list to " + JSON_STORE_GL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_GL);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads grocery list from file
    private void loadGroceryList() {
        try {
            grocery = jsonReaderGL.readGroceryList();
            System.out.println("Loaded your grocery list from " + JSON_STORE_GL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_GL);
        }
    }
}