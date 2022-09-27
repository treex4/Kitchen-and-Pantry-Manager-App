package model;

import model.Kitchen;
import model.FoodItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitchenTest {
    private Kitchen testKitchen;

    @BeforeEach
    public void setUp() {
        testKitchen = new Kitchen();
    }

    @Test
    public void testEmptyKitchenList() {
        assertEquals(testKitchen.getItemCount(), 0);
        assertEquals(testKitchen.getAllItems(), "");
        assertEquals(testKitchen.getFoodItem("apple"), "Error: Item not found.\n");
    }

    @Test
    public void testGetItemNotInList() {
        assertTrue(testKitchen.addFoodItem("apple", "snack", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getFoodItem("pear"), "Error: Item not found.\n");
    }

    @Test
    public void testAddOneItem() {
        assertTrue(testKitchen.addFoodItem("apple", "snack", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: snack\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
    }

    @Test
    public void testAddSameItemTwice() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertFalse(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
    }

    @Test
    public void testAddMultipleDifferentItems() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertTrue(testKitchen.addFoodItem("chips", "snack", "pantry",
                5, "2022-09-06"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\nchips is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("chips"),
                "Name: chips\nType: snack\nLocation: pantry\nQuantity: 5\nExpiry Date: 2022-09-06\n");
        assertTrue(testKitchen.addFoodItem("milk", "dairy", "fridge",
                1, "2022-07-30"));
        assertEquals(testKitchen.getItemCount(), 3);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\nmilk is located in fridge\n");
        assertEquals(testKitchen.getFoodItem("milk"),
                "Name: milk\nType: dairy\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-07-30\n");
    }

    @Test
    public void testRemoveItemFromFront() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertTrue(testKitchen.addFoodItem("chips", "snack", "pantry",
                5, "2022-09-06"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\nchips is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("chips"),
                "Name: chips\nType: snack\nLocation: pantry\nQuantity: 5\nExpiry Date: 2022-09-06\n");
        assertTrue(testKitchen.addFoodItem("milk", "dairy", "fridge",
                1, "2022-07-30"));
        assertEquals(testKitchen.getItemCount(), 3);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\nmilk is located in fridge\n");
        assertEquals(testKitchen.getFoodItem("milk"),
                "Name: milk\nType: dairy\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-07-30\n");
        assertTrue(testKitchen.removeFoodItem("apple"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(),
                "chips is located in pantry\nmilk is located in fridge\n");
    }

    @Test
    public void testRemoveItemFromBack() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertTrue(testKitchen.addFoodItem("chips", "snack", "pantry",
                5, "2022-09-06"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\nchips is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("chips"),
                "Name: chips\nType: snack\nLocation: pantry\nQuantity: 5\nExpiry Date: 2022-09-06\n");
        assertTrue(testKitchen.addFoodItem("milk", "dairy", "fridge",
                1, "2022-07-30"));
        assertEquals(testKitchen.getItemCount(), 3);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\nmilk is located in fridge\n");
        assertEquals(testKitchen.getFoodItem("milk"),
                "Name: milk\nType: dairy\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-07-30\n");
        assertTrue(testKitchen.removeFoodItem("milk"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\n");
    }

    @Test
    public void testRemoveItemFromMiddle() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertTrue(testKitchen.addFoodItem("chips", "snack", "pantry",
                5, "2022-09-06"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\nchips is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("chips"),
                "Name: chips\nType: snack\nLocation: pantry\nQuantity: 5\nExpiry Date: 2022-09-06\n");
        assertTrue(testKitchen.addFoodItem("milk", "dairy", "fridge",
                1, "2022-07-30"));
        assertEquals(testKitchen.getItemCount(), 3);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\nmilk is located in fridge\n");
        assertEquals(testKitchen.getFoodItem("milk"),
                "Name: milk\nType: dairy\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-07-30\n");
        assertTrue(testKitchen.removeFoodItem("chips"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nmilk is located in fridge\n");
    }

    @Test
    public void testRemoveItemNotInList() {
        assertTrue(testKitchen.addFoodItem("apple", "fruit", "pantry",
                3, "2022-08-03"));
        assertEquals(testKitchen.getItemCount(), 1);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("apple"),
                "Name: apple\nType: fruit\nLocation: pantry\nQuantity: 3\nExpiry Date: 2022-08-03\n");
        assertTrue(testKitchen.addFoodItem("chips", "snack", "pantry",
                5, "2022-09-06"));
        assertEquals(testKitchen.getItemCount(), 2);
        assertEquals(testKitchen.getAllItems(), "apple is located in pantry\nchips is located in pantry\n");
        assertEquals(testKitchen.getFoodItem("chips"),
                "Name: chips\nType: snack\nLocation: pantry\nQuantity: 5\nExpiry Date: 2022-09-06\n");
        assertTrue(testKitchen.addFoodItem("milk", "dairy", "fridge",
                1, "2022-07-30"));
        assertEquals(testKitchen.getItemCount(), 3);
        assertEquals(testKitchen.getAllItems(),
                "apple is located in pantry\nchips is located in pantry\nmilk is located in fridge\n");
        assertEquals(testKitchen.getFoodItem("milk"),
                "Name: milk\nType: dairy\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-07-30\n");
        assertFalse(testKitchen.removeFoodItem("banana"));
        assertEquals(testKitchen.getItemCount(), 3);
    }
}