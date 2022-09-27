package model;

import model.GroceryList;
import model.ItemToBuy;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroceryListTest {
    private GroceryList testGroceryList;

    @BeforeEach
    public void setUp() {
        testGroceryList = new GroceryList();
}

    @Test
    public void testEmptyGroceryList() {
        assertEquals(testGroceryList.getItemCount(), 0);
        assertEquals(testGroceryList.getAllItems(), "");
    }

    @Test
    public void testAddOneItem() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
    }

    @Test
    public void testAddMultipleDifferentItems() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("pear");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
        testGroceryList.addItem("peach");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
    }

    @Test
    public void testAddMultipleSameItem() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\napple\n");
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\napple\napple\n");
    }
    @Test
    public void testRemoveItemFromFrontOfList() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("pear");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
        testGroceryList.addItem("peach");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
        assertTrue(testGroceryList.removeItem("apple"));
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "pear\npeach\n");
    }
    @Test
    public void testRemoveItemFromBackOfList() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("pear");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
        testGroceryList.addItem("peach");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
        assertTrue(testGroceryList.removeItem("peach"));
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
    }

    @Test
    public void testRemoveItemFromMiddleOfList() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("pear");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
        testGroceryList.addItem("peach");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
        assertTrue(testGroceryList.removeItem("pear"));
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npeach\n");
    }

    @Test public void testRemoveItemNotInList() {
        testGroceryList.addItem("apple");
        assertEquals(testGroceryList.getItemCount(), 1);
        assertEquals(testGroceryList.getAllItems(), "apple\n");
        testGroceryList.addItem("pear");
        assertEquals(testGroceryList.getItemCount(), 2);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\n");
        testGroceryList.addItem("peach");
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
        assertFalse(testGroceryList.removeItem("chips"));
        assertEquals(testGroceryList.getItemCount(), 3);
        assertEquals(testGroceryList.getAllItems(), "apple\npear\npeach\n");
    }
}
