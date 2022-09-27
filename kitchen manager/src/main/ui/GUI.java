package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the GUI for the kitchen application
public class GUI extends JFrame implements ActionListener {
    private static final String KITCHEN_FILE = "./data/kitchen.json";
    private static final String GROCERY_FILE = "./data/grocerylist.json";
    private GroceryList masterGL;
    private Kitchen masterK;
    private JsonWriter jsonWriterK = new JsonWriter(KITCHEN_FILE);
    private JsonReader jsonReaderK = new JsonReader(KITCHEN_FILE);
    private JsonWriter jsonWriterGL = new JsonWriter(GROCERY_FILE);
    private JsonReader jsonReaderGL = new JsonReader(GROCERY_FILE);

    private JPanel mainMenu;
    private JButton kitchen;
    private JButton grocery;
    private JButton quit;

    private JPanel kitchenMenu;
    private JButton list;
    private JButton modify;
    private JButton save;
    private JButton load;
    private JButton back;

    private JPanel addItemPanelK;
    private JButton addToKitchen;
    private JButton removeFromKitchen;
    private JTextField f1;
    private JTextField f2;
    private JTextField f3;
    private JTextField f4;
    private JTextField f5;
    private JLabel name;
    private JLabel type;
    private JLabel location;
    private JLabel quantity;
    private JLabel expiry;

    private JPanel inventoryPanel;

    private JPanel groceryMenu;
    private JButton list1;
    private JButton modify1;
    private JButton save1;
    private JButton load1;
    private JButton back1;

    private JPanel addItemPanelG;
    private JButton addToList;
    private JButton removeFromList;
    private JTextField f6;
    private JLabel itemName;

    private JPanel listPanel;

