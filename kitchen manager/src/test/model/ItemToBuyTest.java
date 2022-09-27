package model;

import model.ItemToBuy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemToBuyTest {
    private ItemToBuy testItem;

    @BeforeEach
    public void setUp() {
        testItem = new ItemToBuy("apples");
    }

    @Test
    public void testSetName() {
        assertTrue(testItem.getName().equals("apples"));
        testItem.setName("pear");
        assertEquals(testItem.getName(), "pear");
    }

    @Test
    public void testSetQuantity() {
        assertEquals(testItem.getQuantity(), 1);
        testItem.setQuantity(5);
        assertEquals(testItem.getQuantity(), 5);
    }
}
