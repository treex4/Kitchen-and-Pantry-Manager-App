package model;

import model.FoodItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {
    private FoodItem testItem;

    @BeforeEach
    public void setUp() {
        testItem = new FoodItem("apple", "fruit", "fridge", 3, "2023-08-31");
    }

    @Test
    public void testSetFoodName() {
        assertTrue(testItem.getName().equals("apple"));
        testItem.setFoodName("pear");
        assertEquals(testItem.getName(), "pear");
    }

    @Test
    public void testSetFoodType() {
        assertTrue(testItem.getFoodType().equals("fruit"));
        testItem.setFoodType("snack");
        assertEquals(testItem.getFoodType(), "snack");
    }

    @Test
    public void testSetLocation() {
        assertTrue(testItem.getLocation().equals("fridge"));
        testItem.setLocation("pantry");
        assertEquals(testItem.getLocation(), "pantry");
    }

    @Test
    public void testSetQuantity() {
        assertTrue(testItem.getQuantity() == 3);
        testItem.setQuantity(2);
        assertEquals(testItem.getQuantity(), 2);
    }

    @Test
    public void testSetExpiryDate() {
        assertTrue(testItem.getExpiryDate().equals("2023-08-31"));
        testItem.setExpiryDate("2022-07-31");
        assertEquals(testItem.getExpiryDate(), "2022-07-31");
    }
}