    // EFFECTS: Makes the gui for the Kitchen app
    public GUI() {
        super("My Kitchen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 550));
        initializeMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu panel and sets the background colour
    public void initializeMenu() {
        EventLog.getInstance().clear();
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.pink);
        add(mainMenu);
        JLabel message = new JLabel("Welcome to your Kitchen!");
        addLabel(message, mainMenu, 35);

        ImageIcon img = new ImageIcon("./data/kitchen.jpg");
        JLabel kitchenImage = new JLabel(img);
        mainMenu.add(kitchenImage);

        initializeMainMenuButtons();
        addButtonToMenu(kitchen, mainMenu);
        addButtonToMenu(grocery, mainMenu);
        addButtonToMenu(quit, mainMenu);
        addActionToButton();
        mainMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes main menu buttons and gives them labels
    private void initializeMainMenuButtons() {
        kitchen = new JButton("View Kitchen");
        grocery = new JButton("View Grocery List");
        quit = new JButton("Quit Application");
    }

    // MODIFIES: this
    // EFFECTS: adds button to main menu
    public void addButtonToMenu(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.pink);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: gives each main menu related button their action
    private void addActionToButton() {
        kitchen.addActionListener(this);
        kitchen.setActionCommand("View Kitchen");
        grocery.addActionListener(this);
        grocery.setActionCommand("View Grocery List");
        quit.addActionListener(this);
        quit.setActionCommand("Quit Application");
    }

    // EFFECTS: performs action of button that is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Kitchen")) {
            initializeKitchenPanel();
        } else if (e.getActionCommand().equals("View Grocery List")) {
            initializeGroceryPanel();
        } else if (e.getActionCommand().equals("Quit Application")) {
            printEventLog();
            System.exit(0);
        } else {
            kitchenActionPerformed(e);
            groceryActionPerformed(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets main menu visibility to true, all others false
    private void returnToMainMenu() {
        mainMenu.setVisible(true);
        if (kitchenMenu != null) {
            kitchenMenu.setVisible(false);
        }
        if (groceryMenu != null) {
            groceryMenu.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the welcome message for the menu panel
    private void addLabel(JLabel message, JPanel panel, int size) {
        message.setFont(new Font("Arial", Font.BOLD, size));
        panel.add(message);
    }

    // MODIFIES: this
    // EFFECTS: creates the kitchen menu panel and sets the background colour
    private void initializeKitchenPanel() {
        mainMenu.setVisible(false);
        kitchenMenu = new JPanel();
        kitchenMenu.setBackground(Color.pink);
        add(kitchenMenu);
        JLabel header = new JLabel("Now Viewing: Kitchen");
        addLabel(header, kitchenMenu, 30);
        JLabel message = new JLabel("Please load your kitchen first then choose from the following options:");
        addLabel(message, kitchenMenu, 15);
        ImageIcon img = new ImageIcon("./data/pantry.png");
        JLabel pantryImage = new JLabel(img);
        kitchenMenu.add(pantryImage);
        initializeKitchenMenuButtons();
        addButtonToMenu(list, kitchenMenu);
        addButtonToMenu(modify, kitchenMenu);
        addButtonToMenu(save, kitchenMenu);
        addButtonToMenu(load, kitchenMenu);
        addButtonToMenu(back, kitchenMenu);
        addActionToKitchenButton();
        kitchenMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes kitchen menu buttons and gives them labels
    private void initializeKitchenMenuButtons() {
        list = new JButton("View Kitchen Inventory");
        modify = new JButton("Modify List");
        save = new JButton("Save Kitchen");
        load = new JButton("Load Existing Kitchen");
        back = new JButton("Back to Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: gives each kitchen menu button their action
    private void addActionToKitchenButton() {
        list.addActionListener(this);
        list.setActionCommand("View Kitchen Inventory");
        modify.addActionListener(this);
        modify.setActionCommand("Modify List");
        save.addActionListener(this);
        save.setActionCommand("Save List");
        load.addActionListener(this);
        load.setActionCommand("Load Existing Kitchen");
        back.addActionListener(this);
        back.setActionCommand("Back to Main Menu");
    }

    // EFFECTS: performs action of button that is clicked
    private void kitchenActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Kitchen Inventory")) {
            initializeKitchenList();
        } else if (e.getActionCommand().equals("Modify List")) {
            initializeModifyPanel();
        } else if (e.getActionCommand().equals("Save List")) {
            saveKitchen();
        } else if (e.getActionCommand().equals("Load Existing Kitchen")) {
            loadKitchen();
        } else if (e.getActionCommand().equals("Return to Kitchen")) {
            returnToKitchen1();
        } else if (e.getActionCommand().equals("Back to Kitchen")) {
            returnToKitchen();
        } else if (e.getActionCommand().equals("Back to Main Menu")) {
            returnToMainMenu();
        } else if (e.getActionCommand().equals("Add Item to Kitchen")) {
            addItemToKitchen();
        } else if (e.getActionCommand().equals("Remove Item from Kitchen")) {
            removeItemFromKitchen();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets visibility of kitchen to true, others false
    private void returnToKitchen() {
        addItemPanelK.setVisible(false);
        kitchenMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets visibility of kitchen to true, others false
    private void returnToKitchen1() {
        inventoryPanel.setVisible(false);
        kitchenMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the panel that displays the kitchen inventory
    private void initializeKitchenList() {
        inventoryPanel = new JPanel();
        BoxLayout boxLayOut = new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS);
        inventoryPanel.setLayout(boxLayOut);
        inventoryPanel.setBackground(Color.pink);
        JButton exit = new JButton("Back to Kitchen");
        addButtonToMenu(exit, inventoryPanel);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setActionCommand("Return to Kitchen");
        exit.addActionListener(this);
        JLabel header = new JLabel("Items in your Kitchen:");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventoryPanel.add(header);
        createInventoryList();
        add(inventoryPanel);
        kitchenMenu.setVisible(false);
        mainMenu.setVisible(false);
        inventoryPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds inventory list onto inventory panel
    private void createInventoryList() {
        for (FoodItem i : masterK.getItemList()) {
            JLabel food = new JLabel(i.getName() + " is located in " + i.getLocation());
            food.setFont(new Font("Arial", Font.BOLD, 15));
            food.setAlignmentX(Component.CENTER_ALIGNMENT);
            inventoryPanel.add(food);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the panel that displays fields for user to input food item
    private void initializeModifyPanel() {
        addItemPanelK = new JPanel(new GridLayout(0, 2));
        JLabel header = new JLabel("For removing, only input item name.\n For adding, input all fields");
        addLabel(header, addItemPanelK, 10);
        JButton exit = new JButton("Back to Kitchen");
        exit.setActionCommand("Back to Kitchen");
        exit.addActionListener(this);
        addButtonToMenu(exit, addItemPanelK);
        addItemPanelK.setBackground(Color.pink);
        add(addItemPanelK);
        createAddItemPage();
        kitchenMenu.setVisible(false);
        mainMenu.setVisible(false);
        addItemPanelK.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: generates the fields for the user to input information
    private void createAddItemPage() {
        addToKitchen = new JButton("Add Item to Kitchen");
        addToKitchen.setActionCommand("Add Item to Kitchen");
        addToKitchen.addActionListener(this);
        removeFromKitchen = new JButton("Remove Item from Kitchen");
        removeFromKitchen.setActionCommand("Remove Item from Kitchen");
        removeFromKitchen.addActionListener(this);

        name = new JLabel("Name of Item:");
        f1 = new JTextField(10);
        type = new JLabel("Item Type:");
        f2 = new JTextField(10);
        location = new JLabel("Location:");
        f3 = new JTextField(10);
        quantity = new JLabel("Quantity:");
        f4 = new JTextField(10);
        expiry = new JLabel("Expiry Date (YYYY-MM-DD):");
        f5 = new JTextField(10);

        addLabelsToAddItemPage();
    }

    // MODIFIES: this
    // EFFECTS: adds user input labels onto the add item panel
    private void addLabelsToAddItemPage() {
        addItemPanelK.add(addToKitchen);
        addItemPanelK.add(removeFromKitchen);

        addItemPanelK.add(name);
        addItemPanelK.add(f1);
        addItemPanelK.add(type);
        addItemPanelK.add(f2);
        addItemPanelK.add(location);
        addItemPanelK.add(f3);
        addItemPanelK.add(quantity);
        addItemPanelK.add(f4);
        addItemPanelK.add(expiry);
        addItemPanelK.add(f5);

        modifyLabelsForAddItemPage();
    }

    // MODIFIES: this
    // EFFECTS: changes label and text field attributes for add item page
    private void modifyLabelsForAddItemPage() {
        addItemPanelK.setFont(new Font("Arial", Font.BOLD, 12));

        name.setFont(new Font("Arial", Font.BOLD, 24));
        type.setFont(new Font("Arial", Font.BOLD, 24));
        location.setFont(new Font("Arial", Font.BOLD, 24));
        quantity.setFont(new Font("Arial", Font.BOLD, 24));
        expiry.setFont(new Font("Arial", Font.BOLD, 24));

        f1.setMaximumSize(new Dimension(500, 400));
        f2.setMaximumSize(new Dimension(500, 400));
        f3.setMaximumSize(new Dimension(500, 400));
        f4.setMaximumSize(new Dimension(500, 400));
        f5.setMaximumSize(new Dimension(500, 400));
    }

    // MODIFIES: this
    // EFFECTS: adds inputted item to kitchen inventory
    private void addItemToKitchen() {
        try {
            masterK.addFoodItem(f1.getText(), f2.getText(), f3.getText(),
                    Integer.parseInt(f4.getText()), f5.getText());
            System.out.println("Successfully Added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please retry.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes inputted item from kitchen inventory
    private void removeItemFromKitchen() {
        masterK.removeFoodItem(f1.getText());
        System.out.println("Successfully Removed!");
    }

    // MODIFIES: this
    // EFFECTS: saves kitchen to file
    private void saveKitchen() {
        try {
            jsonWriterK.open();
            jsonWriterK.write(masterK);
            jsonWriterK.close();
            System.out.println("Saved your kitchen to " + KITCHEN_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + KITCHEN_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads kitchen from file
    private void loadKitchen() {
        try {
            masterK = jsonReaderK.readKitchen();
            System.out.println("Loaded your kitchen from " + KITCHEN_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + KITCHEN_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the grocery menu panel and sets the background colour
    private void initializeGroceryPanel() {
        mainMenu.setVisible(false);
        groceryMenu = new JPanel();
        groceryMenu.setBackground(Color.pink);
        add(groceryMenu);
        JLabel header = new JLabel("Now Viewing: Grocery List");
        addLabel(header, groceryMenu, 30);
        JLabel message = new JLabel("Please load your grocery list first then choose from the following options:");
        addLabel(message, groceryMenu, 15);
        ImageIcon img = new ImageIcon("./data/list.png");
        JLabel listImage = new JLabel(img);
        groceryMenu.add(listImage);
        initializeGroceryMenuButtons();
        addButtonToMenu(list1, groceryMenu);
        addButtonToMenu(modify1, groceryMenu);
        addButtonToMenu(save1, groceryMenu);
        addButtonToMenu(load1, groceryMenu);
        addButtonToMenu(back1, groceryMenu);
        addActionToGroceryButton();
        groceryMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes grocery menu buttons and gives them labels
    private void initializeGroceryMenuButtons() {
        list1 = new JButton("View Grocery List");
        modify1 = new JButton("Modify Grocery List");
        save1 = new JButton("Save Grocery List");
        load1 = new JButton("Load Existing List");
        back1 = new JButton("Back to Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: gives each grocery menu button their action
    private void addActionToGroceryButton() {
        list1.addActionListener(this);
        list1.setActionCommand("View List");
        modify1.addActionListener(this);
        modify1.setActionCommand("Modify Grocery List");
        save1.addActionListener(this);
        save1.setActionCommand("Save Grocery List");
        load1.addActionListener(this);
        load1.setActionCommand("Load Existing List");
        back1.addActionListener(this);
        back1.setActionCommand("Back to Main Menu");
    }

    // EFFECTS: performs action of button that is clicked
    private void groceryActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View List")) {
            initializeGroceryList();
        } else if (e.getActionCommand().equals("Modify Grocery List")) {
            initializeModifyListPanel();
        } else if (e.getActionCommand().equals("Save Grocery List")) {
            saveGroceryList();
        } else if (e.getActionCommand().equals("Load Existing List")) {
            loadGroceryList();
        } else if (e.getActionCommand().equals("Back to Main Menu")) {
            returnToMainMenu();
        } else if (e.getActionCommand().equals("Add Item to List")) {
            addItemToList();
        } else if (e.getActionCommand().equals("Remove Item from List")) {
            removeItemFromList();
        } else if (e.getActionCommand().equals("Back to Grocery Menu")) {
            returnToGrocery();
        } else if (e.getActionCommand().equals("Return to Grocery Menu")) {
            returnToGrocery1();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds inputted item to grocery list
    private void addItemToList() {
        try {
            masterGL.addItem(f6.getText());
            System.out.println("Successfully Added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please retry.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes inputted item from grocery list
    private void removeItemFromList() {
        masterGL.removeItem(f6.getText());
        System.out.println("Successfully Removed!");
    }

    // MODIFIES: this
    // EFFECTS: sets visibility of grocery menu to true, others false
    private void returnToGrocery() {
        addItemPanelG.setVisible(false);
        groceryMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets visibility of grocery menu to true, others false
    private void returnToGrocery1() {
        listPanel.setVisible(false);
        groceryMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves grocery list to file
    private void saveGroceryList() {
        try {
            jsonWriterGL.open();
            jsonWriterGL.write(masterGL);
            jsonWriterGL.close();
            System.out.println("Saved your grocery list to " + GROCERY_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + GROCERY_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads grocery list from file
    private void loadGroceryList() {
        try {
            masterGL = jsonReaderGL.readGroceryList();
            System.out.println("Loaded your grocery list from " + GROCERY_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + GROCERY_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the panel that displays fields for user to input grocery item
    private void initializeModifyListPanel() {
        addItemPanelG = new JPanel(new GridLayout(0, 2));
        JLabel header = new JLabel("Add and Remove Items after inputting the name of the item.");
        addLabel(header, addItemPanelG, 10);
        JButton exit = new JButton("Back to Grocery Menu");
        exit.setActionCommand("Back to Grocery Menu");
        exit.addActionListener(this);
        addButtonToMenu(exit, addItemPanelG);
        addItemPanelG.setBackground(Color.pink);
        add(addItemPanelG);
        createAddToListPage();
        groceryMenu.setVisible(false);
        mainMenu.setVisible(false);
        addItemPanelG.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: generates the fields for the user to input information
    private void createAddToListPage() {
        addToList = new JButton("Add Item to List");
        addToList.setActionCommand("Add Item to List");
        addToList.addActionListener(this);
        removeFromList = new JButton("Remove Item from List");
        removeFromList.setActionCommand("Remove Item from List");
        removeFromList.addActionListener(this);

        itemName = new JLabel("Name of Item:");
        f6 = new JTextField(10);

        addLabelsToAddToListPage();
    }

    // MODIFIES: this
    // EFFECTS: adds user input labels onto the add item to list panel
    private void addLabelsToAddToListPage() {
        addItemPanelG.add(addToList);
        addItemPanelG.add(removeFromList);

        addItemPanelG.add(itemName);
        addItemPanelG.add(f6);

        modifyLabelsForAddToListPage();
    }

    // MODIFIES: this
    // EFFECTS: changes label and text field attributes for add item to list page
    private void modifyLabelsForAddToListPage() {
        addItemPanelG.setFont(new Font("Arial", Font.BOLD, 12));
        itemName.setFont(new Font("Arial", Font.BOLD, 24));
        f6.setMaximumSize(new Dimension(500, 400));
    }

    // MODIFIES: this
    // EFFECTS: creates the panel that displays the grocery list
    private void initializeGroceryList() {
        listPanel = new JPanel();
        BoxLayout boxLayOut = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        listPanel.setLayout(boxLayOut);
        listPanel.setBackground(Color.pink);
        JButton exit = new JButton("Back to Grocery Menu");
        addButtonToMenu(exit, listPanel);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setActionCommand("Return to Grocery Menu");
        exit.addActionListener(this);
        JLabel header = new JLabel("Items in your Grocery List:");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPanel.add(header);
        createGroceryList();
        add(listPanel);
        groceryMenu.setVisible(false);
        mainMenu.setVisible(false);
        addItemPanelG.setVisible(false);
        listPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds grocery list onto grocery list panel
    private void createGroceryList() {
        for (ItemToBuy i : masterGL.getItemList()) {
            JLabel food = new JLabel(i.getName());
            food.setFont(new Font("Arial", Font.BOLD, 15));
            food.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(food);
        }
    }

    private void printEventLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDescription());
            System.out.println(e.getDate());
            System.out.println("\n");
        }
    }
}